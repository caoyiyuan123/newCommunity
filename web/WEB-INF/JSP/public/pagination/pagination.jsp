<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/4
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="paging">
    <a href="${pageContext.request.contextPath}?CurrentPage=1"><button>首页</button></a>
    <a href="${pageContext.request.contextPath}?CurrentPage=${page.previews}"><button>上一页</button></a>
    <c:forEach  var="i" begin="${begin}" end="${end}">
        <a id="biao" href="${pageContext.request.contextPath}?CurrentPage=${i}"><button>${i}</button></a>
    </c:forEach>
    <a href="${pageContext.request.contextPath}?CurrentPage=${page.next}"><button>下一页</button></a>
    <a href="${pageContext.request.contextPath}?CurrentPage=${page.totalNum}"><button>尾页</button></a>

    第<input value="${currentPage}" style="width:30px;height:20px;text-align: center"/>页/共<span id="span">${page.totalNum }</span>页
    跳转到第<input value="" id="jump" style="width:30px;height:20px;"/>页
    <button id="buttonOK">确定</button>
</div>

<style>
    button{
        width:60px;
        height: 30px;
    }
    .paging{
        width:800px;
        height: 40px;
        /*background-color: #0a97c9;*/
        margin: 50px auto;
    }
</style>

<script>
    $("#buttonOK").click(function () {
        /**得到的是一个字符串*/
        var totalNum = $("#span").html();
        var newTotalNum = parseInt(totalNum);
        var value = $("#jump").val();
        var newValue = parseInt(value);

        /**将字符串转换为integer*/
        var reg = new RegExp("^[0-9]*$");
        /**判断输入的是否为数字*/
        if(reg.test(value)){
            /**判断输入的数字是否在1~最大页数之间*/
            if(newValue>0 && newValue<=newTotalNum){
                location.href = "loginSuccess?CurrentPage="+newValue;
            }else{
                alert("你输入的数字不合法");
            }
        }else{
            alert("你输入的不是数字");
            return false;
        }


    });

    <%--url = "${pageContext.request.contextPath}?CurrentPage="+value;--%>
    <%--$("#abc").attr("href",url);--%>

        <%--var input = $("#jump");--%>
        <%--input.keyup(function(event){--%>
            <%--if(event.keyCode==13){--%>

                <%--var value = input.val();--%>
                <%--alert(value);--%>
                <%--location.href="${pageContext.request.contextPath}?currentPage="+value;--%>

            <%--}--%>
        <%--})--%>

</script>
