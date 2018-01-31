package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
//import java.mysql.Statement

 
public class TeamManager {
	
	private ResultSet rs = null;
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	private boolean more = false;
	
	public TeamManager(Connection conn, ObjectLayer objectLayer){
		this.conn = conn;
		this.objectLayer = objectLayer;
	}
	
	public Iterator<Team> restoreTeam(edu.uga.cs.recdawgs.entity.Team team) throws SQLException{
		//long id =team.getId();
		//System.out.println("In Rstore Team"+team.getName());
		String restoreTeamSql = "select t.id,t.name,t.iscaptainof,l.id,l.name,"
			+"l.leagueRules,l.matchRules,l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,"
			+" u.username,u.email,u.firstname,u.lastname,u.address,u.password,u.studentid,u.major"
			+" from league l,team t,user u,isMemberof m where t.lid = l.id and u.id=t.iscaptainof and u.id=m.uid and m.teamid=t.id";
		Statement statement = null;
		StringBuffer query = new StringBuffer(500);
		StringBuffer condition = new StringBuffer(500);
		
		condition.setLength(0);
		query.append(restoreTeamSql);
		
		if(team!=null){
			System.out.println("team!=null");
			if(team.getId() > -1 ){
				System.out.println("team.getid>-1");
				query.append(" and t.id = " + team.getId());
			}
			
			else if(team.getName() != null){
				System.out.println("In team.getname");
				String name="'"+team.getName()+"'";
				System.out.println("name"+name);
				query.append(" and t.name="+name);
			}
			else {
				if(team.getCaptain().getId() > -1){
				int captainid=(int)team.getCaptain().getId();
				query.append(" and t.iscaptainof =" + captainid);
			}
				
			}
		}
		
		try{
			statement = (Statement) conn.createStatement();
			if(statement.execute(query.toString())){
				System.out.println("stmt executed");
				ResultSet rs = statement.getResultSet();
				return new TeamIterator(rs,objectLayer);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		/*finally{
			statement.close();
			conn.close();
		}*/
		
		return null;
	}

	public void storeTeam(Team team) throws RDException, SQLException{
		
		String storeLeagueSql = "insert into team (name,isCaptainof,lid) values(?,?,?)";
		String updateLeagueSql = "update team set name=?,isCaptainof =?,lid=? where id=?";
		PreparedStatement pStatement = null;	
		int executeStatus ;
		long teamId;
		try{
		
		if(!team.isPersistent() ){
			//throw new RDException( "TeamManagement: Attempting to save a team with team not defined" );
			pStatement = (PreparedStatement) conn.prepareStatement(storeLeagueSql);
		}
		else
			pStatement = (PreparedStatement) conn.prepareStatement(updateLeagueSql);
		
		if(team.getName() != null)
			pStatement.setString(1, team.getName());
		else
			throw new RDException("Cannot insert team object");
						 
			 
		if( team.getCaptain().getId() > -1 ) {
				 int captain = (int)team.getCaptain().getId();
				 pStatement.setInt(2, captain);                
	                
	          }
	    else
	    	pStatement.setNull(2, java.sql.Types.INTEGER);
	    
			 
		if(team.getParticipatesInLeague().getId() > -1){
				 int league_id = (int)team.getParticipatesInLeague().getId();
				 pStatement.setInt(3, league_id); //error while getting the details of lid...
		}
		else
		pStatement.setNull(3, java.sql.Types.INTEGER);
			 
	    if(team.isPersistent()){
	    	pStatement.setInt(4, (int) team.getId()); 
	    }
	        executeStatus = pStatement.executeUpdate();
	        
	        if( executeStatus >= 1 ) {
                String sql = "select last_insert_id()";
                if( pStatement.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = pStatement.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        teamId = r.getLong( 1 );
                        if( teamId > 0 )
                            team.setId( teamId ); 
                    }
                }
            }
            else
                throw new RDException( "TeamManagement: failed to save a team" );
		
		
	}
		catch(SQLException e){
		e.printStackTrace();
		//throw new RDException( "League.storeLeague: failed to save a League information: " + e );			
		}
		/*finally{
		pStatement.close();
		conn.close();
		}*/
	}

	public void deleteTeam(Team team) throws RDException{
		System.out.println("Delete Team block");
		String deleteTeamSql;
		if(team.getName() != null){
		deleteTeamSql = "delete from team where name=?";
		}
		else{
		   deleteTeamSql = "delete from team where id=?";
		}
		PreparedStatement pStatement = null;
		int executeStatus;
		// if(!team.isPersistent()){
		// return;
		// }
		try{
		pStatement = (PreparedStatement) conn.prepareStatement(deleteTeamSql);
		System.out.println(pStatement);
		if(team.getName() != null){
		System.out.println("Inside: "+pStatement);
		pStatement.setString(1, team.getName());
		}
		else if(team.getId() >= 0){
		pStatement.setLong(1, team.getId());
		}
		else
		throw new RDException("deleteTeam: trying to delete a team that doesnot exist");
		try{
		executeStatus = pStatement.executeUpdate();
		if( executeStatus == 1 ) {
		System.out.println("Deleted");
		            return;
		        }
		else
		             throw new RDException( "Team.deleteTeam: failed to delete a team" );
		}
		    catch(Exception e){
		e.printStackTrace();
		}
		 
		        
		}
		     catch( SQLException e ) {
		         e.printStackTrace();
		         throw new RDException( "Team.deleteTeam: failed to delete a team: " + e );        
		         }
	}
	}

