package com.community.entity;

import java.util.Date;

/**
 * @Author: jack
 * @Create: 2018-08-31-9:28
 * @Desc: 帖子(包括标题、作者、点击量、回复量、创建时间)
 **/
public class Post {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private Integer clickNum;
    private Integer comments;
    private String creatime;
    private Integer status; //对文章是否点过赞:0：表示没有赞；1表示点过赞

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", clickNum=" + clickNum +
                ", Comments=" + comments +
                ", creatime='" + creatime + '\'' +
                '}';
    }
}
