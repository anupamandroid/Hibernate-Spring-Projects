$(function(){
	switch(menu){
	case 'About Us' : 
		$('#about').addClass('active');
		break;
	case 'View Products' : 
		$('#listProducts').addClass('active');
	  	break;
	case 'Manage Products' : 
		$('#manageProducts').addClass('active');
	  	break;
	case 'Contact Us' : 
		$('#contact').addClass('active');
	  	break;
	default : 
		$('#listProducts').addClass('active');
		$('a_'+menu).addClass('active');
	  	break;
	}
	
	//to tackle CSRF Token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		//set token header for ajax request
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(header, token);
		})
	}
	
	
	
	//Code for jQuery DataTable
		
	var $table = $('#productListTable');
	
	//Execute below code for Product List table
	
	if($table.length){
		//console.log("Inside table");
		
		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';

								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;

								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									str += '<a href="'
										+ window.contextRoot
										+ '/cart/add/'
										+ data
										+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									
									if(userRole !== 'ADMIN') {
										if (row.quantity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {
	
											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
									}
									else {
										str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
									}
									
									return str;

								}

							} ]
				});
		}
	
	
	//Dismissing Alert Message after 3 Seconds
	var $alert = $('.alert');
	
	if($alert.length){
		setTimeOut(function(){
			$alert.fadeOut('slow');
		}, 3000)
	}
	
	//--------------------------------
	// Data Table for Admin
	//--------------------------------
	
	// list of all products for admin
	var $productsTable = $('#productsTable');
	
	
	if($productsTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		console.log(jsonUrl);
		
		$productsTable.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'id'},


					           	{data: 'code',
					           	 bSortable: false,
					           		mRender: function(data,type,row) {
					           			return '<img src="' + window.contextRoot
										+ '/resources/images/' + data
										+ '.jpg" class="dataTableImg"/>';					           			
					           		}
					           	},
					           	{
									data : 'name'
								},
								{
									data : 'brand'
								},
								{
									data : 'quantity',
									mRender : function(data, type, row) {

										if (data < 1) {
											return '<span style="color:red">Out of Stock!</span>';
										}

										return data;

									}
								},
								{
									data : 'unitPrice',
									mRender : function(data, type, row) {
										return '&#8377; ' + data
									}
								},
								{
									data : 'active',
									bSortable : false,
									mRender : function(data, type, row) {
										var str = '';
										if(data) {											
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'" checked="checked">  <div class="slider round"> </div></label>';
											
										}else {
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'">  <div class="slider round"> </div></label>';
										}
										
										return str;
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								}					           	
					],
					
					
					initComplete: function () {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change' , function() {							
							var dText = (this.checked)? 'Do you want to activate the Product?': 'Do you want to de-activate the Product?';
							var checked = this.checked;
							var checkbox = $(this);
							debugger;
						    bootbox.confirm({
						    	size: 'medium',
						    	title: 'Product Activation/Deactivation',
						    	message: dText,
						    	callback: function (confirmed) {
							        if (confirmed) {
							            $.ajax({							            	
							            	type: 'GET',
							            	url: window.contextRoot + '/manage/product/'+checkbox.prop('value')+'/activation',
							        		timeout : 100000,
							        		success : function(data) {
							        			bootbox.alert(data);							        										        			
							        		},
							        		error : function(e) {
							        			bootbox.alert('ERROR: '+ e);
							        			//display(e);
							        		}						            	
							            });
							        }
							        else {							        	
							        	checkbox.prop('checked', !checked);
							        }
						    	}
						    });																											
						});
							
					}
				});
	}
	
	//--------------------------------
	
	$('.switch input[type="checkbox"]').on('change', function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked) ? 'Do you want to activate the product?' :
							'Do you want to deactivate the product?';
		var value = checked.prop('value');
		
		bootbox.confirm({
			size: 'medium',
			title: 'Product Activation & Deactivation',
			message: dMsg,
			callback: function(confirmed){
				//if user clicks on OK button
				if(confirmed){
					console.log(value);
					bootbox.alert({
						size: 'medium',
						title: 'Information',
						message: 'You are going to perform operation on ' + value
					});
				}else{
					checkbox.prop('checked', !checked);
				}
			}
		});
	});
	
	
	//Validation Code for Category
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
			rules : {
				name : {
					required: true,
					minlength: 2
				},
				description : {
					required: true
				}
			},
			messages : {
				name : {
					required: 'Please add the category name!',
					minlength: 'The category name should not be less than 2 characters'
				},
				description : {
					required: 'Please add a description for this category!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element){
				//add the class 'help-block'
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
		});
	}
	
	
	
	//Validation Code for Login
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		$loginForm.validate({
			rules : {
				username : {
					required: true,
					email: true
				},
				password : {
					required: true
				}
			},
			messages : {
				username : {
					required: 'Please enter the username!',
					minlength: 'Please enter the valid address!'
				},
				password : {
					required: 'Please enter the password!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element){
				//add the class 'help-block'
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
		});
	}
		
});