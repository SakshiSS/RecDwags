package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class TeamLeaguectrl {
	private ObjectLayer objectLayer = null;

	public TeamLeaguectrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
	
	public List<Team> findTeam(League l) throws RDException{
		System.out.println("league is::"+l.getId());
		Iterator<Team> i1=objectLayer.restoreTeamParticipatesInLeague(l);
		List<Team> l1=new ArrayList<Team>();
		while(i1.hasNext()){
			Team t1=i1.next();
			
			l1.add(t1);
		}//while
		return l1;
		
		//return null;
		
		
	}


}
