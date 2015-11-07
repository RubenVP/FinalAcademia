<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>View Question</title>
	
	<!-- Bootstrap Core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/css/custom.css" rel="stylesheet">
    
</head>
<body>

    <!-- Navigation -->
    <jsp:include page="navbar.jsp"></jsp:include>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->
			
                <!-- Title -->
                <h1>${question.title}</h1>

                <!-- Author -->
                <p class="lead">
                    <img class="media-object" height="64" width="64" src="${question.userEntity.imageUrl}" alt="${question.userEntity.username}"><span>by:  ${question.userEntity.username}</span>
                </p>

                <hr>

                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Posted on ${question.createDate}</p>

                <hr>

                <!-- Post Content -->
                <p class="lead">${question.description}</p>
                
                <p>ID: <c:out value="${question.id}"></c:out></p>
				<p>CATEGORY: <c:out value="${question.category}"></c:out></p>
				<p>ANSWERED: <c:out value="${question.answered}"></c:out></p>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a class="btn btn-danger" href="/SpringSecurityDatabase/forum/deleteQuestion?questionId=${question.id}">Delete Question</a>
				</sec:authorize>
				
                <hr>

                <!-- Blog Comments -->
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
	                <!-- Comments Form -->
	                <div class="well">
	                    <h4>Answer:</h4>
	                    <form:form method="post" action="/SpringSecurityDatabase/forum/addAnswer?questionId=${question.id}" modelAttribute="comment" role="form">
	
							<div class="form-group">
								<form:textarea path="description" class="form-control" rows="3"  maxlenght="2550"/>
							 </div>
					
							<input class="btn btn-primary" type="submit" value="Send Answer"/>
						</form:form>
	                </div>
	
	                <hr>
	            </sec:authorize>

                <!-- Posted Comments -->
                <!-- Comment -->
                <c:if test="${not empty commentsList}">
	
					<c:forEach items="${commentsList}" var="comment">
		                <div class="media">
		                    <a class="pull-left" href="#">
		                        <img class="media-object" height="64" width="64" src="${comment.userEntity.imageUrl}" alt="${comment.userEntity.username}">
		                    </a>
		                    <div class="media-body">
		                        <h4 class="media-heading">${comment.userEntity.username}
		                            <small>${comment.answerDate}</small>
		                        </h4>
		                        ${comment.description}
		                    </div>
		                </div>
		                
		                <c:if test="${comment.helpful != true}">
		                <!--  
		                	<div>Was this answer helpful?<a class="btn btn-success" href="/SpringSecurityDatabase/forum/answerHelpful?commnetId=${comment.description}&question=${question.id}">yes!</a></div>
						-->
						</c:if>
					</c:forEach>
		
				</c:if>
            </div>
            <!-- /col-lg-8 -->

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">

                <!-- Blog Search Well -->
                <div class="well">
                    <h4>Blog Search</h4>
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                        </button>
                        </span>
                    </div>
                    <!-- /.input-group -->
                </div>

                <!-- Blog Categories Well -->
                <div class="well">
                    <h4>Blog Categories</h4>
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.row -->
                </div>
            </div>

        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Academy App 2015</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="resourses/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resourses/js/bootstrap.min.js"></script>

</body>

</html>
