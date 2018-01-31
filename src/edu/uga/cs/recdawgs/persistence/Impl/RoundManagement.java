package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.SQLException;
import java.sql.Connection;
//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;

public class RoundManagement {
	Connection con=null;
	public RoundManagement(Connection con){
		this.con=con;
	}

	public void storeRoundMatch( Round round, Match match ) throws RDException{
		String insertSql="insert into round(number,lid) values(?,?)";
		PreparedStatement stmt=null;
		try {
			stmt=(PreparedStatement) con.prepareStatement(insertSql);
			int id=(int)match.getId();
			stmt.setInt(1,round.getNumber());
			
			stmt.setInt(2,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
