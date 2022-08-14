package com.imooc.oa.service;

import com.imooc.oa.entity.Node;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {

    private UserService userService = new UserService();
    @Test
    public void checkLogin() {
        userService.checkLogin("uu", "123");

    }

    @Test
    public void checkLogin1() {
        userService.checkLogin("m8", "123");
    }

    @Test
    public void checkLogin2() {
        userService.checkLogin("m8", "test");
    }

    /**
     * 测试不能加参数不然会报错
     */
    @Test
    public void selectNodeByUserId(){
       List<Node> nodeList = userService.selectNodeByUserId(4l);
        System.out.println(nodeList);
    }
}