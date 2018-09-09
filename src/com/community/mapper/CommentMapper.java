package com.community.mapper;

import com.community.entity.Comments;
import com.community.entity.Post;

import java.util.List;

/**
 * @Author: jack
 * @Create: 2018-09-05-14:52
 * @Desc:  对文章的评论内容
 **/
public interface CommentMapper {

    /**向数据库添加信息*/
    void add(Comments comments);

    /**根据文章标题来查询所有的评论内容*/
    List<Comments> queryAllByTitle(String title);


}
