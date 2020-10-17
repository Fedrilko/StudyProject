<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin page</title>
</head>
<body>
<p>Admin ${currentUser.lastName} (<a href="logout">Logout</a>) </p>
<p><a href="add">Add new user</a></p>
<u:usersTable users="${users}"/>

</body>
</html>