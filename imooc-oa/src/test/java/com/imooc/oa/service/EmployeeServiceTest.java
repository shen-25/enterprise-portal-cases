package com.imooc.oa.service;

import com.imooc.oa.entity.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    public void selectById() {
       Employee employee =  employeeService.selectById(3l);
        System.out.println(employee);
    }
}