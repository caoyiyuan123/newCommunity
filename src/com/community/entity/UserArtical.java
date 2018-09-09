package com.community.entity;

/**
 * @Author: jack
 * @Create: 2018-09-07-16:43
 * @Desc:  判断哪个用户对那篇文章是否点赞
 **/
public class UserArtical {

    private Integer id;
    private Integer uId;  //用户的id
    private Integer aId;  //文章的id
    private Integer status; //是否点赞

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserArtical{" +
                "id=" + id +
                ", uId=" + uId +
                ", aId=" + aId +
                ", status=" + status +
                '}';
    }
}
