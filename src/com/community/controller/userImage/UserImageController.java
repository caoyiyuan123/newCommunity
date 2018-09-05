package com.community.controller.userImage;

import com.community.entity.User;
import com.community.mapper.UserMapper;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @Author: jack
 * @Create: 2018-09-01-14:15
 * @Desc: 修改头像的页面
 **/
@Controller
@RequestMapping("/update")
public class UserImageController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ServletConfig servletConfig;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/ModifyImage")
    public String UpdateUserImage(){
        return "public/updateImg/updateImage";
    }

    /**
     * 文件上传
     * @param request
     * @param response
     * @param session:用来保存用户的相关信息
     * @return
     * @throws ServletException
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException{

        String path = servletContext.getRealPath("\\")+"load";
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        SmartUpload smartUpload = new SmartUpload();
        smartUpload.initialize(servletConfig,request,response);
        try {
            smartUpload.upload();
            smartUpload.save(path);

            /**获取上传文件的名字*/
            String filename = smartUpload.getFiles().getFile(0).getFileName();
            /**获取表单中的username值
             * 将路径写入数据库*/
            String username = session.getAttribute("username").toString();
            User user = new User();
            user.setUsername(username);
            user.setImageAddress(path+"\\"+filename);
            userMapper.updateImage(user);
            System.out.println("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

        return "上传成功";
    }
}
