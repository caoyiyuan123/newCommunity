package com.community.controller.IndexController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: jack
 * @Create: 2018-08-15-16:40
 * @Desc: 个人主页
 **/
@Controller
@RequestMapping("/personal")
public class PersonalController {

    /**发帖子的页面*/
    @RequestMapping("/sendMessage")
    public String send(){
        return "PersonalHomepage/Personal";
    }


}
