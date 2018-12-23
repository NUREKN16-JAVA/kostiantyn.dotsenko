<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>User management. Add</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/add" method="post">
		<p>First name</p>
		<input type="text" name="firstName" value="">
		<p>Last name</p>
		<input type="text" name="lastName" value="">
		<p>Date of birth</p>
		<input type="text" name="date" value=""> <br> <input
			type="submit" name="okButton" value="Ok"> <input
			type="submit" name="cancelButton" value="Cancel">
	</form>

	<c:if test="${requestScope.error != null}">
		<script>
			alert('${requestScope.error}');
		</script>
	</c:if>
</body>
</html>