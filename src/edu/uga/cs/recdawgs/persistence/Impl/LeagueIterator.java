package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.RDException;
//import edu.uga.cs.recdawgs.persistence.Impl.League;

public class LeagueIterator implements Iterator<League> {
	
	private ResultSet   rs = null;
    private ObjectLayer objLayer = null;
    private boolean     more = false;
    
    public LeagueIterator(ResultSet rs , ObjectLayer objectLayer) throws RDException{
		this.rs = rs;
		this.objLayer = objectLayer;
		try {
            more = rs.next();
        }
		catch(Exception e){
			throw new RDException("LeagueIterator : cannot create an iterator; root cause" + e);
		}
	}

   
    
	@Override
	public boolean hasNext() {
		return more;
	}

	@Override
	public League next() {
		long id ;
		String name;
		String leagueRules;
		String matchRules;
		boolean isIndoor;
		int minTeams;
		int maxTeams;
		int minMembers;
		int maxMembers;
		Team winner;
		League league = null;
		
		if(more){
			try{
			id = rs.getLong(1);
			System.out.println("id in iterator"+id);
			name = rs.getString(2);
			leagueRules = rs.getString(3);
			matchRules = rs.getString(4);
			isIndoor = rs.getBoolean(5);
			minTeams = rs.getInt(6);
			maxTeams = rs.getInt(7);
			System.out.println("minTeams"+minTeams+"-->"+maxTeams);
			minMembers = rs.getInt(8);
			maxMembers = rs.getInt(9);
			
			more = rs.next();
			
			
			
			}
			catch(Exception e){
				throw new NoSuchElementException("LeagueIterator: No next League object; root cause:"+e);
			}
			try{
			league = objLayer.createLeague(name, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minMembers, maxMembers);
			league.setId(id);
			System.out.println("minTeams"+minTeams+"-->"+maxTeams);
			league.setMinTeams(minTeams);
			league.setMaxTeams(maxTeams);
			}
			catch(RDException e){
				e.printStackTrace();
				System.out.println(e);
			}
			
		
			return league;
		
	}
		else{
			throw new NoSuchElementException( "LeagueIterator: No next league object" );
		}
		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	
	
	

}
