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

import com.servlets.dao.SoftwareDao;
import com.servlets.model.Software;

/**
 * Servlet implementation class SoftwareServlet
 */
@WebServlet("/create-software")
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SoftwareDao softwareDao = new SoftwareDao();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoftwareServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/createSoftware.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String softwareName = request.getParameter("softwareName");
	     String description = request.getParameter("description");
	     String[] accessLevels = request.getParameterValues("accessLevels");

	     System.out.println("Software Name: " + softwareName);
	     System.out.println("Description: " + description);
	    
	      if (accessLevels != null) {
	         for (String accessLevel : accessLevels) {
	            System.out.println("Access Level: " + accessLevel);
	          }
	      }
	      
	      String accesslevel = String.join(",", accessLevels);
	      Software software = new Software();
	      software.setSoftwareName(softwareName);
	      software.setDescription(description);
	      software.setAccessLevels(accesslevel);
	      
	      HttpSession session = request.getSession();
	      
	      try {
			int result = softwareDao.createSoftware(software);
			if(result > 0) {
				response.sendRedirect("create-software");
			    session.setAttribute("creat-software", "successfully created");
			} else {
			    session.setAttribute("errorMessage", "Failed to create, please try again");
			}
					    
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute("errorMessage", "something went wrong, please try again");
		}

	}

}
