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
import edu.uga.cs.recdawgs.entity.impl.SportsVenueImplementor;
import edu.uga.cs.recdawgs.persistence.Impl.SportsVenueManager;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class FindVenue
 */
@WebServlet("/FindVenue")
public class FindVenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "FindAllClubs-Result.ftl";

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
    }//init

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindVenue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        List<List<Object>>  sportsVenues= null;
        List<Object>        venue = null;
        
		List<SportsVenue> sv=null;
		HttpSession         httpSession=null;
        Session             session=null;
        String              ssid=null;
        LogicLayer logicLayer=null;

		System.out.println("Find Sports Venue");
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
        Map<String,Object> root = new HashMap<String,Object>();
        
      System.out.println("session & logiclayer successfull");
      String text=request.getParameter("txtVenueName");
      System.out.println("test::"+text);
		if(text==""){
			text=null;
		}//if
		System.out.println("test::"+text);
		if(text==null){
			SportsVenue sv1=null;
			try {
				sv=logicLayer.findSportsVenue(sv1);
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//if
		
		else{
			System.out.println("text::"+text);
			SportsVenue sv1=new SportsVenueImplementor();
			sv1.setName(text);
			//sv1.setId(-1);
			//sv1.setAddress();
			
			try {
				sv=logicLayer.findSportsVenue(sv1);
				System.out.println("sv size"+sv.size());
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//else
		sportsVenues=new LinkedList<List<Object>>();
		 root.put( "sportVenues", sportsVenues);

		if(sv.size()<1){
			System.out.println("sv"+sv.get(0));
		}//if
		SportsVenue s=null;
		 for( int i = 0; i < sv.size(); i++ ) {
             s= (SportsVenue) sv.get( i );
             //Person founder = objectModel.findEstablishedBy( c );
             //Person founder = c.getFounder();
             venue = new LinkedList<Object>();
             venue.add(s.getId());
             venue.add(s.getName());
             venue.add(s.getAddress());
             boolean status=s.getIsIndoor();
             if(status){
            	 
            	 venue.add("Indoor");
             }
             else{
            	 venue.add("Outdoor");
             }
             //venue.add(s.getIsIndoor());
             sportsVenues.add(venue);
           }//for
		 try {
			resultTemplate.process( root, toClient );
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         toClient.flush();
		/*Iterator<SportsVenue> i=sv.iterator();
		while(i.hasNext()){
			SportsVenue sv2=i.next();
			System.out.println("sv2::"+sv2.getId());
		}//while*/
	}//post

}
