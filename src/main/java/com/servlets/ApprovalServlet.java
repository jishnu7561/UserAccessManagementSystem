package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.dao.RequestDao;

/**
 * Servlet implementation class ApprovalServlet
 */
@WebServlet("/approve-reject")
public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDao requestDao = new RequestDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/pendingRequests.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestId = request.getParameter("requestId");
        String action = request.getParameter("action");
        
        if ("approve".equals(action)) {
            boolean isApproved = false;
			try {
				isApproved = requestDao.approveRequest(requestId);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isApproved) {
            	response.sendRedirect(request.getContextPath() + "?success");
            } else {
            	response.sendRedirect(request.getContextPath() + "?failed");

            }
            
        } else if ("reject".equals(action)) {
            boolean isRejected = false;
			try {
				isRejected = requestDao.rejectRequest(requestId);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (isRejected) {
            	response.sendRedirect(request.getContextPath() + "?success");
            } else {
            	response.sendRedirect(request.getContextPath() + "?failed");

            }
        }
	}

}
