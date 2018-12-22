
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>User_management/Browse</title>
</head>
<body>
	<table id="userTable" style="border: 1px;">
		<tr>
			<th></th>
			<th>First name</th>
			<th>Last name</th>
			<th>Date of birth</th>
		</tr>
		<c:forEach var="user" items="${sessionScope.users}">
		<tr>
			<td><input type="radio" name="id" id="id" value="${user.id}" /></td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.dateOfBirth}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>