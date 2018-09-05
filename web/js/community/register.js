/**html页面加载完之后执行*/
$(function () {
    username_check();
    password_check();
    code_check();
    click_check();
    click_verifyCode();
});

/**校验用户名*/
    var result = 0;
    var count1 = 0;
    var count2 = 0;
    var count3 = 0;
function username_check() {

        /**失去焦点事件*/
        $("#username").blur(function () {
            var content = $("#username").val();
            /**匹配空格*/
            var a = new RegExp("\\s+");
            console.log(a.test(content));
            var $username_div = $("#username_div");
            var $span = $username_div.find("span").eq(0);
            console.log($span);
            var $img = $username_div.find("img").eq(0);
            // var reg = /^[a-zA-Z_][a-zA-Z_0-9]{5,9}$/;
            var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]{4,10}$/;
            /**匹配未输入任何内容*/
            if(content.length === 0){
                $span.css("display","inline");
                $img.css("display","inline");
                $span.html("请输入用户名!");
                count1++;

            } else if(reg.test(content.trim())){
                var username = $("#username").val();

                var data = {
                    username:username
                };
                // alert(JSON.stringify(data));
                /**利用ajax向后台发送参数*/
                $.ajax({
                    type:"POST",
                    url:'/register/username_check',
                    dataType:"json", //表示返回值的类型
                    contentType:"application/json;charset=UTF-8",
                    data:JSON.stringify(data),//将json对象转成json字符串
                    success:function (result) {
                        var value = result.value;

                        if(value === "false"){
                            // alert("该用户名已被占用");
                            $span.css("display","inline");
                            $img.css("display","inline");
                            $img.attr("src","../../../images/fail2.png");
                            $span.html("该用户名已存在");
                            count1++;

                        }else{
                            // alert("该用户名可用");
                            $span.html("");
                            $img.css("display","inline");
                            $img.attr("src","../../../images/ok.png");
                            count1 = 0;
                        }
                    }

                });
            }else{
                // alert("有问题！");
                /**选择div下的span元素和img元素*/
                 $span.css("display","inline");
                 $img.css("display","inline");

                $span.html("用户名不合法!");
                // $span.innerHTML = "123";
                // $span.setAttribute("style","display:inline");
                // document.getElementById("username_div").getElementsByTagName("span")[0].innerHTML="123";
                count1 ++;
            }

        });
}

/**校验密码*/
function password_check() {
    /**失去焦点事件*/
    $("#password").blur(function () {
        var $content = $("#password").val();
        var $password_div =  $("#password_div");
        var $span = $password_div.find("span").eq(0);
        var $img = $password_div.find("img").eq(0);
        var reg = /^[a-zA-Z][a-zA-Z0-9]{5,9}$/;
        if($content.length === 0){
            $span.css("display","inline");
            $img.css("display","inline");
            // $span.attr("style","display:inline");
           $span.html("请输入密码");
           count2++;
        }else if(reg.test($content.trim())){
            $span.html("");
            $img.css("display","inline");
            $img.attr("src","../../../images/ok.png");
            count2 = 0;
        }else{
            /**选择div下的span元素和img元素*/
            $span.css("display","inline");
            $img.css("display","inline");
            $img.attr("src","../../../images/fail2.png");
            $span.html("密码不合法!");
            count2++;
        }

    });
}

/**校验验证码*/
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
            count3++;
        } else {
            /**输入验证码后发送后台比较*/
            var data = {value:$content};
            $.ajax({
                contentType:'application/json;charset=utf-8',
                type:'POST',
                url:'/register/compare',
                data:JSON.stringify(data),
                dataType:'text',
                success:function (result) {
                    // alert(result);
                    if(result == "true"){
                        $img.css("display","inline");
                        $img.attr("src","../../../images/ok.png");
                        $span.html("");
                        count3 = 0;
                    }else{
                        $img.css("display","inline");
                        $img.attr("src","../../../images/fail2.png");
                        $span.css("display","inline");
                        $span.html("验证码错误");
                        count3++;

                    }
                }
            });



        }


    });
}

/**单击注册的单击事件*/
function click_check() {
    $("#register").click(function () {
        /**监听所有的失去焦点事件*/
      $("input").trigger("blur");
      /**rusult用来统计页面错误的个数，依次来判断是否可以提交表单*/
      result = count1+count2+count3;
      // alert("错误的次数:"+result);
      if(result != 0){
          // alert("表单不能提交");
          return false;
      }
      alert("注册成功");
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
    $img.attr("src","/register/changeImg?"+time);
}

