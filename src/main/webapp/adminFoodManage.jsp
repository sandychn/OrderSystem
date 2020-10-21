<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>餐品管理</title>
    <c:import url="common_css.jsp"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <style>
        .content {
            margin: 20px auto;
        }

        .operation {
            margin: 10px;
        }

        .operation>button {
            margin: 3px;
        }

        #foods {
            margin: 5px;
        }

        .center-block {
            display: block;
            margin: auto;
        }
    </style>
</head>

<body>
<c:import url="adminHeader.jsp"/>
<div class="container">
    <section class="content">
        <div class="operation float-right">
            <button id="btn_add" type="button" class="btn btn-primary"><span style="color:white">新增</span></button>
            <button id="btn_edit" type="button" class="btn btn-info"><span style="color:white">修改</span></button>
            <button id="btn_delete" type="button" class="btn btn-success">删除</button>
        </div>
        <div class="modal fade" id="addFood" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">添加菜品</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="AdminFoodCreate" method="post" enctype="multipart/form-data">
                        <div id="addFoodModal" class="modal-body">
                            <div class="form-horizontal">
                                <div class="form-group row">
                                    <label for="foodName" class="col-sm-4 control-label">菜品名称:*</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="foodName" name="foodName" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="foodImage" class="col-sm-4 control-label">菜品图片:*</label>
                                    <div class="col-sm-8">
                                        <input type="file" id="foodImage" name="foodImage" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="foodDescription" class="col-sm-4 control-label">菜品描述:*</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="foodDescription" name="foodDescription" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="foodPrice" class="col-sm-4 control-label">菜品价格:*</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="foodPrice" name="foodPrice" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="foodKindId" class="col-sm-4 control-label">菜品种类:*</label>
                                    <div class="col-sm-8">
                                        <select id="foodKindId" name="foodKindId" required>
                                            <%=request.getAttribute("kind_options")%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="center-block">
                                <button id="addFoodsInfo" type="submit" class="btn btn-success">保存</button>&nbsp;&nbsp;
                                <button id="cancelAdd" type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="editFoodInfo" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">修改菜品</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="AdminFoodUpdate" method="post" enctype="multipart/form-data">
                        <div id="editFoodModal" class="modal-body">
                            <div class="form-horizontal">
                                <input id="editFoodId" name="editFoodId" type="text" hidden>
                                <div class="form-group row">
                                    <label for="editFoodName" class="col-sm-4 control-label">菜品名称:*</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="editFoodName" name="editFoodName" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="editFoodImage" class="col-sm-4 control-label">菜品图片:*</label>
                                    <div class="col-sm-8">
                                        <a href="#" id="originalFoodImageLink" target="_blank">查看现有图片</a>
                                        <input type="file" id="editFoodImage" name="editFoodImage">
                                        <div>(不修改图片则无须选择)</div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="editFoodDescription" class="col-sm-4 control-label">菜品描述:*</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="editFoodDescription" name="editFoodDescription" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="editFoodPrice" class="col-sm-4 control-label">菜品价格:*</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="editFoodPrice" name="editFoodPrice" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="editFoodKindId" class="col-sm-4 control-label">菜品种类:*</label>
                                    <div class="col-sm-8">
                                        <select id="editFoodKindId" name="editFoodKindId" required>
                                            <%=request.getAttribute("kind_options")%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="center-block">
                                <button id="saveEdit" type="submit" class="btn btn-success">保存</button>&nbsp;&nbsp;
                                <button id="cancelEdit" type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="deleteFood" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">确认要删除吗？</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-footer">
                        <form action="AdminFoodDelete" method="post">
                            <input id="deleteFoodId" name="deleteFoodId" type="text" hidden>
                            <button id="delete" type="submit" class="btn btn-danger">删除</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <table id="foods" class="table table-striped table-bordered row-border hover order-column" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>菜品ID</th>
                <th>菜品名称</th>
                <th>菜品图片路径</th>
                <th>菜品描述</th>
                <th>菜品价格</th>
                <th>菜品种类</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </section>
</div>
<c:import url="common_js.jsp"/>
<script src="https://cdn.bootcss.com/datatables/1.10.16/js/jquery.dataTables.js"></script>
<script>
    let foodIdToKindIdDict = <%=request.getAttribute("food_id_to_kind_id_dict")%>;
    let data = <%=request.getAttribute("food_table_array")%>;
    let titles = ['foodID','foodName','imageUrl','description','price','kindName']
    $(function () {
        var table = $('#foods').DataTable({
            data: data,
            "pagingType": "full_numbers",
            "bSort": true,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "columnDefs": [{
                "searchable": false,
                "orderable": true,
                "targets": 0
            }],
            "order": [[0, 'asc']]
        });
        // table.on('order.dt search.dt', function() {
        //     table.column(0, {
        //         search: 'applied',
        //         order: 'applied'
        //     }).nodes().each(function(cell, i) {
        //         // cell.innerHTML = i + 1;
        //     });
        // }).draw();

        $('#foods tbody').on('click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            } else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });

        $("#btn_add").click(function(){
            $("#addFood").modal()
        });

        $("#cancelAdd").on('click', function() {
            $("#addFoodModal").find('input').val('')
        })

        $('#btn_edit').click(function () {
            if (table.rows('.selected').data().length) {
                let rowData = table.rows('.selected').data()[0];
                $("#editFoodId").val(rowData[0])
                $("#editFoodName").val(rowData[1]);
                $("#originalFoodImageLink").attr('href', rowData[2]);
                $("#editFoodDescription").val(rowData[3]);
                $("#editFoodPrice").val(rowData[4]);
                $("#editFoodKindId").val(foodIdToKindIdDict[rowData[0]]);
                $("#editFoodInfo").modal()
            } else {
                alert('请选择项目');
            }
        });


        $("#cancelEdit").click(function() {
            $("#editFoodModal").find('input').val('')
        })

        $('#btn_delete').click(function () {
            if (table.rows('.selected').data().length) {
                let rowData = table.rows('.selected').data()[0];
                $("#deleteFoodId").val(rowData[0])
                $("#deleteFood").modal()
            } else {
                alert('请选择项目');
            }
        });
    })
</script>
</body>
</html>
