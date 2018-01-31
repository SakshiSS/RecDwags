package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.persistence.Impl.MembershipManager;

public class LeaveTeamCtrl {
	
private ObjectLayer objectLayer = null;
    
    public LeaveTeamCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }

    public void leaveteam(Long id,String tname) throws RDException{
    
    	Student            student = null;
        Student              modelStudent = null;
        Iterator<Student>     studentIter = null;
        Team                 team = null;
        Team                 modelTeam = null;
        Iterator<Team>       teamIter = null;
        MembershipManager           membership = null;
        MembershipManager           modelMembership = null;
        Iterator<Student> memebrshipIter = null;

        
        modelTeam = objectLayer.createTeam();
        modelTeam.setName(tname);
        
        
        teamIter = objectLayer.findTeam(modelTeam);
        System.out.println(teamIter.toString());
       
        while(teamIter.hasNext()){
            team = teamIter.next();
            System.out.println( "CtrlJoinClub.joinClub: found club: " +team);
        }//while
        System.out.println("team's captain"+team.getCaptain().getId());
        if(teamIter==null )
           throw new RDException( "Failed to locate a Club called: " + tname );
        
        modelStudent = objectLayer.createStudent();
        modelStudent.setId(id);
        studentIter = objectLayer.findStudent( modelStudent );
        while(studentIter.hasNext()) {
            student= studentIter.next();
            System.out.println( "CtrlJoinClub.joinClub: found person: " +student);
        }
       // System.out.println("student::"+student.getId());
        if(student== null )
            throw new RDException( "Failed to locate a Person with id: " +id);
        memebrshipIter= objectLayer.restoreStudentMemberOfTeam(team);
        boolean flag=false;
        while(memebrshipIter.hasNext()){
        	Student s=memebrshipIter.next();
        	if(s.getId()==student.getId()){
        		flag=true;
        		break;
        	}//if
        	
        }//while
        if(flag==false){
        	System.out.println("Student is Not a member of Team");
        	return;
        }//if
        objectLayer.deleteStudentMemberOfTeam(student, team);
 

        
    }//leave

	

}
