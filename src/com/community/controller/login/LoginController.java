package com.community.controller.login;

import com.community.Utils.PageUtils;
import com.community.Utils.VerifyCodeUtils;
import com.community.entity.Post;
import com.community.entity.User;
import com.community.mapper.PostMapper;
import com.community.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: jack
 * @Create: 2018-08-19-19:34
 * @Desc: 相关登录的操作
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private PostMapper postMapper;

    @RequestMapping("/loginIndex")
    public String login(){
        return "homePage/login";
    }

    /**从登录页面到主页面*/
    @RequestMapping("/loginCheck")
    public String loinSuccess(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        //response.setContentType("html/text;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");

        /**利用Session取得用户名和密码*/
        session.setAttribute("username",username);

        //1.获取subject主体对象
        Subject subject = SecurityUtils.getSubject();
        //2.将用户名和密码存入token中
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            //3.主体对象执行登录功能
            try{
                subject.login(token);
                logger.info("登录成功");
            }catch (Exception e){
                logger.error("登录失败");
                return "errorPage/error";
            }

        }
        return "redirect:/login/loginSuccess";
    }


    /**保存发布的文章*/
    @RequestMapping("/saveContent")
    public String saveContent(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        String username = session.getAttribute("username").toString();
        String title = request.getParameter("title");
        String comment = request.getParameter("comment");
        logger.info(title);
        logger.info(comment);
        logger.info(username);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = sdf.format(date);
        logger.info(newDate);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(comment);
        post.setAuthor(username);
        post.setCreatime(newDate);
        postMapper.add(post);
        return "redirect:/login/loginSuccess";

    }


    /**进行分页*/
    @RequestMapping("/loginSuccess")
    public String loginSuccess(@RequestParam(value="CurrentPage",defaultValue = "1", required=false) String CurrentPage,
                               HttpServletRequest request){
        /**从数据库中获取数据*/
        String count = "5";
        Integer size = 5;
        PageUtils page = new PageUtils(5,5);
        int totalCounts = postMapper.queryTotalCounts();
        logger.info(totalCounts);
        page.setTotalCounts(totalCounts);
        logger.info(CurrentPage);
        page.setCurPage(CurrentPage);
        request.setAttribute("page",page);

        List<Post> lists = postMapper.queryPage(page);
        request.setAttribute("size",page.getSize());
        request.setAttribute("begin",page.getBegin());
        request.setAttribute("end",page.getEnd());
        request.setAttribute("list",lists);
        request.setAttribute("currentPage",page.getCurPage());
        return "MainPage/MainPage";
    }

    /**改变验证码*/
    @RequestMapping(value = "/changeImg",method = RequestMethod.GET)
    public void changeImg(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Controller","no-cache");
        response.setDateHeader("Expires",0);

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

    /**实现上传图片功能*/
    @RequestMapping(value = "/ModifyImage",method = RequestMethod.POST)
    @ResponseBody
    public String changeImage(@RequestBody String data) throws IOException {
        /**将json字符串转成json对象*/
        JSONObject jsonObject = JSONObject.fromObject(data);
        /**获取文件的头部分*/
        String header = "data:image/jepg;base64,";
        /**获取文件的实体部分*/
        String image = jsonObject.getString("value");
        String username = jsonObject.getString("username");
        /**剪切头部分*/
        image = image.substring(header.length());
        BASE64Decoder decoder = new BASE64Decoder();
            /**用base64解码，decodeBuffer()转成二进制编码*/
            byte[] bytes = decoder.decodeBuffer(image);
            long time = System.currentTimeMillis();
            /**指定保存的路径*/
            String imageAddress = "F:/temp/"+time+".jpg";
            File path = new File(imageAddress);
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(bytes);
            outputStream.close();

        /**查询数据库修改图片的地址*/
        User user = new User();
        user.setUsername(username);
        user.setImageAddress(imageAddress);
        userMapper.updateImage(user);

        return imageAddress;
    }

    /**服务器向浏览器发送图片*/
    @RequestMapping(value = "/getImage",method = RequestMethod.POST)
    @ResponseBody
    public String sendImg(@RequestBody String path) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(path);
        /**获取文件的路径*/
        String paths = jsonObject.get("value").toString();
        System.out.println(paths);
        File file = new File(paths);
        /**读取图片*/
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",out);
        /**将当前输出流转为字节数组*/
        byte[] data = out.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        /**对字节数组进行编码*/
        String imageString = encoder.encodeBuffer(data).trim();
        imageString.replaceAll("\n","").replaceAll("\r","");
        return imageString;
    }
}
