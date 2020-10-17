<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/16
  Time: 15:14
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <main>
        <div class="card">
            <div class="card-header">个人信息</div>
            <div class="card-body">
                <form>
                    <div class="form-group row">
                        <label for="phone_number" class="col-4 col-form-label text-center font-weight-bold">手机号</label>
                        <div class="col-8">
                            <input type="text" disabled class="form-control-plaintext text-center" id="phone_number" value="12345678901">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="username" class="col-4 col-form-label text-center font-weight-bold">用户名</label>
                        <div class="col-8">
                            <input type="text" disabled class="form-control-plaintext text-center" id="username" value="张三">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="balance" class="col-4 col-form-label text-center font-weight-bold">账户余额</label>
                        <div class="col-8">
                            <input type="text" disabled class="form-control-plaintext text-center" id="balance" value="0.00">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
