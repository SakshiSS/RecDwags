package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;

public class TeamImplementor implements Team {

	private long id;
	private String name;
	private Student captain;
	private League participant;
	private League winner;
	public TeamImplementor(String name){
		this.name=name;
		
	}
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		if( id>=0)
				return true;
		else
			return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) throws RDException {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public Student getCaptain() {
		// TODO Auto-generated method stub
		return captain;
	}

	@Override
	public void setCaptain(Student student) throws RDException {
		// TODO Auto-generated method stub
		this.captain=student;
	}

	@Override
	public League getParticipatesInLeague() {
		// TODO Auto-generated method stub
		return participant;
	}

	@Override
	public void setParticipatesInLeague(League league) throws RDException {
		// TODO Auto-generated method stub
		this.participant=league;
	}

	@Override
	public League getWinnerOfLeague() {
		// TODO Auto-generated method stub
		return winner;
	}

	@Override
	public void setWinnerOfLeague(League league) throws RDException {
		// TODO Auto-generated method stub
		this.winner=league;
	}

}
