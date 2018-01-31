package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class AwayTeamForMatch {

private ObjectLayer objectLayer = null;
    
    public AwayTeamForMatch( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    public Team findTeam(Match m){
    	
    	Team t=null;
    	try {
			t=objectLayer.restoreTeamAwayTeamMatch(m);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("team::"+t.getName());
    	
    	return t;
    }

    
	
}
