package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class SearchLeagueCtrl {
private ObjectLayer objectLayer = null;
League leagueObject = null;
    
    public SearchLeagueCtrl(ObjectLayer objectModel)
    {
        this.objectLayer = objectModel;
    }
    
    public League searchLeague(String leagueName) throws RDException{
    	Iterator<League> leagueItr = null;
        League league = null;
        leagueObject = objectLayer.createLeague();
        leagueObject.setName(leagueName);
        leagueObject.setId(-1);
        leagueItr = objectLayer.findLeague(leagueObject);
        
        while(leagueItr.hasNext() ) {
            league = leagueItr.next();
            
            System.out.println("leagueName: "+league.getName());
            System.out.println("leagueName: "+league.getName());
            System.out.println("leaguemin team"+league.getMaxTeams());
            
        }
    	
		return league;
    	
    }
}
