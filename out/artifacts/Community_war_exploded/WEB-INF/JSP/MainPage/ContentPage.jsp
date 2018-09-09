<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="com.community.controller.IndexController.ContentController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/13
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" charset="UTF-8"/>
    <title>内容页面</title>
    <link type="text/css" rel="stylesheet" href="../../../styles/reply_posts/replyPosts.css"/>
    <script type="text/javascript" src="../../../js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../../../js/community/comment.js"></script>


    <style>
        #userImage{
            width: 50px;
            height: 50px;
            border-radius: 50%;
            position: relative;
            left:-20px;
            top:20px;
        }

        .username{
            position: relative;
            left:10px;
            top:0px;
            font-family: "微软雅黑", sans-serif;
            font-size: 15px;
            color:black;
        }
    </style>

    <script>

    </script>
</head>
<body>
    <jsp:include page="../public/top/top.jsp"/>

    <div class="title" id="title">${title}</div>
    <div class="content"  style="word-wrap: break-word;word-break: break-all;">
        <div class="mainBody">
                ${content}
        </div>
        <div class="content_evaluation">
                    <p class="like" style="">
                       <img id="like_img" src="../../../images/like.png" onclick="isClick();"><br>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span id="like_num" style="font-size: larger">${clickNum}</span>
                    </p>
                    <p style="text-align: right">
                        <a href="#write_comment"><span id="show_comment">评论</span></a>
                        <span>${comments}</span>&nbsp;&nbsp;&nbsp;
                        <a href="#"><span >举报</span>&nbsp;&nbsp;&nbsp;</a>
                     </p>
        </div>
    </div>
    <div class="comment">
        <c:forEach items="${list}" var="record">
                <div class="comment_username">
                    <span>
                        <img id="userImage" class="userImage" name="" src="${pageContext.request.contextPath}/demo/queryImage?username=${record.username}">
                    </span>
                    <span class="username">${record.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;

                    <span>${record.creatime}</span>
                </div>
                <div class="comment_content">
                    <p>${record.content}</p>
                </div>
                <p class="comment_bottom" style="text-align: right;cursor: pointer">
                    <span>评论</span><span>${record.answer}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span id="like_span">点赞</span><span id="numbs">${record.like}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <%--<span>收藏</span>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                    <%--<span>举报</span>--%>
                </p>
        </c:forEach>
                <%--<hr style="border:1px solid cornflowerblue">--%>
    </div>
    <div class="writeComment" id="write_comment">
        <form action="${pageContext.request.contextPath}/loginSuccess/sendContent?title="+${title} method="post" onsubmit="return check();">
            <textarea class="input_box" id="content" name="content"></textarea>
            <div class="commit">
                <%--<span>提交</span>--%>
                <%--<span>撤销</span>--%>

            </div>
            <input type="submit"  id="btns" value="提交">
            <button style="margin-left: 30px" onclick="window.history.back()">撤销</button>

        </form>


    </div>

</body>
</html>
