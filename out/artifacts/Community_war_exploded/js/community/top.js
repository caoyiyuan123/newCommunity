$(function () {
    query_image();
});


/**查询数据库该用户是否有头像*/
function query_image() {
    //var username = $(".welcome").html();
    var image = document.getElementById('userImg');
    image.setAttribute('src',"/top/queryImage?id="+Math.random());

}




