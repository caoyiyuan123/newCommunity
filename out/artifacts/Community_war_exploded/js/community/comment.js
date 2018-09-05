/**查看帖子内容*/
$(function () {
    //isClick();
});

/**检查是否点过赞*/
var flag = false; //默认为点赞增加
function isClick() {
    if(flag){
        likeNumAdd();
        flag = false;
    }else{
        likeNumDecrease();
        flag = true;
    }
}

/**点赞数字增加*/
function likeNumAdd() {
        // $("#like_img").css("background-color","red");
        var num = $("#like_num").text();
        num++;
       $("#like_num").text(num);

       /**利用ajax来刷新点赞后的页面*/
       var title = $("#title").html();
       var data = {title:title,nums:num,};
       alert()
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
