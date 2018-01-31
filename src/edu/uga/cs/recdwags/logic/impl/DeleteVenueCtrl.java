package edu.uga.cs.recdwags.logic.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class DeleteVenueCtrl {
private ObjectLayer objectLayer = null;
    
    public DeleteVenueCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }

    public void deleteVenue(SportsVenue sportsvenue) throws RDException{
    	
    	objectLayer.deleteSportsVenue(sportsvenue);
    }//delete
        

}
