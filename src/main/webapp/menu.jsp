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
                        <li class="nav-item">
                            <a class="nav-link" href="home1.html">炒菜</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="home2.html">凉菜</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="home3.html">汤类</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="home4.html">甜品</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="home5.html">酒水</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="home6.html">主食</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="col-md-10 column">
            <div class="row clearfix" id="tab1" style="margin-bottom: 10px">
                <div class="col-md-3 column">
                    <a href="things.jsp"><img src="image/1.jpg" class="img-thumbnail" style="width:100%"/></a>
                    <div class="item-desciption">
                        脆皮鸡腿   ¥99
                        <br>
                        <button type="button" class="btn btn-light">下单</button>
                    </div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/2.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                        巧克力蛋糕   ¥99
                        <br>
                        <button type="button" class="btn btn-light">下单</button>
                    </div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/3.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    肥牛卷   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/4.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    马卡龙   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
            </div>
            <div class="row clearfix" id="tab2" style="margin-bottom: 10px">
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/5.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    黑椒牛排   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/6.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    杭州小笼包   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/7.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    酸汤馄饨   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/8.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    蜜汁烤全鸡   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
            </div>
            <div class="row clearfix" id="tab3" style="margin-bottom: 10px">
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/9.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    番茄蛋塔   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/10.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    双蛋烤肉   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/11.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    香菇炖乌鸡   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/12.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    蓝莓蛋糕   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
            </div>
            <div class="row clearfix" id="tab4" style="margin-bottom: 10px">
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/13.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    重庆火锅   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/14.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    手工水饺   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/15.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    四川涮串   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                    <img src="image/16.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    草莓蛋糕   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
            </div>
            <div class="row clearfix" id="tab5" style="margin-bottom: 10px">
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/17.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    牛丸意面   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/18.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    汤圆   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/19.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    桂花茶   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/20.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    可乐鸡翅   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
            </div>
            <div class="row clearfix" id="tab6" style="margin-bottom: 10px">
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/21.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    酱香茄子   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/22.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    烤生蚝   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/23.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    玉米炖鸡腿   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button></div>
                </div>
                <div class="col-md-3 column">
                    <a href="things.jsp">
                        <img src="image/24.jpg" class="img-thumbnail" style="width:100%"/>
                    </a>
                    <div class="item-desciption">
                    惠灵顿牛排   ¥99
                    <br>
                    <button type="button" class="btn btn-light">下单</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="common_js.jsp"/>
</body>
</html>