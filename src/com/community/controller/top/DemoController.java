package com.community.controller.top;

import com.community.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: jack
 * @Create: 2018-09-01-11:32
 * @Desc: 处理头部的请求
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LogManager.getLogger(DemoController.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @RequestMapping(value = "/queryImage",method = RequestMethod.GET)
    public void queryImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("html/text;charset=utf-8");
        String username = request.getParameter("username");
        /**根据用户名查询用户的头像*/
        String imageAddress = userMapper.queryImage(username);
        logger.info(imageAddress);
        File file = new File(imageAddress);
        FileInputStream fis = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) > 0){
            out.write(buffer,0,len);
        }
        fis.close();
        logger.info("我想确保方法没有问题");

    }


}
