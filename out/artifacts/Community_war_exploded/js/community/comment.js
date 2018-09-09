/**查看帖子内容*/
$(function () {
    //isClick();
});


/**检查评论框输入的内容是否为空*/
function check() {
    var value = $("#content").val();
    alert(value);
    if(value === ''){
        alert("输入的内容不能为空");
        return false;
    }
    return true;
}

/**点赞数字增加*/
function isClick() {
        /**获取文章的标题*/
        var title = $("#title").text();
        alert(title);

       var data = {title:title};

       $.ajax({
            url:'/loginSuccess/saveLikesNums',
            contentType:'application/json;charset=utf-8',
            type:'POST',
            data:JSON.stringify(data),
            dataType:'text',
           success:function (result) {
               alert(result);
               $("#like_num").html(result);


           }


       });
}

/**继续点数字减少*/
function likeNumDecrease(){
        // $("#like_img").css("background-color","red");
        var num = $("#like_num").text();
        num--;
        $("#like_num").text(num);

        /**利用ajax来刷新点赞后的页面*/
        var title = $("#title").html();
        var data = {title:title,nums:num};
        $.ajax({
            url:'/loginSuccess/saveLikesNums',
            contentType:'application/json;charset=utf-8',
            type:'POST',
            data:JSON.stringify(data),
            dataType:'text',
            success:function (result) {
                // alert("点赞成功");
            }
        });
}
