package com.community.mapper;

import com.community.entity.UserArtical;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.Assert.*;

public class UserArticalMapperTest {


    private static final Logger logger = LogManager.getLogger(UserArticalMapperTest.class);

    @Autowired
    private UserArticalMapper userArticalMapper;

    @Before
    public void init(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        System.out.println(ac);
        userArticalMapper = ac.getBean("userArticalMapper",UserArticalMapper.class);
       logger.info(userArticalMapper);
    }

    @Test
    public void queryStatus() throws Exception {
        Integer u_id = 20;
        Integer a_id = 2;
        UserArtical userArtical = new UserArtical();
        userArtical.setuId(u_id);
        userArtical.setaId(a_id);
        Integer num = userArticalMapper.queryStatus(userArtical);
        logger.info(num.toString());
    }


    @Test
    public void add() throws Exception {
        UserArtical userArtical = new UserArtical();
        userArtical.setuId(20);
        userArtical.setaId(8);
        userArticalMapper.add(userArtical);
    }

    @Test
    public void check() throws Exception {
        UserArtical userArtical = new UserArtical();
        userArtical.setuId(20);
        userArtical.setaId(3);
        String flag = userArticalMapper.check(userArtical);
        logger.info(flag);
    }

    @Test
    public void modifyStatus() throws Exception {
        UserArtical userArtical = new UserArtical();
        userArtical.setuId(20);
        userArtical.setaId(1);
        userArticalMapper.modifyStatus(userArtical);
        logger.info("成功");
    }
}