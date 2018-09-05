package com.community.service;

import com.community.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Author: jack
 * @Create: 2018-08-16-15:18
 * @Desc:
 **/
@Service(value = "userService")
public interface UserService {

    int save(User user);

    User findByName(String username);

}
