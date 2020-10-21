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
        .space-div {
            width: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
<c:import url="adminHeader.jsp"/>
<div class="container">
    <div class="order-detail-table">
        <div class="h2" style="margin-bottom: 30px">订单详情</div>
        <div style="margin-bottom: 20px">
            <b>订单号：</b><%=request.getAttribute("order_id")%><div class="space-div"></div>
            <b>用户ID：</b><%=request.getAttribute("user_id")%><div class="space-div"></div>
            <b>时间：</b><%=request.getAttribute("order_time")%><div class="space-div"></div>
            <b>桌号：</b><%=request.getAttribute("table_number")%><div class="space-div"></div>
            <b>状态：</b><span class="order-status"><%=request.getAttribute("order_status")%></span>
        </div>
        <%=request.getAttribute("orderDetailTableHtml")%>
    </div>
</div>
<c:import url="common_js.jsp"/>
<script>
function change_order_status(orderID, status) {
    $.ajax({
        type: "GET",
        contentType: "application/text;charset=UTF-8",
        url: "AdminOrderChangeStatus?order_id="+orderID+"&status="+status,
        success: function(result) {
            alert(result);
            let order_status_span = $(".order-status")[0];
            if (status === 1) {
                order_status_span.innerText = "已完成";
            } else {
                order_status_span.innerText = "未完成";
            }
        },
        error: function(e){
            console.log(e.status);
            alert(e.responseText);
        }
    });
}
</script>
</body>
</html>