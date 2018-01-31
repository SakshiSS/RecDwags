package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.persistence.Impl.TeamIterator;

public class FindAllTeamsCtrl {

	 private ObjectLayer objectLayer = null;
	    
	    public FindAllTeamsCtrl(ObjectLayer objectModel)
	    {
	        this.objectLayer = objectModel;
	    }

	    public List<Team> findAllTeams()
	            throws RDException
	    {
	        List<Team> teams = null;
	        Iterator<Team> teamIter = null;
	        Team team = null;
            System.out.println("In findallteamctrl");
	        teams = new LinkedList<Team>();
	        
	        // retrieve all Team objects
	        //
	        teamIter = objectLayer.findTeam(null);
	       
	        System.out.println("Check to see:"+teamIter.hasNext());
	        while(teamIter.hasNext()) {
	        	
	            team = teamIter.next();
	            teams.add(team);
	        }

	        return teams;
	    }

}
