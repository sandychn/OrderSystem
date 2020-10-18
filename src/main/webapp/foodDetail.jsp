<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜品详情</title>
    <c:import url="common_css.jsp"/>
    <style>
        .text-bigger {
            font-size: 1.3em;
        }
        .food-count {
            display: inline-block;
            font-size: 2em;
            padding: 0 15px;
        }
        .fas {
            font-size: 2em;
            cursor: pointer;
            color: gray;
        }
        .fas:hover {
            color: blue;
        }
        .food-image {
            border-radius: 20px;
            box-shadow: 5px 5px 2px gray;
        }
    </style>
</head>
<body>
<%
String userId = (String)request.getSession().getAttribute("login_user_id");
String foodId = (String)request.getAttribute("food_id");
String foodName = (String)request.getAttribute("food_name");
String foodImageURL = (String)request.getAttribute("food_image_url");
String foodDescription = (String)request.getAttribute("food_description");
String foodPrice = (String)request.getAttribute("food_price");
String foodCount = (String)request.getAttribute("food_count");
%>
<c:import url="header.jsp"/>
<div class="container">
    <div class="card" style="padding-bottom: 100px">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-5" style="margin-bottom: 20px">
                    <img class="w-100 food-image" src="<%=foodImageURL%>" alt="<%=foodName%>"/>
                </div>
                <div class="col-sm-7">
                    <div class="h2" style="margin-bottom: 20px;"><b><%=foodName%></b></div>
                    <div><%=foodDescription%></div>
                    <div style="padding: 30px 0">
                        <span class="price" style="font-size: 2em"><b>¥</b>&nbsp;<%=foodPrice%> / 份</span>
                    </div>
                    <div class="no-select">
                        <span class="text-bigger" style="padding-right: 15px;">份数</span>
                        <span class="fas fa-minus-circle" onclick="item_modify_minus('<%=userId%>','<%=foodId%>')"></span>
                        <div class="food-count" id="food-<%=foodId%>"><%=foodCount%></div>
                        <span class="fas fa-plus-circle" onclick="item_modify_plus('<%=userId%>','<%=foodId%>')"></span>
                    </div>
                    <div style="margin-top: 50px;">
                        <a href="Menu"><button class="btn btn-primary">返回点餐菜单</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="common_js.jsp"/>
<script src="js/cartItemModify.js"></script>
</body>
</html>
