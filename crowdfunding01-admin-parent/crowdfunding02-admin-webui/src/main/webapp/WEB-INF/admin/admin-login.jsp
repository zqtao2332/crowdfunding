<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/5/21
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%--  基本路径前缀  --%>
    <base
            href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">

    <style>
    </style>
    <title>爱众筹</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" href="index.html" style="font-size: 32px;">爱众筹-创意产品-众筹平台</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">

    <%--    action="admin/do/login.html" 自行处理 action="security/do/login.html"  交给spring security 处理 --%>
    <form action="security/do/login.html" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading">
            <i class="glyphicon glyphicon-log-in"></i> 管理员登录
        </h2>
        <%-- 自行处理异常信息 --%>
        <%-- <p>${requestScope.exception.message }</p> --%>
        <%-- spring security 给定的异常信息 --%>
        <p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
        <div class="form-group has-success has-feedback">
            <input type="text" name="loginAcct" value="pc" class="form-control" id="inputSuccess4"
                   placeholder="请输入登录账号" autofocus> <span
                class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" name="userPswd" value="123456" class="form-control" id="inputSuccess4"
                   placeholder="请输入登录密码" style="margin-top: 10px;"> <span
                class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <button id="saveRoleBtn" type="submit" class="btn btn-lg btn-success btn-block">登录</button>
    </form>
</div>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>


</body>
</html>