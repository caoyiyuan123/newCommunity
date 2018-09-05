$(function () {
    queryLargerImg();

});

$("#formData").ajaxForm(function (data) {
    alert("123");
});

/**查询数据库该用户是否有头像*/
function queryLargerImg() {
    var username = $("#username").html();
    var Img = document.getElementById('Img');
    var largerImg = document.getElementById('largerImg');
    var mediumImg = document.getElementById('mediumImg');
    var smallImg = document.getElementById('smallImg');

    Img.setAttribute('src',"/top/queryImage?username="+username);
    largerImg.setAttribute('src',"/top/queryImage?username="+username);
    mediumImg.setAttribute('src',"/top/queryImage?username="+username);
    smallImg.setAttribute('src',"/top/queryImage?username="+username);

}

/**图片预览*/
function preview(file) {
    var reader = new FileReader();
    reader.onload = function (e) {
    //     var list = document.getElementsByName('img');
    //    alert(list.length);
    //    for(var i=0;i++;i<list.length){
    //        list[i].src = e.target.result;
    //    }
        document.getElementById('Img').src = e.target.result;
        document.getElementById('largerImg').src = e.target.result;
        document.getElementById('mediumImg').src = e.target.result;
        document.getElementById('smallImg').src = e.target.result;
        };

    reader.readAsDataURL(file.files[0]);
}