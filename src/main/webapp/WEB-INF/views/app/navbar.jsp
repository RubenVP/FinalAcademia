<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                 <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
			        <a class="navbar-brand" href="/SpringSecurityDatabase/index">Academy App</a>
			    </sec:authorize>
			    <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			        <a class="navbar-brand" href="/SpringSecurityDatabase/forum">Academy App</a>
			    </sec:authorize>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<sec:authorize access="hasRole('ROLE_USER')">
	                    <li>
	                        <a href="/SpringSecurityDatabase/forum/askQuestion">Ask a Question</a>
	                    </li>
	                    <li>
	                        <a href="/SpringSecurityDatabase/forum/recentQuestions">Profile<span class="badge"><c:out value="${numberMessages}"></c:out></span></a>
	                    </li>
	                </sec:authorize>
	                 <sec:authorize access="hasRole('ROLE_ADMIN')">
	                    <li>
	                        <a href="/SpringSecurityDatabase/forum/inbox">Unanswered Questions</a>
	                    </li>
	                </sec:authorize>
	            </ul>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                    	<li>
                    		<a class="navbar-brand" href="#">
						        <img alt="Brand" src="http://placehold.it/64x64" class="brand-img">
						      </a>
                    	</li>
	                    <li>
	                        <p class="logged-user"><sec:authentication property="principal.username"/></p>
	                    </li>
	                    <li>
	                        <a class="btn btn-default btn-xs" href="<c:url value='/j_spring_security_logout' />">Log Out</a>
	                    </li>
	                </sec:authorize>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>