<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点餐菜单</title>
    <c:import url="common_css.jsp"/>
    <link rel="stylesheet" href="css/menu.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <nav class="navbar-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar"
                        aria-controls="collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul class="nav flex-column" id="menu">
                        <li class="nav-item"><a class="nav-link" href="Menu">全部</a></li>
                        <%=request.getAttribute("kind_html")%>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="col-md-10 column">
            <%= request.getAttribute("foods_html") %>
        </div>
    </div>
</div>
<c:import url="common_js.jsp"/>
<script src="js/cartItemModify.js"></script>
</body>
</html>
