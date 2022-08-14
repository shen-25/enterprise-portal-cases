package com.imooc.oa.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.UserService;
import com.imooc.oa.service.exception.BussinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/check_login")
public class LoginServlet extends HttpServlet {
     private UserService userService = new UserService();
    Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map map  = new HashMap();
        try {
            User user = userService.checkLogin(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("login_user", user);
            map.put("code", "0");
            map.put("message", "success");
            map.put("redirect_url", "/index");
        } catch (BussinessException e) {
            map.put("code", e.getCode());
            map.put("message", e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            map.put("code", e.getClass().getSimpleName());
            map.put("message", e.getMessage());
            logger.error(e.getMessage(), e);
        }
        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("test");
    }
}
