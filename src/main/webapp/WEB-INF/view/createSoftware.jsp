<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Software</title>
<style>
    /* General Styling */
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

    input[type="text"], textarea, select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 14px;
    }

    textarea {
        resize: vertical;
    }

    button {
        display: block;
        width: 100%;
        background: #007bff;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
    }

    button:hover {
        background: #0056b3;
    }

    .checkbox-group {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .checkbox-group input {
        margin-right: 10px;
    }

    .checkbox-group label {
        display: inline-block;
        font-weight: normal;
    }

    input[type="text"]:focus, textarea:focus, select:focus {
        border-color: black;
        outline: none;
    }
</style>
</head>
<body>

<div class="container">
    <h2>Create Software</h2>
    

    	<% 
        	String role = (String) session.getAttribute("role"); 
        	if (role == null) { 
    	%>
        	<script type="text/javascript">
            	window.location.href = "<%= request.getContextPath() %>/login";
        	</script>
    	<% 
        	} else if (role.equals("Employee") || role.equals("Manager")) { 
    	%>
        	<script type="text/javascript">
            	window.location.href = "<%= request.getContextPath() %>/login";
        	</script>
    	<% 
        	}
    	%>

    <!-- Form to submit -->
    <form action="<%= request.getContextPath() %>/create-software" method="post">
        <div class="form-group">
            <label for="softwareName">Software Name:</label>
            <input type="text" id="softwareName" name="softwareName" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" class="form-control" rows="4" required></textarea>
        </div>

        <div class="form-group">
            <label>Access Levels:</label>
            <div class="checkbox-group">
                <div>
                    <input type="checkbox" id="read" name="accessLevels" value="Read">
                    <label for="read">Read</label>
                </div>
                <div>
                    <input type="checkbox" id="write" name="accessLevels" value="Write">
                    <label for="write">Write</label>
                </div>
                <div>
                    <input type="checkbox" id="admin" name="accessLevels" value="Admin">
                    <label for="admin">Admin</label>
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
