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
 * Servlet implementation class DeleteVenue
 */
@WebServlet("/DeleteVenue")
public class DeleteVenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static  String         templateDir = "WEB-INF/templates";
	  static  String         resultTemplateName = "DeleteVenue-Result.ftl";

	  private Configuration  cfg; 
	  public DeleteVenue() {
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
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		System.out.println("In Delete Venue");
		httpSession = request.getSession();
		
		 try {
	          resultTemplate = cfg.getTemplate( resultTemplateName );
	      } 
	      catch (IOException e) {
	          throw new ServletException( 
	                  "Can't load template in: " + templateDir + ": " + e.toString());
	      }

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
      String text=request.getParameter("txtVenueName");
      
		System.out.println("Text is::"+text);
		/*Long id=Long.parseLong(text);*/
		SportsVenue sv1=new SportsVenueImplementor();
		sv1.setName(text);
		List<SportsVenue> sv2=null;
		try {
			sv2=logicLayer.findSportsVenue(sv1);
		} catch (RDException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//SportsVenue> sv2=null;
		System.out.println("sv2:");
		for(int i=0;i<sv2.size();i++){
			SportsVenue sv3=sv2.get(i);
			System.out.println("sv3:"+sv3.getId());
			sv1.setId(sv3.getId());
			System.out.println("sv1 id::"+sv1.getId());
		}//for
		
		try {
			logicLayer.deleteVenue(sv1);
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

	
	}//post
	

}
