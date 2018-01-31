package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindStudentTeamCtrl {

private ObjectLayer objectLayer = null;
    
    public FindStudentTeamCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    public List<Team> findTeam(String t_name) throws RDException{
    	
    	Team t=new TeamImplementor(t_name);
    	t.setId(-1);
    	Iterator<Team> i=objectLayer.findTeam(t);
    	List<Team> l1=new ArrayList<Team>();
    	Team t1=null;
    	while(i.hasNext()){
    	    t1=i.next();
    		l1.add(t1);
    		
    	}//while
    /*	Iterator<Student> s=objectLayer.restoreStudentMemberOfTeam(t1);
    	List<Student> l2=new ArrayList<Student>();
    	Student s1=null;
    	while(s.hasNext()){
    		s1=s.next();
    		l2.add(s1);
    	}//while
    	*/
    	return l1;
    	
    	//return null;
    }//findteam
    
	
}
