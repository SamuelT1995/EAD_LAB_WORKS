//Samuel Tenagne Alemu - UGR-8506-14

package bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.DBConnectionManager;

@WebServlet("/registerBook")
public class BookRegistrationServlet extends HttpServlet {
    private final DBConnectionManager dbConnectionManager;
    
 // Constructor for dependency injection
    public BookRegistrationServlet(DBConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");

        try (Connection connection = dbConnectionManager.openConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Books (title, author, price) VALUES (?, ?, ?)")) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, price);
            ps.executeUpdate();
            response.getWriter().write("Book successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Couldn't add book!");
        }
    }
}
