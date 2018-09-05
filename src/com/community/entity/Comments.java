package com.community.entity;

import java.util.Date;

/**
 * @Author: jack
 * @Create: 2018-09-05-14:46
 * @Desc: 对文章的评论
 **/
public class Comments {
    private Integer id;
    private String username; //评论的用户名
    private String content; //评论的内容
    private Integer like;  //点赞数量
    private Integer answer; //回复该评论的数量
    private String creatime; //发表评论的时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", like=" + like +
                ", answer=" + answer +
                ", creatime=" + creatime +
                '}';
    }
}
