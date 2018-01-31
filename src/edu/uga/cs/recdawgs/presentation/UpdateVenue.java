package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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
 * Servlet implementation class UpdateVenue
 */
@WebServlet("/UpdateVenue")
public class UpdateVenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static  String         templateDir = "WEB-INF/templates";
	  static  String         resultTemplateName = "UpdateVenue-Result.ftl";

	  private Configuration  cfg; 
	    public UpdateVenue() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


	  public void init() 
	  {
	      // Prepare the FreeMarker configuration;
	      // - Load templates from the WEB-INF/templates directory of the Web app.
	      //
	      cfg = new Configuration();
	      cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
	  }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Template       resultTemplate = null;
	    BufferedWriter toClient = null;
	      
		HttpSession httpSession=null;
		System.out.println("In UpdateVenue");
		httpSession = request.getSession();
		String ssid=null;
		Session session=null;
	    LogicLayer logicLayer=null;
	    String sv_name=null;
	    String sv_address=null;
	    String Indoor=null;
	    String outdoor=null;
	    String id=null;
	    
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
        
        System.out.println("Session and logic both successfull");
        id=request.getParameter("oldId");
        SportsVenue sv1=new SportsVenueImplementor();
		sv1.setName(id);
		
		List<SportsVenue> sv2=null;
		try {
			sv2=logicLayer.findSportsVenue(sv1);
		} catch (RDException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//SportsVenue> sv2=null;
		System.out.println("sv2:");
		long id2=0;
		for(int i=0;i<sv2.size();i++){
			SportsVenue sv3=sv2.get(i);
			System.out.println("sv3:"+sv3.getId());
			sv1.setId(sv3.getId());
			id2=sv3.getId();
		}//for
		
		
        sv_name=request.getParameter("svName");        
        sv_address=request.getParameter("svAddress");
        Indoor=request.getParameter("Indoor");
        outdoor=request.getParameter("Outdoor");
       // Long id1=Long.parseLong(id);
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
        
        try {
        	SportsVenue sv=new SportsVenueImplementor(sv_name,sv_address,isIndoor);
        	System.out.println("id::"+id2);
        	sv.setId(id2);
        	SportsVenue sv5=new SportsVenueImplementor(null,null,false);
        	sv5.setName(sv_name);
        	
        	List<SportsVenue> lsv=logicLayer.findSportsVenue(sv5);
        	
        	/*if(lsv.size()>0){
        		response.sendRedirect("Error.html");
        		return;
        	}//if*/
        	logicLayer.updateSportsVenue(sv);
			//sv_id=logicLayer.createSportsVenue(sv_name,sv_address,isIndoor);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Map<String,Object> root = new HashMap<String,Object>();
 
        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();

        
       
		
	}//doPost

}
