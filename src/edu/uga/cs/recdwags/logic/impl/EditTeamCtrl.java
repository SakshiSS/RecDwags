package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.presentation.SearchTeam;

public class EditTeamCtrl {
	
	
private ObjectLayer objectLayer = null;
    
    public EditTeamCtrl(ObjectLayer objectModel)
    {
        this.objectLayer = objectModel;
    }
    
    public long editTeam(String teamName, Long captainId, Long leagueName) throws RDException
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
        modelTeam = objectLayer.createTeam();
        modelTeam.setName(teamName);
        modelTeam.setId(-1);
        teamIter = objectLayer.findTeam(modelTeam);
//        if(!teamIter.hasNext())
//            throw new RDException( "A team with this name doesn't exist: " + teamName );
//        else{
//        	team = 
//        }
        
        modelLeague = objectLayer.createLeague();
        modelLeague.setId(leagueName);
        leagueIter = objectLayer.findLeague(modelLeague);
        while(leagueIter.hasNext() ) {
            league = leagueIter.next();
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
        
        SearchTeamCtrl searchTeam = new SearchTeamCtrl(objectLayer);
        Team teamObj = null;
        try{
        teamObj = searchTeam.searchTeam(teamName); //question here
        System.out.println("TeamObj Id:"+teamObj.getId());
        }catch(Exception e){
        	System.out.println("Team doesn't exist. Please create one");
        }
        
        team.setName(teamName);
        if(teamObj != null)
        team.setId(teamObj.getId());
        System.out.println("Team Id:"+team.getId());
        team.setCaptain(student);
        team.setParticipatesInLeague(league);
        
        objectLayer.storeTeam(team);
        
    	
    	return team.getId();
    }


}
