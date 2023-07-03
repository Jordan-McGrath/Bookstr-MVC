/*
 * MVC Add Controller to handle HTTP Request for adding a book.
 */

package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import models.Book;

@WebServlet({ "/add" })
public class AddController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * doGet to handle the request for the Add book page.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("add-book.jsp").forward(request, response);
	}

	/*
	 * doPost to add book to the database. There is no ID parameter as the ID is
	 * auto. ID 1000 is a place holder and gets replaced with the next available ID.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDAO dao = new BookDAO();

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String genres = request.getParameter("genres");
		String characters = request.getParameter("characters");
		String synopsis = request.getParameter("synopsis");
		Book newBook = new Book(1000, title, author, date, genres, characters, synopsis);
		dao.insertBook(newBook);

		
		ArrayList<Book> books = dao.getAllBooks();
		request.setAttribute("books", books);

		// Forward to the "books.jsp" page
		request.getRequestDispatcher("books.jsp").forward(request, response);
	}
}