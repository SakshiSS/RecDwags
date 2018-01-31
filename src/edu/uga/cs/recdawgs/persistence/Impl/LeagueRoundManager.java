package edu.uga.cs.recdawgs.persistence.Impl; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class LeagueRoundManager {

	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public LeagueRoundManager(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object; 

		
	}
		
	public void League(Connection conn, ObjectLayer object) {
		this.conn = conn;
		this.objectLayer = object; 
	}
	
	public void Round(Connection conn, ObjectLayer object) {
		this.conn = conn;
		this.objectLayer = object; 
	}

	//Store a link between a League and a Round of matches in the league.
	
	public void storeLeagueRound(League league, Round round) throws RDException {
		long leagueId = league.getId();
		long roundId = round.getId();
		
		String updateLeagueRoundSql = "UPDATE round SET lid=? WHERE id=? ";
		PreparedStatement stmt = null; 
		int inscnt;
		
		if(!league.isPersistent()  || !round.isPersistent()) {	 
			 throw new RDException( "LeagueRound.store: Attempting to store a link with no league or round defined" );
		}
		
		try {
			stmt = (PreparedStatement) conn.prepareStatement(updateLeagueRoundSql); 
			stmt.setLong(1, leagueId);
			stmt.setLong(2, roundId); 
			
			inscnt = stmt.executeUpdate();
			if(inscnt >= 1) {
				return; 
			}
		} //try
		catch (SQLException e) {
			e.printStackTrace(); 
			throw new RDException("LeagueRound.store: failed to store a League-Round link: " + e); 
		} //catch 
	} //storeLeagueRound

	//Return Rounds of matches for a given League
	
	public Iterator<Round> restoreLeagueRound(League league) throws RDException {
		//String getLeagueRoundSql = "SELECT R.id, R.number, R.lid FROM round R";
		String getLeagueRoundSql= "select r.id, r.number, l.id, l.name, " + 
    	"l.leagueRules, l.matchRules, l.isIndoor, l.minTeams, l.maxTeams, l.minMembers , l.maxMembers " + 
    	"from round r, league l where r.lid=l.id ";
		Statement stmt = null;
       	StringBuffer query = new StringBuffer( 100 );
       	StringBuffer condition = new StringBuffer( 100 );
       //	System.out.println("League Id- given:" +league.getId());
       	if(league.getId() == 0 || !league.isPersistent() ) {
       		throw new RDException("League restore : For the given link league is not persistant");
		}
		
		condition.setLength(0); 
		query.append(getLeagueRoundSql); 
		
		if (league != null) {
			if (league.getId() >= 0) {
				query.append(" and l.id=" + league.getId()); 
			}
		} //if round is not null
		
		try {
			System.out.println(query.toString());
			stmt = (Statement) conn.createStatement(); 
			
			if(stmt.execute(query.toString())) {
				ResultSet r = stmt.getResultSet();
				return new RoundIterator(r, objectLayer);
			} //if stmt.execute returned a result 
		} //try
		catch (Exception e){
			throw new RDException("LeagueRound.restore: failed to restore persistent LeagueRound object; Root cause: " + e); 
		}
		throw new RDException("LeagueRound.restore: failed to restore persistent LeagueRound object");
	} //restoreLeagueRound 

	//Delete a link between a League and a Round of matches in the league
	
	public void deleteLeagueRound(League league, Round round)
			throws RDException {
	/*	String deleteLeagueRoundSql = "UPDATE round SET lid=null where id=?";
		PreparedStatement stmt = null;
        int inscnt;
        
        if( !league.isPersistent() || !round.isPersistent()) // does the league or round object persistent? 
        	return; 
        }
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteLeagueRoundSql );          
            stmt.setLong(1, round.getId());
            inscnt = stmt.executeUpdate();
            
            //successful 
            if( inscnt == 1 ) {
                return;
            }
            else {
                throw new RDException( "LeagueRound.delete: failed to delete a relation between round and league" );
        	}
        } //try 
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "LeagueRound.delete: failed to delete a relation between venue and league: " + e );        }
		} //catch 		    
      */  	
	}//deleteLeagueRound 

} //LeagueRound 