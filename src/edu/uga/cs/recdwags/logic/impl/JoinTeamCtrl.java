package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.persistence.Impl.MembershipManager;

public class JoinTeamCtrl {

private ObjectLayer objectLayer = null;
    
    public JoinTeamCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }

    public void jointeam(Long id,String tname) throws RDException{
    	Student            student = null;
        Student              modelStudent = null;
        Iterator<Student>     studentIter = null;
        Team                 team = null;
        Team                 modelTeam = null;
        Iterator<Team>       teamIter = null;
        //MembershipManager mm=null;
        MembershipManager           membership = null;
        MembershipManager           modelMembership = null;
        Iterator<Team> memebrshipIter = null;

      //  Membership           membership = null;
       // Membership           modelMembership = null;
       // Iterator<Membership> membershipIter = null;

        System.out.println("tname:"+tname);
        modelTeam = objectLayer.createTeam();
        modelTeam.setName(tname);
        //modelTeam.setId(-1);
        
        teamIter = objectLayer.findTeam(modelTeam);
        System.out.println(teamIter.toString());
        //team=teamIter.next();
        //System.out.println("team::"+team);
        while(teamIter.hasNext()){
            team = teamIter.next();
            System.out.println( "CtrlJoinClub.joinClub: found club: " +team);
        }//while
        if(teamIter==null )
           throw new RDException( "Failed to locate a Club called: " + tname );
        //System.out.println("team"+team.getId());
        
        modelStudent = objectLayer.createStudent();
        modelStudent.setId(id);
        studentIter = objectLayer.findStudent( modelStudent );
        while(studentIter.hasNext()) {
            student= studentIter.next();
            System.out.println( "CtrlJoinClub.joinClub: found person: " +student);
        }
        System.out.println("student::"+student.getId());
        if(student== null )
            throw new RDException( "Failed to locate a Person with id: " +id);

         //objectLayer.createStudentMemberOfTeam(modelStudent, modelTeam);
        //modelMembership.setPerson( person );
        //modelMembership.setClub( club );
        memebrshipIter= objectLayer.restoreStudentMemberOfTeam(modelStudent);
        if(memebrshipIter==null){
        	System.out.println("Mebership table is null");
        }//if
        else if(memebrshipIter.hasNext())
            throw new RDException( "This person is already a member of this club" );

        System.out.println("team::"+team.getName()+"-->"+team.getId());
       // membership = objectLayer.createMembership( person, club, new Date() );
        //objectLayer.storeMembership( membership );
         objectLayer.createStudentMemberOfTeam(student, team);
        

        
    	
    	//objectLayer.deleteSportsVenue(sportsvenue);
    }//delete

	
	
}
