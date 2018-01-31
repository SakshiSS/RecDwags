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
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class CancelRegisteration
 */
@WebServlet("/CancelRegisteration")
public class CancelRegisteration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static  String         templateDir = "WEB-INF/templates";
	  static  String         resultTemplateName = "CancelRegistration-Result.ftl";
	  private Configuration  cfg; 
	  
    public CancelRegisteration() {
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
	  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Template       resultTemplate = null;
	    BufferedWriter toClient = null;
	    
		System.out.println("in cancel registration");
		HttpSession httpSession=null;
		//System.out.println("Create SportsVenue");
		httpSession = request.getSession();
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

toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding()));

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
        String Name=new String("Name");
        String id=new String("id");
		String name=(String) httpSession.getAttribute(Name);
		//long id1=(long) httpSession.getAttribute(id);
	    //System.out.println("id1::"+id1);
	    System.out.println("name::"+name);
	    Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
	    s.setUserName(name);
	    s.setId(-1);
	    List<Student> l=null;
	     try {
			l=logicLayer.findStudent(s);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     long id1=0;
	     for(int i=0;i<l.size();i++){
	    	 System.out.println("id::"+l.get(i).getId());
	    	 id1=l.get(i).getId();
	     }//for
	     
	     Student s1=new StudentImplementor(null,null,null,null,null,null,null,null);
	     s1.setUserName(name);
	     s1.setId(id1);
	     logicLayer.cancelRegis(s1);
	     
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
