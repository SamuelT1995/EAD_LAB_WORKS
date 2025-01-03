package bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component 
public class DBConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/BookstoreDB";
    private static final String USER = "root";
    private static final String PASSWORD = "sql123";

    public Connection openConnection() throws SQLException {
        try {
            // Explicitly load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
