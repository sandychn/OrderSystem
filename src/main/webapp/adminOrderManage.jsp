<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理</title>
    <c:import url="common_css.jsp"/>
    <style>
        td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>
<c:import url="adminHeader.jsp"/>
<div class="container">
    <div class="no-food-in-cart-alert alert alert-success" style="display: none">暂无订单记录</div>
    <div class="record-table">
        <%=request.getAttribute("orderTableHtml")%>
    </div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>