$(function () {
    query_image();
});


/**查询数据库该用户是否有头像*/
function query_image() {
    var username = $(".welcome").html();
    var img = document.getElementById('userImg');
    img.setAttribute('src',"/top/queryImage?username="+username);

}




