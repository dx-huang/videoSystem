<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/9
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
<form action="/sendUrl" method="post">
    邮箱：<input type="text" name="email">
    <input type="submit" value="提交">
</form>
</body>
</html>
