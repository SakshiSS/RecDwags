package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class UpdateVenueCtrl {
	
	
private ObjectLayer objectLayer = null;
    
    public UpdateVenueCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    
    public void createVenue(SportsVenue venue) throws RDException{
   //	SportsVenue modelvenue=null;
    //	SportsVenue venue=null;
    /*	Iterator<SportsVenue> venueIter=null;
    	modelvenue=objectLayer.createSportsVenue();
    	modelvenue.setName(sv_name);
    	venueIter=objectLayer.findSportsVenue(modelvenue);
    	if(venueIter.hasNext()){
    		 throw new RDException("Sports Venue Already Exist");

    	}//if*/
    	//venue=objectLayer.createSportsVenue(sv_name, sv_address, isIndoor);
    	
    	objectLayer.storeSportsVenue(venue);
    	//return venue.getId();
    }//createVenue



}
