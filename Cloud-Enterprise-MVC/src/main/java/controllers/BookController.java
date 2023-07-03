/*
 * Display and search book MVC Controller to handle HTTP Request.
 * This also handles the request to delete
 */

package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import models.Book;

@WebServlet({ "/books", "/search" })
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * doGet to retrieve all books for searched books depending on the request
	 * parameter
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		
		// Search books or retrieve books from search parameters  
		String searchInput = request.getParameter("searchInput");
		if (searchInput != null && !searchInput.isEmpty()) {
			ArrayList<Book> books = dao.searchBooks(searchInput);
			request.setAttribute("books", books);
			request.getRequestDispatcher("books.jsp").forward(request, response);
		} else {
			ArrayList<Book> books = dao.getAllBooks();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/books.jsp").forward(request, response);
		}
	}

	/*
	 * doPost method to handle the search input as well as delete book. This uses
	 * switch statements and actions accordingly from its parameters.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		String action = request.getParameter("action");
		switch (action) {
		
		// Delete Method 
		case "delete":
			int Id = Integer.parseInt(request.getParameter("id"));
			try {
				dao.deleteBook(Id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		// Search Method via Title, Author or Synopsis
		case "search":
			String search = request.getParameter("searchInput");
			String sql = "SELECT * FROM books";
			if (search != null && !search.isEmpty()) {
				sql += " WHERE title LIKE '%" + search + "%'" + " OR author LIKE '%" + search + "%'"
						+ " OR synopsis LIKE '%" + search + "%'";
			}
			ArrayList<Book> books = dao.searchBooks(sql);
			if (!books.isEmpty()) {
				request.setAttribute("books", books);
				request.getRequestDispatcher("books.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("books.jsp").forward(request, response);
			}
			break;
		}

		response.sendRedirect(request.getContextPath() + "/books");
	}
}