package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlets.dao.UserDao;
import com.servlets.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		session.removeAttribute("signup");
		
		
		User newUser = new User();
		newUser.setPassword(password);
		newUser.setUsername(username);
		try {
			User validUser = userDao.loginUser(newUser);
			if (validUser != null) {
				if ("Employee".equalsIgnoreCase(validUser.getRole())) {
                    response.sendRedirect("request-access"); 
                } else if ("Manager".equalsIgnoreCase(validUser.getRole())) {
                    response.sendRedirect("approve-reject"); 
                } else if ("Admin".equalsIgnoreCase(validUser.getRole())) {
                    response.sendRedirect("create-software"); 
                } else {
                    request.setAttribute("errorMessage", "No proper role.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
				session.setAttribute("username", validUser.getUsername());
				session.setAttribute("role", validUser.getRole());
				session.setAttribute("userId", validUser.getId());
				
			} else {
				request.setAttribute("errorMessage", "Invalid credentials.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
