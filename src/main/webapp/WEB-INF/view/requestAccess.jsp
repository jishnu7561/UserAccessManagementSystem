<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% List softwareList = (List)session.getAttribute("softwareList");%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
        	font-size: 24px;
        	margin-bottom: 20px;
        	color: #333;
        }

        .form-group {
            margin-bottom: 15px;
            margin-right: 15px;
        }

        label {
            display: block;
        	font-size: 16px;
        	margin-bottom: 8px;
        	color: #555;
        }

        select, textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            
        }

        textarea {
            resize: vertical;
        }
        
		textarea:focus, select:focus {
    		border-color: black;
    		outline: none;
		}


        button {
        	width: 100%;
        	padding: 12px;
        	background-color: #117bed;
        	border: none;
        	color: white;
        	font-size: 16px;
        	border-radius: 4px;
        	cursor: pointer;
        	transition: background-color 0.3s;
    	}

        button:hover {
            background: #0056b3;
        }
        </style>
</head>
<body>
	<div class="container">
        <h2>Request Access</h2>

        <form action="<%= request.getContextPath() %>/request-access" method="post">
            <!-- Software Name Dropdown -->
            <div class="form-group">
                <label for="softwareName">Software Name:</label>
                <select id="softwareName" name="softwareId" class="form-control" required>
                    <option value="" disabled selected>Select Software</option>
                    <!-- Dynamically populated options -->
                    
                    <c:forEach items="${softwareList}" var="software">
    					<option value="${software.id}">${software.softwareName}</option>
					</c:forEach>
                </select>
            </div>

            <!-- Access Type Dropdown -->
            <div class="form-group">
                <label for="accessType">Access Type:</label>
                <select id="accessType" name="accessType" class="form-control" required>
                    <option value="Read">Read</option>
                    <option value="Write">Write</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>

            <!-- Reason for Request -->
            <div class="form-group">
                <label for="reason">Reason for Request:</label>
                <textarea id="reason" name="reason" class="form-control" rows="4" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit Request</button>
        </form>
    </div>
</body>
</html>