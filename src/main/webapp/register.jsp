<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/15
  Time: 20:29
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="card">
        <div class="card-header">注册</div>
        <div class="card-body">
            <form action="UserRegister" method="post" onsubmit="return validate()">
                <div class="form-group row">
                    <label for="phone_number" class="col-sm-3 col-form-label text-center">手机号</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="phone_number" name="phone_number"
                               placeholder="请输入手机号" maxlength="11" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="username" class="col-sm-3 col-form-label text-center">姓名</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="请输入姓名" maxlength="20" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="user_password" class="col-sm-3 col-form-label text-center">密码</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="user_password" name="user_password"
                               placeholder="请输入密码" maxlength="20" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="user_password_confirm" class="col-sm-3 col-form-label text-center">确认密码</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="user_password_confirm" name="user_password_confirm"
                               placeholder="请输入确认密码" maxlength="20" required>
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">注册</button>
                </div>
            </form>
            <div class="alert alert-warning" id="message_text" style="display: none">
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
<script>
    function validate() {
        let phone_number = document.getElementById("phone_number").value;
        let password = document.getElementById("user_password").value;
        let password_confirm = document.getElementById("user_password_confirm").value;
        let password_length = password.length;

        let is_phone_number_valid = /^1[3456789]\d{9}$/.test(phone_number);
        let password_same = (password === password_confirm);

        if (password_same && is_phone_number_valid && password_length >= 6) {
            return true;
        }

        let message_text_div = $("#message_text")[0];

        console.log(password_length);

        if (!is_phone_number_valid) {
            message_text_div.innerText = "输入的手机号不合法";
        } else if (!password_same) {
            message_text_div.innerText = "两次输入的密码不一致";
        } else if (password_length < 6) {
            message_text_div.innerText = "密码长度需至少6位";
        } else if (password_length > 20) {
            message_text_div.innerText = "密码长度至多20位";
        }

        message_text_div.style = "";
        return false;
    }
</script>
</body>
</html>
