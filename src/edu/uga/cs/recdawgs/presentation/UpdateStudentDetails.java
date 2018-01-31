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

import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
//import edu.uga.cs.recdawgs.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Servlet implementation class UpdateStudentDetails
 */
@WebServlet("/UpdateStudentDetails")
public class UpdateStudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "UpdateUserProfile.ftl";

    private Configuration cfg; 

    public void init() 
    {
    	try{
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do get of updateUserDeatils");
		HttpSession studentSession = request.getSession();
		studentSession.setAttribute("StudentId", 17);
		HttpSession sess_Student_Name = request.getSession();
		//sess_Student_Name.setAttribute("StudentName","sakshi16");
		//String student_name = (String) request.getSession().getAttribute("StudentName");
		
		Template resultTemplate = null;
		BufferedWriter toClient = null;
        LogicLayer logicLayer = null;
        Student studentObject = null;
       List <Student> getStudent = null;
        long student_id = 0;
        String firstName = null;
        String lastName = null;
        String userName = null;
        String passWord = null;
        String email = null;
        String studentId = null;
        String major = null;
        String address = null;
       
        List<List<Object>>  students= new LinkedList<List<Object>>();
        List<Object>        student = null;
        
        HttpSession httpSession;
        Session session;
        String ssid;
        
        
        long user_id =  Long.parseLong(request.getSession().getAttribute("StudentId").toString());
        System.out.println("Student Id :" + user_id);
        
        try {
		      resultTemplate = cfg.getTemplate( resultTemplateName );
		  } 
		  catch( IOException e ) {
		      throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
		  }
		  toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
			//response.setContentType("text/html; charset=" +resultTemplate.getEncoding());
			
			httpSession = request.getSession();
			if(httpSession == null) { //not logged in session expired
				//ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
				System.out.println("No session  for the user");
		          return;

			}
			
			ssid = (String) httpSession.getAttribute("ssid");
			if(ssid == null){
				//not logged in
				System.out.println("Not logged in");
				return;
			}
			session = SessionManager.getSessionById(ssid);
			if(session == null){
				//show error
				System.out.println("Session expired, not logged ");
				return;
			}
			
			 logicLayer = session.getLogicLayer();
			
			Map<String,Object> root = new HashMap<String,Object>();
			root.put("students",students);
			  
			  try { 
				 
			     String Name=new String("Name");
			     String student_name=(String) httpSession.getAttribute("Name");
			     System.out.println("studentusername::"+student_name);
				  String id=new String("id");
				  studentObject = new StudentImplementor( null, null, null, null, null, null, null, null);
			      studentObject.setUserName(student_name);
			      studentObject.setId(-1);
				  getStudent =logicLayer.findStudent(studentObject);
				  for(int i=0;i<getStudent.size();i++){
					  student_id = getStudent.get(i).getId();
					  httpSession.setAttribute("id",student_id);
					  firstName  = getStudent.get(i).getFirstName();
					  lastName = getStudent.get(i).getLastName();
					  userName = getStudent.get(i).getUserName() ;
					  passWord = getStudent.get(i).getPassword();
					  email = getStudent.get(i).getEmailAddress();
					  studentId = getStudent.get(i).getStudentId();
					  major = getStudent.get(i).getMajor();
					  address = getStudent.get(i).getAddress();
					  student=new LinkedList<Object>();
					  student.add(getStudent.get(i).getId());
					  student.add(getStudent.get(i).getFirstName());
					  student.add(getStudent.get(i).getLastName());
					  student.add(getStudent.get(i).getUserName());
					  student.add(getStudent.get(i).getPassword());
					  student.add(getStudent.get(i).getEmailAddress());
					  student.add(getStudent.get(i).getStudentId());
					  student.add(getStudent.get(i).getMajor());
					  student.add(getStudent.get(i).getAddress());
					  
					  students.add(student);
					  System.out.println("the student details" + student_id + " " +firstName+" "+lastName+" "+userName+" "+passWord+" "+email+" "+major+" " +address);	  
		  
					  }//for
			
			      // Build the data-model
			      //
			      
			     /*studentMap.put("Student_Id", student_id);
			     studentMap.put("user_name", userName);
			     studentMap.put("first_name", firstName);
			     studentMap.put("last_name", lastName);
			     studentMap.put("password", passWord);
			     studentMap.put("email", email);
			     studentMap.put("major", major);
			     studentMap.put("address", address);*/
			     
			      
			       
			      

			  } 
			  catch (Exception e) {
				// TODO: handle exception
				  e.printStackTrace();
			}
			  
			  try {
			      resultTemplate.process(root, toClient );
			      toClient.flush();
			  } 
			  catch (Exception e) {
			      throw new ServletException( "Error while processing FreeMarker template", e);
			  }

			  toClient.close();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
