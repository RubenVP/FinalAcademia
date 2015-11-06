<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="/SpringSecurityDatabase/index"><button>Log In</button></a>

	<div>
		<form:form method="POST" action="registerUser" commandName="user">
			
			<form:label path="name">First Name:</form:label><span>*</span>
			<form:input path="name"/>
			<form:errors path="name"></form:errors>
			<br>
			
			<form:label path="lastName">Last Name:</form:label>
			<form:input path="lastName"/>
			<br>
			
			<form:label path="username">Username:</form:label><span>*</span>
			<form:input path="username"/>
			<form:errors path="username"></form:errors>
			<br>
			
			<form:label path="password">Password:</form:label><span>*</span>
			<form:input path="password" type="password"/> 
			<form:errors path="password"></form:errors>
			<br>
			
			<form:label path="email">email:</form:label><span>*</span>
			<form:input path="email" type="email"/>
			<form:errors path="email"></form:errors>
			<br>
		
			<input type="submit" value="Join the App!"/>
		</form:form>
	</div>
	
	<c:if test="${registerSuccessMsg != null}">
		<h2>registerSuccessMsg!!</h2>
	</c:if>
	
	
</body>
</html>