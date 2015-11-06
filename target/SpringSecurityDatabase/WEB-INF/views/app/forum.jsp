<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>App Forum For Question</title>
</head>
<body>

	<a href="/SpringSecurityDatabase/forum/recentQuestions"><button>Recent Questions</button></a>
	<a href="/SpringSecurityDatabase/forum/inbox"><button>Inbox</button></a>

	<c:forEach items="${questions}" var="question">
		<p>ID: <c:out value="${question.id}"></c:out></p>
		<p>Date: <c:out value="${question.createDate}"></c:out></p>
		<p>CATEGORY: <c:out value="${question.category}"></c:out></p>
		<p>QUESTION: <c:out value="${question.title}"></c:out></p>
		<p>ANSWERED: <c:out value="${question.answered}"></c:out></p>
		<p>ASKED BY: <c:out value="${question.userEntity.username}"></c:out></p>

		<form action="/SpringSecurityDatabase/forum/question" method="get">
			<input type="hidden" name="id" value="${question.id}">
			<input type="submit" value="Watch Question"/>
		</form>
			
	</c:forEach>
		
	<a href="/SpringSecurityDatabase/forum/askQuestion">Ask a Question</a>
</body>
</html>