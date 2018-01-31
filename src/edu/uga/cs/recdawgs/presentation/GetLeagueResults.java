package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class GetLeagueResults
 */
@WebServlet("/GetLeagueResults")
public class GetLeagueResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "DisplayLeaguesRes-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    } 
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLeagueResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedWriter toClient = null;
		
		
		
		System.out.println("went to get method of viewLeagueResults");
		LogicLayer logicLayer = null;
		HttpSession httpSession ;
		Session session ;
		String ssid;
		
		Template resultTemplate;
		List<League> leagueList = null;
		List<List<Object>> leagues = null;
		
		List<Object> league = null;
		League l = null;
		
		String leagueName = request.getParameter("ddlLeagueName");
//Preparing the http response	
		
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
        Map<String,Object> leagueResultsMap = new HashMap<String,Object>();
        try {
        if(leagueName!= null){
        	System.out.println("League Name :" +leagueName);
        	List<Integer> rounds = null;
        	        	
				rounds  = logicLayer.getAllRoundsForLeague(leagueName);
				
			
			leagueResultsMap.put("rounds",rounds);
			
			 try{
		        	resultTemplate.process(leagueResultsMap, toClient );
		        	System.out.println("some info"+toClient);
		        	toClient.flush();
		        }
		        catch(TemplateException e){
		        	throw new ServletException("Error while processing the Freemarker template" +e);
		        }
			
        }
        else{
        	System.out.println( " no submit");
                	//here
        	
        }
        } catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		toClient.close();
	}

}
