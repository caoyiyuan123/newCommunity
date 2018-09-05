package com.community.mapper;

import com.community.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.Assert.*;

public class UserMapperTest {



    @Autowired
    private UserMapper userMapper;

    @Before
    public void init(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        System.out.println(ac);
        userMapper = ac.getBean("userMapper",UserMapper.class);
        System.out.println(userMapper);
    }

    @Test
    public void add() throws Exception {


        User record = new User();
        record.setUsername("zhangsan");
        record.setPassword("123456");
        userMapper.add(record);
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findByName() throws Exception {
        User user = userMapper.findByName("caoyiyuan");
        System.out.println(user);
    }

    @Test
    public void queryImage() throws Exception {
        String username="wangwu";
        String address = userMapper.queryImage(username);
        System.out.println(address);
    }

}