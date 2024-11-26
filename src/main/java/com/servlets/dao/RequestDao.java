package com.servlets.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.servlets.model.Requests;
import com.servlets.model.Software;

public class RequestDao {

	
	public int addRequest (Requests request) throws SQLException, ClassNotFoundException {

		int result =0;
		
        Class.forName("org.postgresql.Driver");
        
        // Database connection
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

            String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, request.getUserId());
                stmt.setInt(2, request.getSoftwareId());
                stmt.setString(3, request.getAccessType());
                stmt.setString(4, request.getReason());
                stmt.setString(5, request.getStatus());
                
                // Execute the query
                result = stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
	}
	
	public List<RequestDetails> getALlRequests () throws SQLException, ClassNotFoundException {

		List<RequestDetails> requestDetailsList = new ArrayList<>();
	    String query = "SELECT r.id AS request_id, u.username AS employee_name, s.name AS software_name, " +
	                 "r.access_type, r.reason, r.status " +
	                 "FROM requests r " +
	                 "JOIN users u ON r.user_id = u.id " +
	                 "JOIN software s ON r.software_id = s.id " +
	                 "WHERE r.status = 'Pending'";
		
        Class.forName("org.postgresql.Driver");
        
        // Database connection
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

            
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                
                ResultSet resultSet = stmt.executeQuery();
                
                
                while (resultSet.next()) {
                    Requests request = new Requests();
                    System.out.println("name: "+resultSet.getString("userName")+"   "+"softwareName: "+resultSet.getString("softwareName"));
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return requestDetailsList;
	}
	
	public boolean approveRequest(String requestId) throws SQLException, ClassNotFoundException {
		
		boolean result = false;
       
		 Class.forName("org.postgresql.Driver");
	        
	        // Database connection
	        try (Connection connection = DriverManager.getConnection(
	                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

	            String query = "UPDATE requests SET status = ? WHERE request_id = ?";
	            
	            try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            	stmt.setString(1, "Approved");
	                stmt.setString(2, requestId);
	                
	                int approved = stmt.executeUpdate();
	                
	                if (approved > 0) {
	                    result = true;
	                }
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	        }

        return result; 
    }

    public boolean rejectRequest(String requestId) throws SQLException, ClassNotFoundException {
       
    	boolean result = false;
        
		 Class.forName("org.postgresql.Driver");
	        
	        // Database connection
	        try (Connection connection = DriverManager.getConnection(
	                "jdbc:postgresql://localhost:5432/user_access_management", "postgres", "@springboot123")) {

	            String query = "UPDATE requests SET status = ? WHERE request_id = ?";
	            
	            try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            	stmt.setString(1, "Reject");
	                stmt.setString(2, requestId);
	                
	                int approved = stmt.executeUpdate();
	                
	                if (approved > 0) {
	                    result = true;
	                }
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	        }

       return result; 
    }
	
}
