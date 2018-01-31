package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class GetAllMatchesForRoundCtrl {
	
	private ObjectLayer objectLayer = null;
	
	public GetAllMatchesForRoundCtrl(ObjectLayer ob){
		this.objectLayer = ob;
	}
	
	public List<Match> getAllMatchesForRound(Integer roundNumber) throws RDException{
		Round round = null;
		Round modelRound = null;
		Match match = null;
		List<Match> matchList = new LinkedList<Match>();
		Iterator<Match> matchItr = null;
		Iterator<Round> roundItr = null;
		
		System.out.println("roundnumber"+roundNumber);
		round = objectLayer.createRound();
		round.setNumber(roundNumber);
		//round.setId(-1);
		round.setId(0);
		roundItr = objectLayer.findRound(round);
		
		if(roundItr.hasNext()){
			modelRound = roundItr.next();
			
		}
		
		
		System.out.println("Model Round Id: "+modelRound.getId());
		matchItr = objectLayer.restoreRoundMatch(modelRound);
		
		while(matchItr.hasNext()){
			match = matchItr.next();
			matchList.add(match);
		}
		
		return matchList;
		
		
	}

}
