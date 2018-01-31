package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import edu.uga.cs.recdawgs.object.ObjectLayer;

public class HomeTeamCtrl {

private ObjectLayer objectLayer = null;
    
    public HomeTeamCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    
    public List<Match> hometeam(long id,String t_name){
    	Team t=new TeamImplementor(t_name);	
		t.setId(id);
		List<Match> l=new ArrayList<Match>();
		Iterator<Match> i=null;
		try {
		 i=objectLayer.restoreTeamHomeTeamMatch(t);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(i.hasNext()){
			Match m=i.next();
			System.out.println("Homematch::"+m.getId());
			l.add(m);
		}//while
		return l;
    	
    }//hometeam

    
}
