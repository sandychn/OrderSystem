<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/16
  Time: 10:34
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>result</title>
    <c:import url="common_css.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="alert alert-primary">
        <%=request.getAttribute("result_message")%>
    </div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>
