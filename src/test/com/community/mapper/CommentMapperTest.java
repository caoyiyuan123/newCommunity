package com.community.mapper;

import com.community.entity.Comments;
import com.community.entity.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CommentMapperTest {



    private static final Logger logger = LogManager.getLogger(CommentMapper.class);

    @Autowired
    private CommentMapper commentMapper;

    @Before
    public void init(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        System.out.println(ac);
        commentMapper = ac.getBean("commentMapper",CommentMapper.class);
        System.out.println(commentMapper);
    }

    @Test
    public void add() throws Exception {
        Comments comments = new Comments();
        String username = "这样真的好吗";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newdate = sdf.format(date);
        logger.info(newdate);
        String content = "我这是随便打的";
        comments.setUsername(username);
        comments.setContent(content);
        comments.setCreatime(newdate);
        commentMapper.add(comments);
        logger.info("添加成功");

    }

    @Test
    public void queryAllById() throws Exception {

            String title = "足球公园";

            List<Comments> lists = commentMapper.queryAllByTitle(title);
            for(Comments record:lists){
                logger.info(record);
            }
    }
}