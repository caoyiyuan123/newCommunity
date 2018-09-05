/**登录界面的js*/
$(function () {
   code_check();
    click_verifyCode();
    click_enter();
});

/**校验验证码*/
var count = 0;
function code_check() {
    $("#Code_name").blur(function () {
        var $content = $("#Code_name").val();
        var $code_div = $("#code_div");
        var $span = $code_div.find("span").eq(0);
        var $img = $code_div.find("img").eq(0);
        if($content.length === 0){
            /**没有输入验证码的情况*/
            $img.css("display","inline");
            $span.css("display","inline");
            $span.html("请输入验证码");
            count++;
        } else {
            /**输入验证码后发送后台比较*/
            var data = {value:$content};
            $.ajax({
                contentType:'application/json;charset=utf-8',
                type:'POST',
                url:'/login/compare',
                data:JSON.stringify(data),
                dataType:'text',
                success:function (result) {
                    // alert(result);
                    if(result == "true"){
                        $img.css("display","inline");
                        $img.attr("src","../../../images/ok.png");
                        $span.html("");
                        count = 0;
                    }else{
                        $img.css("display","inline");
                        $img.attr("src","../../../images/fail2.png");
                        $span.css("display","inline");
                        $span.html("验证码错误");
                        count++;

                    }
                }
            });



        }


    });
}

/**单击验证码事件*/
function click_verifyCode() {
    changeImg();
    $("#verifyCode").click(function () {
        changeImg();
    });
}

/**修改验证码*/
function changeImg() {

    var $img = $("#verifyCode");
    var d = new Date();
    var time = d.getTime();
    $img.attr("src","/login/changeImg?"+time);
}

/**登录按钮失去焦点事件*/
function click_enter(){
    $("#enter").click(function () {
        // alert(count);
        if(count != 0){
            return false;
        }else{
            // alert("登录成功");
        }

    });
}