package com.community.controller.comment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: jack
 * @Create: 2018-09-05-15:24
 * @Desc:  对文章的内容评论
 **/
@Controller
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @RequestMapping(value = "/sendContent" , method = RequestMethod.POST)
    public String sendContent(@RequestParam("content") String content, HttpSession session,
                              HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("Utf-8");
        logger.info(session.getAttribute("username"));
        logger.info(content);
        /**用户发表评论的时间*/
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = sdf.format(date);
        logger.info(newDate);
        return "MainPage/ContentPage";
    }
}
