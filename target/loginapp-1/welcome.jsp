<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, <%= request.getParameter("username") %>!</h1>
    <p>You have successfully logged in.</p>
    <a href="login.jsp">Logout</a>
</body>
</html>

