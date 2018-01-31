package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LeagueOperations
 */
@WebServlet("/LeagueOperations")
public class LeagueOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeagueOperations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String leaguewinner=request.getParameter("btnLeagueWinner");
		if(leaguewinner!=null){
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/ListLeague").forward(request,response);
	
		}//if
	
	}//post

}
