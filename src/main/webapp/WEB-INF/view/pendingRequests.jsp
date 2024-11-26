<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        /* Add your styles here for the page layout */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius:15px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #a0a4a8;
            text-align: left;
        }

        th {
            background-color: #fff;
            color: black;
            font-weight: bold;
        }

        button {
            padding: 8px 16px;
            margin: 4px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            font-size: 14px;
        }

        .approve {
            background-color: #28a745;
            color: white;
        }

        .reject {
            background-color: #dc3545;
            color: white;
        }

        button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
	<div class="container">
        <h2>Pending Requests</h2>
        <% 
            String success = request.getParameter("success");
        	String failed = request.getParameter("failed");
            if (success != null) {
        %>
            <script type="text/javascript">
                alert("Request has been successfully processed!");
            </script>
        <% 
            } else if(failed != null) { 
        %>
        	<script type="text/javascript">
                alert("Request has been failed!");
            </script>
        <% 
            }
        %>
        <table>
            <thead>
                <tr>
                    <th>Employee Name</th>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason for Request</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through the pending requests -->
                <c:forEach var="request" items="${pendingRequests}">
                    <tr>
                        <td>${request.userName}</td>
                        <td>${request.softwareName}</td>
                        <td>${request.accessType}</td>
                        <td>${request.reason}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/approve-reject" method="post">
                                <input type="hidden" name="requestId" value="${request.id}">
                                <button type="submit" name="action" value="approve" class="approve">Approve</button>
                                <button type="submit" name="action" value="reject" class="reject">Reject</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>