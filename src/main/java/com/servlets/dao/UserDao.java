package com.servlets.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.servlets.model.User;

public class UserDao {
    
    // Register a new user
    public int registerUser(User user) throws SQLException, ClassNotFoundException {
        int result = 0;
        
        
        Class.forName("org.postgresql.Driver");
        
        // Database connection
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, 'Employee')";
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                
                // Execute the query
                result = stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
    

    // Login user
    public User loginUser(User user) throws SQLException, ClassNotFoundException {
        User isValidUser = new User();
        
  
        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        isValidUser.setUsername(rs.getString("username"));
                        isValidUser.setPassword(rs.getString("password"));
                        isValidUser.setRole(rs.getString("role"));
                    }
                }
            }
        }
        return isValidUser;
    }
}
