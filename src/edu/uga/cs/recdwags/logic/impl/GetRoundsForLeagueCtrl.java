package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class GetRoundsForLeagueCtrl {
	
	ObjectLayer objectLayer = null;
	League leagueObject = null;
	Round roundObject = null; 
	public  GetRoundsForLeagueCtrl(ObjectLayer objectLayer){
		this.objectLayer = objectLayer;
		
	}
	
	public  List<Integer> findRoundsForLeague(String leagueName) throws RDException{
		
		
		Round round = null;
		Iterator<Round> roundItr = null;
		List<Integer> rounds =new ArrayList<Integer>();
		Iterator<League> leagueItr = null;
		League modelLeague = null;
		leagueObject = objectLayer.createLeague();
        leagueObject.setName(leagueName);
        System.out.println("In GetRoundsForLeagueCtrl id:"+leagueObject.getId());
        System.out.println("In GetRoundsForLeagueCtrl id:"+leagueObject.getName());
        leagueObject.setId(-1);
        leagueItr = objectLayer.findLeague(leagueObject);
        
        if(leagueItr.hasNext()){
        	System.out.println("League Iter has league");
        	modelLeague = leagueItr.next();
        }
        
        
		roundItr = objectLayer.restoreLeagueRound(modelLeague);
		
		while(roundItr.hasNext()){
			round = roundItr.next();
			rounds.add(round.getNumber());
			//rounds.add(round);
		}
		
		
		return rounds;
	}

}
