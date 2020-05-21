<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="/resources/css/style.css">
<script src="webjars/jquery/2.1.4/jquery.min.js"></script>
<link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="/resources/js/app.js"></script> -->
</head>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/home">Shop-o-Cart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="/cart">Cart</a></li>
				<li class="nav-item"><a class="nav-link" href="/orders">My Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
				
			</ul>
		</div>
	</nav>
	<br>
</html>