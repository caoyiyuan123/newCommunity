package com.community.controller.top;

import com.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * @Author: jack
 * @Create: 2018-09-01-11:32
 * @Desc: 处理头部的请求
 **/
@Controller
@RequestMapping("/top")
public class TopController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @RequestMapping("/queryImage")
    public void queryImg(HttpServletResponse response,
                         HttpSession session) throws IOException {
        response.setContentType("html/text;charset=utf-8");
        String username = session.getAttribute("username").toString();
        /**根据用户名查询用户的头像*/
        String imageAddress = userMapper.queryImage(username);
        File file = new File(imageAddress);
        FileInputStream fis = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) > 0){
            out.write(buffer,0,len);
        }
        fis.close();
        out.close();


    }
}
