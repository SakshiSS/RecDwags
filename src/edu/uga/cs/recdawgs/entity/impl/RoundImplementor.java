package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Round;

public class RoundImplementor implements Round{

	private long id;
	
	private int number;
	
	public RoundImplementor(int number){

		this.number=number;
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
		return id>0;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public void setNumber(int number) throws RDException {
		// TODO Auto-generated method stub
		this.number=number;
	}
	@Override
	public void setLeague(League league) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public League getLeague() {
		// TODO Auto-generated method stub
		return null;
	}

}
