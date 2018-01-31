package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class SearchTeamCtrl {
	
	private ObjectLayer objectLayer = null;
	
	Team teamObject = null;
    
    public SearchTeamCtrl(ObjectLayer objectModel)
    {
        this.objectLayer = objectModel;
     }
    
    
    public Team searchTeam(String searchTeamName) throws RDException{
    	Iterator<Team> teamIter = null;
        Team team = null;
        teamObject = objectLayer.createTeam();
        teamObject.setName(searchTeamName);
        teamIter = objectLayer.findTeam(teamObject);
        
        while(teamIter.hasNext() ) {
            team = teamIter.next();
        }
    	
		return team;
    	
    }

}
