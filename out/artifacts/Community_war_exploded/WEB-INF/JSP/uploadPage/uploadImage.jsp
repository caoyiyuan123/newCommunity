<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送文件</title>
    <script type="text/javascript" src="../../../js/jquery-1.11.1.js"></script>
</head>
<body>
<form id="myform" enctype="multipart/form-data">

    <input type="text" name="name" id="name" value="123" />
    <input type="text" name="pwd" id="pwd" value="321" />

    <input type="file" id="file" name="myfile" />

    <input type="button" onclick="SubmitForm()" value="提交" />

</form>

    <script>
        function SubmitForm(){
            //FormDat对象
            var formobj = new FormData();

            //获取表单中的数据
            var name = document.getElementById("name");
            var pwd = document.getElementById("pwd");
//            var myfile = document.getElementById('file').files[0];

            //向对象中添加要发送的数据
            formobj.append('name',name);
            formobj.append('pwd',pwd);
//            formobj.append('myfile',myfile);

            //XMLHttpRequest对象
            var xmlobj = new XMLHttpRequest();

            //指定提交类型和选择要发送的地址
            xmlobj.open('post','/FileUp');

            //发送数据
            xmlobj.send(formobj);

            xmlobj.onload = function()
            {
                alert(xmlobj.responseText);//获取后台返回的数据
            }
        }
    </script>





</body>
</html>
