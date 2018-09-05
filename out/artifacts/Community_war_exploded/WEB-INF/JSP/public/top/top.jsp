<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <script type="text/javascript" src="../../../../js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../../../../js/community/top.js"></script>
    <link type="text/css" rel="stylesheet" href="../../../../styles/top.css"/>

    <div class="topDiv">
        <a href="${pageContext.request.contextPath}/update/ModifyImage"><img class="userImg" id="userImg" src=""></a>
        <span class="welcome">${sessionScope.username}</span>
        <a class="out" href="">退出</a>
    </div>

