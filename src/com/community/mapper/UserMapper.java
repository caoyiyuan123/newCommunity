package com.community.mapper;

import com.community.entity.User;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: jack
 * @Create: 2018-08-16-15:04
 * @Desc:
 **/
@Repository("userMapper")
public interface UserMapper {
    int add(User user);
    User findByName(String username);
    List<User> findAll();
    /**查询头像*/
    String queryImage(String username);
    /**更改头像*/
    void updateImage(User user);
}
