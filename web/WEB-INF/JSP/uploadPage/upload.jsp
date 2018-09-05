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
    <title></title>
</head>
<body>
    <img id="img" src="">
    <button onclick="showImg()">显示</button>
    <button onclick="coverImg()">隐藏</button><br/>
    <video id="mp4" controls="controls" src="/movie">movie</video>
    <div id="divMp3">

    </div>
    <button onclick="showMp3();">MP3</button>
    <button onclick="showMp4();">MP4</button>

    <script>
        /**显示图片*/
        function showImg() {
            var img = document.getElementById("img");
                img.setAttribute("src","/image");

        }
        /**隐藏图片*/
        function coverImg() {
            var img = document.getElementById("img");
            img.setAttribute("src","");
        }


        var flag = true;
        function showMp3() {

            if(flag){
                var mp3 = document.createElement("audio");
                mp3.setAttribute("id","mp3");
                mp3.setAttribute("controls","controls");
                mp3.setAttribute("src","/mp3");

                document.getElementById("divMp3").appendChild(mp3);
                flag = false;
            }else{
                document.getElementById("divMp3").removeChild(document.getElementById("mp3"));
                flag = true;
            }

        }

        function showMp4() {
            var mp4 = document.getElementById("mp4");

                mp4.setAttribute("src","/mp4");
        }
    </script>
</body>
</html>
