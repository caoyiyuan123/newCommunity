package com.community.entity;

/**
 * @Author: jack
 * @Create: 2018-08-16-15:02
 * @Desc: 用户实体类
 **/
public class User {

    private Integer id; //ID
    private String username;//用户名
    private String password; //密码
    private String imageAddress; //用户头像

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
                '}';
    }
}
