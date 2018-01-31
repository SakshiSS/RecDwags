package edu.uga.cs.recdwags.logic.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class CancelCtrl {
	
	ObjectLayer ob=null;
	public CancelCtrl(ObjectLayer ob){
		this.ob=ob;
	}
	
	public void cancelregis(Student s)throws RDException{
		ob.deleteStudent(s);
		
	
	}
		


}
