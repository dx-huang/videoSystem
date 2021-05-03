<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/9
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
</head>
<body>
<form action="/resetPsd" method="post">
    <input type="hidden" name="p" value="${p}">
    新密码：<input type="text" name="password">
    <input type="submit" value="提交">
</form>

</body>
</html>
