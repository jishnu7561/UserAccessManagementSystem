<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 400px;
        margin: 100px auto;
        padding: 30px;
        background: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    h2 {
        text-align: center;
        font-size: 24px;
        margin-bottom: 20px;
        color: #333;
    }

    label {
        display: block;
        font-size: 16px;
        margin-bottom: 8px;
        color: #555;
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 16px;
        box-sizing: border-box;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus, input[type="password"]:focus {
        border-color: black;
        outline: none;
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        background-color: black;
        border: none;
        color: white;
        font-size: 16px;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .message {
        text-align: center;
        margin-top: 10px;
    }

    .message a {
        text-decoration: none;
        color: #0cf0e1;
        font-weight: bold;
    }

    .message a:hover {
        color: #4aede2;
    }

    .success, .error {
        text-align: center;
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 4px;
    }

    .success {
        background-color: #e8f5e9;
        color: #388e3c;
        border: 1px solid #388e3c;
    }

    .error {
        background-color: #ffebee;
        color: #d32f2f;
        border: 1px solid #d32f2f;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Signup</h2>

    <!-- Signup Form -->
    <form action="<%= request.getContextPath() %>/registration" method="POST">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>

     

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Sign Up">
    </form>

    <div class="message">
        <p>Already have an account? <a href="/user-access-management/login">Login here</a></p>
    </div>
</div>

</body>
</html>