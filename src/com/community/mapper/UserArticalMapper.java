package com.community.mapper;

import com.community.entity.UserArtical;

/**
 * @Author: jack
 * @Create: 2018-09-07-16:47
 * @Desc:
 **/
public interface UserArticalMapper {

    /**查询某用户对某篇文章的点赞状态*/
    int queryStatus(UserArtical userArtical);

    /**将文章的id和用户的id存入数据库*/
    void add(UserArtical userArtical);

    /**检查数据库中是否已经保存了某用户对某文章的操作*/
    String check(UserArtical userArtical);

    /**修改status的值为1*/
    void modifyStatus(UserArtical userArtical);

    /**修改status的值为0*/
    void newModifyStatus(UserArtical userArtical);

}
