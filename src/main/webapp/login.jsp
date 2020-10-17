<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/16
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="card">
        <div class="card-header">登录</div>
        <div class="card-body">
            <form action="UserLogin" method="post">
                <div class="form-group row">
                    <label for="phone_number" class="col-sm-2 col-form-label text-center">手机号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="请输入手机号">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="user_password" class="col-sm-2 col-form-label text-center">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="user_password" name="user_password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
