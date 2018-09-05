/**修改个人头像*/
$(function () {
    previewFile(this);
    uploadImage();

});

/**图片预览*/
var largeImage='';
function previewFile(file){
    if(window.FileReader){
        var reader = new FileReader();
    }else{
        alert("浏览器不支持")
    }
    /**onload事件:文件读取成功触发*/
    reader.onload = function (e) {
        document.getElementById('largeImage').src = e.target.result;
        largeImage = e.target.result;
        // console.log(image);
    };
    /**
     * readAsDataURL()：
     * 该方法将文件读取为一段以 data: 开头的字符串，
     * 这段字符串的实质就是 Data URL，Data URL是一种将小文件直接嵌入文档的方案。
     */
    reader.readAsDataURL(file.files[0]);

}

/**提交请求到后台，将文件对象作为json字符串发送*/
function uploadImage() {
    var data = {value:image};
//            alert(image);
    $.ajax({
        type:'POST',
        url:'/login/ModifyImage',
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(data),//json字符串格式发送
        dataType:'text',
        success:function (result) {
            //alert(result);
            readImg(result);

        }
    });
}

/**接收服务器发送过来的图片*/
function readImg(result) {
    var data = {value:result};
    $.ajax({
        type:'POST',
        url:'/login/getImage',
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(data),//json字符串格式发送
        dataType:'text',
        success:function (res) {
            alert(res);
            $("#myImg").attr("src","data:image/jpg;base64,"+res);
            //alert(decodeURI(res));
        }
    });
}
