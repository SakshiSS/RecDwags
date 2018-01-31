package edu.uga.cs.recdwags.logic.impl;



import java.util.Iterator;
import java.util.Random; 

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;

public class ProfileCtrl {
	
	ObjectLayer objectLayer=null;
	public ProfileCtrl(ObjectLayer objectLayer){
        this.objectLayer = objectLayer;
    }
	
	public long updateStudentDetails(long id,String firstName, String lastName, String username, String password, String email, String studentId, String major, String address) throws RDException{
		Student student  = null;
        Student modelStudent = null;
        Iterator<Student> studentItr = null;
        
        student = objectLayer.createStudent();
        
        
        modelStudent = objectLayer.createStudent();
        
		
		modelStudent.setId(id);
		
		modelStudent.setFirstName(firstName);
		modelStudent.setLastName(lastName);
		modelStudent.setUserName(username);
		modelStudent.setPassword(password);
		modelStudent.setEmailAddress(email);
		modelStudent.setStudentId(studentId);
		modelStudent.setMajor(major);
		modelStudent.setAddress(address); 
		
		studentItr = objectLayer.findStudent(modelStudent);
		
		while(studentItr.hasNext()) {
        	student = studentItr.next();
        }
		
		  if(student == null )
	            throw new RDException( "A student with this id does not exist: " + studentId );
		objectLayer.storeStudent(modelStudent); 
		return student.getId();
		
		
	}
}//updateStudentDetails

//	public void updateAdministratorDetails(Session session, String firstName, String lastName, String username, String password, String email) throws RDException {
//		Administrator admin = session.getUser(administrator);
//		admin.setFirstName(firstName);
//		admin.setLastName(lastName);
//		admin.setUserName(username);
//		admin.setPassword(password);
//		admin.setEmailAddress(email);
//		ob.storeAdministrator(admin); 
//	}//updateAdministratorDetails
//
//	public long resetUserPassword(String username) throws RDException {
//		String ssid = null;
//        User modelUser = ob.createStudent();
//        modelStudent.setUserName(userName);
//		Iterator<Student> students = ob.findStudent(modelStudent);
//		String tempPassword = generateRandomPassword(); 
//        if( students.hasNext() ) {
//        	//if no studentId, student is actually an administrator
//        	if (students.hasNext().getStudentId() == null) {
//        		Aministrator administrator = (Administrator) students.next(); 
//        		administrator.setPassword(tempPassword);
//        		ob.storeAdministrator(administrator); 
//        		session.setUser(administrator); 
//        	}
//        	else {
//            	Student student = students.next();
//            	student.setPassword(tempPassword);
//            	ob.storeStudent(student); 
//            	session.setUser(student);
//            }
//            ssid = SessionManager.storeSession( session );
//            PrintWriter out = response.getWriter(); 
//            response.setContentType("text/html");
//            out.println("<script type=\"text/javascript\">);
//            out.println("alert(tempPassword);");  
//            out.println("</script>");
//        }
//        else
//            throw new RDException( "SessionManager.login: Invalid User Name or Password" );
//        }
//        return ssid;
//	}//resetUserPassword
//	
//	private String generateRandomPassword() {
//		Random rand = new Random();
//		String password = ""; 
//		int randNum;
//		char nextChar;  
//		int numChars = (int) (rand.nextInt()*16) + 5;
//		for(int i=0; i < numChars; i++) {
//			randNum = (rand.nextInt()*26) + 95; 
//			nextChar = (char) randNum;
//			password = password + nextChar;
//		}
//		return password; 
//	}//generateRandomPassword()
//
//} //ProfileCtrl
