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
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class LeagueWinner
 */
@WebServlet("/LeagueWinner")
public class LeagueWinner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeagueWinner() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "LeagueWinner-Result.ftl";

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		httpSession = request.getSession();
		
		 Template            resultTemplate = null;
	        BufferedWriter      toClient = null;
	        List<List<Object>>  teams= null;
	        List<Object>        team = null;
	        resultTemplate = cfg.getTemplate( resultTemplateName );
			toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding()));

			response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

	        
	        

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
        String tid=request.getParameter("btnLeagueId");
        System.out.println("tid::"+tid);
        String[] tlid=tid.split("]");
        System.out.println("tlid::"+tlid[0]+"-->"+tlid[1]+"-->"+tlid[2]);
        Long tid1=Long.parseLong(tlid[0]);
        Long lid=Long.parseLong(tlid[1]);
        System.out.println(tid1+"-->"+lid);
        
        try {
			logicLayer.leaguewinner(tid1,lid);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Map<String,Object> root = new HashMap<String,Object>();
		teams=new LinkedList<List<Object>>();
		
		List<List<String>>  teams1= null;
        List<String>        team1 = null;
        teams1=new LinkedList<List<String>>();
		
		team1=new LinkedList<String>();
		team1.add(tlid[2]);
		teams1.add(team1);
		 root.put( "teams1",teams1);
		 try {
				resultTemplate.process( root, toClient );
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         toClient.flush();
			
        
        
        
        
        
	}

}
