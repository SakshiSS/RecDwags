package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MatchOperations
 */
@WebServlet("/MatchOperations")
public class MatchOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchOperations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Match_Operations");
		//String match_scores=request.getParameter("EnterMatchScores");
		String sm=request.getParameter("EnterScores");
		String sm1=request.getParameter("ScheduleMatch");
		String resolveMatchScores = request.getParameter("ResolveScores");
		if(sm1!=null){
			response.sendRedirect("Schedule_Match.html");	
		}
		if(sm!=null){
			response.sendRedirect("Schedule_Match.html");
			
		}
		
		if(resolveMatchScores != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ResolveScoreGetMatches");
			rd.forward(request, response);
			
		}
		//if(match_scores!=null){
			//}//if
	}//post

}
