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
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class ListTeams
 */
@WebServlet("/ListTeams")
public class ListTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "ListTeam-Result.ftl";

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
    }//init

    public ListTeams() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession         httpSession=null;
        Session             session=null;
        String              ssid=null;
        LogicLayer logicLayer=null;
        Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        List<List<Object>>  teams= null;
        List<Object>        team = null;
        
        resultTemplate = cfg.getTemplate( resultTemplateName );
		toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding()));

		response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        
        
        httpSession = request.getSession();
        if( httpSession == null ) {       // assume not logged in!
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("httpsession is null");
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
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("session is null");
            return; 
        }//if

        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
           // ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("Logiclayer is null");
            return; 
        }//if
        
        System.out.println("session & logiclayer successfull");
        System.out.println("List Teams");
		String lid=request.getParameter("btnLeagueId");
		System.out.println("lid::"+lid);
		List<Team> l=null;
		edu.uga.cs.recdawgs.entity.League l1=new LeagueImplementor(null,null,null,false,0,0,0,0);
		Long lid1=Long.parseLong(lid);
		l1.setId(lid1);
		try {
			l=logicLayer.findTeam(l1);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Size of team::"+l.size());
		Map<String,Object> root = new HashMap<String,Object>();
		teams=new LinkedList<List<Object>>();
		 root.put( "teams",teams);
		 
		 Team t=null;
		 for( int i = 0; i < l.size(); i++ ) {
             t= l.get( i );
             //Person founder = objectModel.findEstablishedBy( c );
             //Person founder = c.getFounder();
             team = new LinkedList<Object>();
             team.add(t.getId());
             team.add(t.getName());
             team.add(t.getParticipatesInLeague().getId());
              //venue.add(s.getIsIndoor());
             teams.add(team);
           }//for
		 try {
			resultTemplate.process( root, toClient );
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         toClient.flush();
		

        
		
	}//post

}
