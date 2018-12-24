<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="user" class="ua.nure.kn.dotsenko.usermanagement.User"
	scope="session" />
<html>
<head>
<title>User management. Details</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/details" method="post">
		<p>Id: ${user.id}</p>
		<p>First name: ${user.firstName}</p>
		<p>Last name: ${user.lastName}</p>
		<p>
			Date of birth:
			<fmt:formatDate value="${user.dateOfBirth}" type="date"
				dateStyle="medium" />
		</p>
		<br> <input type="submit" name="cancelButton" value="Ok">
	</form>

	<c:if test="${requestScope.error != null}">
		<script>
            alert('${requestScope.error}');
        </script>
	</c:if>
</body>