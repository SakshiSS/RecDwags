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

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class SearchTeam
 */
@WebServlet("/SearchTeam")
public class SearchTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "SearchTeam-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Template resultTemplate = null;
		BufferedWriter toClient = null;
        LogicLayer logicLayer = null;
       // Team teamObject = null;
        Team team  = null;
        HttpSession httpSession;
        Session session;
        String ssid;
        
        
        String teamName = request.getParameter("txtTeamName");
        
        
        
      try {
      resultTemplate = cfg.getTemplate( resultTemplateName );
  } 
  catch( IOException e ) {
      throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
  }
  
 // toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream()));
   toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), resultTemplate.getEncoding() ));
   response.setContentType("text/html; charset=" + resultTemplate.getEncoding());
  
  httpSession = request.getSession();
//  if( httpSession == null ) {       // assume not logged in!
//      ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//      return;
//  }
  
  ssid = (String) httpSession.getAttribute("ssid");
//  if( ssid == null ) {       // not logged in!
//      ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//      return;
//  }

  session = SessionManager.getSessionById(ssid);
//  if( session == null ) {
//      ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//      return; 
//  }
  
  logicLayer = session.getLogicLayer();
//  if( logicLayer == null ) {
//      ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//      return; 
//  }
  
  Map<String,Object> teamMap = new HashMap<String,Object>();
  
  try {
	  
      team = logicLayer.searchTeam(teamName);

      // Build the data-model
      //
      
      teamMap.put("team_name",team.getName());
      teamMap.put("team_id", (Long) team.getId());
      teamMap.put("captain_id", team.getCaptain().getStudentId());
      teamMap.put("league_id", team.getParticipatesInLeague().getId());

  } 
  catch( Exception e) {
//      ClubsError.error( cfg, toClient, e );
//      return;
  }

  try {
      resultTemplate.process(teamMap, toClient );
      toClient.flush();
  } 
  catch (Exception e) {
      throw new ServletException( "Error while processing FreeMarker template", e);
  }

  toClient.close();
	

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
