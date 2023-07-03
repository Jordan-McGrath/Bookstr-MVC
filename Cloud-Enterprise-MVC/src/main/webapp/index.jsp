<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>The Book Store</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="scripts/functions.js"></script>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="img/bookstrIcon.png">
</head>

<!-- Title display at the top of the page -->

<div class="jumbotron">
	<div class="container text-center">
		<h1>BOOKSTR</h1>
		<p>the book store</p>
	</div>
</div>

<!--  Nav Bar - Responsive menu though Bootstrap -->

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a style="padding: 0;"
					href="/Cloud-Enterprise-MVC/"><img
						src="img/bookstrSmall.png" class="logo"></a></li>
				<li><a href="/Cloud-Enterprise-MVC/books">View Books</a></li>
				<li><a href="/Cloud-Enterprise-MVC/add">Add</a></li>
			</ul>
		</div>
	</div>
</nav>

<!--  End of Nav Bar - Main Body -->

<body>
	<h1 class="container text-center">Welcome to BOOKSTR, the book
		database</h1>
	<div class="container">
		<img src="images/imagecover.png" class="img-responsive" style="height: 500px;" alt="Bookstr Books">
	</div>
</body>

<!--  Footer -->

<footer class="container-fluid text-center">
	<p>The Book Store by Jordan McGrath</p>
</footer>

</html>