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
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class ListStudentOfTeam
 */
@WebServlet("/ListStudentOfTeam")
public class ListStudentOfTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static  String         templateDir = "WEB-INF/templates";
	static  String         resultTemplateName = "StudentForTeam-Result.ftl";
	private Configuration  cfg; 
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStudentOfTeam() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Template       resultTemplate = null;
	    BufferedWriter toClient = null;
	    
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

		toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() ));

	      response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

		
        ssid = (String) httpSession.getAttribute( "ssid" );

        if( ssid == null ) {       // not logged in!
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("ssid=nul");
            return;
        }//if

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("session==null");
            return; 
        }//if
        
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            System.out.println("logiclayer=null");
        	return; 
        }//if
        
        System.out.println("sesion and logic");
        String t_name=request.getParameter("txtTeamName");
        List<Team> l1=null;
        try {
			l1=logicLayer.findStudentforTeam(t_name);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long id=0;
        for(int i=0;i<l1.size();i++){
        	System.out.println("l1::"+l1.get(i).getId());
        	id=l1.get(i).getId();
        }//for
        Team t=new TeamImplementor(t_name);
        t.setId(id);
        List<Student> l=null;
        try {
			l=logicLayer.findStudentforTeam(t);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Map<String,Object> root = new HashMap<String,Object>();
   	 List<List<Object>> students=null;
   	  List<Object>       student = null;
    
        students=new LinkedList<List<Object>>();
		 root.put( "students",students);
		 String tid=new String("tid");
		 httpSession.setAttribute(tid,id);
        for(int j=0;j<l.size();j++){
        	System.out.println("Student::"+l.get(j).getId());
        	student = new LinkedList<Object>();
            student.add(id);
        	student.add(l.get(j).getId());
        	student.add(l.get(j).getFirstName());
        	students.add(student);
        }//for
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
