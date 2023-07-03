<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
				<li><a style="padding: 0;" href="/Cloud-Enterprise-MVC/"><img
						src="img/bookstrSmall.png" class="logo"></a></li>
				<li class="active"><a href="/Cloud-Enterprise-MVC/books">View
						Books</a></li>
				<li><a href="/Cloud-Enterprise-MVC/add">Add</a></li>
			</ul>
		</div>
	</div>
</nav>

<!--  End of Nav Bar - Search bar for books -->

<body>
	<div class="jumbotron" style="margin-top:-50px;">
		<div class="container text-center">
			<form action="" method="get">
				<input type="hidden" name="action" value="search"> <input
					type="text" class="form-control" id="searchInput"
					name="searchInput"
					placeholder="e.g. 'Harry Potter And The Rusty Trombone'">
				<button type="submit" name="submitSearch" id="searchButton">
					<span class="glyphicon glyphicon-search"></span> Search
				</button>
			</form>
			<form action="books" method="get">
				<input type="submit" value="View all Books">
			</form>
		</div>
	</div>

	<!--  Display all books table -->

	<div class="container text-center">
		<h1>Books</h1>
		<div class="table-responsive">
			<table border="1" class=" table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>Genres</th>
						<th class="text-truncate">Characters</th>
						<th class="text-truncate">Synopsis</th>
						<th>Update<br>Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty books}">
						<p>No books found.</p>
					</c:if>
					<c:forEach var="book" items="${books}">
						<tr>
							<td style="max-width: 150px;"><img
								id="book-cover-${book.id}" src="" alt="Book cover image"
								style="width: 100px;"><br> <br>
								<div style="font-weight: bold;">${book.title}</div>
								<br>
							<div style="color: #008000;">${book.date}</div></td>
							<td style="max-width: 150px;">${book.author}</td>
							<td style="max-width: 150px;">${book.genres}</td>
							<td class="text-truncate expandable" style="max-width: 150px;">${book.characters}</td>
							<td class="text-truncate expandable" style="max-width: 350px;">${book.synopsis}</td>
							<td style="max-width: 30px;"><br>
								<form action="update" method="get">
									<input type="hidden" name="action" value="update"> <input
										type="hidden" name="bookId" value="${book.id}">
									<button value="Update" type="submit">
										<span class="glyphicon glyphicon-edit"></span>
									</button>
								</form> <br>
								<form action="books" method="post">
									<input type="hidden" name="action" value="delete"> <input
										type="hidden" name="id" value="${book.id}">
									<button value="Delete" type="submit"
										onclick="alert('Book Deleted Successfully')">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								</form></td>
						</tr>

						<!--  Script for displaying book covers generated through book titles using Open Library API -->

						<script>
						var apiUrl = "https://openlibrary.org/api/books?bibkeys=title:${fn:escapeXml(book.title)}&format=json&jscmd=data";
						  fetch(apiUrl)
						    .then(response => response.json())
						    .then(data => {
						      var mediumImageUrl = data[`title:${fn:escapeXml(book.title)}`]?.cover?.medium;
						      var imgElement = document.getElementById("book-cover-${book.id}");
						      if (mediumImageUrl) {
						        imgElement.src = mediumImageUrl;
						      } else {
						        imgElement.src = "img/bookthumbnail.png";
						        imgElement.style.width = "150px"; 
						      }
						      imgElement.onerror = null; 
						    })
						    .catch(error => console.error(error));
						</script>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

<!--  Footer -->

<footer class="container-fluid text-center">
	<p>The Book Store by Jordan McGrath</p>
</footer>
</html>