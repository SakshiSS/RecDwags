package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class GetMatchResults
 */
@WebServlet("/GetMatchResults")
public class GetMatchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "DisplayMatchRes-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    } 
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMatchResults() {
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
		Round round = null;
		Match match = null;
		Iterator<Match> matchItr= null;
		List<Match> matchList = null;
		Integer roundNumber  = Integer.parseInt(request.getParameter("ddlRounds"));
		
		
		
		
		
		Template resultTemplate;
		
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
			//System.out.println("Round Number: "+roundNumber);
			try {
				matchList = logicLayer.getAllMatchesForRound(roundNumber);
				System.out.println("MatchList Size: "+matchList.size());
			} catch (RDException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			  // Setup the data-model
	        //
	        Map<String,Object> matchResultsMap = new HashMap<String,Object>();
	        
	        
	        LinkedList<List<Object>> matches= new LinkedList<List<Object>>();
	        Match matchResult = null;
	        LinkedList<Object> matchObject = null;
            
            	for(int i = 0; i<matchList.size(); i++ ) {
                matchResult = (Match) matchList.get(i);
                matchObject = new LinkedList<Object>();
               // match = matchList.get(i);
                matchObject.add(matchResult.getId());
                matchObject.add(matchResult.getHomePoint());   
                matchObject.add(matchResult.getAwayPoints());
                matchObject.add(matchResult.getDate().toString());
                matchObject.add(matchResult.getSportsVenue().getName());
                matchObject.add(matchResult.getHomeTeam().getName());
                matchObject.add(matchResult.getAwayTeam().getName());
                matches.add(matchObject);
            }
	        

	        matchResultsMap.put("matches", matches);
	        
	        //Merging the data model and the template
	        try{
	        	resultTemplate.process(matchResultsMap, toClient );
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
