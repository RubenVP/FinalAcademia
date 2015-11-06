<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Question New</title>
</head>
<body>

	<p>ID: <c:out value="${question.id}"></c:out></p>
	<p>Date: <c:out value="${question.createDate}"></c:out></p>
	<p>CATEGORY: <c:out value="${question.category}"></c:out></p>
	<p>QUESTION: <c:out value="${question.title}"></c:out></p>
	<p>DESCRIPTION: <c:out value="${question.description}"></c:out></p>
	<p>ANSWERED: <c:out value="${question.answered}"></c:out></p>
	<p>ASKED BY: <c:out value="${question.userEntity.username}"></c:out></p>

	<form action="/SpringSecurityDatabase/forum/deleteQuestion" method="post">
		<input type="hidden" name="id" value="${question.id}">
		<input type="submit" value="Delete Question"/>
	</form>
	
	<form action="/SpringSecurityDatabase/forum/updateQuestionAnswered" method="post">
		<input type="hidden" name="id" value="${question.id}">
		<input type="submit" value="Mark Question as Answered"/>
	</form>
	
	<c:if test="${commentsList != null}">
	
		<c:forEach items="${commentsList}" var="comment">
		
			<p>By: <c:out value="${comment.userEntity.username}"></c:out></p>
			<p>Answer: <c:out value="${comment.description}"></c:out></p>
			
		</c:forEach>
		
	</c:if>
	
	<form:form method="post" action="/SpringSecurityDatabase/forum/addAnswer" modelAttribute="comment">

		<form:label path="description">Answer</form:label>
		<form:textarea path="description"/>
		<br>

		<input type="hidden" name="questionId" value="${question.id}" />

		<input type="submit" value="Send Answer"/>
	</form:form>
	
</body>
</html>