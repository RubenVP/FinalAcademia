<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>App Forum</title>
	
	<!-- Bootstrap Core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/css/custom.css" rel="stylesheet">
    
</head>
<body data-ng-app="AcademyApp" data-ng-controller="AcademyAppController">

    <!-- Navigation -->
    <jsp:include page="navbar.jsp"></jsp:include>
   

    <!-- Page Content -->
    <div class="container">

        <div class="row">
       		<div class="col-md-8">
				<table class="table forum table-striped">
					<thead>
				  		<tr>
				  			<th class="cell-stat text-center">Answered</th>
					        <th class="cell-stat-2x">
					        	<h3>Last Questions</h3>
					        </th>
					        <th class="cell-stat text-center hidden-xs">Date</th>
					        <th class="cell-stat text-center hidden-xs">Category</th>
					        <th class="cell-stat-2x text-center hidden-xs">By</th>
				      	</tr>
				    </thead>
				    <tbody>
				    
				    	<c:if test="${empty questions}">
				    		<div class="alert alert-info" role="alert">Sorry there are no results in your search.</div>
				    	</c:if>
				    
				    	<c:forEach items="${questions}" var="question">
						    <tr>
						    	<td class="text-center">
						    		<c:if test="${question.answered eq true}">
							    		<h3><span class="glyphicon glyphicon-ok text-success"></span></h3>
						    		</c:if>
						    		<c:if test="${question.answered eq false}">
						    			<h3><span class="glyphicon glyphicon-remove text-danger"></span></h3>
						    		</c:if>
						    	</td>
						        <td>
						        	<h4><a href="/SpringSecurityDatabase/forum/question?questionId=${question.id}">${question.title}</a></h4>
						        </td>
						        <td class="text-center hidden-xs"><small><i class="fa fa-clock-o"></i>${question.createDate}</small></td>
						        <td class="text-center hidden-xs">${question.category}</td>
						        <td class="text-center hidden-xs">${question.userEntity.username}</td>
						    </tr>
						</c:forEach>
						
				    </tbody>
				</table>
			</div>
			<!-- /colmd-8 -->
			
		 	<!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">

                <!-- Blog Search Well -->
                <div class="well">
                    <h4>Blog Search</h4>
                    <form action="/SpringSecurityDatabase/forum/customSearch" method="post">
	                    <div class="input-group">
	                        <input type="text" name="searchText" class="form-control" maxlength="255" required>
	                        <span class="input-group-btn">
	                            <button class="btn btn-default" type="submit">
	                                <span class="glyphicon glyphicon-search"></span>
	                        	</button>
	                        </span>
	                    </div>
	                    <!-- /.input-group -->
	                </form>
                </div>

                <!-- Blog Categories Well -->
                <div class="well">
                    <h4>Blog Categories</h4>
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                <li><a href="/SpringSecurityDatabase/forum/findByCategory?category=APP">APP</a>
                                </li>
                                <li><a href="/SpringSecurityDatabase/forum/findByCategory?category=PRODUCT">PRODUCT</a>
                                </li>
                                <li><a href="/SpringSecurityDatabase/forum/findByCategory?category=SERVICE">SERVICE</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                <li><a href="/SpringSecurityDatabase/forum/findByCategory?category=SCHEDULE">SCHEDULE</a>
                                </li>
                                <li><a href="/SpringSecurityDatabase/forum/findByCategory?category=DEPARTMENTS">DEPARTMENTS</a>
                                </li>
                                <li><a href="/SpringSecurityDatabase/forum/findByCategory?category=OTHER">OTHER</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-12">
                            <ul class="list-unstyled">
                                <li><a href="/SpringSecurityDatabase/forum">ALL CATEGORIES</a>
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
