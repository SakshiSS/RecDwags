package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class CreateLeagueCtrl {
	ObjectLayer ob=null;
	public CreateLeagueCtrl(ObjectLayer ob){
		this.ob=ob;
	}
	
	public long createLeague(String lname,String lRules,String mRules,boolean isIndoor,int maxTeams,int minTeams,int maxMembers,int minMembers) throws RDException{
		League modelLeague=null;
		Iterator<League> l1=null;
		League league=null;
		modelLeague=ob.createLeague();
		modelLeague.setName(lname);
		
		modelLeague.setId(-1);
		l1=ob.findLeague(modelLeague);
		if(l1.hasNext()){
			throw new RDException("A league with this name already exists: " + lname);
			
		}//if
		league=ob.createLeague(lname, lRules, mRules, isIndoor, minTeams, maxTeams, minMembers, maxMembers);
		league.setMaxTeams(maxTeams);
		league.setMinTeams(minTeams);
		
		ob.storeLeague(league);
		
		
		
		return league.getId();
	}//create
/*	public long createClub( String club_name, String club_addr, long founderId )
            throws ClubsException
    { 
        Date 		    estab = null;
        Club 		    club  = null;
        Club                modelClub = null;
        Iterator<Club>      clubIter = null;
        Person              founder = null;
        Person              modelPerson = null;
        Iterator<Person>    personIter = null;

        // check if a club with this name already exists (name is unique)
        modelClub = objectLayer.createClub();
        modelClub.setName( club_name );
        clubIter = objectLayer.findClub( modelClub );
        if( clubIter.hasNext() )
            throw new ClubsException( "A club with this name already exists: " + club_name );

        // retrieve the founder person
        modelPerson = objectLayer.createPerson();
        modelPerson.setId( founderId );
        personIter = objectLayer.findPerson( modelPerson );
        while( personIter.hasNext() ) {
            founder = personIter.next();
        }

        // check if the person actually exists
        if( founder == null )
            throw new ClubsException( "A person with this id does not exist: " + founderId );

        // create the club
        estab = new Date();		// mark it as created now
        club = objectLayer.createClub( club_name, club_addr, estab, founder );
        objectLayer.storeClub( club );

        return club.getId();
    }*/

	

}
