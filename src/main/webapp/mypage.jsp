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
    <c:import url="common_css.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<%
    String loginUserPhoneNumber = (String)request.getSession().getAttribute("login_user_phone_number");
    String loginUserUsername = (String)request.getAttribute("login_user_username");
    String loginUserBalance = (String)request.getAttribute("login_user_balance");
%>

<div class="container">
    <main>
        <div class="card">
            <div class="card-header">个人信息</div>
            <div class="card-body">
                <form>
                    <div class="form-group row">
                        <label for="phone_number" class="col-4 col-form-label text-center font-weight-bold">手机号</label>
                        <div class="col-8">
                            <input type="text" disabled class="form-control-plaintext text-center" id="phone_number"
                                   value="<%=loginUserPhoneNumber%>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="username" class="col-4 col-form-label text-center font-weight-bold">用户名</label>
                        <div class="col-8">
                            <input type="text" disabled class="form-control-plaintext text-center" id="username"
                                   value="<%=loginUserUsername%>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="balance" class="col-4 col-form-label text-center font-weight-bold">账户余额</label>
                        <div class="col-8">
                            <input type="text" disabled class="form-control-plaintext text-center" id="balance"
                                   value="<%=loginUserBalance%>">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>
