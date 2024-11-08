package jdbc_connection_lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/StudentsDB"; // Replace with your database name
        String username = "root"; // Replace with your MySQL username
        String password = "SAMABILLIONAIRE"; // Replace with your MySQL password

        try {
            // Load the driver class
            Class.forName(driver);
            
            // Establish the connection
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully!");

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
