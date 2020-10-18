<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<p>Admin ${currentUser.lastName} (<a href="logout">Logout</a>) </p>
<h1>${action} user</h1>
<form action="${placeholder}" method="post">
<div>
<label>Login</label>
<input type="text"/>
</div>
<div>
<label>Password</label>
<input type="password"/>
</div>
<div>
<label>Password again</label>
<input type="password"/>
</div>
<div>
<label>Email</label>
<input type="email"/>
</div>
<div>
<label>First name</label>
<input type="text"/>
</div>
<div>
<label>Last name</label>
<input type="text"/>
</div>
<div>
<label>Birth day</label>
<input type="text"/>
</div>
<div>
<label>Role</label>
<select>
<option value="User">User</option>
<option value="Admin">Admin</option>
</select>
</div>
<div>
<input type="submit" value="Ok"/>
<button>Cancel</button>
</div>
</form>
</body>
</html>