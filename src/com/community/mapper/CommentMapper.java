package com.community.mapper;

import com.community.entity.Comments;

/**
 * @Author: jack
 * @Create: 2018-09-05-14:52
 * @Desc:  对文章的评论内容
 **/
public interface CommentMapper {

    /**向数据库添加信息*/
    void add(Comments comments);

}
