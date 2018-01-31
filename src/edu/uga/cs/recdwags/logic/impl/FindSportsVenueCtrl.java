package edu.uga.cs.recdwags.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindSportsVenueCtrl {
	
	 private ObjectLayer objectLayer = null;
	    
	    public FindSportsVenueCtrl( ObjectLayer objectModel )
	    {
	        this.objectLayer = objectModel;
	    }
	    
	    public List<SportsVenue> findVenue(SportsVenue sv) throws RDException{
	    	List<SportsVenue> 	sportvenues  = null;
	        Iterator<SportsVenue> venueitr = null;
	        SportsVenue  sv1 =null;
	        sportvenues=new ArrayList<SportsVenue>();
	        venueitr=objectLayer.findSportsVenue(sv);
	        while(venueitr.hasNext()){
	        	sv1=venueitr.next();
	        	sportvenues.add(sv1);
	        }//while

	    	
	    	
	    	
	    	return sportvenues;
	    }
	    
	    


}
