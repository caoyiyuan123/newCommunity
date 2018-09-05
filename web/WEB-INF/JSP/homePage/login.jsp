<%@page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录页面</title>
    <!--
        href="../../../styles/global.css"。
        静态资源的请求，需要在springmvc.xml文件中配置默认请求处理器。
    -->
    <%--<link type="text/css" rel="stylesheet" href="../../../styles/global.css" />--%>
    <%--<link type="text/css" rel="stylesheet" href="../../../styles/global_color.css" />--%>
    <link type="text/css" rel="stylesheet" href="../../../styles/login.css"/>
    <script type="text/javascript" src="../../../js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../../../js/community/login.js"></script>

    <style >
        .div{
           width: 130px;
        }
        .div span,.div img{
            display: none;
        }
    </style>
</head>
<body class="index">
<br/><br/>
<div class="logo">
    <img src="../../../images/logo.png" alt="logo"/>
    <span class="logo-span">VMS社区</span>
</div>
<div class="login_box" style="overflow: scroll;width: 100%;height: 400px">
    <form action="${pageContext.request.contextPath}/login/loginCheck" method="post">
    <table class="table" cellspacing="10px">
        <tr>
            <th class="login_info">账号：</th>
            <td colspan="2"><input id="username" name="username" type="text" class="width150" placeholder="6~10长度的字母、数字和下划线"/></td>
            <td>
                <div class="div" id="username_div">
                    <img src="../../../images/fail2.png">
                    <span>请输入用户名</span>
                </div>

            </td>
        </tr>
        <tr>
            <th class="login_info">密码：</th>
            <td colspan="2">
                <input id="password" name="pwd" type="password" class="width150" placeholder="6~10长度的字母、数字和下划线"/>
            </td>
            <td>
                <div class="div" id="password_div">
                    <img src="../../../images/fail2.png">
                    <span>请输入密码</span>
                </div>

            </td>
        </tr>
        <tr>
            <th>验证码：</th>
            <td id="code" class="width70"><input id="Code_name" name="" type="text" class="width70"  placeholder="请输入验证码" /></td>
            <td><img id="verifyCode" alt="验证码" title="点击更换" /></td>
            <td>
                <div class="div" id="code_div">
                    <img src="../../../images/fail2.png">
                    <span>请输入验证码</span>
                </div>

            </td>
        </tr>
        <tr>
            <td></td>
            <td class="login_button" colspan="2">
                <input type="submit" id="enter" value="登录" class="submit">
                </input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td></td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>

