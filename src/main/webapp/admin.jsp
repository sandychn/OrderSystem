<%--
  Created by IntelliJ IDEA.
  User: 11370
  Date: 2020/10/17
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台管理</title>
    <c:import url="common_css.jsp"/>
</head>
<body>
<c:import url="adminHeader.jsp"/>
<%
    String loginUserID = (String)session.getAttribute("login_user_id");

    if (loginUserID == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int loginUserIdentity = (int)session.getAttribute("login_user_identity");

    // not an administrator
    if (loginUserIdentity != 1) {
        response.sendRedirect("Menu");
        return;
    }
%>
<div class="container">
    <div class="alert-success alert">管理员，您好！请点击上方链接进入对应功能。</div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>
