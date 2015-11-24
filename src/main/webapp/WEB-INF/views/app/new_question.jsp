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
	<title>New Question</title>
	
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
        
	        <c:if test="${not empty errorMsg}">
				<div class="alert alert-danger" role="alert">Sorry! there is a problem with the question.</div>
			</c:if>
			
			<c:if test="${not empty successMsg}">
				<div class="alert alert-success" role="alert">Question has been asked successfully!</div>
			</c:if>
			
            <!-- Blog Post Content Column -->
            <div class="col-lg-8">
            
                <!-- Comments Form -->
                <div class="well">
                    <h2>Ask a question:</h2>
                    <form:form method="post" action="/SpringSecurityDatabase/forum/askQuestion" modelAttribute="question">

						<div class="form-group">
							<label for="cat">Category</label>
							<select name="cat" class="form-control">
								<option value="PR">Product</option>
								<option value="SC">Service</option>
								<option value="AP">App</option>
								<option value="SC">Schedule</option>
								<option value="DE">Departments</option>
								<option value="OT">other</option>
							</select>
						 </div>
						
						<div class="form-group">
							<form:label path="title">Title</form:label>
							<form:input path="title" class="form-control" maxlength="255"/>
							<form:errors path="title" role="alert"></form:errors>
						</div>
						
						<div class="form-group">
							<form:label path="description">Description</form:label>
							<form:textarea path="description" class="form-control" maxlenght="2550"/>
						</div>
						<br>
				
						<input class="btn btn-primary" type="submit" value="Ask Question"/>
					</form:form>
                </div>
                
            </div>
            <!-- /col-lg-8 -->

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
