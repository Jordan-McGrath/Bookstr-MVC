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
				<li class="active"><a href="/Cloud-Enterprise-MVC/add">Add</a></li>
			</ul>
		</div>
	</div>
</nav>

<!--  End of Nav Bar - Main Body -->

<body>

	<h1 class="container text-center" style="text-decoration:underline;">Got a book you want to share?
		Add it to the store!</h1>
	<div class="container">
	<div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
		<h2>Add Book</h2>
		</div></div>
		
		<!--  Add Book Form -->
		
		<form action="add" method="post" class="form-horizontal">
    <input type="hidden" name="action" value="add">
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">Title:</label>
			<div class="col-sm-10">
			<input class="form-control" type="text" id="title" name="title"><br> 
			</div>
			
			<label for="author" class="control-label col-sm-2">Author:</label> 
			<div class="col-sm-10">
			<input class="form-control" type="text" id="author" name="author"><br> 
			</div>
			
			<label for="date" class="control-label col-sm-2">Date:</label>
			<div class="col-sm-10">
			<input class="form-control" type="text" id="date" name="date" placeholder="DD/MM/YYYY"><br>
			</div>
			
			<label for="genres" class="control-label col-sm-2">Genres:</label>
			<div class="col-sm-10">
			<input class="form-control" type="text" id="genres" name="genres"><br> 
			</div>
			
			<label for="characters" class="control-label col-sm-2">Characters:</label>
			<div class="col-sm-10">
			<input class="form-control" type="text" id="characters" name="characters"><br>
			</div>

			<label for="synopsis" class="control-label col-sm-2">Synopsis:</label>
			<div class="col-sm-10">
			<textarea class="form-control" id="synopsis" name="synopsis"></textarea><br>
			</div>
			<div class="form-group"> 
    		<div class="col-sm-offset-2 col-sm-10">
    		<input type="hidden" name="action" value="add">
			<input type="submit" value="Add Book" onclick="alert('Book Successfully Added')">
			<a href='Cloud-Enterprise-MVC/books' class="btn-default"><input type="submit" value="Cancel"></a>
			</div></div>
		</div></form>
	</div>
</body>

<!--  Footer -->

<footer class="container-fluid text-center">
	<p>The Book Store by Jordan McGrath</p>
</footer>

</html>