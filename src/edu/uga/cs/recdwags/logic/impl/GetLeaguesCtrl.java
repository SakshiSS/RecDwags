package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class GetLeaguesCtrl {
	
	private ObjectLayer obj = null;
	
	public GetLeaguesCtrl(ObjectLayer obj){
		this.obj = obj;		
	}
	
	public List<League> getLeagues() throws RDException{
		System.out.println("Entered GetLeaguesCtrl");
		List<League> leagueList = null;
		Iterator<League> leagueItr = null;
		League league = null;
		
		leagueList = new LinkedList<League>();
		leagueItr = obj.findLeague(null);
		while(leagueItr.hasNext() ){
			league = leagueItr.next();
			leagueList.add(league);
		}
		
		return leagueList;
	}

}
