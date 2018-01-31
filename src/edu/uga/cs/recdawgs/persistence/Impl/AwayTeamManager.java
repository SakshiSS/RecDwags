package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class AwayTeamManager {
	
	private ResultSet rs = null;
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	private boolean more = false;
	
	
	public void Match(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;		
		
	}
	public AwayTeamManager(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;		
		
	}
	/*public void SportsVenue(Connection conn, ObjectLayer object){
		this.conn = conn;
		this.objectLayer = object;
	}*/
	
	 public void storeTeamAwayTeamMatch( Team team, Match match ) throws RDException{
		 
		 long teamId = team.getId();
		 long matchId = match.getId();
		 long teamAwayMatchId ;
		 
		 String updateTeamAwayTeamMatchSql = "update match_Table set awayteamid = ? where id=?";
		 
		 PreparedStatement pStatement = null;
		 int executeStatus;
		 
		 if(!team.isPersistent() || !match.isPersistent()){
			 throw new RDException("MatchAwayTeam: Attempting to save a link with no match or team defined");
		 }
		 
		 try{
			 
			 pStatement = (PreparedStatement) conn.prepareStatement(updateTeamAwayTeamMatchSql);
			 
			 pStatement.setLong(1, team.getId());
			 pStatement.setLong(2, match.getId());
			 
			 executeStatus = pStatement.executeUpdate();
			 
			 if(executeStatus >= 1){
				 String sql = "select last_insert_id()";
				 
				 if(pStatement.execute(sql)){
				 
				 ResultSet rs = pStatement.getResultSet();
				 while(rs.next()){
					 teamAwayMatchId = rs.getLong(1);
				 }
				 }			 
				 
			 }
			 else{
				 throw new RDException("Cannot create a link between match and team for awayTeam");
			 } 
			 		 
		 }
		 catch(SQLException e){
			 
		 }
	 }
	 
	 public Team restoreTeamAwayTeamMatch( Match match ) throws RDException{
		 String selectAwayTeamMatchSql = "select t.id,t.name,t.isCaptainof,t.lid from team t where t.id =?";
		 System.out.println("away team::"+match.getAwayTeam().getId());
		 PreparedStatement pstatement = null;
		 
		 if(!match.isPersistent()){
			 throw new RDException("MatchAwayTeam: Attempting to get a link with no match or away team link defined");
		 }
		
		 try{
			pstatement = (PreparedStatement) conn.prepareStatement(selectAwayTeamMatchSql) ;
			//int a_id=(int)match.getId();
			pstatement.setLong(1,match.getAwayTeam().getId());
			rs = pstatement.executeQuery();
			rs.next();
			long id=rs.getLong(1);
			String name = rs.getString(2);
			Team t=new TeamImplementor(name);
			t.setId(id);
			return t;
			
		 }
		 catch(SQLException e){
			 e.printStackTrace();
			 throw new RDException( "AwayTeamMatch link: Could not restore link between match and awayteam; Root cause: " + e );
		 }	 
		 
		 
	 }
	 
	 public Iterator<Match> restoreTeamAwayTeamMatch( Team team ) throws RDException, SQLException{
		 //String   selectMatchAwayTeamSql="select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,m.awayteamid,m.hometeamid from match_table m";
				 
				 /*"select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
				 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
				 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
				 " from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
				 " and at.lid=l.id and ht.lid=l.id";*/
				 
				 
				 //"select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,m.awayteamid,m.hometeamid from match_table m";//,sv.name,sv.address,"
				 //+" from match_table m";

			 /*+"sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username"+
"hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
			 +"l.matchRules,"
+ "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,*/ /*at.name,at.iscaptainof,as.username,"
			
			+"as.email,as.firstname,as.lastname,as.address,as.password,as.studentid,as.major"+ */
				 /*,round r,team ht,team at,user hs,user as,sportsVenue sv,league l*/ /*+" where m.svid=sv.id and m.rid=r.id and at.iscaptainof=as.id and ht.iscaptainof=hs.id"+*/
//" and at.lid=l.id and ht.lid=l.id";
		 		  
		 /*String selectMatchAwayTeamSql = "Select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,m.rid,m.hometeamid, "
			 							+"m.awayteamid where m.awayteamid = ?";*/
		 
		 /*String selectMatchAwayTeamSql="select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
				 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
				 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
				 "  from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
				 "and at.lid=l.id and ht.lid=l.id ";*/
		 
		/* String selectMatchAwayTeamSql= "select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
		 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
		 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
		 +"l.matchRules,"
		 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
		 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
		 "  from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and m.hometeamid= ht.id and m.awayteamid=at.id and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
		 "and at.lid=l.id and ht.lid=l.id ";*/
		 
		 
		 String selectMatchAwayTeamSql="select distinct m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
				 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
				 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
				 "  from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and ( m.awayteamid = at.id) and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
				 "and at.lid=l.id and ht.lid=l.id ";

		 StringBuffer query=new StringBuffer(100);
			query.append(selectMatchAwayTeamSql);
			if(team!=null){
				if(team.isPersistent()){
					int a_id=(int)team.getId();
					query.append(" and m.awayteamid="+a_id);
				}//if
			}//if
		 
			Statement stmt=null;
			try {
				stmt=(Statement) conn.createStatement();
				int id1=(int)team.getId();
				//stmt.setLong(1,id1);
				if(stmt.execute(query.toString())){
					ResultSet rs=stmt.getResultSet();
					return new MatchIterator(rs,objectLayer);
				}//if
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}/*finally{
				stmt.close();
				conn.close();
			}*/
			return null;
			}//restore
	 
	 public void deleteTeamAwayTeamMatch( Team team, Match match ) throws RDException{
		 String deleteMatchAwayTeamSql = "update match_table set awayteamid=null where id =? ";
			
			PreparedStatement    pStatement = null;
	        int                  executeStatus;
	             
	        if( !match.isPersistent() || !team.isPersistent()) 
	        	throw new RDException( "AwayTeamMatchLink: Attempting to delete a link with no match or team defined" );
	        
	        try {
	            pStatement = (PreparedStatement) conn.prepareStatement( deleteMatchAwayTeamSql );          
	            pStatement.setLong( 1, match.getId() );
	            executeStatus = pStatement.executeUpdate();
	            
	            if( executeStatus >= 1 ) {
	                return;
	            }
	            else
	                throw new RDException( "AwayTeamMatchLink: Attempting to delete a link with no match or team defined" );
	        }
	        catch( SQLException e ) {
	            e.printStackTrace();
	            throw new RDException( "AwayTeamMatchLink: Attempting to delete a link with no match or team defined" + e );        }
	}		    
	 

}
	


