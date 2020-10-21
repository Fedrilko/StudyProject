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
<form onsubmit="return false" action="${action.toLowerCase()}" method="post">
<div>
<label>Login</label>
<c:choose>
<c:when test="${action.equals(\"Add\")}">
<input type="text" id="login" name="login" value="${user.login}"/>
<label id="email_msg" style="display:none;">Field can not be empty</label>
</c:when>
<c:when test="${action.equals(\"Edit\")}">
<input type="text" id="login" name="login" value="${user.login}" readonly="readonly"/>
</c:when>
</c:choose>
</div>
<div>
<label>Password</label>
<input type="password"  id="password" name="password" value="${user.password}"/>
<label id="password_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>Password again</label>
<input type="password" value="${user.password}"/>
<label id="password_rpt_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>Email</label>
<input type="email" id="email" name="email" value="${user.email}"/>
<label id="email_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>First name</label>
<input type="text" id="firstName" name="firstName" value="${user.firstName}"/>
<label id="first_name_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>Last name</label>
<input type="text" id="lastName" name="lastName" value="${user.lastName}"/>
<label id="last_name_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>Birth day</label>
<input type="text" id="birthDate" name="birthDate" value="${user.birthDate}" placeholder="YYYY-MM-DD"/>
<label id="dirthday_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>Role</label>
<select name="role">
<c:choose>
<c:when test="${user.role.name.equals(\"Admin\") }">
<option value="User">User</option>
<option value="Admin" selected>Admin</option>
</c:when>
<c:when test="${user.role.name.equals(\"User\") }">
<option value="User" selected>User</option>
<option value="Admin">Admin</option>
</c:when>
<c:when test="${user == null}">
<option value="User">User</option>
<option value="Admin">Admin</option>
</c:when>
</c:choose>
</select>
</div>
<div>
<input type="submit" value="Ok" id="submit_form"/>
<a href="main">Cancel</a>
<!--  <button>Cancel</button>-->
</div>
</form>
<c:if test="${msg != null}">
<p>${msg}</p>
</c:if>

<script type="text/javascript" src="resources/javascript/jquery.js"></script>
<script type="text/javascript" src="resources/javascript/validation_script.js"></script>
</body>
</html>