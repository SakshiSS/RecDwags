package edu.uga.cs.recdawgs.entity.impl;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;

public class LeagueWinner {
	Connection con=null;
	public LeagueWinner(Connection con){
		this.con=con;
	}
	
	public void storeTeamWinnerOfLeague(Team team,League league ) throws RDException{
		String insertSql="insert into team(name,lid)values(?,?)";
		int lid=0;
		//String updateSql="update sportsVenue set name=?,address=?,isIndoor=? where id =?";
		PreparedStatement stmt=null;
		try {
			stmt = (PreparedStatement) con.prepareStatement(insertSql);
			stmt.setString(1,team.getName());
			lid=(int)league.getId();
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}//winnerof

}
