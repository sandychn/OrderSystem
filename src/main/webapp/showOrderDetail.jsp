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
    <title>订单详情</title>
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
    <div class="order-detail-table">
        <div class="h2" style="margin-bottom: 30px">订单详情</div>
        <%=request.getAttribute("orderDetailTableHtml")%>
    </div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>