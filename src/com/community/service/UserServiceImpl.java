package com.community.service;

import com.community.entity.User;
import com.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: jack
 * @Create: 2018-08-16-15:18
 * @Desc: 用户表
 **/
@Service(value="userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;


    @Override
    public int save(User user) {
        return userMapper.add(user);
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

}
