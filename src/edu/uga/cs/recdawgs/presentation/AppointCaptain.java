package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
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
 * Servlet implementation class AppointCaptain
 */
@WebServlet("/AppointCaptain")
public class AppointCaptain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static  String         templateDir = "WEB-INF/templates";
	static  String         resultTemplateName = "AppointCaptain-Result.ftl";
	private Configuration  cfg; 
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointCaptain() {
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

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Template       resultTemplate = null;
	    BufferedWriter toClient = null;
	    
		HttpSession httpSession=null;
		Session session=null;
		String ssid=null;
		LogicLayer logicLayer=null;
		System.out.println("In AppointCaptain");
		httpSession = request.getSession();
		try {
	          resultTemplate = cfg.getTemplate( resultTemplateName );
	      } 
	      catch (IOException e) {
	          throw new ServletException( 
	                  "Can't load template in: " + templateDir + ": " + e.toString());
	      }

		toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() ));

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
      /*  String txt=request.getParameter()
        System.out.println("txt"+txt);
        String Name=new String("Name");
		String uname=(String)httpSession.getAttribute(Name);
		System.out.println("uname in joinTeam presentaion::"+uname);
		Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
		s.setId(id);
		Team t=new TeamImplementor(txt);
		logicLayer.appointCaptain(s,t);*/
        /*String stid=request.getParameter("stid");
        String teamid=request.getParameter("teamid");
        System.out.println("student and team id::"+stid+"--->"+teamid);
        //int stid1=Integer.parseInt(stid);
		Long stid1=Long.parseLong(stid);
		Long teamid1=Long.parseLong(teamid);
		Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
		s.setId(stid1);
		Team t=new TeamImplementor(null);
		t.setId(teamid1);
		try {
			logicLayer.appointCaptain(s,t);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        String sid=request.getParameter("btnLeagueId");
        Integer sid1=Integer.parseInt(sid);
        System.out.println("sid::"+sid);
        Long id=Long.parseLong(sid);
        //logicLayer.appointCaptain()
        Long tid= (Long)httpSession.getAttribute("tid");
        System.out.println("tid:"+tid);
        
        Team t=new TeamImplementor(null);
        t.setId(tid);
        Student s1=new StudentImplementor(null,null,null,null,null,null,null,null);
        s1.setId(sid1);
        try {
			logicLayer.appointCaptain(s1, t);
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
