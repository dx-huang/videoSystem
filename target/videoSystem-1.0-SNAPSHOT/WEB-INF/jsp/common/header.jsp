<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--导航条-->
<nav class="navbar shadow navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">VIDEO</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link ${clickNavType == 0?"active":""}" href="/">首页 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${clickNavType == 1?"active":""}" href="/course">课程</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${clickNavType == 2?"active":""}" href="/vip">会员</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${clickNavType == 3?"active":""}" href="https://live.bilibili.com/?spm_id_from=333.851.b_7072696d617279467269656e64736869704c696e6b.2" target="_blank">直播</a>
                </li>
            </ul>
            <c:choose>
                <c:when test="${!empty SESSION_LOGINUSER}">
                    ${SESSION_LOGINUSER.email}
                    <a href="/logout" class="mr-2 ml-2">退出</a>
                </c:when>
                <c:otherwise>
                    <a href="#" class="mr-2" data-toggle="modal" data-target="#loginModal" >登录</a>/
                    <a href="#" class="ml-2 mr-5" data-toggle="modal" data-target="#registerModal">注册</a>
                </c:otherwise>
            </c:choose>
            <form class="form-inline my-2 my-lg-0" action="/search" method="post">
                <input class="form-control mr-sm-2" name="keyword" type="search" placeholder="搜索视频" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
            </form>
        </div>
    </div>

</nav>

<br>
<!--登录-->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">登录</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/login" method="post" onsubmit="return checklogin()">
                    <div class="form-group">
                        <label for="loginInputEmail">邮箱：</label>
                        <input type="email" class="form-control" id="loginInputEmail" name="email" aria-describedby="emailHelp" placeholder="请输入邮箱" required>
                    </div>
                    <div class="form-group">
                        <label for="loginInputPassword">密码：</label>
                        <input type="password" class="form-control" id="loginInputPassword" name="password" placeholder="请输入密码" required>
                        <small id="loginHelp" style="color: #ff0000"></small>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="checkLogin" name="autoLogin" value="1">
                        <label type="form-check-label" for="checkLogin">自动登录</label>
                        <a href="/forgetPsd" class="float-right">忘记密码</a>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                </form>
            </div>

            <a href="#" class="ml-3 mb-3" data-toggle="modal" data-dismiss="modal" data-target="#registerModal">还没有账号？点我注册</a>
        </div>
    </div>
</div>
<!--注册-->
<div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">注册</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"></span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/regist" method="post" onsubmit="return checkregist()">
                    <div class="form-group">
                        <label for="registerInputEmail">邮箱：</label>
                        <input type="email" class="form-control" id="registerInputEmail" name="email" aria-describedby="emailHelp" placeholder="请输入邮箱" onblur="checkRegistEmail(this)" required>
                        <small id="registerEmailHelp" style="color: #ff0000"></small>
                    </div>
                    <div class="form-group">
                        <label for="registerInputPassword">密码：</label>
                        <input type="password" class="form-control" id="registerInputPassword" name="password" placeholder="请输入密码" onblur="checkRegistPassword(this)" required>
                        <small id="registerPasswordHelp" style="color: #ff0000"></small>
                    </div>
                    <div class="form-group">
                        <label for="vcode">验证码：</label>
                        <div class="row ml-1">
                            <input type="vcode" class="form-control col-md-6" id="vcode" name="vcode" maxlength="4" placeholder="请输入验证码" required>
                            <img src="/vcode" class="col-md-3 offset-md-1" onclick="changeVcode(this)" style="cursor: pointer">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">注册</button>
                    </div>
                </form>
            </div>
            <a href="#" class="ml-3 mb-3" data-toggle="modal" data-dismiss="modal" data-target="#loginModal">已有账号？点我登录</a>
        </div>
    </div>
</div>


<!--JS脚本-->
<script type="application/javascript">
    //注册模态框部分
    //更新二维码图片
    function changeVcode(imgNode){
         imgNode.src = "/vcode?ram" + new Date().getTime();
    }

    //注册中邮箱的格式
    function checkRegistEmail(EmailInput){
        var Email = EmailInput.value;
        var patt = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        var suc = patt.test(Email);
        if (!suc){
            $("#registerInputEmail").removeClass("is-valid");
            $("#registerInputEmail").addClass("is-invalid");
            $("#registerEmailHelp").text("邮箱格式不正确");
            return;
        }else{
            $("#registerInputEmail").removeClass("is-invalid");
            $("#registerInputEmail").addClass("is-valid");
            $("#registerEmailHelp").text("");
        }

        $.ajax({
            url:"/checkEmail?email="+ Email,
            success: function (result) {
                if (result.code == 1){
                    //用户可以被注册
                    $("#registerInputEmail").removeClass("is-invalid");
                    $("#registerInputEmail").addClass("is-valid");
                    $("#registerEmailHelp").text("");

                }else {
                    //用户不可以被注册
                    $("#registerInputEmail").removeClass("is-valid");
                    $("#registerInputEmail").addClass("is-invalid");
                    $("#registerEmailHelp").text("用户已存在");

                }
            }

        })

    }

    //注册中密码长度不为六位数
    function checkRegistPassword(PasswordInput){
        var password = PasswordInput.value;
        var len = password.length;
        if (len < 6){
            $("#registerInputPassword").removeClass("is-valid");
            $("#registerInputPassword").addClass("is-invalid");
            $("#registerPasswordHelp").text("密码不小于6位");
            return;
        }else {
            $("#registerInputPassword").removeClass("is-invalid");
            $("#registerInputPassword").addClass("is-valid");
            $("#registerPasswordHelp").text("");
        }
    }

    //当表单中的输入框为不正确格式时不提交表单(通过判断是否有is-valid属性来决定表单是否提交)
    function checkregist() {
        var emailFlag = $("#registerInputEmail").hasClass('is-invalid');
        var pwdFlag = $("#registerInputPassword").hasClass('is-invalid');
        if (emailFlag == true || pwdFlag == true){
            //不可以提交表单
            return false;
        }
        //可以提交表单
        return true;
    }

    //登录模态框部分
    function checklogin() {
        var submitFlag = false;
        var loginEmail = $("#loginInputEmail").val();
        var loginPassword = $("#loginInputPassword").val();
        console.log(loginEmail+"--"+loginPassword);
        $.ajax({
            url:"/checkLogin",
            type:"post",
            data:{email:loginEmail,password:loginPassword},
            async: false,
            success:function (result) {
                if (result.code == 1){
                    submitFlag = true;
                }else {
                    $("#loginHelp").text(result.message);
                    submitFlag = false;
                }

            }
        })
        return submitFlag;
    }





</script>

</body>
</html>
