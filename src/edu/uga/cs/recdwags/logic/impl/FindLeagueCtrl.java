package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindLeagueCtrl {
	private ObjectLayer objectLayer = null;
    
    public FindLeagueCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    public List<League> findLeague(League l) throws RDException{
    	List<League> 	sportvenues  = null;
        Iterator<League> venueitr = null;
        League  sv1 =null;
        sportvenues=new ArrayList<League>();
        League league=null;
        venueitr=objectLayer.findLeague(l);
       while(venueitr.hasNext()){
        	sv1=venueitr.next();
        	sportvenues.add(sv1);
        }//while

  System.out.println("Size of venueitr"+venueitr.toString()); 
  
    	
    	
    	return sportvenues;
    }

    

}
