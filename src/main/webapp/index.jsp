<%--
  Created by IntelliJ IDEA.
  User: Sandy
  Date: 2020/10/15
  Time: 20:29
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <c:import url="common_css.jsp"/>
    <style>
        .enter-caption {
            position: absolute;
            right: 3%;
            top: 3%;
            z-index: 10;
            color: white;
        }
        a {
            color: white;
            text-decoration: none;
        }
        a:hover {
            cursor: pointer;
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="image/hero_bg_1.jpg" class="d-block w-100" alt="">
            <div class="enter-caption">
                <a class="h4" href="Menu">进入点餐系统</a>
            </div>
        </div>
        <div class="carousel-item">
            <img src="image/hero_bg_2.jpg" class="d-block w-100" alt="">
            <div class="enter-caption">
                <a class="h4" href="Menu">进入点餐系统</a>
            </div>
        </div>
        <div class="carousel-item">
            <img src="image/hero_bg_3.jpg" class="d-block w-100" alt="">
            <div class="enter-caption">
                <a class="h4" href="Menu">进入点餐系统</a>
            </div>
        </div>
        <div class="carousel-item">
            <img src="image/hero_bg_4.jpg" class="d-block w-100" alt="">
            <div class="enter-caption">
                <a class="h4" href="Menu">进入点餐系统</a>
            </div>
        </div>
        <div class="carousel-item">
            <img src="image/hero_bg_5.jpg" class="d-block w-100" alt="">
            <div class="enter-caption">
                <a class="h4" href="Menu">进入点餐系统</a>
            </div>
        </div>
    </div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>