package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class AwayTeamMatchCtrl {

	ObjectLayer ob=null;
	public AwayTeamMatchCtrl(ObjectLayer ob){
		this.ob=ob;
	}
	
	public List<Match> findawaymatch(long id,String t_name){
		Team t=new TeamImplementor(t_name);	
		t.setId(id);
		List<Match> l=new ArrayList<Match>();
		Iterator<Match> i=null;
		try {
		 i=ob.restoreTeamAwayTeamMatch(t);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(i.hasNext()){
			Match m=i.next();
			System.out.println("Awaymatch::"+m.getId());
			l.add(m);
		}//while
		
		
		return l;
			
	}
	
	
}
