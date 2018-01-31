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

import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class EnterScoreSchedule
 */
@WebServlet("/EnterScoreSchedule")
public class EnterScoreSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterScoreSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "EnterScoresSchedule-Result.ftl";

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
    }//init

    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        List<List<String>>  teams= null;
        List<String>        team = new LinkedList<String>();
        
        HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		httpSession = request.getSession();
		 ssid = (String) httpSession.getAttribute( "ssid" );

	        if( ssid == null ) {       // not logged in!
	            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
	        	System.out.println("ssid=nul");
	            return;
	        }

	        session = SessionManager.getSessionById( ssid );
	        if( session == null ) {
	            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
	        	System.out.println("session==null");
	            return; 
	        }
	        
	        logicLayer = session.getLogicLayer();
	        if( logicLayer == null ) {
	            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
	            System.out.println("logiclayer=null");
	        	return; 
	        }
	        

        
        resultTemplate = cfg.getTemplate( resultTemplateName );
		toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding()));

	response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

		String Teams1=request.getParameter("btnMatch1");
		String Teams2=request.getParameter("btnMatch2");
		System.out.println("Teams1::"+Teams1);
		System.out.println("Teams2::"+Teams2);
		if(Teams1!=null){
			String[] teamsplit=Teams1.split("]");
			System.out.println("teamsplit"+teamsplit[0]+"-->"+teamsplit[1]+"-->"+teamsplit[2]);
			
			Map<String,Object> root = new HashMap<String,Object>();
			teams=new LinkedList<List<String>>();
			
			 root.put( "teams",teams);
			 team.add(teamsplit[0]);
			 team.add(teamsplit[1]);
			 team.add(teamsplit[2]);
			 teams.add(team);
			 String team1=new String("team1");
			 String team2=new String("team2");
			 String id=new String("id");
			 
			 httpSession.setAttribute("team1",teamsplit[1]);
			 httpSession.setAttribute("team2",teamsplit[2]);
			 //String team2=new String("team2");
			 httpSession.setAttribute("id",teamsplit[0]);
				
			 try {
					resultTemplate.process( root, toClient );
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         toClient.flush();
				

	        
			
			
			
		}//if
		if(Teams2!=null){
			String[] teamsplit=Teams2.split("]");
			System.out.println("teamsplit"+teamsplit[0]+"-->"+teamsplit[1]+"-->"+teamsplit[2]);
			Map<String,Object> root = new HashMap<String,Object>();
			teams=new LinkedList<List<String>>();
			
			 root.put( "teams",teams);
			 team.add(teamsplit[0]);
			 team.add(teamsplit[1]);
			 team.add(teamsplit[2]);
			 teams.add(team);
			 String team1=new String("team1");
			 String team2=new String("team2");
			 String id=new String("id");
			 
			 httpSession.setAttribute("team1",teamsplit[1]);
			 httpSession.setAttribute("team2",teamsplit[2]);
			 //String team2=new String("team2");
			 httpSession.setAttribute("id",teamsplit[0]);
				
			 try {
					resultTemplate.process( root, toClient );
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         toClient.flush();
			
		}//if
		
	}


}
