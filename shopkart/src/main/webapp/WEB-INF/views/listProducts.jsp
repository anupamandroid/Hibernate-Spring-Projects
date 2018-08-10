<div class="container">
	<div class="row">
	
		<!-- To display Sidebar -->
		<div class="col-lg-3 col-md-3">
			<%@ include file="./shared/sidebar.jsp" %>
		</div>
		
		<!-- To display the actual products -->
		<div class="col-lg-9 col-md-9">
			
			<!-- Breadcrumb component -->
			<div class="row">
				<div class="col-lg-12 col-md-12">
					<c:if test="${userClickAllProducts == true}">
						<script>
							window.categoryId = '';
						</script>
						
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>
					
					<c:if test="${userClickCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>
						
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
		
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="container-fluid">
						<div class="table-responsive">
							<table id="productListTable" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Image</th>
										<th>Name</th>
										<th>Brand</th>
										<th>Price</th>
										<th>Qty. Available</th>
										<th>Options</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>