<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/19
  Time: 4:27
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单记录</title>
    <c:import url="common_css.jsp"/>
    <style>
        td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="no-food-in-cart-alert alert alert-success" style="display: none">暂无订单记录</div>
    <div class="record-table">
        <%=request.getAttribute("orderTableHtml")%>
    </div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>