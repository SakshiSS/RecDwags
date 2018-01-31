package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Servlet implementation class ResolveScoreGetMatches
 */
@WebServlet("/ResolveScoreGetMatches")
public class ResolveScoreGetMatches extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "DisplayMatchesFromSR.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolveScoreGetMatches() {
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
		
		System.out.println("Inside do post of ResolveScoreGetMatches - get matches");
		
		Template resultTemplate = null;
		BufferedWriter toClient = null;
        String team_name = null;
        String captain_name = null;
        String league = null;
        //long team_id = 0;
        //long league_id = 0;
        //long captain_id = 0;
        LogicLayer logicLayer = null;
        HttpSession httpSession;
        Session session;
        String ssid;
        
        List<ScoreReport> scoreReportList = null;
        List<List<Object>> scoreReports = null;
        List<Object> scoreReportObject = null;
        ScoreReport scoreReport = null;
        
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } 
        catch (IOException e) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() ));

        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());
        
        
        httpSession = request.getSession();
//      if( httpSession == null ) {       // assume not logged in!
//          ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//          return;
//      }

      ssid = (String) httpSession.getAttribute("ssid");
//      if( ssid == null ) {       // not logged in!
//          ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//          return;
//      }
      session = SessionManager.getSessionById( ssid );
//      if( session == null ) {
//          ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//          return; 
//      }
      
      logicLayer = session.getLogicLayer();
//      if( logicLayer == null ) {
//          ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//          return; 
//      }
      
      scoreReports = new LinkedList<List<Object>>();
      Map<String,Object> matchMap = new HashMap<String,Object>();
      
      
      try {
		scoreReportList = logicLayer.findAllScoreReports();
	} catch (RDException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      List<Object> matchId = new ArrayList<Object>();
      List<Object> matchIds = new ArrayList<Object>();
      
      System.out.println("Score Report Size:"+scoreReportList.size());
      for(int i = 0; i< scoreReportList.size(); i++){
    	  scoreReport = scoreReportList.get(i);
    	  scoreReportObject = new  LinkedList<Object>();
    	  System.out.println("score report presetn " + scoreReport.getId());
    	  
    	  scoreReportObject.add(scoreReport.getId());
    	  scoreReportObject.add(scoreReport.getHomePoint());
    	  scoreReportObject.add(scoreReport.getAwayPoints());
    	  scoreReportObject.add(scoreReport.getMatch());
    	  scoreReportObject.add(scoreReport.getStudent());
    	  System.out.println("Score Report boject get id" + scoreReport.getId());
    	   //only below three are required for displaying the template   
    	  System.out.println("Score Report Match:"+scoreReport.getMatch());
    	  matchId.add(scoreReport.getMatch().getId() );
    	  matchId.add(scoreReport.getMatch().getHomeTeam().getName());
    	  matchId.add(scoreReport.getMatch().getAwayTeam().getName());
    	  
    	  matchIds.add(matchId);
      }
     
      
      matchMap.put("match_ids",matchIds);
      
      try {
          resultTemplate.process(matchMap, toClient);
          toClient.flush();
      } 
      catch (Exception e) {
          throw new ServletException( "Error while processing FreeMarker template", e);
      }

      toClient.close();
      
      
      		
		
	}

}
