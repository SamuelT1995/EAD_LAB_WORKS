package bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchBooksServlet {
	  @WebServlet("/searchBooks")
      public class SearchTaskServlet extends HttpServlet {
          private DBConnectionManager dbManager = new DBConnectionManager();

          protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
              String query = request.getParameter("description");

              try (Connection connection = dbManager.openConnection();
                   PreparedStatement ps = connection.prepareStatement("SELECT * FROM Tasks WHERE description LIKE ?")) {
                  ps.setString(1, "%" + query + "%");
                  ResultSet rs = ps.executeQuery();
                  PrintWriter writer = response.getWriter();
                  while (rs.next()) {
                      writer.println("Task: " + rs.getString("description"));
                  }
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
      }
}
