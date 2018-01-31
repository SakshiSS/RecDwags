package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class CreateVenueCtrl {

private ObjectLayer objectLayer = null;
    
    public CreateVenueCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }

    public long createVenue( String sv_name,String sv_address,boolean isIndoor) throws RDException{
    	SportsVenue modelvenue=null;
    	SportsVenue venue=null;
    	Iterator<SportsVenue> venueIter=null;
    	/*System.out.println("In CreateVenue Ctrl");
    	modelvenue=objectLayer.createSportsVenue();
    	modelvenue.setName(sv_name);
    	modelvenue.setId(-1);
    	venueIter=objectLayer.findSportsVenue(modelvenue);
    	if(venueIter.hasNext()){
    		 throw new RDException("Sports Venue Already Exist");

    	}//if*/
    	venue=objectLayer.createSportsVenue(sv_name, sv_address, isIndoor);
    	
    	objectLayer.storeSportsVenue(venue);
    	return venue.getId();
    }//createVenue
	
}//createVenueCtrl
