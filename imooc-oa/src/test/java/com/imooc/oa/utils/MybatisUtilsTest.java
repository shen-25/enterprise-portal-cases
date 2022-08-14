package com.imooc.oa.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MybatisUtilsTest {
    @Test
    public void testCase1() {
        String result = (String) MybatisUtils.executeQuery(sqlSession -> {
            String out = (String) sqlSession.selectOne("test.sample");
            System.out.println(out);
            return out;
        });
        System.out.println(result);
    }

    @Test
    public void testCase2() {
        String result = (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.sample"));
        System.out.println(result);
    }
}