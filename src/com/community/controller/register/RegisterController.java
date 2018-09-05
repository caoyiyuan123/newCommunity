package com.community.controller.register;

import com.community.Utils.VerifyCodeUtils;
import com.community.entity.User;
import com.community.service.UserService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: jack
 * @Create: 2018-08-11-18:28
 * @Desc: 登录页面
 **/
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;



    /**
     * 注册成功
     * @return
     */
    @RequestMapping("/registerSuccess")
    public String success(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        /**将用户名作为盐值*/
        ByteSource salt = ByteSource.Util.bytes(username);
        /**
         * MD5加密:
         * 使用SimpleHash类对原始密码进行加密
         * 第一个参数表示使用MD5方式加密
         * 第二个参数表示为原始密码
         * 第三个参数表示使用用户名作为盐值
         * 第四个参数表示加密的次数
         * 最后用toHex()方法将加密后的密码转为String。
         */
        String newPwd = new SimpleHash("MD5",password,salt,1024).toHex();
        User user = new User();
        user.setUsername(username);
        user.setPassword(newPwd);

        userService.save(user);
        return "homePage/login";
    }

    @RequestMapping("/registerIndex")
    public String register(){
        return "homePage/register";
    }


    /**处理前端传过来的ajax的参数*/
    @RequestMapping(value = "/username_check",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public String authentication(@RequestBody String data){
        System.out.println(data);
        JSONObject dataObj = JSONObject.fromObject(data); //将json字符串转换为json对象
        String username = dataObj.get("username").toString();

        User user = userService.findByName(username);


        Map map = new HashMap();
        if(user == null){
            map.put("value","true");

        }else {
            map.put("value","false");
        }

        String json = JSONObject.fromObject(map).toString();


        return json;
    }

    /**改变验证码*/
    @RequestMapping(value = "/changeImg",method = RequestMethod.GET)
    public void changeImg(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Controller","no-cache");
        response.setDateHeader("Expires",0);
        System.out.println("************");
        response.setContentType("image/jpeg");

        //生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话的session
        HttpSession session = request.getSession(true);
        session.setAttribute("rand",verifyCode.toLowerCase());
        //生成图片
        int w = 100,h=40;
        try {
            VerifyCodeUtils.outputImage(w,h,response.getOutputStream(),verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**比较验证码*/
    @RequestMapping(value = "/compare",method = RequestMethod.POST)
    @ResponseBody
    public String compare(@RequestBody String data, HttpSession  session){
        JSONObject jsonObject = JSONObject.fromObject(data);
        String verifyCode = jsonObject.get("value").toString();
        String rand = session.getAttribute("rand").toString();
        if (rand.equalsIgnoreCase(verifyCode)) {
            return "true"; //输入的值与图片的值相同
        }else{
            return "false";
        }

    }



}
