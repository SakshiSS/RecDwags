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
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/FindUser")
public class FindUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "FindUser-Result.ftl";

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
		
		Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        List<List<Object>>  teams= null;
        List<Object>        team = null;
        
		HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		System.out.println("In Delete Venue");
		httpSession = request.getSession();
		
		/* try {
	          resultTemplate = cfg.getTemplate( resultTemplateName );
	      } 
	      catch (IOException e) {
	          throw new ServletException( 
	                  "Can't load template in: " + templateDir + ": " + e.toString());
	      }

		 toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding()));

	      response.setContentType("text/html; charset=" + resultTemplate.getEncoding());
*/

		resultTemplate = cfg.getTemplate( resultTemplateName );
		toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding()));

		response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

		Map<String,Object> root = new HashMap<String,Object>();
		teams=new LinkedList<List<Object>>();
		
		 root.put( "teams",teams);
		
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
  String name=request.getParameter("txtTeamName");
  Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
  s.setUserName(name);
  s.setId(-1);
  List<Student> ls=null;
  try {
	ls=logicLayer.findStudent(s);
} catch (RDException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  System.out.println("size::"+ls.size());
  for(int k=0;k<ls.size();k++){
	  team=new LinkedList<Object>();
	  System.out.println("list::"+ls.get(k).getFirstName());
	  team.add(ls.get(k).getFirstName());
	  team.add(ls.get(k).getId());
	  team.add(ls.get(k).getAddress());
	  //team.add(ls.get(k).get)
	  teams.add(team);
  }//for
      //logicLayer.findStudent()
  try {
		resultTemplate.process( root, toClient );
	} catch (TemplateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   toClient.flush();
	
  
  
	}//post

}
