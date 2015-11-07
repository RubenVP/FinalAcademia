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
	<title>App Forum</title>
	
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
        
			<div class="col-lg-12">
  					
				<form action="/SpringSecurityDatabase/forum/formInbox" method="post" class="form-inline">
					<div class="form-group">
						<label for="state">Select:</label>
						<select name="state" class="form-control">
							<option value="unanswered">Unanswered</option>
							<option value="answered">Answered</option>
						</select>
					</div>
					<input type="submit" class="btn btn-info form-control" value="Whatch Questions"/>
				</form>
			
				<table class="table forum table-striped">
					<thead>
				  		<tr>
				  			<th class="cell-stat text-center">Answered</th>
					        <th class="cell-stat-2x">
					        	<h3>Answered/unanswered Questions</h3>
					        </th>
					        <th class="cell-stat text-center hidden-xs">Date</th>
					        <th class="cell-stat text-center hidden-xs">Category</th>
					        <th class="cell-stat-2x text-center hidden-xs">By</th>
				      	</tr>
				    </thead>
				    <tbody>
				    
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
