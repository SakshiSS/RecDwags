package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class GetLeaguesList
 */
@WebServlet("/GetLeaguesList")
public class GetLeaguesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static String templateDir = "WEB-INF/templates";
	  static String resultTemplateName = "GetLeaguesListTmpl.ftl"; // get the template file
	  private Configuration cfg;
 
	  public void init() throws ServletException {
		  System.out.println("Servlet name" + getServletName());
		  cfg = new Configuration();
		  System.out.println("confi : "+cfg.toString());
		  cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
	  }
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLeaguesList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
BufferedWriter toClient = null;
	
		
		
		System.out.println("went to get method of getleagues");
		LogicLayer logicLayer = null;
		HttpSession httpSession ;
		Session session ;
		String ssid;
		
		Template resultTemplate;
		List<League> leagueList = null;
		List<List<Object>> leagues = null;
		
		List<Object> league = null;
		League l = null;
		
//Preparing the http response
		
		String teamName = request.getParameter("txtTeamName");
		

        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch( IOException e ) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        
        toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),resultTemplate.getEncoding()));
		response.setContentType("text/html; charset=" +resultTemplate.getEncoding());

		
		
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
		
		logicLayer = session.getLogicLayer();

        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();
        
        try{
        	leagueList = logicLayer.getLeagues();
        	///get clubs function call;
        	
        	//Build the datamodel
        	leagues = new LinkedList<List<Object>>();
        	Team teamNew = logicLayer.searchTeam(teamName);
        	String studentName = teamNew.getCaptain().getUserName();
        	String leagueName = teamNew.getParticipatesInLeague().getName();
        	
        	for(int i =0; i<leagueList.size();i++){
        		l = (League) leagueList.get(i);
        		//League newLeague = l
        		league = new LinkedList<Object>();
        		league.add(l.getId());
        		long league_id = l.getId();
        		HttpSession session_League_Id = request.getSession();
        		session_League_Id.setAttribute("LeagueId",league_id  );
        		
        		league.add(l.getName());
        		league.add(l.getLeagueRules());
        		league.add(l.getMatchRules());
        		league.add(Boolean.toString(l.getIsIndoor()));
        		league.add(l.getMinTeams());
        		league.add(l.getMaxTeams());
        		league.add(l.getMinMembers());
        		league.add(l.getMaxMembers());
        		
        		leagues.add(league);
        	}
        	
        	root.put("leagues", leagues);
        	root.put("TeamName", teamName);
        	root.put("StudentName",studentName );
        	root.put("leagueNameSelect", leagueName);
        	
        		
        	}
        catch(Exception e){
        	e.printStackTrace();
        	return;        	
        }
        //Merging the data model and the template
        try{
        	resultTemplate.process(root, toClient );
        	System.out.println("some info"+toClient);
        	toClient.flush();
        }
        catch(TemplateException e){
        	throw new ServletException("Error while processing the Freemarker template" +e);
        }
        toClient.close() ;
        //request.getRequestDispatcher("/GetLeagues-Result.ftl").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
