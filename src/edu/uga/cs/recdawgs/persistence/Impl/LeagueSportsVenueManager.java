package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class LeagueSportsVenueManager {
	private ResultSet rs = null;
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	private boolean more = false;
	
	
	/*public void League(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;		
		
	}
	public void SportsVenue(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;
	}*/
	
	public  LeagueSportsVenueManager(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;
		
	}
	
	
	 public void storeLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException{
		 long leagueId = league.getId();
		 long sportsVenueId = sportsVenue.getId();
		 
		 String insertLeagueSportsVenueSql = "insert into has  (svid,lid) values(?,?)";
		 
		 //from where to get this id?
		 PreparedStatement pStatement = null;
		 int executeStatus;
		 long leagueVenueLinkId;
		 
		 
		 if(!league.isPersistent()  || !sportsVenue.isPersistent() )	 
			 throw new RDException( "LeagueSportsVenueLink: Attempting to save a link with no league or venue defined" );
		 
		 try{
			 
			 pStatement = (PreparedStatement)conn.prepareStatement(insertLeagueSportsVenueSql);
			 pStatement.setLong(1, league.getId());
			 pStatement.setLong(2, sportsVenue.getId());
			 
			 executeStatus = pStatement.executeUpdate();
			 
			 if(executeStatus >= 1){
				 String sql = "select last_insert_id()";
				 
				 if( pStatement.execute( sql ) ){
					 ResultSet rs = pStatement.getResultSet();
					 
					 while(rs.next()){
						 leagueVenueLinkId = rs.getLong(1);
						 //where to store link id?
					 }
				 }
			 }
			 else{
				 throw new RDException("Cannot create a link between league and sportsVenue");
			 }
			 
			 
			 
			 
		 }
		 catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 
		 
		 
	 }

	   
	    public Iterator<League> restoreLeagueSportsVenue( SportsVenue sportsVenue ) throws RDException{
	    	
	    	String getLeagueVenueLinksSql = "Select h.lid, l.name,l.leagueRules,l.matchRules,l.isIndoor,"
	    									+"l.minTeams,l.maxTeams,l.minMembers,l.maxMembers from"
	    									+"has h, league l, sportsVenue s where"
	    									+"h.svid = s.id and h.lid = l.id ";
	    	
	    	 Statement    stmt = null;
	         StringBuffer query = new StringBuffer( 100 );
	         StringBuffer condition = new StringBuffer( 100 );
	         
	         if(sportsVenue.getId() == 0 || !sportsVenue.isPersistent() )
	        	 throw new RDException("SportsVenue restore : For the given link sports venue is not persistant");
	         
	         condition.setLength(0);
	         
	         query.append(getLeagueVenueLinksSql);
	         
	         if(sportsVenue != null){
	        	 if(sportsVenue.isPersistent()){
	        		 query.append( " where s.id = " + sportsVenue.getId() );
	        	 }
	         }
	         
	         try {
	             stmt = conn.createStatement();

	             
	             if( stmt.execute( query.toString() ) ) { // statement returned a result
	                 ResultSet r = stmt.getResultSet();
	                 return new LeagueIterator( r, objectLayer );
	             }
	         }
	         catch( Exception e ) {      
	             throw new RDException( "LeagueSportsVenueLink.restore: Could not restore persistent league object; Root cause: " + e );
	         }

	         // if we reach this point, it's an error
	         throw new RDException( "LeagueSportsVenueLink: Could not restore persistent league object" ); 
	         
	    }

	    
	    public Iterator<SportsVenue> restoreLeagueSportsVenue( League league ) throws RDException{
			String getLeagueVenueLinksSql = "Select h.svid, s.name,s.address,s.isIndoor from"
								+"has h, league l, sportsVenue s where"
								+"h.svid = s.id and h.lid = l.id ";
				
				Statement    stmt = null;
				StringBuffer query = new StringBuffer( 100 );
				StringBuffer condition = new StringBuffer( 100 );
				
				if(league.getId() == 0 || !league.isPersistent() )
				throw new RDException("League restore : For the given link league is not persistant");
				
				condition.setLength(0);
				
				query.append(getLeagueVenueLinksSql);
				
				if(league != null){
				if(league.isPersistent()){
				query.append( " and l.id = " + league.getId() );
				}
				}
				
				try {
				stmt = conn.createStatement();
				
				// retrieve the persistent Person object
				//
				if( stmt.execute( query.toString() ) ) { // statement returned a result
				ResultSet r = stmt.getResultSet();
				return new SportsVenueIterator( r, objectLayer );
				}
				}
				catch( Exception e ) {      // just in case...
				throw new RDException( "LeagueSportsVenueLink.restore: Could not restore persistent league object; Root cause: " + e );
				}
				
				// if we reach this point, it's an error
				throw new RDException( "LeagueSportsVenueLink: Could not restore persistent league object" ); 

	    }

	   
	    		public void deleteLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException{
	    		
	    			String deleteLeagueVenueSql = "delete from has where lid = ? and svid = ?";
	    			
	    			PreparedStatement    stmt = null;
	    	        int                  inscnt;
	    	             
	    	        if( !league.isPersistent() || !sportsVenue.isPersistent()) // is the league or sportsvenue object persistent?  If not, nothing to actually delete
	    	            return;
	    	        
	    	        
	    	        try {
	    	            stmt = (PreparedStatement) conn.prepareStatement( deleteLeagueVenueSql );          
	    	            stmt.setLong( 1, league.getId() );
	    	            stmt.setLong(2, sportsVenue.getId());
	    	            inscnt = stmt.executeUpdate();
	    	            
	    	            if( inscnt == 1 ) {
	    	                return;
	    	            }
	    	            else
	    	                throw new RDException( "LeagueSportsVenueLink.delete: failed to delete a realation between venue and league" );
	    	        }
	    	        catch( SQLException e ) {
	    	            e.printStackTrace();
	    	            throw new RDException( "LeagueSportsVenueLink.delete: failed to delete a realation between venue and league " + e );        }
	    }

}
