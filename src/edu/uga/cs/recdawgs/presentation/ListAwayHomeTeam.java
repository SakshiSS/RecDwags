package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
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
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class ListAwayHomeTeam
 */
@WebServlet("/ListAwayHomeTeam")
public class ListAwayHomeTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAwayHomeTeam() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "HomeAwayTeam-Result.ftl";

    private Configuration  cfg; 

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), 
                "WEB-INF/templates"
                );
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Template       resultTemplate = null;
        BufferedWriter toClient = null;
        
		HttpSession httpSession=null;
		Session session=null;
		String ssid=null;
		LogicLayer logicLayer=null;
		System.out.println("In AppointCaptain");
		
        resultTemplate = cfg.getTemplate( resultTemplateName );
	    
	    
	    
	    toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),resultTemplate.getEncoding()));
         
	    response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

		httpSession = request.getSession();
		if( httpSession == null ) {       // assume not logged in!
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("httpsession==null not loggedin");
            return;
            
        }//if
        ssid = (String) httpSession.getAttribute( "ssid" );
        if( ssid == null ) {       // not logged in!
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("ssid is null");
            return;
        }//if
        

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
           // ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("sesssion is null");
            return; 
        }//if
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
           // ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("logic layer is null");
            return; 
        }//if
        
        System.out.println("Session and logic both successfull");

        String t_name=request.getParameter("txtTeamName");
        //String t_name=request.getParameter("txtTeamName");
        List<Team> l1=null;
        try {
			l1=logicLayer.findStudentforTeam(t_name);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long id=0;
        for(int i=0;i<l1.size();i++){
        	System.out.println("l1::"+l1.get(i).getId());
        	id=l1.get(i).getId();
        }//for
        List<Match> away_matches=null;
        //All Matches where team="New1" is hometeam
        away_matches=logicLayer.getAwayTeamMatches(id,t_name);
        System.out.println("away_matches"+away_matches.size());
        Team t2=new TeamImplementor(t_name);
        t2.setId(id);
        List<Match> home_matches =null; 
        Team t=null;
        //All Matches where team="New1" is hometeam
        home_matches=logicLayer.getHomeTeamMatches(id,t_name);
        Map<String,Object> root = new HashMap<String,Object>();
        LinkedList<List<Object>> teams=new LinkedList<List<Object>>();
        LinkedList<List<Object>> teams1=new LinkedList<List<Object>>();
		
		root.put( "teams",teams);
		root.put("teams1",teams1);
		List<Object> team=null;
		//team=new LinkedList<Object>();
		//team.add(t2.getName());
        List<Object> team1=null;
        
        for(int i=0;i<home_matches.size();i++){
        	Match m=home_matches.get(i);
        	team1=new LinkedList<Object>();
        	System.out.println("Match::"+m.getAwayTeam().getId());
        	//team.add(m.getAwayTeam().getName());
        	//team.add(m.getHomeTeam().getName());
        	t=logicLayer.findTeamAsAwayTeam(m);
        	System.out.println("Away_Team"+t.getName());
        	//team.add(t.g);
        	team1.add(t2.getName());
            
        	team1.add(t.getName());
        	team1.add(m.getId());
        	teams1.add(team1);
        	
        }//for
        Team t1=null;
        for(int j=0;j<away_matches.size();j++){
        	Match m1=away_matches.get(j);
        	System.out.println("Match::"+m1.getHomeTeam().getId());
        	team=new LinkedList<Object>();
    		
        	//team.add(m1.getHomeTeam().getName());
        	
        	//team.add(m1.getAwayTeam().getName());
        	
        	t1=logicLayer.findTeamAsHomeTeam(m1);
        	System.out.println("Home_Team"+t1.getName());
        	
        	team.add(t1.getName());
        	team.add(t2.getName());
            
        	team.add(m1.getId());
        	teams.add(team);
       }//for
        System.out.println("teams::"+teams.size());
        for(int k=0;k<teams.size();k++){
        	System.out.println("teams"+k+teams.get(k));
        }//for
        System.out.println("teams1::"+teams1.size());
        for(int k=0;k<teams1.size();k++){
        	System.out.println("team::"+teams1.get(k));
        }//for
        try {
			resultTemplate.process(root,toClient);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//catch
        
        
        toClient.close();
        
       
        
		
	}//post

}
