<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="users" type="java.util.List" required="true"%>

<table>
	<tr>
		<th>Login</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Age</th>
		<th>Role</th>
		<th>Actions</th>
	</tr>

	<c:forEach items="${users}" var="user">
		<tr>
			<td><c:out value="${user.login}" /></td>
			<td><c:out value="${user.firstName}" /></td>
			<td><c:out value="${user.lastName}" /></td>
			<td><c:out value="${user.birthDate}" /></td>
			<td><c:out value="${user.role.name}" /></td>
			<td><a href="edit?userId=${user.id}">Edit</a> 
			<a href="delete?userId=${user.id}">Delete</a></td>
		</tr>
	</c:forEach>

</table>


