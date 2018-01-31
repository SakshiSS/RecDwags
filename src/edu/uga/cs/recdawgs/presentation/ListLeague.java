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
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class ListLeague
 */
@WebServlet("/ListLeague")
public class ListLeague extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "ListAllLeague-Result.ftl";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListLeague() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
    }//init

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Template       resultTemplate = null;
		BufferedWriter toClient = null;
		 List<List<Object>>  leagues= null;
	        List<Object>        league = null;
	         
		HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		httpSession = request.getSession();

		
		 try {
	          resultTemplate = cfg.getTemplate( resultTemplateName );
	      } 
	      catch (IOException e) {
	          throw new ServletException( 
	                  "Can't load template in: " + templateDir + ": " + e.toString());
	      }

	    toClient = new BufferedWriter(
	              new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() )
	              );
         
	    response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

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
       List<edu.uga.cs.recdawgs.entity.League> l=null;
       System.out.println("Session and logic both successfull");
        try {
			l=logicLayer.findLeague(null);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Iterator<edu.uga.cs.recdawgs.entity.League> i=l.iterator();
        while(i.hasNext()){
        	edu.uga.cs.recdawgs.entity.League l1=i.next();
        	System.out.println("league::"+l1.getId());
        }//while
        Map<String,Object> root = new HashMap<String,Object>();
        leagues=new LinkedList<List<Object>>();
		 root.put( "leagues",leagues);
		 
		 edu.uga.cs.recdawgs.entity.League l1=null;
		 for( int j = 0; j < l.size(); j++ ) {
             l1= l.get(j);
             //Person founder = objectModel.findEstablishedBy( c );
             //Person founder = c.getFounder();
             
             league = new LinkedList<Object>();
             //league.add(team)
             league.add(l1.getName());
             league.add(l1.getId());
             
              //venue.add(s.getIsIndoor());
             leagues.add(league);
           }//for
		 try {
			resultTemplate.process( root, toClient);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        toClient.close();

 		
	}//post

}
