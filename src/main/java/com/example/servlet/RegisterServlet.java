package com.example.servlet;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:postgresql://172.18.0.1:5432/loginapp_db";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "redhat123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Load the PostgreSQL driver (optional for modern JDBC)
            Class.forName("org.postgresql.Driver");

            // Establish the connection to the database
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to insert user details
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the SQL query
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                // Redirect to login page if registration is successful
                response.sendRedirect("login.jsp");
            } else {
                // Redirect back to registration page with error if registration fails
                response.sendRedirect("register.jsp?error=true");
            }

            // Close resources
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection problem", e);
        }
    }
}

