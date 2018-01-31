package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class RoundMatchManager {
	
	private ResultSet rs = null;
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	private boolean more = false;
	
public RoundMatchManager(Connection con,ObjectLayer ob){
	this.conn=con;
	this.objectLayer=ob;
}
/*	public void Match(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;		
		
	}
	public void Round(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;
	}*/

	 // Round--includes-->Match;   multiplicity: 1 - 1..*
    //
    /** 
     * Store a link between a Round and a Match to be played in the Round.
     * @param round the Round to be linked
     * @param match the Match to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeRoundMatch( Round round, Match match ) throws RDException{
    	long matchId = match.getId();
		 long roundId = round.getId();
		 
		 String updateSql="update match_table set rid=? where id =?;";
		 PreparedStatement pStatement = null;
		 int executeStatus;
		 long matchVenueLinkId;
		 if(!match.isPersistent()  || !round.isPersistent() )	 
			 throw new RDException( "MatchRoundLink: Attempting to save a link with no match or venue defined" );
		 
		 try{
			 pStatement = (PreparedStatement)conn.prepareStatement(updateSql);
			 pStatement.setLong(1, round.getId());
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
				 throw new RDException("Cannot create a link between match and round");
			 } 
			 
		 }
		 catch(SQLException e){
			 e.printStackTrace();
		 } 
    	
    }

    /** 
     * Return Matches played in a given Round.
     * @param round the Round
     * @return an Iterator with all matches played in the round
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Match> restoreRoundMatch( Round round ) throws RDException{
    	
    	String getMatchRoundLinksSql = "select distinct m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				 +"sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
"hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
+ "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
	" from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and m.rid=r.id and m.hometeamid=ht.id and m.awayteamid=at.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id"+
" and (at.lid=l.id or ht.lid=l.id)";
				

      	PreparedStatement stmt = null;
      	StringBuffer query = new StringBuffer( 100 );
      	StringBuffer condition = new StringBuffer( 100 );

      	if(round.getId() == 0 || !round.isPersistent() )
      		throw new RDException("Match restore : For the given link match is not persistant");

      	condition.setLength(0);

      	query.append(getMatchRoundLinksSql);

      	if(round != null){
      		if(round.isPersistent()){
      			System.out.println("round is persistent:"+round.getId());
      			query.append( " and m.rid = " + round.getId() );
      		}
      	}
      	System.out.println("Statement :" +query);
      	try {
      		stmt = (PreparedStatement) conn.prepareStatement(getMatchRoundLinksSql);

      		// retrieve the persistent Person object
      		//
      		if( stmt.execute( query.toString() ) ) {// statement returned a result
      			System.out.println("Query executed: ");
      			ResultSet r = stmt.getResultSet();
      			return new MatchIterator( r, objectLayer );
      		}
      	}
      	catch( Exception e ) {      // just in case...
      		throw new RDException( "MatchRoundLink.restore: Could not restore persistent match object; Root cause: " + e );
      	}

      	// if we reach this point, it's an error
      	throw new RDException( "MatchRoundLink: Could not restore persistent match object" ); 
    }

    /** 
     * Delete a link between a Round and a Match to be played in the Round.
     * @param round the Round
     * @param match the Match
     * @throws RDException in case an error occurred during the store operation 
     */
    public void deleteRoundMatch( Round round, Match match ) throws RDException{
    	
    	String deleteMatchRoundSql = "update match_table set rid=null where id =?";
		
		PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !match.isPersistent() || !round.isPersistent()) // is the league or sportsvenue object persistent?  If not, nothing to actually delete
        	throw new RDException( "MatchRoundLink: Attempting to delete a link with no match or venue defined" );
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteMatchRoundSql );          
            stmt.setLong( 1, match.getId() );
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RDException( "LeagueSportsRoundLink.delete: failed to delete a realation between venue and league" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "LeagueSportsVenueLink.delete: failed to delete a realation between venue and league " + e );        }
}		    
    	
    	

}
