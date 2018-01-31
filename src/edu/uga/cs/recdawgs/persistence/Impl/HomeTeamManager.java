package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class HomeTeamManager {
	Connection con=null;
	ObjectLayer ob=null;
	public HomeTeamManager(Connection con1,ObjectLayer ob){
		this.con=con1;
		this.ob=ob;
	}
	public void storeTeamHomeTeamMatch( Team team, Match match ) throws RDException, SQLException{
		/*String insertSql="insert into match_table(home_points,awaypoints,mdate,isCompleted,hometeamid)"+
	"values(?,?,?,?,?)";*/
		String update_sql="update match_table set id=? and hometeamid=?";
		PreparedStatement stmt=null;
		if(!match.isPersistent() || !team.isPersistent()){
	    	 throw new RDException( "HomeTeamMatchLinking: Attempting to save a link with no team or match defined" );
			 
	     }//if
	    
		try {
			stmt=(PreparedStatement) con.prepareStatement(update_sql);
			stmt.setLong(1,match.getId());
			int hid=(int)team.getId();
			stmt.setInt(2,hid);
			/*Date ud=match.getDate();
			java.sql.Date sd=new java.sql.Date(ud.getTime());
			stmt.setDate(3,sd);
			stmt.setBoolean(4,match.getIsCompleted());
			int id=(int)team.getId();
			stmt.setInt(5,id);*/
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			stmt.close();
			con.close();
		}*/
		
		
	}//storeAwayTeam
	public Team restoreTeamHomeTeamMatch( Match match ) throws RDException, SQLException{
		String select_query="select t.id,t.name,t.isCaptainof,t.lid from team t where t.id =?";
		PreparedStatement stmt=null;
		System.out.println("hometeam_id"+match.getHomeTeam().getId());
		long id1=0;
		String name=null;
		int lid=0;
		int iscaptainof=0;
		try {
			stmt=(PreparedStatement) con.prepareStatement(select_query);
			//int id=(int)match.getHomeTeam().getId();
			stmt.setLong(1,match.getHomeTeam().getId());
		    if(stmt.execute()){
		    	ResultSet rs=stmt.getResultSet();
		    	rs.next();
		    	id1=rs.getLong(1);
		    	name=rs.getString(2);
		    	lid=rs.getInt(3);
		    	iscaptainof=rs.getInt(4);
		    }//if
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Team t2=new TeamImplementor(name);
		t2.setId(id1);
		return t2;
		
		
	}//restore
	
	public Iterator<Match> restoreTeamHomeTeamMatch( Team team ) throws RDException, SQLException{
		//String   selectSql="select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,m.awayteamid,m.hometeamid from match_table m";
				/*"select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				+" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
				"hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				+"l.matchRules,"
				+ "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				+"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
				" from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
				"and at.lid=l.id and ht.lid=l.id";*/
				//"select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,m.awayteamid,m.hometeamid from match_table m";//,sv.name,sv.address,"
				 /*+"sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username"+
"hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
+ "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,as.username,"
				 +"as.email,as.firstname,as.lastname,as.address,as.password,as.studentid,as.major"+ 
	"from match_table m,round r,team ht,team at,user hs,user as,sportsVenue sv,league l where m.svid=sv.id and m.rid=r.id and at.iscaptainof=as.id and ht.iscaptainof=hs.id"+
"at.lid=l.id and ht.lid=l.id";*/
		/* String selectSql="select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
				 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
				 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
				 "  from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
				 "and at.lid=l.id and ht.lid=l.id ";*/
		
	/*	String selectSql="select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
		 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
		 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
		 +"l.matchRules,"
		 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
		 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
		 "  from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and (m.hometeamid= ht.id or m.awayteamid=at.id) and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
		 "and at.lid=l.id and ht.lid=l.id ";*/
		String selectSql="select distinct m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,m.svid,sv.name,sv.address,"
				 +" sv.isIndoor,m.rid,r.number,m.hometeamid,ht.name,ht.iscaptainof,hs.username,"+
				 "hs.email,hs.firstname,hs.lastname,hs.address,hs.password,hs.studentid,hs.major,l.id,l.name,l.leagueRules,"
				 +"l.matchRules,"
				 + "l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers,m.awayteamid,at.name,at.iscaptainof,ast.username,"
				 +"ast.email,ast.firstname,ast.lastname,ast.address,ast.password,ast.studentid,ast.major"+ 
				 "  from match_table m,round r,team ht,team at,user hs,user ast,sportsVenue sv,league l where m.svid=sv.id and ( m.hometeamid = ht.id) and m.rid=r.id and at.iscaptainof=ast.id and ht.iscaptainof=hs.id "+
				 "and at.lid=l.id and ht.lid=l.id";
		



	StringBuffer query=new StringBuffer(100);
	query.append(selectSql);
	if(team!=null){
		if(team.isPersistent()){
			System.out.println("team is persistent");
			int h_id=(int)team.getId();
			System.out.println("h_id"+h_id);
			query.append(" and m.hometeamid="+h_id);
		}//if
	}//if
/*if(team==null || !team.isPersistent()){
	
}//if*/
		
//String select_query="select id,home_points,away_points,mdate,isCompleted,svid,rid,hometeamid,awayteamid from match where hometeamid=?";
	Statement stmt=null;
	try {
		stmt=(Statement) con.createStatement();
		int id1=(int)team.getId();
		//stmt.setLong(1,id1);
		if(stmt.execute(query.toString())){
			ResultSet rs=stmt.getResultSet();
			return new MatchIterator(rs,ob);
		}//if
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}/*finally{
		stmt.close();
		con.close();
	}*/
	return null;
	}//restore
	
	public void deleteTeamHomeTeamMatch( Team team, Match match ) throws RDException{
		/*String delete_query="delete from team where id=? and lid=?";
		PreparedStatement stmt=null;
		try {
			stmt=(PreparedStatement) con.prepareStatement(delete_query);
			int id1=(int)match.getId();
			stmt.setInt(2,id1);
			stmt.setLong(1,team.getId());
			int result=stmt.executeUpdate();
			if(result==1){
				System.out.println("Deleted");
			}//if
			else{
				System.out.println("not Deleted");
			}//else
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}//delete
	
}
	
	
	

