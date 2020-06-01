<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/5/31
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%-- 加载公共头部 --%>
<%@include file="/WEB-INF/include-head.jsp" %>
<%-- 引入pagination css --%>
<link rel="stylesheet" href="css/pagination.css"/>
<%-- 引入pagination js --%>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<%-- 引入自定义的js 文件--%>
<script type="text/javascript" src="zqtScript/auth-role.js"></script>
<script type="text/javascript">
    $(function () {
        // 1.为分页操作初始化数据，存放使用window对象，全局使用
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        // 2.调用执行分页的函数， 显示分页效果
        generateRolePage();

        // 给查询按钮绑定响应函数
        $("#searchBtn").click(function () {

            // 1.获取关键词数据，赋值给对应的全局变量 keyword
            window.keyword = $("#keywordInput").val();

            // 2.调用分页函数,刷新表格主体
            generateRolePage();
        });

        // 给新增按钮添加响应事件，弹出新增模态框
        $("#showAddModalBtn").click(function () {
            $("#addModel").modal('show');
        });
    });
</script>

<body>

<%-- 加载公共导航栏 --%>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%-- 加载公共侧边栏 --%>
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button
                            type="button"
                            id="showAddModalBtn"
                            class="btn btn-primary"
                            style="float: right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>

                            <%-- 动态显示role 分页数据 --%>
                            <tbody id="rolePageBody"></tbody>

                            <%-- 动态显示分页导航栏 --%>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页导航栏 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 统一添加模态框在页面的最后，因为加入的模态框默认是不显示的 --%>
<%-- 这里添加一个新增的模态框，通过给按钮添加点击事件来调用模态框 --%>
<%@include file="/WEB-INF/model-add.jsp"%>
</body>
</html>