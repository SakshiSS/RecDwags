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
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class JoinTeam
 */
@WebServlet("/JoinTeam")
public class JoinTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static  String         templateDir = "WEB-INF/templates";
	static  String         resultTemplateName = "JoinTeam-Result.ftl";
	private Configuration  cfg; 

   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinTeam() {
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
    }//init

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
		Template       resultTemplate = null;
	    BufferedWriter toClient = null;
	      
		HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		System.out.println("JoinTeams");
		
		try {
	          resultTemplate = cfg.getTemplate( resultTemplateName );
	      } 
	      catch (IOException e) {
	          throw new ServletException( 
	                  "Can't load template in: " + templateDir + ": " + e.toString());
	      }

		toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() ));

	      response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

	      
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

        String Name=new String("Name");
		String uname=(String)httpSession.getAttribute(Name);
		System.out.println("uname in joinTeam presentaion::"+uname);
		//ObjectLayer ob=new ObjectLayer();
		Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
		s.setUserName(uname);
		s.setId(-1);
		List<Student> ls=null;
		try {
			ls = logicLayer.findStudent(s);
			System.out.println("ls size::"+ls.size());
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Student s1=null;
		for(int i=0;i<ls.size();i++){
			 s1=ls.get(i);
			System.out.println("Student in presentation ::"+s1.getId());
		}//for
		System.out.println("uname::"+uname);
		String tName=request.getParameter("txtTeamName");
		long id=s1.getId();
		logicLayer.joinTeam(id,tName);
		
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
