<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="users" type="java.util.List" required="true" %>

<jsp:useBean id="ageService" class="com.khodko.studyproject.services.impl.BasicAgeCalculationService"/>

<c:set var="currentDate" value="<%= new java.sql.Date(System.currentTimeMillis())%>"/>

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
        <c:set var="birthDate" value="${user.birthDate}"/>

        <tr>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${ageService.calculateAge(birthDate, currentDate)}"/></td>
            <td><c:out value="${user.role.name}"/></td>
            <td><a href="edit?userLogin=${user.login}">Edit</a>
                <a href="remove?userId=${user.id}" onclick="return confirm('Are you sure?')">Delete</a></td>
        </tr>
    </c:forEach>

</table>
