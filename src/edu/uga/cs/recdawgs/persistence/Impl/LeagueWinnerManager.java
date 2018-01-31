package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class LeagueWinnerManager {
	Connection con=null;
	ObjectLayer ob=null;
	public LeagueWinnerManager(Connection con,ObjectLayer ob){
		this.con=con;
		this.ob=ob;
	}
	
	public void storeTeamWinnerOfLeague( Team team, League league ) throws RDException, SQLException{
		//String select_query="insert into team(name,lid) values(?,?)";
		String update="update team set lid=? where id=?";
		PreparedStatement stmt=null;
		try {
			stmt=(PreparedStatement) con.prepareStatement(update);
			stmt.setLong(2,team.getId());
			int lid=(int)league.getId();
			stmt.setInt(1,lid);
			stmt.execute();
			ResultSet rs=stmt.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}//store
	
     public Team restoreTeamWinnerOfLeague( League league ) throws RDException, SQLException{
		System.out.println("league id::"+league.getId());
		String select_query="select id,name,lid from team where lid=?";
		PreparedStatement stmt=null;
		String name=null;
		League l=null;
		long id1=0;
		try {
			stmt=(PreparedStatement) con.prepareStatement(select_query);
			int id=(int) league.getId();
	        stmt.setLong(1,id);
	        if(stmt.execute()){
	        ResultSet rs=stmt.getResultSet();
	        id1=rs.getLong(1);
	        name=rs.getString(2);
	        
	        
	        }//if
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Team t=new TeamImplementor(name);
		return t;
		
	}//restore
     
     public League restoreTeamWinnerOfLeague( Team team ) throws RDException, SQLException{
    	 String select_query="select id,name,leagueRules,matchRules,isIndoor,minTeams,maxTeams,minMembers,maxMembers from league where id=?";
 		PreparedStatement stmt=null;
 		String name=null;
 		League l=null;
 		long id1=0;
 	    String name1=null;
 	    String matchRules=null;
 	    String lRules=null;
 	    boolean isIndoor=false;
 	    int minTeams=0;
 	    int maxTeams=0;
 	    int minMembers=0;
 	    int maxMembers=0;
 	    
 		 try {
			stmt=(PreparedStatement) con.prepareStatement(select_query);
			int id=(int)team.getId();
			stmt.setLong(1,id);
			if(stmt.execute()){
				ResultSet rs=stmt.getResultSet();
				id1=rs.getLong(1);
				name1=rs.getString(2);
				lRules=rs.getString(3);
				matchRules=rs.getString(4);
				isIndoor=rs.getBoolean(5);
				minTeams=rs.getInt(6);
				maxTeams=rs.getInt(7);
				minMembers=rs.getInt(8);
				maxMembers=rs.getInt(9);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		 League league=new LeagueImplementor(name1,lRules,matchRules,isIndoor,minTeams,maxTeams,minMembers,maxMembers);
 		 return league;
     }//restore
     public void deleteTeamWinnerOfLeague( Team team, League league ) throws RDException{
    	 /*String delete_query="delete id,name,lid from team where id=? and lid=?";
    	 PreparedStatement stmt=null;
    	 ResultSet rs=null;
    	 try {
			stmt=(PreparedStatement) con.prepareStatement(delete_query);
			stmt.setLong(1,team.getId());
			int id=(int)league.getId();
			stmt.setInt(2,id);
			int r=stmt.executeUpdate();
			if(r==1){
				System.out.println("Deleted");
			}//if
			else{
				System.out.println("Not Deleted");
			}
				} catch (SQLException e) {
			e.printStackTrace();
		}*/
    	 
     }

}
