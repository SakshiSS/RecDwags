package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeamOperations
 */
@WebServlet("/TeamOperations")
public class TeamOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamOperations() {
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
		String joinTeam=request.getParameter("btnJoinTeam");
		String leaveTeam=request.getParameter("btnLeaveTeam");
		String appointcaptain=request.getParameter("btnAppointCaptain");
		if(joinTeam!=null){
			System.out.println("JoinTeam");
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/JoinTeam").forward(request,response);
	
		}//if
		if(leaveTeam!=null){
			System.out.println("Leave Team");
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/LeaveTeam").forward(request,response);
	
		}//if
		if(appointcaptain!=null){
			System.out.println("AppointCaptain");
		    //response.sendRedirect("Appoint_Captain");
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/ListStudentOfTeam").forward(request,response);
	
			
		}//if
	}//post

}
