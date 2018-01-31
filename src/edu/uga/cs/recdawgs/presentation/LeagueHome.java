package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import edu.uga.cs.recdawgs.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.core.Configurable;

/**
 * Servlet implementation class CreateLeague
 */
@WebServlet("/LeagueHome")
public class LeagueHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  /*static  String         templateDir = "WEB-INF/templates";
	  static  String         resultTemplateName = "CreateLeague-Result.ftl"; // get the template file
	  private Configuration cfg;*/
    
	/*  public void init(){
		  cfg = new Configuration();
		  
		  cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
	  }*/
	  
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LeagueHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
BufferedWriter toClient = null;
		
		
		
		LogicLayer logicLayer = null;
		HttpSession httpSession ;
		Session session ;
		String ssid;
		
		String createLeague = request.getParameter("btnCreateLeague");
		String getLeagues = request.getParameter("btnGetLeagues");
		String editLeague = request.getParameter("btnEditLeague");
		String deleteLeague = request.getParameter("btnDeleteLeague");
		String searchLeague = request.getParameter("btnSearchLeague");
		String leaguewinner=request.getParameter("btnLeagueWinner");
		String leagueResults = request.getParameter("btnViewLeagueRes");
		//String leagueResults = request.getParameter("btnViewLeagueRes");
				
		//Preparing the http response
		
		toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
		//response.setContentType("text/html; charset=" +resultTemplate.getEncoding());
		
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
		
		if(leaguewinner!=null){
			ServletContext context=request.getSession().getServletContext();
			context.getRequestDispatcher("/ListLeague").forward(request,response);
	
		}//if
		
		if( createLeague != null){
			System.out.println( "League creation page");
			response.sendRedirect("CreateLeague.html");
		}
		else if( getLeagues != null){
			System.out.println( "Get Leagues");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/GetLeagues");
			rd.forward(request, response);
			
		}
      else if(leagueResults != null){
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewLeagueResults");
			rd.forward(request, response);
			
		}
		
		else if(searchLeague != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SearchLeague");
			rd.forward(request, response);
		}
		else if(deleteLeague != null ){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/DeleteLeague");
			rd.forward(request, response);
		}
		else if(editLeague != null){
			System.out.println( "Edit League");
			String league_Name= request.getParameter("txtLeagueName");
			HttpSession session_League_Name = request.getSession();
			session_League_Name.setAttribute("LeagueName", league_Name);
				
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/EditLeagueGetData");
			rd.forward(request,response);
			
		}
				

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			}

}
