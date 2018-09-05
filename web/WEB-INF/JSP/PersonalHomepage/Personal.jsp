<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/15
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <link type="text/css" rel="stylesheet" href="../../../styles/personal/personal.css">
</head>
<body>

    <jsp:include page="../public/top/top.jsp"/>
    <form action="${pageContext.request.contextPath}/login/saveContent">
        <div class="title">
            <input id="title" name="title" type="text"/>
        </div>
        <div class="content">
            <textarea class="comment" name="comment" placeholder="请输入内容">
                请输入内容，字数不限
            </textarea>
        </div>
        <div class="bottom">
            <span><button>发布</button></span>&nbsp;&nbsp;&nbsp;
            <span><button>撤销</button></span>
        </div>
    </form>
</body>
</html>
