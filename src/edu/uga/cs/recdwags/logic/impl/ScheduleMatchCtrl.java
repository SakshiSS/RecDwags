package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class ScheduleMatchCtrl {
	
	
	ObjectLayer objectLayer=null;
	public ScheduleMatchCtrl(ObjectLayer objectLayer){
        this.objectLayer = objectLayer;
    }
	
	public void scheduleMatch(Match m){
		System.out.println("MATCH IS CTRL"+m.getId()+"-->"+m.getDate());
		//Match modelMatch=new MatchImplementor(null,);
		Match modelMatch=new MatchImplementor(0,0,null,false,null,null);
		 modelMatch.setId(m.getId());
		 Iterator<Match> im=null;
		 List<Match> lm=null;
		 Match m1=null;
		 //System.out.println("match::");
		  try {
			im=objectLayer.findMatch(modelMatch);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  while(im.hasNext()){
			  m1=im.next();
			  System.out.println("m1::"+m1.getAwayPoints()+"-->"+m1.getSportsVenue().getId());
			  try {
				m1.setDate(m.getDate());
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  m1.setId(m.getId());
		  }//while
		try {
			objectLayer.storeMatch(m1);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//scheduleMatch
	

}
