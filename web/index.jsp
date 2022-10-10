<%--
  Created by IntelliJ IDEA.
  User: ilovesshan
  Date: 2022/10/10
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html;UFT-8" pageEncoding="UTF-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>
    <base href="<%=basePath%>">
</head>
<body>
<h2>USM项目(前后端分离)</h2>
<P>前端使用: vue3 + ts + axios</P>
<P>后端使用: ssm + mybatis + mysql</P>
</body>
</html>
