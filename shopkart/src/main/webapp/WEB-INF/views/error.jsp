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
	    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      		<div class="container">
        		<a class="navbar-brand" href="${contextRoot}/home">ShopKart</a>
        	</div>
        </nav>
        
        <div class="content">
        	<div class="container">
        		<div class="row">
        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        				<div class="jumbotron">
        					<h1>${errorTitle}</h1>
        					<hr>
        					<blockquote>
        						${errorDescription}
        					</blockquote>
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
	    
	    <!-- Data Table Plugin --> 
		<script src="${js}/jquery.dataTables.js"></script>
	
		<!-- DataTable Bootstrap Plugin --> 
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- Custom JavaScript -->
		<script src="${js}/shopkart.js"></script>
	
	</div>
  </body>

</html>