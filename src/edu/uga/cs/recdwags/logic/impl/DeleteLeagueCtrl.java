package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class DeleteLeagueCtrl {

	private ObjectLayer objectLayer = null;
	//Team deleteTeam = null;
	League deleteLeague = null;
	    
	    public DeleteLeagueCtrl(ObjectLayer objectModel)
	    {
	        this.objectLayer = objectModel;
	    }
	    
	    public void deleteLeague(String deleteLeagueName) throws RDException{
	 
	        //Team team = objectLayer.createTeam();
	    	League league = objectLayer.createLeague();
	    	league.setName(deleteLeagueName);
	    	//System.out.println("l2::"+l2.getId());
	        objectLayer.deleteLeague(league);
	    
	    	
	    }
	
}
