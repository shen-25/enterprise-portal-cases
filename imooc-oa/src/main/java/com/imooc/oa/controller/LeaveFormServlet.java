package com.imooc.oa.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.oa.entity.LeaveForm;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.LeaveFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "LeaveFormServlet", value = "/leave/*")
public class LeaveFormServlet extends HttpServlet {

    private LeaveFormService leaveFormService = new LeaveFormService();
    private Logger logger = LoggerFactory.getLogger(LeaveFormServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);

        if (methodName.equals("create")) {
            this.create(request, response);
        } else if ("list".equals(methodName)) {
            this.getLeaveFormList(request, response);
        }else if ("audit".equals(methodName)) {
            this.audit(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("login_user");
        String formType = request.getParameter("formType");
        String strStartTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String reason = request.getParameter("reason");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(user.getEmployeeId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        Map result = new HashMap();
        try {
            form.setStartTime(sdf.parse(strStartTime));
            form.setEndTime(sdf.parse(endTime));
            form.setFormType(Integer.parseInt(formType));
            form.setReason(reason);
            form.setCreateTime(new Date());
            leaveFormService.createLeaveForm(form);
            result.put("code", "0");
            result.put("message", "success");

        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("请假申请异常", e);
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e.getMessage());
        }

        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }

    private void getLeaveFormList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("login_user");
       List<Map> mapList = leaveFormService.getLeaveFromList("process", user.getEmployeeId());
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "");
        result.put("count", mapList.size());
        result.put("data", mapList);
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }
    private void audit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formId = request.getParameter("formId");
        String result = request.getParameter("result");
        String reason = request.getParameter("reason");
        User user = (User) request.getSession().getAttribute("login_user");
        Map map = new HashMap();
        try {
            leaveFormService.audit(Long.parseLong(formId), user.getEmployeeId(), result, reason);
            map.put("code", "0");
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("请假单审核失败", e);
            map.put("code", e.getClass().getSimpleName());
            map.put("message", e.getMessage());
        }
        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }
}
