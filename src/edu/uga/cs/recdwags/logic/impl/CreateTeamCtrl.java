package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class CreateTeamCtrl {
	
private ObjectLayer objectLayer = null;
    
    public CreateTeamCtrl(ObjectLayer objectModel)
    {
        this.objectLayer = objectModel;
    }
    
    public long createTeam(String teamName, Long captainId, Long leagueName) throws RDException
    {
        Team team  = null;
        Team modelTeam = null;
        Iterator<Team> teamIter = null;
        League league = null;
        League modelLeague = null;
        Iterator<League> leagueIter = null;
        Student student = null;
        Student modelStudent = null;
        Iterator<Student> studentIter = null;
        
        team = objectLayer.createTeam();
        
//        modelTeam = objectLayer.createTeam();
//        modelTeam.setName(teamName);
//        teamIter = objectLayer.findTeam(modelTeam);
//        if(teamIter.hasNext())
//            throw new RDException( "A team with this name already exists: " + teamName );
//        
        modelLeague = objectLayer.createLeague();
        modelLeague.setId(leagueName);
        leagueIter = objectLayer.findLeague(modelLeague);
        if(leagueIter != null){
        while(leagueIter.hasNext()) {
            league = leagueIter.next();
        }
        }
        
        if(league == null )
            throw new RDException( "A league with this name does not exist: " + leagueName );
        
        modelStudent = objectLayer.createStudent();
        modelStudent.setId(captainId);
        studentIter = objectLayer.findStudent(modelStudent);
        while(studentIter.hasNext()) {
        	student = studentIter.next();
        }
        
        if(student == null )
            throw new RDException( "A captain with this id does not exist: " + captainId );
        
        
        team.setName(teamName);
        team.setCaptain(student);
        team.setParticipatesInLeague(league);
        
        objectLayer.storeTeam(team);
        
    	
    	return team.getId();
    }

}
