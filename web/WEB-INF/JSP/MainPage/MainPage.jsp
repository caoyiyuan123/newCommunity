<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>主页面</title>
    <link type="text/css" rel="stylesheet" media="all" href="../../../styles/whole.css" />
    <link type="text/css" rel="stylesheet" media="all" href="../../../styles/MainPage/mainPage.css" />
    <script type="text/javascript" src="../../../js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../../../js/community/MainPage.js"></script>
    <style type="text/css">
        .userImg{
             width:50px;
             height: 50px;
             border-radius: 50%;
             position: relative;
             left:400px;
         }
        .welcome{
            position: relative;
            left:400px;
            top:-18px;
            font-family: "微软雅黑", sans-serif;
            font-size: 15px;
            color:white;
        }
        .search{
            position: relative;
        }
    </style>
</head>
<body>
<!--主要区域开始-->
<div class="search">
    <input class="search_context" type="text" value="" placeholder="你想知道的这里都有">
    <input class="search_button" type="button" value=""/>
    <a href="${pageContext.request.contextPath}/update/ModifyImage"><img class="userImg" id="userImg" src=""></a>
    <span class="welcome">${sessionScope.username}</span>
    <%--<button class="login_btn"><a href="${pageContext.request.contextPath}/login">登录</a></button>--%>
    <%--<button class="register_btn"><a href="${pageContext.request.contextPath}/register">注册</a></button>--%>
</div>



<div class="main">
    <form action="" method="">
        <!--数据区域：用表格展示数据-->
        <div class="data">
            <table class="table">
                <thead class="head">
                    <tr>
                        <td class="id">ID</td>
                        <td class="title">标题</td>
                        <td class="author">作者</td>
                        <td class="click">点击</td>
                        <td class="answer">回复</td>
                        <td class="creatime">创建时间</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="post">
                    <tr>
                        <td class="id">${post.id}</td>
                        <td class="title"><a href="${pageContext.request.contextPath}/loginSuccess/content_check?title=${post.title}">${post.title}</a></td>
                        <td class="author">${post.author}</td>
                        <td class="click">${post.clickNum}</td>
                        <td class="answer">${post.comments}</td>
                        <td class="creatime">${post.creatime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>

</div>
<!--主要区域结束-->
<!--底部分页-->
    <jsp:include page="../public/pagination/pagination.jsp"/>
</body>
</html>
