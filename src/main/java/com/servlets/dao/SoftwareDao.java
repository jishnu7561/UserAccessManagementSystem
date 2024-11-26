package com.servlets.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.servlets.model.Software;

public class SoftwareDao {
	
	
	// create new software
	public int createSoftware (Software software) throws SQLException, ClassNotFoundException {

		int result =0;
		
        Class.forName("org.postgresql.Driver");
        
        // Database connection
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

            String query = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, software.getSoftwareName());
                stmt.setString(2, software.getDescription());
                stmt.setString(3, software.getAccessLevels());
                
                // Execute the query
                result = stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
	}
	
	
	// fetch all software details
	public List<Software> getAllSoftware() {
        List<Software> softwareList = new ArrayList();

        String query = "SELECT * FROM software";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123");
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet result = preparedStatement.executeQuery()) {

                while (result.next()) {
                    Software software = new Software();
                    System.out.println(" 333333333333333333333333333333333333 ");
                    software.setId(result.getInt("id"));
                    software.setSoftwareName(result.getString("name"));
                    software.setDescription(result.getString("description"));
                    software.setAccessLevels(result.getString("access_levels"));

                    softwareList.add(software);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return softwareList;
    }

}
