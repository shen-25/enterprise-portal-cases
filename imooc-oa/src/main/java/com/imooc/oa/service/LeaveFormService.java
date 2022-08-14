package com.imooc.oa.service;

import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.dao.LeaveFormDao;
import com.imooc.oa.dao.NoticeDao;
import com.imooc.oa.dao.ProcessFlowDao;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.LeaveForm;
import com.imooc.oa.entity.Notice;
import com.imooc.oa.entity.ProcessFlow;
import com.imooc.oa.service.exception.BussinessException;
import com.imooc.oa.utils.MybatisUtils;

import javax.imageio.ImageReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeaveFormService {
    public LeaveForm createLeaveForm(LeaveForm form) {
        return (LeaveForm) MybatisUtils.executeUpdate(sqlSession -> {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectById(form.getEmployeeId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH时");
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);

            if (employee.getLevel() == 8) {
                form.setState("approved");
            } else {
                form.setState("processing");
            }
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            leaveFormDao.insert(form);
            //2.增加第一条流程数据，说明数据已提交，状态为complete
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
             ProcessFlow flow1 = new ProcessFlow();
            flow1.setFormId(form.getFormId());
            flow1.setOperatorId(employee.getEmployeeId());
            flow1.setAction("apply");
            flow1.setCreateTime(new Date());
            flow1.setOrderNo(1);
            flow1.setState("complete");
            flow1.setIsLast(0);
            processFlowDao.insert(flow1);
            if (employee.getLevel() < 7) {
                Employee dmanager = employeeDao.selectLeader(employee);
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(form.getFormId());
                flow2.setOperatorId(dmanager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setOrderNo(2);
                flow2.setState("process");
                long diff = form.getEndTime().getTime() - form.getStartTime().getTime();
                float hours = diff/(1000*60*60)* 1f;
                System.out.println(hours);
                if(hours >= BussinessConstants.MANAGER_AUDIT_HOURS) {
                    flow2.setIsLast(0);
                    processFlowDao.insert(flow2);
                    Employee manager = employeeDao.selectLeader(dmanager);
                    ProcessFlow flow3 = new ProcessFlow();
                    flow3.setFormId(form.getFormId());
                    flow3.setOperatorId(manager.getDepartmentId());
                    flow3.setCreateTime(new Date());
                    flow3.setAction("audit");
                    flow3.setState("ready");
                    flow3.setIsLast(1);
                    flow3.setOrderNo(3);
                    processFlowDao.insert(flow3);
                } else{
                    flow2.setIsLast(1);
                    processFlowDao.insert(flow2);
                }
                // 请假单已提交消息
                String noticeContent = String.format("您的请假申请[%s-%s]已提交，请等待上级审批。",
                        sdf.format(form.getStartTime()),
                        sdf.format(form.getEndTime()));
                noticeDao.insert(new Notice(employee.getEmployeeId(), noticeContent));
                // 通知部门经理审批消息
                noticeContent = String.format("%s-%s提起请假申请[%s-%s]，请尽快审批。",
                       employee.getTitle(), employee.getName(), sdf.format(form.getStartTime()), sdf.format(form.getEndTime()));
                noticeDao.insert(new Notice(dmanager.getEmployeeId(), noticeContent));
            } else if (employee.getLevel() == 7) {
                Employee manager = employeeDao.selectLeader(employee);
                ProcessFlow flow = new ProcessFlow();
                flow.setFormId(form.getFormId());
                flow.setOperatorId(manager.getEmployeeId());
                flow.setAction("audit");
                flow.setCreateTime(new Date());
                flow.setOrderNo(2);
                flow.setIsLast(1);
                flow.setState("process");
                processFlowDao.insert(flow);
                processFlowDao.insert(flow);
                // 请假单已提交消息
                String noticeContent = String.format("您的请假申请[%s-%s]已提交，请等待上级审批。",
                        sdf.format(form.getStartTime()),
                        sdf.format(form.getEndTime()));
                noticeDao.insert(new Notice(employee.getEmployeeId(), noticeContent));
                // 通知总经理审批消息
                noticeContent = String.format("%s-%s提起请假申请，请尽快审批。",
                        employee.getTitle(), employee.getName());
                noticeDao.insert(new Notice(manager.getEmployeeId(), noticeContent));
            } else if(employee.getLevel() == 8){
                ProcessFlow flow = new ProcessFlow();
                flow.setFormId(form.getFormId());
                flow.setOperatorId(employee.getEmployeeId());
                flow.setAction("audit");
                flow.setCreateTime(new Date());
                flow.setState("approved");
                flow.setReason("自动通过");
                flow.setOrderNo(2);
                flow.setIsLast(1);
                flow.setAuditTime(new Date());
                flow.setResult("approved");
                processFlowDao.insert(flow);
                String noticeContent = String.format("您的请假申请[%s-%s]系统已自动批准通过",
                        employee.getTitle(), employee.getName());
                noticeDao.insert(new Notice(employee.getEmployeeId(), noticeContent));
            }
           return form;
        });
    }

    public List<Map> getLeaveFromList(String pfState, Long operatorId) {
       return  (List<Map>)MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            return leaveFormDao.selectByParams(pfState, operatorId);
        });
    }

    public void audit(Long formId, Long operatorId, String result, String reason) {
        MybatisUtils.executeUpdate(sqlSession -> {
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            List<ProcessFlow> flowList = processFlowDao.selectByFormId(formId);
            if (flowList.size() == 0) {
                throw new BussinessException("PF001", "无效审批流程");
            }
            List<ProcessFlow> processFlowList = flowList.stream().filter(p -> p.getOperatorId() == operatorId && p.getState().equals("process")).collect(Collectors.toList());
            ProcessFlow process = null;
            if (processFlowList.size() == 0) {
                throw new BussinessException("PF002", "没有找到待处理的任务");
            } else {
                process = processFlowList.get(0);
                process.setState("complete");
                process.setResult(result);
                process.setReason(reason);
                process.setAuditTime(new Date());
                processFlowDao.update(process);
            }
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            LeaveForm form = leaveFormDao.selectById(formId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH时");
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectById(form.getEmployeeId()); // 表单提交人信息
            Employee operator = employeeDao.selectById(operatorId); // 经办人信息
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            //如果当前任务是最后一个节点,请假表的状态改为approved/refused
            if (process.getIsLast() == 1) {
                form.setState(result);
                leaveFormDao.update(form);

                String strResult = null;
                if(result.equals("approved")) {
                    strResult = "批准";
                } else if(result.equals("refused")) {
                    strResult = "驳回";
                }
                String noticeContent = String.format("您的请假申请[%s-%s]%s%s已%s，审批意见：%s，审批流程已结束。",
                        sdf.format(form.getStartTime()), sdf.format(form.getEndTime()), operator.getTitle(), operator.getName(), strResult, reason);
                noticeDao.insert(new Notice(form.getEmployeeId(), noticeContent));
                noticeContent = String.format("%s-%s提起请假申请[%s-%s]您已%s，审批意见：%s，审批流程已结束。",
                        employee.getTitle(), employee.getName(), sdf.format(form.getStartTime()), sdf.format(form.getEndTime()), strResult, reason);
                noticeDao.insert(new Notice(operatorId, noticeContent));
            } else {
                List<ProcessFlow> readyList = flowList.stream().filter(p -> p.getState().equals("ready")).collect(Collectors.toList());

                if (result.equals("approved")) {
                    ProcessFlow readyProcess = readyList.get(0);
                    readyProcess.setState("process");
                    processFlowDao.update(readyProcess);

                    // 消息1:通知表单提交人，部门经理已审批通过，交由上级继续审批
                    String noticeContent1 = String.format("您的请假申请[%s-%s]%s%s已批准，审批意见：%s。",
                            sdf.format(form.getStartTime()), sdf.format(form.getEndTime()), operator.getTitle(), operator.getName(), reason);
                    noticeDao.insert(new Notice(form.getEmployeeId(), noticeContent1));
                    // 消息2:通知总经理有新的审批任务
                    String noticeContent2 = String.format("%s-%s提起请假申请[%s-%s]，请尽快审批。",
                            employee.getTitle(), employee.getName(), sdf.format(form.getStartTime()),  sdf.format(form.getEndTime()));
                    noticeDao.insert(new Notice(readyProcess.getOperatorId(), noticeContent2));
                    // 消息3:通知部门经理（当前经办人），员工申请单你已批准，交由上级审批
                    String noticeContent3 = String.format("%s-%s提起请假申请[%s-%s]您已批准，交由上级继续审批。",
                            employee.getTitle(), employee.getName(), sdf.format(form.getStartTime()),  sdf.format(form.getEndTime()));
                    noticeDao.insert(new Notice(operatorId, noticeContent3));

                } else if (result.equals("refused")) {
                    for (ProcessFlow p : readyList) {
                        p.setState("cancel");
                        processFlowDao.update(p);
                    }
                    form.setState("refused");
                    leaveFormDao.update(form);

                    // 消息1:通知申请人表单已被驳回
                    String noticeContent1 = String.format("您的请假申请[%s-%s]%s%s已驳回，审批意见：%s。",
                            sdf.format(form.getStartTime()),  sdf.format(form.getEndTime()), operator.getTitle(), operator.getName(), reason);
                    noticeDao.insert(new Notice(form.getEmployeeId(), noticeContent1));
                    // 消息2:通知经办人"您已驳回"
                    String noticeContent3 = String.format("%s-%s提起请假申请[%s-%s]您已驳回。",
                            employee.getTitle(), employee.getName(), sdf.format(form.getStartTime()), sdf.format(form.getEndTime()));
                    noticeDao.insert(new Notice(operatorId, noticeContent3));
                }
            }
            return null;
        });
    }
}
