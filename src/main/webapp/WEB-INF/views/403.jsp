<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>App Add User</title>
	
	 <!-- Bootstrap Core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/css/custom.css" rel="stylesheet">
	
</head>
<body>

 	<!-- Navigation -->
    <jsp:include page="app/navbar.jsp"></jsp:include>

    <!-- Page Content -->
    <div class="container">

        <div class="row">
        
			<div class="col-md-12 text-center">
				
				<div class="alert alert-danger" role="alert">
					<h1>Sorry.. you don't have access to this page.</h1>
				</div>
				
				<img alt="Access Denied: You Didnt Said The Magic Word" src="/SpringSecurityDatabase/resources/img/accessDenied.jpg">
			
				<br><br>
	
				<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
			        <a class="btn btn-lg btn-primary" href="/SpringSecurityDatabase/index">Back to Log In</a>
			    </sec:authorize>
			    <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			        <a class="btn btn-lg btn-primary" href="/SpringSecurityDatabase/forum">Back Forum</a>
			    </sec:authorize>
				 
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
    <script src="resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/js/bootstrap.min.js"></script>

</body>
</html>