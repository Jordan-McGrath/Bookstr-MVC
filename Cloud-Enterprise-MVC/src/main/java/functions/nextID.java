package functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * The nextID class provides a method, getNextBookId, 
 * that retrieves the next available book ID from a database table 
 * by querying the existing IDs and finding the first available ID.
 */

public class nextID {

    public static int getNextBookId(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM books");
        ArrayList<Integer> ids = new ArrayList<Integer>();
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }

        // Find the next available book ID
        int nextId = 1;
        while (ids.contains(nextId)) {
            nextId++;
        }

        return nextId;

	}
}