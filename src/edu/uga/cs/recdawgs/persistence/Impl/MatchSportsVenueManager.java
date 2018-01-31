package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class MatchSportsVenueManager {
	
	private ResultSet rs = null;
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	private boolean more = false;
	
	
	public void Match(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;		
		
	}
	public void SportsVenue(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;
	}
	
	public MatchSportsVenueManager(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;
	}

	/**
	 * @param args
	 */
		// TODO Auto-generated method stub
		
		// Match--isPlayedAt-->SportsVenue;   multiplicity: * - 1
	    //
	    /** 
	     * Store a link between a Match and a SportsVenue used in the match.
	     * @param match the Match to be linked
	     * @param sportsVenue the SportsVenue to be linked
	     * @return 
	     * @throws RDException in case an error occurred during the store operation 
	     */		
		 public void storeMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException
		 {
			 long matchId = match.getId();
			 long sportsVenueId = sportsVenue.getId();
			 
			 String updateSql="update match_table set svid=? where id =?";
			 
			 
			 PreparedStatement pStatement = null;
			 int executeStatus;
			 long matchVenueLinkId;
			 if(!match.isPersistent()  || !sportsVenue.isPersistent() )	 
				 throw new RDException( "MatchSportsVenueLink: Attempting to save a link with no match or venue defined" );
			 
			 try{
				 
				 pStatement = (PreparedStatement)conn.prepareStatement(updateSql);
				 pStatement.setLong(1, sportsVenue.getId());
				 pStatement.setLong(2, match.getId());
				 
				 executeStatus = pStatement.executeUpdate();
				 
				 if(executeStatus >= 1){
					 String sql = "select last_insert_id()";
					 
					 if( pStatement.execute( sql ) ){
						 ResultSet rs = pStatement.getResultSet();
						 
						 while(rs.next()){
							 matchVenueLinkId = rs.getLong(1);
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

		    /** 
		     * Return the Matches played at a given SportsVenue.
		     * @param sportsVenue the SportsVenue
		     * @return an Iterator of all Matches played at the SportsVenue
		     * @throws RDException in case an error occurred during the restore operation 
		     */
		    public Iterator<Match> restoreMatchSportsVenue( SportsVenue sportsVenue ) throws RDException{
		    	
		    	String getMatchVenueLinksSql = "Select m.id, m.home_points, m.away_points, m.mdate, m.isCompleted, m.svid, m.rid, m.hometeamid, m.awayteamid from"
						+"match m, sportsVenue s";
						
		
		PreparedStatement stmt = null;
		StringBuffer query = new StringBuffer( 100 );
		StringBuffer condition = new StringBuffer( 100 );
		
		if(sportsVenue.getId() == 0 || !sportsVenue.isPersistent() )
		throw new RDException("Match restore : For the given link match is not persistant");
		
		condition.setLength(0);
		
		query.append(getMatchVenueLinksSql);
		
		if(sportsVenue != null){
		if(sportsVenue.isPersistent()){
		query.append(" where m.svid = " + sportsVenue.getId() );
		}
		}
		
		try {
		stmt = (PreparedStatement) conn.prepareStatement(getMatchVenueLinksSql);
		
		// retrieve the persistent Person object
		//
		if( stmt.execute( query.toString() ) ) { // statement returned a result
		ResultSet r = stmt.getResultSet();
		return new NewMatchIterator( r, objectLayer );
		}
		}
		catch( Exception e ) {      // just in case...
		throw new RDException( "LeagueSportsVenueLink.restore: Could not restore persistent league object; Root cause: " + e );
		}
		
		// if we reach this point, it's an error
		throw new RDException( "LeagueSportsVenueLink: Could not restore persistent league object" ); 
		 
		    	
		    }

		    /** 
		     * Return SportsVenue where a given Match was played.
		     * @param match the Match
		     * @return SportsVenue where the Match was played
		     * @throws RDException in case an error occurred during the restore operation 
		     */
		    public SportsVenue restoreMatchSportsVenue( Match match ) throws RDException{
		    	
	  	String getMatchSportsVenueLinksSql = "Select s.id, s.name, s.address, s.isIndoor from"
						+"match m, sportsVenue s where"
						+"s.id = ?";
		
		PreparedStatement stmt = null;
		StringBuffer query = new StringBuffer( 100 );
		StringBuffer condition = new StringBuffer( 100 );
		
		if(match.getId() == 0 || !match.isPersistent() )
		throw new RDException("Match restore : For the given link match is not persistant");
		
		condition.setLength(0);
		
		query.append(getMatchSportsVenueLinksSql);
		
		try {		
		stmt = (PreparedStatement)conn.prepareStatement(getMatchSportsVenueLinksSql);
		 stmt.setLong(1, match.getSportsVenue().getId());
		
		// retrieve the persistent Person object
		//
		if( stmt.execute( query.toString() ) ) { // statement returned a result
		ResultSet r = stmt.getResultSet();
		SportsVenue spv = null;
		spv.setId(rs.getLong("id"));
		spv.setName(rs.getString("name"));
		spv.setAddress(rs.getString("address"));
		spv.setIsIndoor(rs.getBoolean("isIndoor"));
		return spv;
		}
		}
		catch( Exception e ) {      // just in case...
		throw new RDException( "LeagueSportsVenueLink.restore: Could not restore persistent league object; Root cause: " + e );
		}
		
		// if we reach this point, it's an error
		throw new RDException( "LeagueSportsVenueLink: Could not restore persistent league object" ); 
		    	
		    }

		    /** 
		     * Delete a link between a Match and a SportsVenue used in the match.
		     * @param match the Match
		     * @param sportsVenue the SportsVenue
		     * @throws RDException in case an error occurred during the delete operation 
		     */
		    public void deleteMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException{
		    	
		    	String deleteMatchVenueSql = "update match_table set svid=null where id =?";
    			
    			PreparedStatement    stmt = null;
    	        int                  inscnt;
    	             
    	        if( !match.isPersistent() || !sportsVenue.isPersistent()) // is the league or sportsvenue object persistent?  If not, nothing to actually delete
    	        	throw new RDException( "LeagueSportsVenueLink: Attempting to delete a link with no match or venue defined" );
    	        
    	        
    	        try {
    	            stmt = (PreparedStatement) conn.prepareStatement( deleteMatchVenueSql );          
    	            stmt.setLong( 1, match.getId() );
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
		


