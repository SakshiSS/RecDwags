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
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class MembershipManager {
	
	Connection con=null;
	ObjectLayer ob=null;
	public MembershipManager(Connection con,ObjectLayer obj){
		this.con=con;
	    this.ob=obj;
	}
	
	 public void storeStudentMemberOfTeam( Student student, Team team ) throws RDException, SQLException{
		 String  insertMembershipSql = "insert into isMemberof(teamid,uid) values(?,?)";
		 
	        PreparedStatement    stmt = null;
	        int                  inscnt;
	        long                teamid,uid;
	        System.out.println("In persistent layer::");
	        //System.out.println("team::"+team.getId());
	        System.out.println("student::"+student.getId());
	        if(!student.isPersistent() || !team.isPersistent()){
	        throw new RDException( "StudentMemberTeam: Attempting to save a link with no team or student defined" );
	 			
	        }
	        System.out.println("connection::"+con);
	        try {
				stmt=(PreparedStatement) con.prepareStatement(insertMembershipSql);
				stmt.setLong(1,team.getId());
				stmt.setLong(2,student.getId());
				inscnt = stmt.executeUpdate();
								
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}/*finally{
				stmt.close();
				con.close();
			}*/
	        
	        
	           
		 
		 
	 }//store
	
	
	
	public Iterator<Student> restoreStudentMemberOfTeam( Team team ) throws RDException, SQLException{
String selectSql="select s.id,s.username,s.email,s.firstname,s.lastname,s.address,s.password,"+
	"s.studentid,s.major from user s";
		StringBuffer sb=new StringBuffer(100);
         sb.append(selectSql);
         if(team==null || team.getId()<=-1 || !team.isPersistent()){
        	 throw new RDException("Student IsMemberof restore : For the given link match is not persistant");
         }//if
       //  sb.append("where s.id in(select uid from isMemberof where teamid=?)");
         if(team!=null){
        	 if(team.isPersistent()){
        		 sb.append(" where s.id in(select uid from isMemberof where teamid="+team.getId());
        		 sb.append(")");
        	 }//if
         }//if
         Statement  stmt = null;
	       try {
			stmt=con.createStatement();
			 
			if( stmt.execute(sb.toString())) { 
                ResultSet r = stmt.getResultSet();
                return new StudentIterator(r,ob);
            }//if
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			stmt.close();
			con.close();
		}*/
			       
	      return null;
        
         
    }//restoreStudent
	
	public Iterator<Team> restoreStudentMemberOfTeam( Student student ) throws RDException, SQLException{
		
		String selectSql="select t.id,t.name,t.iscaptainof,t.lid,l.name,l.leagueRules,l.matchRules,l.isIndoor,l.minTeams,"
		 		+ "l.maxTeams,l.minMembers,l.maxMembers,s.username,s.email,s.firstname,s.lastname,s.address,s.password,s.studentid,s.major "
		 		+ "from team t,league l,user s,isMemberof m where t.id=m.uid and s.id=m.id and l.id=t.lid";
		 	
	   //String selectSql="select t.id,t.name,t.iscaptainof,t.lid,l. from team t,user s, isMemberof m " 
		//	   +"where t.id = m.teamid and m.uid = s.id";
	   StringBuffer query=new StringBuffer(100);
	  query.append(selectSql);
	  if(student==null && student.getId()<=-1 && !student.isPersistent()){
		  throw new RDException("Team restore : For the given link student is not persistant");
	          
	  }//if
	  // query.append("where t.id in (select teamid from isMemberof where uid="+student.getId()+")");
	   //System.out.println("Student in Restore"+student.getId());
	   if(student!=null){
		   if(student.isPersistent())
		  query.append(" and s.id="+student.getId());
	   }//if
	   Statement  stmt = null;
       try {
		//stmt=(PreparedStatement) con.prepareStatement(selectSql);
    	   stmt=con.createStatement();
		//stmt.setLong(1,student.getId());
		if(stmt.execute(selectSql)) { 
            System.out.println("Stmt executed");		
            ResultSet r = stmt.getResultSet();
            return new TeamIterator(r,ob);
        }//if
        
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}/*finally{
		stmt.close();
		con.close();
	}*/
       
      return null;
    
	
	}//restoreTeam
	
	public void deleteStudentMemberOfTeam( Student student, Team team ) throws RDException{
		String del1="delete from isMemberof where teamid=? and uid=?";
		PreparedStatement stmt=null;
		 int del=0;
		StringBuffer query=new StringBuffer(100);
		query.append(del1);
		//query.append("where teamid=?");
		//query.append("and uid=?");
		try {
			stmt=(PreparedStatement) con.prepareStatement(del1);
			int teamid1=(int)team.getId();
			System.out.println("Teamid"+teamid1);
			stmt.setInt(1,teamid1);
			int uid1=(int)student.getId();
			System.out.println("uid::"+uid1);
			stmt.setInt(2,uid1);
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
	}//deleteStudent
	
	

}
