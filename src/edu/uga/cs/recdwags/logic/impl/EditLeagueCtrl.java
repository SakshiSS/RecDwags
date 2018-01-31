package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class EditLeagueCtrl {

	private ObjectLayer objLayer = null;
	
	public  EditLeagueCtrl(ObjectLayer objLayer){
		this.objLayer = objLayer;
	}
	
	public long editLeague(String leagueName, String leagueRules, String matchRules, boolean isIndoor, int minTeams, int maxTeams, int minMembers, int maxMembers) throws RDException{
		
		League league = null;
		League modelLeague = objLayer.createLeague();
		Iterator<League> leagueItr = null;
		
	
		league = objLayer.createLeague();
		modelLeague = objLayer.createLeague();
		modelLeague.setId(-1);
		modelLeague.setName(leagueName);		
		modelLeague.setLeagueRules(leagueRules);
		modelLeague.setMatchRules(matchRules);
		modelLeague.setIsIndoor(isIndoor);
		modelLeague.setMinTeams(minTeams);
		modelLeague.setMaxTeams(maxTeams);
		modelLeague.setMinMembers(minMembers);
		modelLeague.setMaxMembers(maxMembers);
		
		leagueItr = objLayer.findLeague(modelLeague);
		
		if(leagueItr.hasNext()){
			league = leagueItr.next();
		}
		
		modelLeague.setId( league.getId());
		objLayer.storeLeague(modelLeague);
		
		
		
		
		return league.getId();
		
		
		
		
		
	}
	
}
