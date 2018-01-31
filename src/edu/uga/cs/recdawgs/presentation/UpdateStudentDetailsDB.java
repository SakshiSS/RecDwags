package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class UpdateStudentDetailsDB
 */
@WebServlet("/UpdateStudentDetailsDB")
public class UpdateStudentDetailsDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "UpdateStudentDetails-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentDetailsDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside post of updateStudentDetailsDB");
		Template resultTemplate = null;
		BufferedWriter toClient = null;
		String firstName = null; 
		String lastName = null; 
		String username = null; 
		String password = null; 
		String email = null;
		String type_user = null; 
		String studentId = null; 
		String major = null; 
		String address = null; 
		long user_id = 0;
		Student studentObject= null;

		HttpSession httpSession = null; 
		Session session = null; 
		LogicLayer logicLayer = null; 
		
		 try {
	            resultTemplate = cfg.getTemplate(resultTemplateName);
	        } 
	        catch (IOException e) {
	            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
	        }
	        
	        toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), resultTemplate.getEncoding() ));

	        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());
		
		String submit = request.getParameter("submit"); 
		System.out.println("Submit value :" +submit);
		String cancel = request.getParameter("cancel"); 
		System.out.println("Cancel value :" +cancel);
		if (cancel != null) {
			//response.sendRedirect("Admin_HomePage.html");
		}
		else if(submit != null) {
		System.out.println("inside submit");
			httpSession = request.getSession(); 
			String ssid = (String) httpSession.getAttribute("ssid"); 
			if(ssid != null) {
				session = SessionManager.getSessionById(ssid); 
			}
			//if session is not already started, try to start session  
			if(session == null) {
				try {
					session = SessionManager.createSession(); 
				}
				catch(Exception e){
					System.out.println("Unable to Create Session");
	            	e.printStackTrace();
	                return;
				}
			}//if session == null 
		
			logicLayer = session.getLogicLayer(); 
			
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName"); 
			username = request.getParameter("username");
			password = request.getParameter("password");		
			email = request.getParameter("email"); 
			studentId = request.getParameter("studentId"); 
			major = request.getParameter("major"); 
			address = request.getParameter("address"); 
			System.out.println("in db::studentid and address"+studentId+"-->"+address);
			//type_user = request.getParameter("type_user"); 
			studentObject = new StudentImplementor( firstName, lastName, username, password, email, studentId, major, address);
			long  student_id = (long) httpSession.getAttribute("id");
			
			System.out.println("Student id  in servlet :" +student_id);
			
					try {
						user_id = logicLayer.updateStudentDetails(student_id,firstName, lastName, username, password, email, studentId, major, address);
					} catch (RDException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				
				httpSession.setAttribute( "ssid", ssid );
			
				
//			if (ssid != null) {
//				System.out.println("Update Details Successful "+ssid);
//           	}//if
//           	else{
//        	   System.out.println("Update Details NOT Successful");
//			}	
			
			
			Map<String,Object> output_status = new HashMap<String,Object>();
			
			 output_status.put( "student_name", username);
		        output_status.put( "student_id", student_id);
		        
		        try {
		            resultTemplate.process(output_status, toClient);
		            toClient.flush();
		        } 
		        catch (TemplateException e) {
		            throw new ServletException( "Error while processing FreeMarker template", e);
		        }

		        toClient.close();
			
			
		} //else if submit != null
		else {
			System.out.println("no option got submitted");
		}
		
		
	}

}
