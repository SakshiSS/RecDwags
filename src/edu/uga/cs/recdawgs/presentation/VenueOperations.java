package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VenueOperations
 */
@WebServlet("/VenueOperations")
public class VenueOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenueOperations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("In venue Operation");
		String createVenue=request.getParameter("btnCreateVenue");
		String searchVenue=request.getParameter("btnSearchVenue");
		String textvenue=request.getParameter("txtVenueName");
		String getVenue=request.getParameter("btnGetVenues");
		String editVenue=request.getParameter("btnEditVenue");
		String deleteVenue=request.getParameter("btnDeleteVenue");
		if(createVenue!=null){
			System.out.println("CreateVenue");
			response.sendRedirect("VenueCreation.html");
				}//if
		if(searchVenue!=null){
			System.out.println("In SearchVenue");
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/FindVenue").forward(request,response);
	
		}//if
		if(getVenue!=null){
			System.out.println("In getVenue");
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/FindVenue").forward(request,response);
	
		}//if
		if(editVenue!=null){
			System.out.println("In EditVenue");
			response.sendRedirect("UpdateVenue.html");
		}//if
		if(deleteVenue!=null){
			System.out.println("In deleteVenue");
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/DeleteVenue").forward(request,response);
	
		}//if
		
	}//post

}
