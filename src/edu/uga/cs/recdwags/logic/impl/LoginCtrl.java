package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;

public class LoginCtrl {
	
	ObjectLayer ob=null;
	public LoginCtrl( ObjectLayer objectLayer )
    {
        this.ob = objectLayer;
    }
	
	public String login( Session session, String userName, String password )
            throws RDException
    {
        String ssid = null;
        
        Student modelStudent =ob.createStudent();
        modelStudent.setUserName(userName);
        modelStudent.setPassword(password);
        Iterator<Student> students = ob.findStudent(modelStudent);
        if(students==null){
        	return null;
        }
        if( students.hasNext() ) {
            Student student = students.next();
           System.out.println("student type"+student.gettype_user());
           String type_user=student.gettype_user();
          
            session.setUser(student);
            
            ssid = SessionManager.storeSession( session );
        }
        else
            throw new RDException( "SessionManager.login: Invalid User Name or Password" );
        
        return ssid;
    }
    

}
