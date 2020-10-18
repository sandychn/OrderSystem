<%--
  Created by IntelliJ IDEA.
  User: 11370
  Date: 2020/10/16
  Time: 9:30
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <c:import url="common_css.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body>
<c:import url="header.jsp"/>
<div class="cont">
    <div class="form sign-in">
        <div class="vcenter">
            <div class="form-title text-center">欢迎光临</div>
            <form action="UserLogin" method="post" onsubmit="return login_validate()">
                <label for="login_phone_number" class="text-center">手机号码</label>
                <input class="form-input" type="text" id="login_phone_number" name="login_phone_number"
                       maxlength="11" required>
                <label for="login_user_password" class="text-center">密码</label>
                <input class="form-input" type="password" id="login_user_password" name="login_user_password"
                       maxlength="16" required>
                <button type="submit" class="login_btn">登录</button>
            </form>
            <div class="alert alert-warning" id="login_message_text" style="display:none;"></div>
        </div>
    </div>
    <div class="sub-cont">
        <div class="img">
            <div class="img__text m--up">
                <div class="h3 text-center">第一次来么?</div>
                <div class="text-center" style="font-size: 13px">来注册发现更多的快乐吧!</div>
            </div>
            <div class="img__text m--in">
                <div class="h3 text-center">已经有账号?</div>
                <div class="text-center" style="font-size: 13px">如果已有账号了，请直接登录!</div>
            </div>
            <div class="img__btn">
                <span class="m--up">前往注册</span>
                <span class="m--in">前往登录</span>
            </div>
        </div>
        <div class="form sign-up">
            <div class="vcenter">
                <div class="form-title text-center">注册新账号</div>
                <form action="UserRegister" method="post" onsubmit="return register_validate()">
                    <label for="register_phone_number" class="text-center">手机号码</label>
                    <input class="form-input" type="text" id="register_phone_number" name="register_phone_number"
                           maxlength="11" required>
                    <label for="register_username" class="text-center">用户名</label>
                    <input class="form-input" type="text" id="register_username" name="register_username"
                           maxlength="20" required>
                    <label for="register_user_password" class="text-center">密码</label>
                    <input class="form-input" type="password" id="register_user_password" name="register_user_password"
                           maxlength="20" required>
                    <label for="register_user_password_confirm" class="text-center">确认密码</label>
                    <input class="form-input" type="password" id="register_user_password_confirm"
                           maxlength="20" required>
                    <button type="submit" class="register_btn">注册</button>
                </form>
                <div class="alert alert-warning" id="register_message_text" style="display:none;"></div>
        </div>
    </div>
</div>
<c:import url="common_js.jsp"/>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>
