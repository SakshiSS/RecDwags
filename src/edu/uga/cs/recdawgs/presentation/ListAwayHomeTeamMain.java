package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListAwayHomeTeamMain
 */
@WebServlet("/ListAwayHomeTeamMain")
public class ListAwayHomeTeamMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAwayHomeTeamMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String enterscore=request.getParameter("EnterScores");
		String schedule=request.getParameter("Schedule");
		
		if(enterscore!=null){
			System.out.println("Enter Scores");
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/ListAwayHomeTeam").forward(request,response);
	
		}//if
		
		
		if(schedule!=null){
			System.out.println("Enter Scores");
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/ListAwayHomeTeamSchedule").forward(request,response);
	
		}//if

	}

}
