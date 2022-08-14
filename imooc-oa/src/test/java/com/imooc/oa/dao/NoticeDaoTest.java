package com.imooc.oa.dao;

import ch.qos.logback.core.db.dialect.HSQLDBDialect;
import com.imooc.oa.entity.Notice;
import com.imooc.oa.utils.MybatisUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
public class NoticeDaoTest {
    @Test
    public void testInsert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            Notice notice = new Notice();
            notice.setContent("我在测试数据");
            notice.setCreateTime(new Date());
            notice.setReceiverId(2l);
            noticeDao.insert(notice);
            return null;
        });
    }

}