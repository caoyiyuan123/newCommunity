<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/13
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

                <div class="comment_username">
                    <span>
                        <img id="userImage" class="userImage" name="" src="../../../images/user_images1535105510944.jpg">
                    </span>
                    <span class="username">${sessionScope.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>2018-09-05:15:32:58</span>
                </div>
                <div class="comment_content">
                    <p>来得及公开是可敬的考虑过斯柯达经历过</p>
                </div>
                <p class="comment_bottom" style="text-align: right;cursor: pointer">
                    <span>评论</span><span>12</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span id="like_span">点赞</span><span id="numbs">125</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>收藏</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>举报</span>
                </p>
                <%--<hr style="border:1px solid cornflowerblue">--%>
    </div>
    <div class="writeComment" id="write_comment">
        <form action="${pageContext.request.contextPath}/comment/sendContent" method="post">
            <textarea class="input_box" name="content"></textarea>
            <div class="commit">
                <%--<span>提交</span>--%>
                <%--<span>撤销</span>--%>
                <input type="submit" value="提交">
                <button style="margin-left: 30px" onclick="window.history.back()">撤销</button>
            </div>
        </form>


    </div>

</body>
</html>
