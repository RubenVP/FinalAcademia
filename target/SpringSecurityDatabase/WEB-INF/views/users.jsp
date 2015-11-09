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

				<h1>Log In To the App</h1>
			
				<c:if test="${not empty registerSuccessMsg}">
					<div class="alert alert-success" role="alert">${registerSuccessMsg}</div>
				</c:if>
			</div>
			<!-- /.col-md-12 -->
		</div> 
		<!-- /.row -->
	
		<div class="row">
		
			<div class="col-md-4 col-md-offset-4">
			
				<div class="text-center">
			        <div class="btn-group">
			          <a href="/SpringSecurityDatabase/users" role="tab" data-toggle="tab" class="btn btn-primary"><i class="fa fa-plus"></i>Join As New User</a>
			          <a href="/SpringSecurityDatabase/index" role="tab" data-toggle="tab" class="btn btn-danger"><i class="fa fa-user"></i> Log In</a>
			        </div>
			    </div>
			    
					<form:form method="POST" action="registerUser" modelAttribute="user" enctype="multipart/form-data">
						<div class="tab-content">
				    	  <div class="tab-pane fade in active" id="new">
				          <br>
				          <fieldset>
				 	        <div class="form-group">
				              <div class="right-inner-addon">
				                <i class="fa fa-envelope"></i>
				                <form:input path="name" class="form-control input-md" placeholder="First Name" type="text" maxlength="50"></form:input>
				             	<form:errors path="name" class="alert alert-danger" role="alert"></form:errors>
				              </div>
				            </div>
				            <div class="form-group">
				              <div class="right-inner-addon">
				                <i class="fa fa-key"></i>
				                <form:input path="lastName" class="form-control input-md" placeholder="Last Name" type="text" maxlength="50"></form:input>
				              </div>
				            </div>
				            <div class="form-group">
				              <div class="right-inner-addon">
				                <i class="fa fa-envelope"></i>
				                <form:input path="username" class="form-control input-md" placeholder="Username" type="text" maxlength="50"></form:input>
				                <form:errors path="username" class="alert alert-danger" role="alert"></form:errors>
				              </div>
				            </div>
				            <div class="form-group">
				              <div class="right-inner-addon">
				                <i class="fa fa-key"></i>
				                <form:input path="password" class="form-control input-md" placeholder="Password" type="password" maxlength="128"></form:input>
				              	<form:errors path="password" class="alert alert-danger" role="alert"></form:errors>
				              </div>
				            </div>
				            <div class="form-group">
				              <div class="right-inner-addon">
				                <i class="fa fa-envelope"></i>
				                <form:input path="email" class="form-control input-md" placeholder="E-mail" type="email" maxlength="255"></form:input>
				             	<form:errors path="email" class="alert alert-danger" role="alert"></form:errors>
				              </div>
				            </div>
				            <div class="form-group">
				              <div class="right-inner-addon">
					            <form:label for="fileData" path="fileData">File</form:label><br/>
	                    		<form:input path="fileData" type="file"/>
				              </div>
				            </div>
				            <input type="submit" value="Join the App!" class="btn btn-primary btn-md btn-block"/>
				          </fieldset>
				        </div>
					</div>
					
				</form:form>
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