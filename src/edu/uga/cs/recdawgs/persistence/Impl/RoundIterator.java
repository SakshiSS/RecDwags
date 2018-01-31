package edu.uga.cs.recdawgs.persistence.Impl;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class RoundIterator implements Iterator<Round>
{
	ResultSet rs=null;
	ObjectLayer ob=null;
	boolean more=false;
	public RoundIterator(ResultSet rs,ObjectLayer obj){
		this.rs=rs;
		this.ob=obj;
		try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            try {
				throw new RDException( "Iterator: Cannot create an iterator; root cause: " + e );
			} catch (RDException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }	
		
	}//constructor
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		
		return more;
	}
	//id, number, lid

	@Override
	public Round next() {
		// TODO Auto-generated method stub
		long id=0;
		int number=0;
		long lid=0;
		
		String h_name=null;
		long a_id=0;
		String a_name=null;
		//SportsVenue s=null;
		//Team h1=null;
	    String l_name=null;
	    
	    String l_leagueRules=null;
	    String l_matchRules=null;
	    boolean l_isIndoor=false;
	    int l_minTeams=0;
	    int l_maxTeams=0;
	    int l_minMembers=0;
	    int l_maxMembers=0;
	    Round r=null;
		//Round r=null;
		League l=null;
		//String h_id;
		try {
			id=rs.getLong(1);
			number=rs.getInt(2);
			lid=rs.getLong(3);
		    /*h_id=rs.getString(4);
		    h_name=rs.getString(5);*/
			//l.name" + 
			//select r.id, r.number, l.id, l.name" + 
	    	//"l.leagueRules, l.matchRules, l.isIndoor, l.minTeams, l.maxTeams, l.minMembers , l.maxMembers
	    	//"l.leagueRules, l.matchRules, l.isIndoor, l.minTeams, l.maxTeams, l.minMembers , l.maxMembers "
			l_name=rs.getString(4);
			l_leagueRules=rs.getString(5);
			l_matchRules=rs.getString(6);
			l_isIndoor=rs.getBoolean(7);
			l_minTeams=rs.getInt(8);
			l_maxTeams=rs.getInt(9);
			l_minMembers=rs.getInt(10);
			l_maxMembers=rs.getInt(11);
			more=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			l=ob.createLeague(l_name,l_leagueRules,l_matchRules,l_isIndoor,l_minTeams,l_maxTeams,l_minMembers,l_maxMembers);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.setId((long)lid);
		try {
			r=ob.createRound(number);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.setId(id);
		r.setLeague(l);
		 
		return r;
		
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

	}

	
		
}