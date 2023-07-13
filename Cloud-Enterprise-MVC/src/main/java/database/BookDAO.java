package database;

/*
 * Book DAO connected to a mudfoot server to retrieve data. 
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Book;
import functions.nextID;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class BookDAO {

    Book oneBook = null;
	Connection conn = null;
	Statement stmt = null;
	
	/*
	 * Please replace your MySQL login details here. 
	 * Once you have added your login details, you can run the create book schema and add the books. 
	 */
	
    private static String user = "YOUR_USERNAME";
    private static String password = "YOUR_PASSWPRD";
    private static String url = "YOU_MYSQL_DATABASE_URL" + user;

    
	public BookDAO() {}
/*
 * Open Mudfoot database connection
 */
	private void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

	/*
	 * Close connection to the database
	 */
    private void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
/*
 *  Creates a Book object using result set, returning the created book.
 */

	private Book getNextBook(ResultSet rs) {
		Book thisBook = null;
		try {
			thisBook = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("date"),
					rs.getString("genres"), rs.getString("characters"), rs.getString("synopsis"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisBook;
	}
	
	/*
	 * Return all books from the database by executing a select query and creating a list of Book.
	 */

	public ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<Book>();
        openConnection();

        try {
            String selectSQL = "select * from books";
            ResultSet rs = stmt.executeQuery(selectSQL);

            while (rs.next()) {
                oneBook = getNextBook(rs);
                allBooks.add(oneBook);
            }

            rs.close();
        } catch (SQLException se) {
            System.out.println(se);
        } finally {
            closeConnection();
        }

        return allBooks;
    }
	
	/*
	 * Get book by ID retrieving a specific book by its ID from the database 
	 * using a select query and returns the corresponding Book object.
	 */
	
	public Book getBookByID(int id) {

		openConnection();
		oneBook = null;
		
		try {
			String selectSQL = "select * from books where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneBook = getNextBook(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneBook;
	}

	/*
	 * Insert Book method using prepared statements. ID for new book auto populated
	 * with next available id using 'nextID' function.
	 */

	public boolean insertBook(Book b) {
	    boolean success = false;
	    openConnection();
	    try {
	        int nextId = nextID.getNextBookId(conn); // Pass the connection object
	        String sql = "INSERT INTO books (id, title, author, date, genres, characters, synopsis) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nextId);
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getAuthor());
			pstmt.setString(4, b.getDate());
			pstmt.setString(5, b.getGenres());
			pstmt.setString(6, b.getCharacters());
			pstmt.setString(7, b.getSynopsis());
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				success = true;
				System.out.println("Book inserted successfully.");
			}
			pstmt.close();

	    } catch (SQLException e) {
	    	System.out.println("Failed to delete book" + e);
	        
	    } finally {
	        closeConnection();
	    }
	    return success;
	}
	
	/*
	 * Update book method using prepared statements
	 */

	public boolean updateBook(Book b) {
		boolean success = false;
		openConnection();
		try {
			String query = "UPDATE books SET id = ?, title = ?, author = ?, date = ?, genres = ?, characters = ?, synopsis = ? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getId());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getAuthor());
			pstmt.setString(4, b.getDate());
			pstmt.setString(5, b.getGenres());
			pstmt.setString(6, b.getCharacters());
			pstmt.setString(7, b.getSynopsis());
			pstmt.setInt(8, b.getId());
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				success = true;
				System.out.println("Book updated successfully.");
			}
			pstmt.close();

			// Create a catch and close connection
		} catch (SQLException e) {
			System.out.println("Failed to update book" + e);
		} finally {
			closeConnection();
		}
		return success;
	}

	/*
	 * Delete book method, executing a delete query by ID.
	 */

	public boolean deleteBook(int id) throws SQLException {
		boolean success = false;
		try {
			openConnection();
			String sql = "DELETE FROM books WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			// Check to see if ID
			int del = stmt.executeUpdate();

			// Statement to see if book has been deleted or print else statements if not

			if (del > 0) {
				System.out.println("Book deleted successfully.");
				success = true;
			} else {
				System.out.println("No book found with ID " + id + ".");
			}
			closeConnection();
		} catch (SQLException e) {
			throw new SQLException("Failed to delete book.");
		}
		return success;
	}

	/*
	 * General search method using prepared statements executing a select query to 
	 * return books by Title, Author or Synopsis
	 */

	public ArrayList<Book> searchBooks(String keyword) {
		ArrayList<Book> matchingBooks = new ArrayList<Book>();
		openConnection();
		try {
			String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR synopsis LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				matchingBooks.add(getNextBook(rs));
				System.out.println("Returning searched books.");
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Failed to search book" + e);
		} finally {
			closeConnection();
		}
		return matchingBooks;
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}
