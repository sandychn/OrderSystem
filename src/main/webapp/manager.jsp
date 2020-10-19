<%--
  Created by IntelliJ IDEA.
  User: 11370
  Date: 2020/10/17
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台管理</title>
    <c:import url="common_css.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="js/bootbox.min.js"></script>
</head>
<body>


<div class="box">
    <div class="title">对菜品的增删改</div>
    <div class="content">
        <!--搜索输入框及查询、重置按钮-->
        <div class="container content_width">
            <div class="person_search">
                <div class="search_input">
                    <div class="input-group mb-3">
                        <span>菜名：</span>
                        <input id="Ktext" type="text" class="form-control" placeholder="请输入菜名">
                    </div>
                </div>

                <div class="search_input">
                    <button class="btn btn-primary search_btn" type="button" id="search_btn">查询</button>
                    <button class="btn btn-primary search_btn" type="button" id="back_btn">重置</button>
                </div>
            </div>
            <div class="line"></div>
        </div>
        <!--添加按钮及bootstrap的模态框-->
        <div class="export">
            <button id="new_add" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan">
                <img src="images/food/add_two.png"/>
                <span>添加</span>
            </button>
            <div class="modal fade" id="renyuan">
                <div class="modal-dialog modal-lg modal_position">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">添加</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <table id="xztb" class="table">
                                <!--新修改弹窗的样式-->
                                <tbody>
                                <tr>
                                    <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>菜名</label>
                                    </td>
                                    <td><input class="userName" type="text" placeholder="请输入菜名"/></td>

                                </tr>

                                <tr>
                                    <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>餐品ID</label>
                                    </td>
                                    <td><input class="jobNum" type="number" placeholder="请输入餐品ID"/></td>
                                </tr>

                                <tr>
<%--                                    <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>手机号</label>--%>
<%--                                    </td>--%>
<%--                                    <td><input class="phoneNum" type="number" placeholder="请输入手机号"/></td>--%>
                                    <td class="tb_bg"><label for="">餐品图片路径</label></td>
                                    <td><input type="text" placeholder="请输入餐品图片路径"/></td>
                                </tr>

                                <tr>
                                    <td class="tb_bg"><label for="">餐品描述</label></td>
                                    <td><input type="text" placeholder="请输入餐品描述"/></td>

                                </tr>
                                <tr>
                                    <td class="tb_bg"><label for="">餐品价格</label></td>
                                    <td><input type="text" placeholder="请输入餐品价格"/></td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                            <button id="add_btn" type="button" class="btn btn-secondary">确定</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--表格列表-->
        <table id="tb" class="table">
            <thead>
            <tr>
                <th>菜名</th>
                <th>餐品ID</th>
                <th>餐品图片路径</th>
                <th>餐品描述</th>
                <th>餐品价格</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="show_tbody">
        <%--表格内容--%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <a href="#" class="edit">编辑</a>
                    <a href="#" class="del">删除</a>
                </td>
            </tr>


            </tbody>
        </table>
    </div>
</div>

<script src="js/mejs.js"></script>

</body>
</html>
