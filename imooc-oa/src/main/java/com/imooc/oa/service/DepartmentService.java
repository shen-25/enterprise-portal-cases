package com.imooc.oa.service;

import com.imooc.oa.dao.DepartmentDao;
import com.imooc.oa.entity.Department;
import com.imooc.oa.utils.MybatisUtils;

public class DepartmentService {
    public Department selectById(Long departmentId) {
        return (Department) MybatisUtils.executeQuery(sqlSession -> {
            DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
            return departmentDao.selectById(departmentId);
        });
    }
}
