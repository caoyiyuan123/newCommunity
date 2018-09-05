package com.community.mapper;

import com.community.Utils.PageUtils;
import com.community.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 对帖子类进行相关的增删查改操作
 */
@Repository("postMapper")
public interface PostMapper {
    void add(Post post);

    /**根据作者来查询文章内容*/
    Post queryPost(String title);

    /**查询所有的记录*/
    List<Post> queryAll();

    /**修改点赞的数量*/
    void updateLikesNums(Post post);

    /**查询所有的记录总数*/
    int queryTotalCounts();

    /**分页查询*/
    List<Post> queryPage(PageUtils page);
}
