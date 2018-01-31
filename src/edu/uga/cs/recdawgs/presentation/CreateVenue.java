package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
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
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class CreateVenue
 */
@WebServlet("/CreateVenue")
public class CreateVenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * 
     * 
     * 
     */
	
	
	static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "CreateVenue-Result.ftl";

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

        // TODO Auto-generated constructor stub
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Template       resultTemplate = null;
        BufferedWriter toClient = null;
        
		HttpSession httpSession=null;
		System.out.println("Create SportsVenue");
		httpSession = request.getSession();
		String ssid=null;
		Session session=null;
	    LogicLayer logicLayer=null;
	    String sv_name=null;
	    String sv_address=null;
	    
	    String Indoor=null;
	    String outdoor=null;
	    
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
        sv_name=request.getParameter("svName");        
        sv_address=request.getParameter("svAddress");
        Indoor=request.getParameter("Indoor");
        outdoor=request.getParameter("Outdoor");
        
        if(sv_name==null || sv_address==null){
        	System.out.println("Parametere are null");
        	return;
        }//if
        if( Indoor == null && outdoor==null ) {
           // ClubsError.error( cfg, toClient, "Unspecified founder_id" );
        	System.out.println("Is Indoor null");
            return;
        }//if
        boolean isIndoor=false;
        if(Indoor!=null){
        	isIndoor=true;
        }//if
        long sv_id=0;
        SportsVenue modelvenue=null;
        System.out.println("In CreateVenue Ctrl");
        modelvenue=new SportsVenueImplementor(sv_name,sv_address,isIndoor);
        modelvenue.setId(-1);
        
    	//modelvenue=objectLayer.createSportsVenue();
    	//modelvenue.setName(sv_name);
    	//modelvenue.setId(-1);
        List<SportsVenue> venueIter=null;
    	try {
			venueIter=logicLayer.findSportsVenue(modelvenue);
		} catch (RDException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	/*if(venueIter.hasNext()){
    		 throw new RDException("Sports Venue Already Exist");

    	}//if*/
        if(venueIter.size()>0){
        	response.sendRedirect("Error.html");
        	return;
        }//if

        try {
			sv_id=logicLayer.createSportsVenue(sv_name,sv_address,isIndoor);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("sv_id::"+sv_id);
       
        
     // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();

        root.put( "venue_name",sv_name);
        root.put( "venue_id", new Long(sv_id));

     // Merge the data-model and the template
        //
        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();

        
        

		
	}//post

}
