package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindStudentforTeam {
private ObjectLayer objectLayer = null;
    
    public FindStudentforTeam( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    public List<Student> findStudent(Team t) throws RDException{
    	Iterator<Student> i=objectLayer.restoreStudentMemberOfTeam(t);
    	Student s=null;
    	List<Student> ls=new ArrayList<Student>();
    	while(i.hasNext()){
    		s=i.next();
    		ls.add(s);
    	}//while
    	return ls;
    }//findStudent
	
}
