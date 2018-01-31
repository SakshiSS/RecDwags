package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

//import javax.security.auth.login.Configuration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   /* static  String  templateDir = "WEB-INF/templates";
    static  String  resultTemplateName = "MainWindow.ftl";

    private Configuration  cfg;*/ 

  /*  public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), 
                "WEB-INF/templates"
                );
    }*/


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=null;
		String password=null;
		HttpSession httpsession=null;
		Session session=null;
		LogicLayer  logicLayer = null;
        System.out.println("In Login");
        String loginAdmin = request.getParameter("LoginAdmin");
        String loginStudent=request.getParameter("LoginStudent");
        String register = request.getParameter("Register");
        if(loginAdmin != null){
        	System.out.println("For loginAdmin");
        	//ServletContext context=request.getSession().getServletContext();
            //context.getRequestDispatcher("/LoginAdmin").forward(request,response);
        	
        	
        	
        	
        	
        //System.out.println("For loginStudent");
        	//ServletContext context=request.getSession().getServletContext();
            //context.getRequestDispatcher("/LoginStudent").forward(request,response);
        	
        	/*logicLayer = session.getLogicLayer();
 		   username=request.getParameter("uname");
 			password=request.getParameter("password");
 			System.out.println("username:"+username);
 			System.out.println("password"+password);
 			String Name=new String("Name");
 			if( username == null || password == null || username=="" || password=="" ) {
 	           //ClubsError.error( cfg, toClient, "Missing user name or password" );
 	           response.sendRedirect("Error.html");
 	            System.out.println("User and pwd null");
 				return;
 	        }//if*/
 			//httpsession.setAttribute(Name,username);
 			
        	
        	httpsession=request.getSession();
    		String ssid=(String)httpsession.getAttribute("ssid");
    		
    		
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
    		   username=request.getParameter("uname");
    			password=request.getParameter("password");
    			System.out.println("username:"+username);
    			System.out.println("password"+password);
    			String Name=new String("Name");
    			if( username == null || password == null || username=="" || password=="") {
    	           // ClubsError.error( cfg, toClient, "Missing user name or password" );
    				response.sendRedirect("Error1.html");
    	            System.out.println("User and pwd null");
    				return;
    	        }//if
    			Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
    			s.setUserName(username);
    			s.setPassword(password);
    			
    			String id=new String("id");
    			httpsession.setAttribute(Name,username);
    			Student s1=null;
    			try {          
    	            ssid = logicLayer.login( session, username, password );
    	            if(ssid==null){
    	            	response.sendRedirect("Error1.html");
    	            }//if
    	            System.out.println( "Obtained ssid: " + ssid );
    	            httpsession.setAttribute( "ssid", ssid );
    	            System.out.println( "Connection: " + session.getConnection() );
    	            session = SessionManager.getSessionById( ssid );
    	            
    	           // Session s=(Session)httpsession.getAttribute(ssid);
    	             s1=session.getUser();
    	            System.out.println("Student::"+s1.getAddress());
    	            System.out.println("Student::"+s1.getStudentId());
    	            if(s1.getStudentId()!=null && ssid!=null){
    	            	response.sendRedirect("Error1.html");
    	            }//if
    	            
    	        } 
    			
    	        catch ( Exception e ) {
    	           // ClubsError.error( cfg, toClient, e );
    	        	response.sendRedirect("Error1.html");
    	            return;
    	        }
    			System.out.println("s1.getid::"+s1.getId());
    			httpsession.setAttribute(id,s1.getId());
    			
               if((ssid!=null) && (s1.getStudentId()==null)){
            	   System.out.println("Login Successfull"+ssid);
            	   response.sendRedirect("Admin_HomePage.html");
            	
    		
    	}//if

	

        	
        	
        }//if
        if(loginStudent!=null){
        	System.out.println("For loginStudent");
        	//ServletContext context=request.getSession().getServletContext();
            //context.getRequestDispatcher("/LoginStudent").forward(request,response);
        	
        	/*logicLayer = session.getLogicLayer();
 		   username=request.getParameter("uname");
 			password=request.getParameter("password");
 			System.out.println("username:"+username);
 			System.out.println("password"+password);
 			String Name=new String("Name");
 			if( username == null || password == null || username=="" || password=="" ) {
 	           //ClubsError.error( cfg, toClient, "Missing user name or password" );
 	           response.sendRedirect("Error.html");
 	            System.out.println("User and pwd null");
 				return;
 	        }//if*/
 			//httpsession.setAttribute(Name,username);
 			
        	
        	httpsession=request.getSession();
    		String ssid=(String)httpsession.getAttribute("ssid");
    		
    		
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
    		   username=request.getParameter("uname");
    			password=request.getParameter("password");
    			System.out.println("username:"+username);
    			System.out.println("password"+password);
    			String Name=new String("Name");
    			if( username == null || password == null || username=="" || password=="") {
    	           // ClubsError.error( cfg, toClient, "Missing user name or password" );
    				response.sendRedirect("Error1.html");
    	            System.out.println("User and pwd null");
    				return;
    	        }//if
    			httpsession.setAttribute(Name,username);
    			Student s1=null;
    			try {          
    	            ssid = logicLayer.login( session, username, password );
    	            if(ssid==null){
    	            	response.sendRedirect("Error1.html");
    	            }//if
    	            System.out.println( "Obtained ssid: " + ssid );
    	            httpsession.setAttribute( "ssid", ssid );
    	            System.out.println( "Connection: " + session.getConnection() );
    	            session = SessionManager.getSessionById( ssid );
    	            
    	           // Session s=(Session)httpsession.getAttribute(ssid);
    	             s1=session.getUser();
    	            System.out.println("Student::"+s1.getAddress());
    	            System.out.println("Student::"+s1.getStudentId());
    	            
    	            HttpSession session_Student_Id = request.getSession();
    	            session_Student_Id.setAttribute("StudentId", s1.getId());
    	            
    	            HttpSession session_Student_Name = request.getSession();
    	            session_Student_Id.setAttribute("StudentName", s1.getUserName());
    	            
    	            
    	            
    	            if(s1.getStudentId()==null && ssid!=null){
    	            	response.sendRedirect("Error1.html");
    	            }//if
    	            
    	        } 
    	        catch ( Exception e ) {
    	           // ClubsError.error( cfg, toClient, e );
    	        	response.sendRedirect("Error1.html");
    	            return;
    	        }
    			
               if((ssid!=null) && (s1.getStudentId()!=null)){
            	   System.out.println("Login Successfull"+ssid);
            	   response.sendRedirect("Admin_HomePage.html");
            	
    		
    	}//if

	

        	
        	
	    	
        }//if
        
        if( register != null){
        	System.out.println("for register");
        	response.sendRedirect("Register.html");
        }
       /* httpsession=request.getSession();
		String ssid=(String)httpsession.getAttribute("ssid");
		   if( ssid != null ) {
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
	        }*/
	        
		   /*logicLayer = session.getLogicLayer();
		   username=request.getParameter("uname");
			password=request.getParameter("password");
			System.out.println("username:"+username);
			System.out.println("password"+password);
			String Name=new String("Name");
			if( username == null || password == null ) {
	           // ClubsError.error( cfg, toClient, "Missing user name or password" );
	            System.out.println("User and pwd null");
				return;
	        }//if
			httpsession.setAttribute(Name,username);
			try {          
	            ssid = logicLayer.login( session, username, password );
	            System.out.println( "Obtained ssid: " + ssid );
	            httpsession.setAttribute( "ssid", ssid );
	            System.out.println( "Connection: " + session.getConnection() );
	        } 
	        catch ( Exception e ) {
	           // ClubsError.error( cfg, toClient, e );
	            return;
	        }
			*/
           /*if(ssid!=null){
        	   System.out.println("Login Successfull"+ssid);
        	   response.sendRedirect("Admin_HomePage.html");
        	   //ServletContext context=request.getSession().getServletContext();
   			   //context.getRequestDispatcher("/FindVenue").forward(request,response);
   	
           }//if
           else{
        	   System.out.println("NOT Successfull"+ssid);
                  
           }*/
           

			
			
   
		
	}//dopost

}
