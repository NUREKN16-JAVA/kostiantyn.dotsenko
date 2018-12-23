<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="user" class="ua.nure.kn.dotsenko.usermanagement.User"
	scope="session" />
<html>
<head>
<title>User management. Edit</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/edit" method="post">
		<input type="hidden" name="id" value="${user.id}">
		<p>First name</p>
		<input type="text" name="firstName" value="${user.firstName}">
		<p>Last name</p>
		<input type="text" name="lastName" value="${user.lastName}">
		<p>Date of birth</p>
		<input type="text" name="date"
			value="<fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/>">
		<br> <input type="submit" name="okButton" value="Ok"> <input
			type="submit" name="cancelButton" value="Cancel">
	</form>

	<c:if test="${requestScope.error != null}">
		<script>
	        alert('${requestScope.error}');
	    </script>
	</c:if>
</body>
</html>