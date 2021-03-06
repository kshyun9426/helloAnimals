<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello, Animal!</title>

<link rel="stylesheet" 
	 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	 	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
	 	crossorigin="anonymous">
<link rel="stylesheet" 
		href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
		integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" 
		crossorigin="anonymous">
	<style>
    	img#MOVE_TOP_BTN {
    		position: fixed;
    		right: 2%;
    		bottom: 50px;
    		display: none;
    		z-index: 999;
		}
    </style>
	
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
		crossorigin="anonymous"
		type="text/javascript"></script>  -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" 
		crossorigin="anonymous"
		type="text/javascript"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" 
		crossorigin="anonymous"
		type="text/javascript"></script>
	
	<!-- 위로가기 버튼 -->
    <img src="/helloanimal/resources/img/top.png" class="rounded-circle" id="MOVE_TOP_BTN"></img>
			
	<!-- navigation bar -->
	<nav class="navbar navbar-expand-md navbar-dark bg-primary">
    	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
        	<span class="navbar-toggler-icon"></span>
    	</button>
    	<div class="navbar-collapse collapse" id="collapsingNavbar">
        	<ul class="navbar-nav">
            	<li class="nav-item active">
                	<a class="navbar-brand abs" href="/helloanimal/main/page"><span class="mb-0 h1">Home</span></a>
            	</li>
            	<li class="nav-item">
                	<a class="navbar-brand abs" href="/helloanimal/community/list"><span class="mb-0 h1"><small>Community</small></span></a>
            	</li>
        	</ul>
        	<ul class="navbar-nav ml-auto">
            	<li class="nav-item">
                	Hello, Animals!!
            	</li>
        	</ul>
    	</div>
	</nav>
	
	
	<script>
    $(function() {
	    $(window).scroll(function() {
	        if ($(this).scrollTop() > 300) {
	            $('#MOVE_TOP_BTN').fadeIn();
	        } else {
	            $('#MOVE_TOP_BTN').fadeOut();
	        }
	    });
	    
	    $("#MOVE_TOP_BTN").click(function() {
	        $('html, body').animate({
	            scrollTop : 0
	        }, 400);
	        return false;
	    });
	});
    </script>