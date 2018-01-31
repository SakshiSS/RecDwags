package edu.uga.cs.recdwags.logic.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class LeagueWinnerCtrl {
private ObjectLayer objectLayer = null;
    
    public LeagueWinnerCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    public void setWinner(Long tid,Long lid) throws RDException{
    	Team t=new TeamImplementor(null);
    	t.setId(tid);
    	League l=new LeagueImplementor(null,null,null,false,0,0,0,0);
    	l.setId(lid);
    	objectLayer.createTeamWinnerOfLeague(t, l);
    	
    }//setWinner


}
