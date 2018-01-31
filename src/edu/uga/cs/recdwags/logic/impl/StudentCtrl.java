package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class StudentCtrl {
private ObjectLayer objectLayer = null;
    
    public StudentCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    public void createStudent(String fname, String lname, String uname, String pwd, String email, String studentId,String major, String address){
    /*Student s=new StudentImplementor(fname,lname,uname,pwd,email,studentId,major,address);
    s.setId(-1);
    Iterator<Student> i=null;
    try {
		i=objectLayer.findStudent(s);
	} catch (RDException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
    //System.out.println("i::"+i);
    /*if(i!=null){
    	try {
			throw new RDException("Duplicate Name");
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }//if*/
    	Student s=null;
    
    	try {
			s=objectLayer.createStudent(fname, lname, uname, pwd, email, studentId, major, address);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			objectLayer.storeStudent(s);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
