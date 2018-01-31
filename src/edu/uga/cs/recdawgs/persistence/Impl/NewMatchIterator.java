package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class NewMatchIterator implements Iterator<Match> {
 
	ResultSet rs=null;
	ObjectLayer ob=null;
	boolean more=false;
	public NewMatchIterator(ResultSet rs,ObjectLayer obj){
		this.rs=rs;
		this.ob=obj;
		try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            try {
				throw new RDException( "SportsVenueIterator: Cannot create an iterator; root cause: " + e );
			} catch (RDException e1) {
				e1.printStackTrace();
			}
        }	
		
	}//constructor
	@Override
	public boolean hasNext() {
		return more;
	}

	@Override
	public Match next() {
		long id=0;
		int home_points=0;
		int away_points=0;
		Date m_date=null;
		boolean isCompleted=false;
		long s_id=0;
		String s_name=null;
		String s_address=null;
		boolean isIndoor=false;
		long r_id=0;
		int r_number=0;
		long h_id=0;
		String h_name=null;
		long a_id=0;
		String a_name=null;
		SportsVenue s=null;
		Team h1=null;
		Team h2=null;
		Round r=null;
		Match m=null;
		int h_iscaptainof=0;
		String h_username=null;
		String h_email=null;
		String h_firstname=null;
		String h_lastname=null;
		String h_address=null;
		String h_password=null;
		String h_sid=null;
		String h_major=null;
		long lid=0;
		String l_name=null;
		String l_leagueRules=null;
		String l_matchRules=null;
	    boolean l_isIndoor=false;
	    int l_minTeam=0;
	    int l_maxTeams=0;
	    int l_minMembers=0;
	    int l_maxMembers=0;
	    int awayteamid=0;
	    String a_username=null;
	    String a_email=null;
	    String a_firstname=null;
	    String a_lastname=null;
	    String a_address=null;
	    String a_password=null;
	    String a_sid=null;
	    String a_major=null;
	    int a_iscaptainof=0;
	    SportsVenue sv=null;
	    Round rnd=null;
	    Team ht=null;
	    Student hu=null;
	    League l=null;
	    Team at=null;
	    Match m1=null;
	    Student au=null;
	    //String a_name=null;
		if(more){
		try {
			id=rs.getLong(1);
			home_points=rs.getInt(2);
			away_points=rs.getInt(3);
			m_date=rs.getDate(4);
			isCompleted=rs.getBoolean(5);
			s_id=rs.getLong(6);
			 awayteamid=rs.getInt(7);
			 h_id=rs.getLong(8);
			    
		   /* s_name=rs.getString(7);
		    s_address=rs.getString(8);
		    isIndoor=rs.getBoolean(9);
		    r_id=rs.getLong(10);
		    r_number=rs.getInt(11);
		    h_name=rs.getString(13);
		    h_iscaptainof=rs.getInt(14);
		    h_username=rs.getString(15);
		    h_email=rs.getString(16);
		    h_firstname=rs.getString(17);
		    h_lastname=rs.getString(18);
		    h_address=rs.getString(19);
		    h_password=rs.getString(20);
		    h_sid=rs.getString(21);
		    h_major=rs.getString(22);
		    lid=rs.getLong(23);
		    l_name=rs.getString(24);
		    l_leagueRules=rs.getString(25);
		    l_matchRules=rs.getString(26);
		    l_isIndoor=rs.getBoolean(27);
		    l_minTeam=rs.getInt(28);
		    l_maxTeams=rs.getInt(29);
		    l_minMembers=rs.getInt(30);
		    l_maxMembers=rs.getInt(31);*/
		    //awayteamid=rs.getInt(7);
		    //h_id=rs.getLong(8);
		    
		    //a_name=rs.getString(8);
		   /* a_iscaptainof=rs.getInt(34);
		    a_username=rs.getString(35);
		    a_email=rs.getString(36);
		    a_firstname=rs.getString(37);
		    a_lastname=rs.getString(38);
		    a_address=rs.getString(39);
		    a_password=rs.getString(40);
		    a_sid=rs.getString(41);
		    a_major=rs.getString(42);*/
		    
		    try {
				sv=ob.createSportsVenue(s_name,s_address, isIndoor);
				sv.setId(s_id);
				rnd=ob.createRound(r_number);
				rnd.setId(r_id);
				hu=ob.createStudent(h_firstname,h_lastname,h_username,h_password,h_email,h_sid,h_major,h_address);
				hu.setId(h_iscaptainof);
				au=ob.createStudent(a_firstname,a_lastname,a_username,a_password,a_email,a_sid,a_major,a_address);
				au.setId(a_iscaptainof);
				
				l=ob.createLeague(l_name,l_leagueRules,l_matchRules, l_isIndoor,l_minTeam,l_maxTeams,l_minMembers,l_maxMembers);
			    l.setId(lid);
			    
				ht=ob.createTeam(h_name);
				ht.setId(h_id);
				ht.setCaptain(hu);
				ht.setParticipatesInLeague(l);
			    at=ob.createTeam(a_name);
			    at.setId(awayteamid);
			    at.setCaptain(au);
			    at.setParticipatesInLeague(l);
			    
			     m1=ob.createMatch(home_points, away_points,m_date, isCompleted,ht,at);
			     m1.setId(id);
			     m1.setHomeTeam(ht);
			     m1.setAwayTeam(at);
			     m1.setRound(rnd);
			     m1.setSportsVenue(sv);
			     
			    
			    } catch (RDException e) {
				e.printStackTrace();
			}
		    
		    
			more=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return m1;
		}//if
		else{
			throw new NoSuchElementException("MatchIterator::No next  match element");
		}
		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}

}
