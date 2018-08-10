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
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">

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
	    <%@ include file="./shared/navbar.jsp" %>
	    
	    <!-- Page Content -->
	    <div class="content">
	    
		    <!-- Load Only if User Clicks On Title -->
		    <c:if test="${userClickHome == true}">
		    	<%@ include file="home.jsp" %>
			</c:if>
			
			<!-- Load Only if User Clicks On About -->
			<c:if test="${userClickAbout == true}">
		    	<%@ include file="about.jsp" %>
			</c:if>
			
			<!-- Load Only if User Clicks On Contact -->
			<c:if test="${userClickContact == true}">
		    	<%@ include file="contact.jsp" %>
			</c:if>
			
			<!-- Load Only if User Clicks On View Products or a Particular Category -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
		    	<%@ include file="listProducts.jsp" %>
			</c:if>
			
			<!-- Load Only if User Clicks On Manage Products -->
			<c:if test="${userCickManageProducts == true}">
		    	<%@ include file="manageProducts.jsp" %>
			</c:if>
			
			<!-- Load Only if User Clicks on a Particular Product -->
			<c:if test="${userClickShowProduct == true}">
		    	<%@ include file="singleProduct.jsp" %>
			</c:if>
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
		
		<!-- Bootbox Script -->
	    <script src="${js}/bootbox.min.js"></script>
	    
		<!-- Custom JavaScript -->
		<script src="${js}/shopkart.js"></script>
	
	</div>
  </body>

</html>