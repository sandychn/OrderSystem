<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/17
  Time: 8:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<header class="navbar navbar-expand-md navbar-dark bg-dark" style="margin-bottom: 20px">
    <a class="navbar-brand" href="index.jsp">点餐系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">点餐</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">购物车</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="my.jsp">个人中心</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item">
                <a class="nav-link" href="login.jsp">登录</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="register.jsp">注册</a>
            </li>
        </ul>
    </div>
</header>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
