package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.dao.RequestDao;
import com.servlets.dao.SoftwareDao;
import com.servlets.model.RequestStatus;
import com.servlets.model.Requests;
import com.servlets.model.Software;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/request-access")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SoftwareDao softwareDao = new SoftwareDao();
	RequestDao requestDao = new RequestDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Software> softwareList = softwareDao.getAllSoftware();

        request.setAttribute("softwareList", softwareList);
        
        request.getSession().setAttribute("softwareList", softwareList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/requestAccess.jsp");
        dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String softwareId = request.getParameter("softwareId"); 
		 String accessType = request.getParameter("accessType"); 
		 String reason = request.getParameter("reason");
		 
		 int userId = (int) request.getSession().getAttribute("userId"); 
		 System.out.println(userId);
		 Requests requests = new Requests(userId,Integer.parseInt(softwareId),accessType,reason, RequestStatus.Pending);
		 
	     try {
			int requested = requestDao.addRequest(requests);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
