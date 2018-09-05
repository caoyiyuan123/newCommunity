package com.community.controller.IndexController;

import com.community.entity.Post;
import com.community.mapper.PostMapper;
import com.community.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author: jack
 * @Create: 2018-08-13-10:25
 * @Desc: 查看帖子的内容和评论
 **/
@Controller
@RequestMapping("/loginSuccess")
public class ContentController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private PostMapper postMapper;

    /**跳转到正文页面*/
    @RequestMapping("/content_check")
    public String content(@RequestParam("title") String title, HttpServletRequest request) throws UnsupportedEncodingException {

        Post post = postMapper.queryPost(title);
        String tle = post.getTitle();
        String content = post.getContent();
        Integer likes = post.getClickNum();
        Integer comments = post.getComments();

       request.setAttribute("title",tle);
       request.setAttribute("content",content);
       request.setAttribute("clickNum",likes);
       request.setAttribute("Comments",comments);

        return "MainPage/ContentPage";
    }

    /**实现点赞功能*/
    @RequestMapping(value = "/saveLikesNums",method = RequestMethod.POST)
    @ResponseBody
    public String saveLikesNum(@RequestBody String data,HttpServletRequest request) throws UnsupportedEncodingException {

        JSONObject object = JSONObject.fromObject(data);
        String title = object.get("title").toString();
        Integer num = (Integer) object.get("nums");
        Post post = new Post();
        post.setClickNum(num);
        post.setTitle(title);
        postMapper.updateLikesNums(post);
        return num.toString();
    }

    @RequestMapping(value = "/queryImage",method = RequestMethod.POST)
    @ResponseBody
    public String query(@RequestBody String data) throws IOException {
        JSONObject object = JSONObject.fromObject(data);
        String username = object.get("value").toString();
        /**从数据库中查询*/
        String address = userMapper.queryImage(username);
        File file = new File(address);

        /**读取图片*/
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",out);
        /**将当前输出流转为字节数组*/
        byte[] array = out.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        /**对字节数组进行编码*/
        String imageString = encoder.encodeBuffer(array).trim();
        imageString.replaceAll("\n","").replaceAll("\r","");
        return imageString;
    }

}

