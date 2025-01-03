package bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/deleteBook")
public class DeleteBooksServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = new DBConnectionManager().openConnection()) {
            String sql = "DELETE FROM Books WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                int rowsAffected = preparedStatement.executeUpdate();

                response.setContentType("text/html");
                try (PrintWriter out = response.getWriter()) {
                    if (rowsAffected > 0) {
                        out.println("<h3>Book Deleted Successfully!</h3>");
                    } else {
                        out.println("<h3>Book Not Found!</h3>");
                    }
                    out.println("<a href=\"index.html\">Return to Homepagek</a>");
                }
            }
        } catch (Exception e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }
}
