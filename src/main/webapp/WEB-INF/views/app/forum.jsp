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

					<!-- Angular list -->  
					<tr data-ng-if="questions == null">
						<td colspan="5">
				    		<div class="alert alert-info" role="alert">Sorry there are no results in your search.</div>
				    	</td>
				    </tr>	
				    	
					<tbody data-ng-repeat="question in questions | filter:{category:category} | filter:{title:title}">
						<tr>
					    	<td data-ng-if="question.answered == true" class="text-center">
						    	<h3><span class="glyphicon glyphicon-ok text-success"></span></h3>
					    	</td>
					    	<td data-ng-if="question.answered == false" class="text-center">
					    		<h3><span class="glyphicon glyphicon-remove text-danger"></span></h3>
					    	</td>
					        <td>
					        	<h4><a href="/SpringSecurityDatabase/forum/question?questionId={{question.id}}">{{question.title}}</a></h4>
					        </td>
					        <td class="text-center hidden-xs"><small><i class="fa fa-clock-o"></i>{{question.createDate}}</small></td>
					        <td class="text-center hidden-xs">{{question.category}}</td>
					        <td class="text-center hidden-xs">{{question.userEntity.username}}</td>
					    </tr>
				    </tbody>
					<!-- /Angular list -->  
					
				</table>
			</div>
			<!-- /colmd-8 -->
			
		 	<!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">

                <!-- Blog Search Well -->
                <div class="well">
                    <h4>Blog Search</h4>
                    <!-- <form action="/SpringSecurityDatabase/forum/customSearch" method="post"> -->
	                    <div class="input-group">
	                        <input type="text" data-ng-model="title" name="searchText" class="form-control" maxlength="255" required>
	                        <span class="input-group-btn">
	                            <!-- <button class="btn btn-default" type="submit"> -->
	                            <button>
	                                <span class="glyphicon glyphicon-search"></span>
	                        	</button>
	                        </span>
	                    </div>
	                    <!-- /.input-group -->
	                <!-- </form> -->
                </div>

                <!-- Blog Categories Well -->
                <div class="well">
                    <h4>Blog Categories</h4>
                    
                     <div class="row">
                     	<form>
	                        <div class="col-lg-6">
	                            <ul class="list-unstyled">
	                                <li><input type="radio" data-ng-model="category" name="APP" value="APP"> APP </li>
	                                <li><input type="radio" data-ng-model="category" name="PRODUCT" value="PRODUCT"> PRODUCT </li>
	                                <li><input type="radio" data-ng-model="category" name="SERVICE" value="SERVICE"> SERVICE </li>
	                            </ul>
	                        </div>
	                        <div class="col-lg-6">
	                            <ul class="list-unstyled">
	                                <li><input type="radio" data-ng-model="category" name="SCHEDULE" value="SCHEDULE"> SCHEDULE </li>
	                                <li><input type="radio" data-ng-model="category" name="DEPARTMENTS" value="DEPARTMENTS"> DEPARTMENTS </li>
	                                <li><input type="radio" data-ng-model="category" name="OTHER" value="OTHER"> OTHER </li>
	                            </ul>
	                        </div>
	                        <div class="col-lg-12">
	                            <ul class="list-unstyled">
	                                <li><input type="radio" data-ng-model="category" name="ALL" value=""> ALL CATEGORIES </li>
	           					</ul>
	                        </div>
                        </form>
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
    <script type="text/javascript" src="javascript/jquery-1.11.3.min.js"></script>
    <!-- AngularJs -->
    <script type="text/javascript" src="javascript/angular.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.2/angular-resource.js"></script>
     <script type="text/javascript" src="javascript/custom-angular.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script type="text/javascript" src="javascript/bootstrap.min.js"></script>
    
</body>

</html>
