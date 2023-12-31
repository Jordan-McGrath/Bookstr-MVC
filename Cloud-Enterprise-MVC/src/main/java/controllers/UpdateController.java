/*
 * MVC Update Controller to handle HTTP Request for update Method, handling doGet and doPost.
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

@WebServlet("/update")
public class UpdateController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /*
     * doGet to populate input fields with book data from the given ID
     * This uses getBookById to populate fields.
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDAO dao = new BookDAO();
        Book book = dao.getBookByID(bookId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    /*
     * doPost to update book from the given id generated by the doPost. 
     * Forwards the request on to the main books page.
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String date = request.getParameter("date");
        String genres = request.getParameter("genres");
        String characters = request.getParameter("characters");
        String synopsis = request.getParameter("synopsis");
        Book updatedBook = new Book(id, title, author, date, genres, characters, synopsis);

        BookDAO dao = new BookDAO();
        dao.updateBook(updatedBook);

        // Fetch the updated book list
        ArrayList<Book> books = dao.getAllBooks();
        request.setAttribute("books", books);
     
        // Forward to the "books.jsp" page
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }
}