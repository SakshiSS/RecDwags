package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class TeamIterator implements Iterator<Team>{
	
	private ResultSet   rs = null;
    private ObjectLayer objectLayer = null;
    private boolean     more = false;

	public TeamIterator(ResultSet rs, ObjectLayer objectLayer) throws RDException{
		this.rs = rs;
		this.objectLayer = objectLayer;
		//System.out.println();
		try{
			more=rs.next();
			System.out.println("In team iterator"+more);
		}
		catch(Exception e){
			throw new RDException("Cannot create Team iterator; root cause " +e);
		}
	}

    
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return more;
	}
	
	public Team next(){
		 long id = 0;
		 String teamName = null;
		 int captain = 0 ;
		 String participatesIn = null;
		 int leagueId = 0;
		 String leagueRules= null;
		 String matchRules= null;
		 boolean isIndoor = false;
		 int minTeams = 0;
		 int maxTeams=0;
		 int minMembers=0;
		 int maxMembers=0;
		 String userEmail= null;
		 String userFName= null; 
		 String userLName= null;
		 String userName= null;
		 String userAddress= null;
		 String userPwd= null;
		 String userSId= null;
		 String userMajor= null;  

		 League participant =null;
		 Team team = null;
		 Student student = null;
		 //League winner = null;
		 
		 
		 //l.leagueRules,l.matchRules,l.isIndoor,l.minTeams,l.maxTeams,l.minMembers,l.maxMembers
		 
		 if(more){
			 try{
			 id = rs.getLong(1);
			 teamName = rs.getString(2);
			 captain = rs.getInt(3);
			 leagueId = rs.getInt(4);
			 //System.out.println("id::"+id);
			 //System.out.println("namw"+teamName);
			 participatesIn = rs.getString(5);	 
			 leagueRules = rs.getString(6);
			 matchRules = rs.getString(7);
			 isIndoor = rs.getBoolean(8);
			 minTeams = rs.getInt(9);
			 maxTeams = rs.getInt(10);
			 minMembers = rs.getInt(11);
			 maxMembers = rs.getInt(12);
			 userName = rs.getString(13);
			 userEmail = rs.getString(14);
			 userFName  = rs.getString(15);
			 userLName = rs.getString(16);
			 userAddress = rs.getString(17);
			 userPwd = rs.getString(18);
			 userSId = rs.getString(19);
			 userMajor = rs.getString(20);
			 //u.username,u.email,u.firstname,u.lastname,u.address,u.password,u.studentid,u.major"
			 //System.out.println("In Team Iterator::");
			 more = rs.next();
			 
			 }
			 catch(Exception e){
				 
			 }
			 try{
			 participant = objectLayer.createLeague(participatesIn,leagueRules,matchRules,isIndoor,minTeams,maxTeams, minMembers,maxMembers);
			 participant.setId(leagueId);
			 student = objectLayer.createStudent( userFName, userLName,
						userName, userPwd,  userEmail,
						userSId,  userMajor, userAddress);
			 student.setId((long)captain);
			 
			 team = objectLayer.createTeam(teamName);
			 team.setId(id);
			 team.setParticipatesInLeague(participant);
			 team.setCaptain(student);
			 
			 }
			 catch(Exception e){
				 
			 }
		 return team;
	}
		 else {
	            throw new NoSuchElementException( "TeamIterator: No next team object" );
	        }
	}
	
	public void remove(){
		throw new UnsupportedOperationException();
	}

	
}
