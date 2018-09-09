package com.community.controller.IndexController;

import com.community.entity.Comments;
import com.community.entity.Post;
import com.community.entity.User;
import com.community.entity.UserArtical;
import com.community.mapper.CommentMapper;
import com.community.mapper.PostMapper;
import com.community.mapper.UserArticalMapper;
import com.community.mapper.UserMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: jack
 * @Create: 2018-08-13-10:25
 * @Desc: 查看帖子的内容和评论
 **/
@Controller
@RequestMapping("/loginSuccess")
public class ContentController {

    private static final Logger logger = LogManager.getLogger(ContentController.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private PostMapper postMapper;

    @Autowired(required = false)
    private UserArticalMapper userArticalMapper;

    /**跳转到正文页面*/
    @RequestMapping("/content_check")
    public String content(@RequestParam("title") String title, HttpServletRequest request,
                          HttpSession session) throws UnsupportedEncodingException {

        /**将文章标题保存到Session中*/
        session.setAttribute("title",title);
        logger.info(session.getAttribute("title"));

        /**显示文章内容*/
        Post post = postMapper.queryPost(title);
        String tle = post.getTitle();
        String content = post.getContent();
        Integer likes = post.getClickNum();
        Integer comments = post.getComments();

       request.setAttribute("title",tle);
       request.setAttribute("content",content);
       request.setAttribute("clickNum",likes);
       request.setAttribute("Comments",comments);
        /**根据文章的标题查询出id*/
        Integer p_id = post.getId();
        logger.info(p_id);

        /**根据用户id和文章的id来关联用户和文章表(即哪个用户对哪篇文章进行了点赞)*/
        String username = session.getAttribute("username").toString();
        logger.info(username);

        /**根据用户名来查询用户的id*/
        User user = userMapper.findByName(username);
        Integer u_id = user.getId();
        logger.info(u_id);

        /**将文章的id和用户的id存入数据库?注意只能存一次
         * 因此在纯属之前先查询数据库中是否已经有了*/

        UserArtical userArtical = new UserArtical();
        userArtical.setuId(u_id);
        userArtical.setaId(p_id);

        /**判断某用户是否已经浏览过某文章*/
        String flag = userArticalMapper.check(userArtical);
        if(flag == null){
            logger.info("你是第一次浏览该文章");
            userArticalMapper.add(userArtical);
        }


       /**显示评论内容*/
        List<Comments> lists = commentMapper.queryAllByTitle(title);
        for(Comments record:lists){
            logger.info(record);
        }
        request.setAttribute("list",lists);

        return "MainPage/ContentPage";
    }


    /**显示该篇文章的所有评论*/
    @RequestMapping(value = "/sendContent" , method = RequestMethod.POST)
    public String sendContent( HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        logger.info(session.getAttribute("username"));
        String content = request.getParameter("content");

        /**获取文章的标题*/
        String title = session.getAttribute("title").toString();

        /**用户发表评论的时间*/
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /**获取用户名、时间和内容*/
        String username = session.getAttribute("username").toString();
        String newDate = sdf.format(date);


        /**根据标题查询出该文章的id*/
        Post post = postMapper.queryPost(title);
        String contents = post.getContent();
        Integer likes = post.getClickNum();
        Integer comments = post.getComments();
        request.setAttribute("content",contents);
        request.setAttribute("clickNum",likes);
        request.setAttribute("Comments",comments);

        logger.info(post.getId());

        /**从数据库中查询*/
        Comments record = new Comments();
        record.setUsername(username);
        record.setContent(content);
        record.setCreatime(newDate);
        record.setP_id(post.getId());
        commentMapper.add(record);


        /**查询所有的数据，发送给前端页面*/
        List<Comments> lists = commentMapper.queryAllByTitle(title);
        for(Comments list:lists){
            logger.info(list);
        }
        request.setAttribute("list",lists);

        return "MainPage/ContentPage";
    }
    /**实现点赞功能*/
    @RequestMapping(value = "/saveLikesNums",method = RequestMethod.POST)
    @ResponseBody
    public String saveLikesNum(@RequestBody String data,HttpServletRequest request,
                               HttpSession session) throws UnsupportedEncodingException {

        JSONObject object = JSONObject.fromObject(data);
        /**获取文章的标题*/
        String title = object.get("title").toString();
        /**根据文章标题来查询文章的id*/
        Post post = postMapper.queryPost(title);
        Integer a_id = post.getId();
        logger.info(a_id);
        logger.info(title);
        /**获取点赞用户的用户名*/
        String username = session.getAttribute("username").toString();
        logger.info(username);
        /**根据用户名来查询用户的id*/
        User user = userMapper.findByName(username);
        Integer u_id = user.getId();
        logger.info(u_id);

        /**根据用户的id和文章的id来查询用户对文章的操作情况*/
        UserArtical userArtical = new UserArtical();
        userArtical.setuId(u_id);
        userArtical.setaId(a_id);
        int num = userArticalMapper.queryStatus(userArtical);
        logger.info(num);
        /**如果num为0，表示未点赞，则将status值改为1*/
        if(num == 0){
            /**得到该文章的点赞数，并+1*/
            Post record = postMapper.queryPost(title);
            logger.info(record);
            Integer result = record.getClickNum();
            result += 1;
            Post post1 = new Post();
            post1.setTitle(title);
            post1.setClickNum(result);
            postMapper.updateLikesNums(post1);


            /**将status的值修改为1*/
            userArticalMapper.modifyStatus(userArtical);
            return result.toString();

        }else{
            Post record = postMapper.queryPost(title);
            logger.info(record);
            Integer result = record.getClickNum();
            result -= 1;
            Post post1 = new Post();
            post1.setTitle(title);
            post1.setClickNum(result);
            postMapper.updateLikesNums(post1);

            /**将status的值修改为0*/
            userArticalMapper.newModifyStatus(userArtical);
            return result.toString();
        }

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

