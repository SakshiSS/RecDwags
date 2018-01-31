package edu.uga.cs.recdwags.logic.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class AppointCaptainCtrl {
	private ObjectLayer objectLayer = null;

	public AppointCaptainCtrl(ObjectLayer objectLayer){
		this.objectLayer=objectLayer;
	}//appointcaptain
	public void appointCaptain(Student s,Team t) throws RDException{
	    //Studen=null;
		objectLayer.createStudentCaptainOfTeam(s, t);
	    

	}//aapointCaptain
	
}
