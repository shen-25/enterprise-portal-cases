package com.zs.servlet;

import java.util.Calendar;

import com.alibaba.fastjson.JSON;


public class FastJsonSample1 {
	
	public static void main(String[] args) {
		Employee employee = new Employee();
	
		employee.setEmpno(4488);
		employee.setEname("刘晓东");
		employee.setSalary(10000f);
		employee.setJob("客户经理");
		employee.setDname("市场部");
		Calendar c = Calendar.getInstance();
		c.set(2019, 0, 30, 0, 0, 0);	
		employee.setHdate(c.getTime());
	    String json = JSON.toJSONString(employee);
	    System.out.println(json);
	    System.out.println(JSON.parseObject(json, Employee.class).getEmpno());
	}

}
