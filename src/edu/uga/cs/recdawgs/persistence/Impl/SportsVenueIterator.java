package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.impl.SportsVenueImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class SportsVenueIterator implements Iterator<SportsVenue> {

	    private ResultSet   rs = null;
	    private ObjectLayer objectLayer = null;
	    private boolean     more = false;

	public SportsVenueIterator(ResultSet r, ObjectLayer ob) {
		this.rs=r;
		this.objectLayer=ob;
		try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            try {
				throw new RDException( "SportsVenueIterator: Cannot create an iterator; root cause: " + e );
			} catch (RDException e1) {
				e1.printStackTrace();
			}
        }
	}//sportsVenueIterator

	@Override
	public boolean hasNext() {
		return more;
	}

	@Override
	public SportsVenue next() {
		long id=0;
		String name=null;
	    String address=null;
	    boolean isIndoor=false;
	    if(more) {
	    	
				try {
					id=rs.getLong(1);
					name=rs.getString(2);
					address=rs.getString(3);
					isIndoor=rs.getBoolean(4);
					more = rs.next();
		            
				} catch (SQLException e) {
					e.printStackTrace();
				}
				SportsVenue sv=null;
				try {
					sv=objectLayer.createSportsVenue(name, address, isIndoor);
					sv.setId(id);
				} catch (RDException e) {
					e.printStackTrace();
				}
				
			   //SportsVenue sv=new SportsVenueImplementor(name,address,isIndoor);
				   //sv.setId(id);
			       return sv;
		}//if
	    else{
	    	//System.out.println("No SportsVenue");
	    	throw new NoSuchElementException("SportsVenue::no Next sportsvenue object");
	    }//else
		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}

}
