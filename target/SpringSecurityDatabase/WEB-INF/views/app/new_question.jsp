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

	<form:form method="post" action="/SpringSecurityDatabase/forum/askQuestion" modelAttribute="question">
		<form:label path="category">Category</form:label>
		<form:select path="category">
			<form:option value="product"></form:option>
			<form:option value="service"></form:option>
			<form:option value="app"></form:option>
			<form:option value="schedule"></form:option>
			<form:option value="departments"></form:option>
			<form:option value="other"></form:option>
		</form:select>
		
		<form:label path="title">Title</form:label>
		<form:input path="title"/>
		<form:errors path="title"></form:errors>
		<br>
		
		<form:label path="description">Description</form:label>
		<form:textarea path="description"/>
		<br>

		<input type="submit" value="Ask Question"/>
	</form:form>
	
	<c:if test="${successMsg != null}">
		<h2>Question has been asked successfully!</h2>
	</c:if>
	
	<c:if test="${errorMsg != null}">
		<h2>Question has been asked successfully!</h2>
	</c:if>
	
</body>
</html>