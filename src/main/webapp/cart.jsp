<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>购物车</title>
	<c:import url="common_css.jsp"/>
	<link rel="stylesheet" href="css/cart.css"/>
	<style>
		.fas {
			cursor: pointer;
			color: gray;
		}
		.food-count {
			display: inline-block;
			width: 30px;
			text-align: center;
		}
	</style>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
	<div class="no-food-in-cart-alert alert alert-success" style="display: none">购物车为空</div>
	<div class="food-table">
		<%=request.getAttribute("cartTableHtml")%>
		<form action="CreateOrder" method="post">
			<div class="text-center" style="margin-bottom: 15px">
				<label for="table_number" class="col-form-label" style="display: inline-block; margin-right: 10px;">请输入桌号</label>
				<input type="number" class="form-control" id="table_number" name="number"
					   min="1" style="width: 100px; display: inline-block" required>
				<input type="text" id="user_id" name="user_id" value="<%=request.getSession().getAttribute("login_user_id")%>" hidden>
			</div>
			<div class="text-center">
				<button class="btn btn-secondary" type="submit">下单</button>
			</div>
		</form>
	</div>
</div>
<c:import url="common_js.jsp"/>
<script src="js/cartItemModify.js"></script>
<script>
	check_no_food_in_cart();
</script>
</body>
</html>