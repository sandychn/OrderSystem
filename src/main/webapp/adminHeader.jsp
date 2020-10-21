<%
    String uri = request.getRequestURI();
    boolean inOrderManagePage = "/adminOrderManage.jsp".equals(uri);
    boolean inFoodManagePage = "/adminFoodManage.jsp".equals(uri);
    boolean inLoginPage = "/login.jsp".equals(uri);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="navbar navbar-expand-md navbar-dark bg-dark" style="margin-bottom: 20px">
    <a class="navbar-brand" href="admin.jsp">点餐系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarNav" aria-controls="navbarNav"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link <%=inOrderManagePage ? "active" : ""%>" href="AdminOrderManage">订单管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=inFoodManagePage ? "active" : ""%>" href="AdminFoodManage">餐品管理</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-md-auto">
            <%
                String login_user_phone_number = (String)request.getSession().getAttribute("login_user_phone_number");
                if (login_user_phone_number != null) {
            %>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
                   role="button" aria-haspopup="true" aria-expanded="false">您好，<%=login_user_phone_number%>&nbsp;&nbsp;(管理员)</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="Logout">登出</a>
                </div>
            </li>
            <%
                } else {
            %>
            <li class="nav-item">
                <a class="nav-link <%=inLoginPage ? "active" : ""%>" href="login.jsp">登录/注册</a>
            </li>
            <%
                }
            %>
        </ul>
    </div>
</header>