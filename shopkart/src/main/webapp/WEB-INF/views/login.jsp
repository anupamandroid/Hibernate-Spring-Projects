<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ShopKart - ${title}</title>

	<script type="text/javascript">
		window.menu = '${title}';
		window.contextRoot = '${contextRoot}';
	</script>
	
    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">

	<!-- Bootstrap Solar Theme -->
	<%-- <link href="${css}/bootstrap-spacelab-theme.css" rel="stylesheet"> --%>
	
	<!-- DataTable Bootstrap CSS -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="${css}/homepage.css" rel="stylesheet">

  </head>

  <body>

	<div class="wrapper">
	    <!-- Navigation -->
	    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	      <div class="container">
	        <a class="navbar-brand" href="${contextRoot}/home">ShopKart</a>
	        
	        <%-- <div class="collapse navbar-collapse" id="navbarResponsive">
	          <ul class="navbar-nav ml-auto">
	            <!-- <li class="nav-item active">
	              <a class="nav-link" href="#">Home
	                <span class="sr-only">(current)</span>
	              </a>
	            </li> -->
	            <li class="nav-item" id="about">
	              <a class="nav-link" href="${contextRoot}/about">About</a>
	            </li>
	            <li class="nav-item" id="listProducts">
	              <a class="nav-link" href="${contextRoot}/show/all/products">View Products</a>
	            </li>
	            <li class="nav-item" id="manangeProducts">
	              <a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a>
	            </li>
	            <li class="nav-item" id="contact">
	              <a class="nav-link" href="${contextRoot}/contact">Contact</a>
	            </li>
	          </ul>
	          
	          <ul class="nav navbar-nav navbar-right">
	          	<li class="nav-item" id="register">
	              <a class="nav-link" href="${contextRoot}/register">Sign Up</a>
	            </li>
	            <li class="nav-item" id="login">
	              <a class="nav-link" href="${contextRoot}/login">Login</a>
	            </li>
	          </ul>
	        </div> --%>
	      </div>
	    </nav>
	    
	    <!-- Page Content -->
	    <div class="content">
	    	<div class="container">
	    	<!-- If credentials are wrong -->
	    		<c:if test="${not empty message}">
	    			<div class="row">
						<div class="col-xl-offset-2 col-lg-offset-2 col-md-offset-2 col-xl-8 col-lg-8 col-md-8">
							<div class="alert alert-danger">${message}</div>
						</div>
					</div>
	    		</c:if>
	    		
	    	<!-- If user has successfully logged out -->
	    		<c:if test="${not empty logout}">
	    			<div class="row">
						<div class="col-xl-offset-2 col-lg-offset-2 col-md-offset-2 col-xl-8 col-lg-8 col-md-8">
							<div class="alert alert-success">${logout}</div>
						</div>
					</div>
	    		</c:if>
				
				<div class="row">
					<div class="col-xl-offset-2 col-lg-offset-2 col-md-offset-2 col-xl-8 col-lg-8 col-md-8">
						<div class="card card-primary">
							<div class="card-header">
								<h4>Login</h4>
							</div>
							<div class="card-block">
								<!-- Form Elements -->
								<form class="form-horizontal" id="loginForm" action="${contextRoot}/login" method="POST">
									<div class="form-group">
										<label class="control-label col-md-4" for="username">Enter Email: </label>
										<div class="col-md-8">
											<input type="text" name="username" id="username" placeholder="Enter Username" class="form-control" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-4" for="password">Enter Password: </label>
										<div class="col-md-8">
											<input type="password" name="password" id="password" placeholder="Enter Password" class="form-control" />
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<input type="submit" class="btn btn-primary" value="Login">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										</div>
									</div>
								</form>
							</div>
							<div class="panel-footer">
								<div class="text-right">
									New User - <a href="${contextRoot}/register">Register Here</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	    <!-- Footer -->
	     <%@ include file="./shared/footer.jsp" %>
	    
	    <!-- JQuery --> 
		<script src="${js}/jquery.min.js"></script>
	    <script src="${js}/jquery.validate.js"></script>
	    
	    <!-- Popper Script -->
	    <script src="${js}/popper.min.js"></script>
	    
	    <!-- Bootstrap core JavaScript -->
	    <script src="${js}/bootstrap.min.js"></script>
	    
		<!-- Custom JavaScript -->
		<script src="${js}/shopkart.js"></script>
	
	</div>
  </body>

</html>