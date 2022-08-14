package com.imooc.oa.dao;

import com.imooc.oa.entity.User;
import com.imooc.oa.utils.MybatisUtils;

public class UserDao {
    /**
     * 根据用户名查询用户表
     * @param username 用户名
     * @return 用户
     */
    public User selectByUsername(String username) {
        User user = (User)MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("usermapper.selectByUsername", username));
        return user;
    }
}
