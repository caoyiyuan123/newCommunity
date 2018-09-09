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

       var data = {title:title};

       $.ajax({
            url:'/loginSuccess/saveLikesNums',
            contentType:'application/json;charset=utf-8',
            type:'POST',
            data:JSON.stringify(data),
            dataType:'text',
           success:function (result) {
               $("#like_num").html(result);
           }
       });
}

