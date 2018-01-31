package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.sql.Connection;
//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class ParticipationManager {
   
	Connection con=null;
	ObjectLayer ob=null;
	
	
	public ParticipationManager(Connection con1,ObjectLayer ob1){
	   this.con=con1;
	   this.ob=ob1;
	}
	
	
	
	public void storeTeamParticipatesInLeague( Team team, League league ) throws RDException, SQLException{
		//String  insertMembershipSql = "insert into team(name,lid) values(?,?)";
		String update="update team set lid=? where id=?";
		 PreparedStatement    stmt = null;
	     int    inscnt;
	     long   teamid,uid;
	     if(!team.isPersistent() || !league.isPersistent()){
	    	 throw new RDException( "TeamLeagueLink: Attempting to save a link with no team or league defined" );
			 
	     }//if
	     try {
				stmt=(PreparedStatement) con.prepareStatement(update);
				stmt.setString(1,team.getName());
				stmt.setLong(2,league.getId());
				inscnt = stmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
	}//store
	
	public Iterator<Team> restoreTeamParticipatesInLeague( League league ) throws RDException, SQLException{
		String selectSql="select t.id,t.name,t.iscaptainof,l.id,l.name,"
			+"l.leagueRules,l.matchRules,l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,"
			+" u.username,u.email,u.firstname,u.lastname,u.address,u.password,u.studentid,u.major"
			+" from team t,user u,league l where t.iscaptainof=u.id and l.id=t.lid";
		System.out.println("league id::"+league.getId());
		Statement    stmt = null;
		StringBuffer query=new StringBuffer(100);
		query.append(selectSql);
		if(league==null || league.getId()<=-1 || !league.isPersistent()){
			throw new RDException( "LeagueTeamLink: Attempting to save a link with no team or league defined" );
			
		}//if
		//query.append("where t.lid="+league.getId());
		if(league!=null){
			if(league.isPersistent()){
				System.out.println("league is persistent");
				int lid=(int)league.getId();
				query.append(" and t.lid="+lid);
				
			}//if
			
		}//if
		System.out.println("query::"+query.toString());
		ResultSet rs=null;
		//long lid=0;
		//String t_name=null;
		try {
			
			stmt=con.createStatement();
			//int id=(int)league.getId();
			//stmt.setInt(1,id);
			if( stmt.execute(query.toString())) { // statement returned a result
				//rs.last();
                rs= stmt.getResultSet();
				int cnt=0;
                /*while(rs.next()){
                	cnt++;
                	System.out.println("rowcnt::"+cnt);
                }//while*/
                //System.out.println("Rows::"+rs.getRow());
                return new TeamIterator(rs,ob);
   			 
              }//if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}//restore
	
	
	public League restoreTeamParticipatesInLeague( Team team ) throws RDException, SQLException{
	String selectSql="select id,name,leagueRules,matchRules,isIndoor,minTeams,maxTeams,minMembers,maxMembers from league";
	
	StringBuffer query=new StringBuffer(100);
	query.append(selectSql);
	Statement stmt=null;
	if(team==null || !team.isPersistent() || team.getId()<=-1){
		throw new RDException( "LeagueTeamLinking: Attempting to save a link with no team or league defined" );
			
	}//if
	if(team!=null){
		if(team.isPersistent()){
			long lid=team.getParticipatesInLeague().getId();
			query.append(" where id="+lid);
		}//if
	}//if
	//query.append(selectSql);
	//query.append("where l.id="+team.getParticipatesInLeague().getId());
	String name=null;
	String leagueRules=null;
	String matchRules=null;
	boolean isIndoor=false;
	int minTeams=0;
	int maxTeams=0;
	int minMembers=0;
	int maxMembers=0;
	long id=0;
	try {
		stmt =con.createStatement();
		
		//stmt.setLong(1,team.getParticipatesInLeague().getId());
		//System.out.println("League::"+team.getParticipatesInLeague().getId());
		if( stmt.execute(query.toString())) { // statement returned a result
	        ResultSet r = stmt.getResultSet();
	        r.next();
	        id=r.getLong(1);
	        name=r.getString(2);
	        leagueRules=r.getString(3);
	        matchRules=r.getString(4);
	        isIndoor=r.getBoolean(5);
	        minTeams=r.getInt(6);
	        maxTeams=r.getInt(7);
	        minMembers=r.getInt(8);
	        maxMembers=r.getInt(9);
	League l=new LeagueImplementor(name,leagueRules,matchRules,isIndoor,minTeams,maxTeams,minMembers,maxMembers);
	l.setId(id);
	return l;
	    }//if
	    
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return null;
	
	}//restoreleague
	
	
	
public void deleteTeamParticipatesInLeague( Team team, League league ) throws RDException{
	/*String deleteSql = "delete from team where lid=? and id=?";
	StringBuffer query=new StringBuffer(100);
	 PreparedStatement    stmt = null;
	 int del;
	 query.append(deleteSql);
	 //query.append("where t.lid="+league.getId());
	 try {
			stmt=(PreparedStatement) con.prepareStatement(deleteSql);
			int lid=(int)league.getId();
			System.out.println("Lid::"+lid);
			stmt.setInt(1,lid);
			stmt.setLong(2, team.getId());
			del=stmt.executeUpdate();
			if(del==1){
				return;
			}//if
			else{
				 throw new RDException( "deleteTeamParticipation.delete: failed to delete a Captain" );
			        
			}//else
		
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		*/	
	    	
}//delete
		
}
