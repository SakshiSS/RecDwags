
package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;

/**
 * Servlet implementation class TeamHome
 */
@WebServlet("/TeamHome")
public class TeamHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		LogicLayer logicLayer = null;
		ObjectLayer objLayer = null;
		HttpSession httpSession =  null;
		Session session = null;
		String ssid = null;
		
		//Initializing objects
		bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
		
		
		
		httpSession = request.getSession();
		if(httpSession == null) { //not logged in session expired
			//ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
			System.out.println("No session  for the user");
	          return;

		}
		
		ssid = (String) httpSession.getAttribute("ssid");
		if(ssid == null){
			//not logged in
			System.out.println("Not logged in");
			return;
		}
		session = SessionManager.getSessionById(ssid);
		if(session == null){
			//show error
			System.out.println("Session expired, not logged ");
			return;
		}
		
		
		String createTeam = request.getParameter("btnCreateTeam");
		String getTeams = request.getParameter("btnGetTeams");
		String searchTeam = request.getParameter("btnSearchTeam");
		String editTeam = request.getParameter("btnEditTeam");
		String deleteTeam = request.getParameter("btnDeleteTeam");
		String teamName = request.getParameter("txtTeamName");
		String joinTeam=request.getParameter("btnJoinTeam");
		String leaveTeam=request.getParameter("btnLeaveTeam");
		String appointcaptain=request.getParameter("btnAppointCaptain");
	
		System.out.println("TeamName  in Team home: "+teamName);
		
		HttpSession teamNameSession = request.getSession();
		teamNameSession.setAttribute("TeamName", teamName);
		
		if(createTeam != null){
			System.out.println("create team in teamhome");
			//response.sendRedirect("CreateTeam.html");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/GetLeaguesListCreateTeam");
			rd.forward(request, response);
			
		}
		else if(getTeams != null) {
			//need to call the get teams functionality.
			System.out.println("Get Teams in TeamHome");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/GetTeams");
			rd.forward(request, response);
			}
		else if(searchTeam != null){
			//need to call the search team functionality
			System.out.println("Search Team in TeamHome");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SearchTeam");
			rd.forward(request, response);
		}
		else if(editTeam != null){
			System.out.println("edit team in teamhome");
			System.out.println("Team Name in edit Team loop: " +request.getSession().getAttribute("TeamName").toString());
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/GetLeaguesList");
			rd.forward(request, response);
			//need to call the edit team functionality
			//response.sendRedirect("EditTeam.html");
		}
		else if(deleteTeam != null){
			//need to call the delete team functionality
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/DeleteTeam");
			rd.forward(request, response);
			
		}
		else if(joinTeam!=null){
			System.out.println("JoinTeam");
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/JoinTeam").forward(request,response);
	
		}//if
		else if(leaveTeam!=null){
			System.out.println("Leave Team");
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/LeaveTeam").forward(request,response);
	
		}//if
		else if(appointcaptain!=null){
			System.out.println("AppointCaptain");
		    //response.sendRedirect("Appoint_Captain");
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/ListStudentOfTeam").forward(request,response);
	
			
		}//if

		else{
			System.out.println("invalid");
		}
		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
