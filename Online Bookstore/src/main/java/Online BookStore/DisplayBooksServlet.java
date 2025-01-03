package bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>Book List</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Published Date</th><th>Actions</th></tr>");

        try (Connection connection = new DBConnectionManager().openConnection()) {
            String sql = "SELECT * FROM Books";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String publishedDate = resultSet.getDate("published_date").toString();

                    // Display task details
                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    out.println("<td>" + title + "</td>");
                    out.println("<td>" + author + "</td>");
                    out.println("<td>" + publishedDate + "</td>");

                    // Add a delete form
                    out.println("<td>");
                    out.println("<form action='deleteBook' method='POST'>");
                    out.println("<input type='hidden' name='id' value='" + id + "'>");
                    out.println("<button type='submit'>Delete</button>");
                    out.println("</form>");
                    out.println("</td>");

                    out.println("</tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='5'>Error loading tasks.</td></tr>");
        }

        out.println("</table>");
    }
}
