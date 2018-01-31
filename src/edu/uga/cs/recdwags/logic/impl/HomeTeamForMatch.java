package edu.uga.cs.recdwags.logic.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class HomeTeamForMatch {

private ObjectLayer objectLayer = null;
    
    public HomeTeamForMatch( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }

public Team findTeamAway(Match m1){
	Team t=null;
	System.out.println("Logic layer::"+m1.getId());
	try {
		t=objectLayer.restoreTeamHomeTeamMatch(m1);
	} catch (RDException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return t;
}
	
	
}
