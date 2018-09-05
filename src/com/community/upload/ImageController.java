package com.community.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: jack
 * @Create: 2018-08-27-23:11
 * @Desc:
 **/
@Controller
public class ImageController {

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/showImg")
    public String image(){
        return "uploadPage/upload";
    }

    /**
     * 服务器向浏览器发送图片流
     * @param response
     * @throws IOException
     */
    @RequestMapping("/image")
    public void demo(HttpServletResponse response) throws IOException {
        /**设置编码格式*/
        response.setContentType("charset=utf-8");
        /**文件所在的位置*/
        File file = new File("F:/temp/1.jpg");
        FileInputStream in = new FileInputStream(file);
        /**创建输出流向网页输入内容*/
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len=in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }
        /**关闭流*/
        in.close();

    }

    @RequestMapping("/mp3")
    public void mp3(HttpServletResponse response) throws IOException {

        response.setContentType("audio/mpeg;charset=utf-8");
        String path = servletContext.getRealPath("/WEB-INF/upload");
        System.out.println(path);
        File file = new File(path+File.separator+"轻音乐 - 世界上最悲伤最伤感最感动的音乐.mp3");
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024*10];
        int len;
        while ((len=in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }

        in.close();

    }

    @RequestMapping("/mp4")
    public void mp4(HttpServletResponse response) throws IOException {

        String path = servletContext.getRealPath("/WEB-INF/upload");
        System.out.println(path);
        File file = new File(path+File.separator+"Mad Clown,金娜英 - 다시 너를(Once Again).mp4");
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024*10];
        int len;
        while ((len=in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }

        in.close();

    }
}
