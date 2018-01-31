package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class StudentCaptainManager {
	Connection con=null;
	ObjectLayer ob=null;
	
	
	public StudentCaptainManager(Connection con1,ObjectLayer ob1){
	   this.con=con1;
	   this.ob=ob1;
	}
	
	public void storeStudentCaptainOfTeam( Student student, Team team ) throws RDException, SQLException{
		//String  insertMembershipSql = "insert into team(name,iscaptainof,lid) values(?,?)";
		//(only update)
		String update="update team set iscaptainof=? where id=?";
		 PreparedStatement    stmt = null;
	     int    inscnt;
	     long   teamid,uid;
	     long teamid1=0;
	     if(!student.isPersistent() || !team.isPersistent()){
	    	 throw new RDException( "CaptainTeamLinking: Attempting to save a link with no team or student defined" );
			 
	     }//if
	     try {
			stmt=(PreparedStatement) con.prepareStatement(update);
	    	 int captain=(int)student.getId();
			stmt.setLong(1,captain);
			stmt.setLong(2,team.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt>=1){
				String sql="select last_insert_id()";
				if(stmt.execute(sql)){
					ResultSet rs=stmt.getResultSet();
					while(rs.next()){
			             teamid1=rs.getLong(1);			
					}//while
				}//if
			}//if
			
			//lid=(int)team.getParticipatesInLeague().getId();
			//stmt.setInt(3,lid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			stmt.close();
			con.close();
		}*/
			
		 
		
	}//storeStudentOfTeam
	public Student restoreStudentCaptainOfTeam( Team team ) throws RDException, SQLException{
		String   selectSql="select id,username,email,firstname,lastname,address,password,studentid,major from user where id=?";
		PreparedStatement    stmt = null;
        
        String tname=null;
        long id=0;
        String username=null;
        String email=null;
        String firstname=null;
        String lastname=null;
        String address=null;
        String password=null;
        String studentid=null;
        String major=null;
        
		StringBuffer query=new StringBuffer(100);
		StringBuffer condition=new StringBuffer(100);
		query.append(selectSql);
		if(team!=null){
			//query.append("where s.id="+team.getCaptain().getId());
			
		}//if
		ResultSet rs=null;
		try {
			stmt = (PreparedStatement) con.prepareStatement(selectSql);
			System.out.println("team::"+team.getCaptain().getId());
			stmt.setLong(1,team.getCaptain().getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if( stmt.execute()) { // statement returned a result
                 rs= stmt.getResultSet();
               }//if
			rs.next();
			
			id=rs.getLong(1);
			username=rs.getString(2);
			email=rs.getString(3);
			firstname=rs.getString(4);
			lastname=rs.getString(5);
			address=rs.getString(6);
			password=rs.getString(7);
			studentid=rs.getString(8);
			major=rs.getString(9);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			stmt.close();
			con.close();
			
		}*/
		Student s=new StudentImplementor(firstname,lastname,username,password,email,studentid,major,address);
		return s;
}//restoreStudentCaptainOfTeam
	 public Iterator<Team> restoreStudentCaptainOfTeam(Student student) throws RDException, SQLException{
		 System.out.println("In Restore");
		 String selectSql="select t.id,t.name,t.iscaptainof,t.lid,l.name,l.leagueRules,l.matchRules,l.isIndoor,l.minTeams,"
		 		+ "l.maxTeams,l.minMembers,l.maxMembers,s.username,s.email,s.firstname,s.lastname,s.address,s.password,s.studentid,s.major "
		 		+ "from team t,league l,user s where t.iscaptainof=s.id and t.lid=l.id";
		 Statement stmt=null;
		 StringBuffer query=new StringBuffer(100);
		 query.append(selectSql);
		 if(student ==null || student.getId()<=-1 || !student.isPersistent()){
			 throw new RDException( "CaptainTeamLinking: Attempting to save a link with no team or student defined" );
				
		 }//if
		 if(student!=null){
			 if(student.isPersistent()){
			 int cid=(int)student.getId();
		   query.append("and t.iscaptainof="+cid);
			 }//if
		 }//if
	    		         
		 //stmt = con.prepareStatement(query);
		 try {
			stmt = con.createStatement();
			/*int id=(int)student.getId();
			System.out.println("id:"+id);
			stmt.setInt(1,id);*/
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			
		 try {
			if( stmt.execute(query.toString()) ) { // statement returned a result
				System.out.println("Executed");
			     ResultSet r = stmt.getResultSet();
			     return new TeamIterator(r,ob);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			stmt.close();
			con.close();
		}*/
		 return  null;
	 }//restoreTeam
	 public void deleteStudentCaptainOfTeam( Student student, Team team ) throws RDException{
		 
	 }
		/* String delete1="delete from team where iscaptainof=? and id=?";
		 PreparedStatement stmt=null;
		 int del=0;
		 StringBuffer sb=new StringBuffer(100);
		 //sb.append(delete1);
		 //sb.append("where t.iscaptainof="+student.getId());
		 
		 try {
			stmt = (PreparedStatement) con.prepareStatement(delete1);
			int id=(int)student.getId();
			System.out.println("id::"+id);
			stmt.setInt(1,id);
			stmt.setLong(2,team.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		 try {
			del=stmt.executeUpdate();
			if(del==1){
				return;
			}//if
			else{
				 throw new RDException( "deleteStudentCaptainOfTeam.delete: failed to delete a Captain" );
			        
			}//else
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }//delete*/

	

}