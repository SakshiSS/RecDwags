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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    private Configuration     cfg;
	static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "Register-Result.ftl";

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Template       resultTemplate = null;
	    BufferedWriter toClient = null;
	
		HttpSession httpSession=null;
		System.out.println("Register");
		httpSession = request.getSession();
		String ssid=null;
		Session session=null;
	    LogicLayer logicLayer=null;
	    
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
 ssid=(String)httpSession.getAttribute("ssid");
    
if( ssid != null) {
    System.out.println( "Already have ssid: " + ssid );
    session = SessionManager.getSessionById( ssid );
    //System.out.println( "Connection: " + session.getConnection());
}
else
    System.out.println( "ssid is null" );

if( session == null ) {
    try {
        session = SessionManager.createSession();
    }
    catch ( Exception e ) {
        //ClubsError.error( cfg, toClient, e );
    	System.out.println("Not Created Session");
    	e.printStackTrace();
        return;
    }
}
//LogicLayer  logicLayer = null;

logicLayer = session.getLogicLayer();
        System.out.println("Session and logic both successfull");
        String fname=request.getParameter("firstName");
        String lname=request.getParameter("lastName");
        String uname=request.getParameter("username");
        String pwd=request.getParameter("password");
        String email=request.getParameter("email");
        String studentId=request.getParameter("studentId");
        String major=request.getParameter("major");
        String address=request.getParameter("address");
        
        Student s=new StudentImplementor(fname,lname,uname,pwd,email,studentId,major,address);
        s.setId(-1);
        List<Student> ls=null;
		try {
			ls = logicLayer.findStudent(s);
			System.out.println("ls size::"+ls.size());
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ls.size()==0){
        logicLayer.createStudent(fname,lname,uname,pwd,email,studentId,major,address);
		}
		else{
			response.sendRedirect("Error.html");
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



	}

}
