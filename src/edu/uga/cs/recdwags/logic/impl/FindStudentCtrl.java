package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindStudentCtrl {
	private ObjectLayer objectLayer = null;
    
    public FindStudentCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    public List<Student> findStudent(Student s) throws RDException{
    
    	System.out.println("In FindStudent ctrl");
    	System.out.println("sname"+s.getUserName());
    	List<Student> 	students = null;
        Iterator<Student> studentitr = null;
        Student  stu =null;
        students=new ArrayList<Student>();
        studentitr=objectLayer.findStudent(s);
        while(studentitr.hasNext()){
        	stu=studentitr.next();
        	students.add(stu);
        }//while
        System.out.println("students size::"+students.size());
    	return students;
    }//find
 

}
