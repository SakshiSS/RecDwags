package edu.uga.cs.recdawgs.entity.impl;

import java.util.Date;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Team;

public class MatchImplementor implements Match{

	private long id;
	private int homePoints;
	private int awayPoints;
	private Date matchDate;
	private boolean isCompleted;
	private Team homeTeam;
	private Team awayTeam;
	private SportsVenue sv;
	private Round r;
	
	public MatchImplementor(int homePoints,int awayPoint,Date matchDate,boolean isCompleted,Team homeTeam,Team awayTeam){
		this.r=r;
		this.homePoints=homePoints;
		this.awayPoints=awayPoints;
		this.matchDate=matchDate;
		this.isCompleted=isCompleted;
		this.homeTeam=homeTeam;
		this.awayTeam=awayTeam;
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
		if( id>0)
			return true;
		else
			return false;
	}

	@Override
	public int getHomePoint() {
		// TODO Auto-generated method stub
		return homePoints;
	}

	@Override
	public void setHomePoint(int homePoints) throws RDException {
		// TODO Auto-generated method stub
		this.homePoints=homePoints;
	}

	@Override
	public int getAwayPoints() {
		// TODO Auto-generated method stub
		return awayPoints;
	}

	@Override
	public void setAwayPoints(int awayPoints) throws RDException {
		// TODO Auto-generated method stub
		this.awayPoints=awayPoints;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return matchDate;
	}

	@Override
	public void setDate(Date date) throws RDException {
		// TODO Auto-generated method stub
		this.matchDate=date;
	}

	@Override
	public boolean getIsCompleted() {
		// TODO Auto-generated method stub
		return isCompleted;
	}

	@Override
	public void setIsCompleted(boolean isCompleted) {
		// TODO Auto-generated method stub
		this.isCompleted=isCompleted;
	}

	@Override
	public Team getHomeTeam() {
		// TODO Auto-generated method stub
		return homeTeam;
	}

	@Override
	public void setHomeTeam(Team homeTeam) throws RDException {
		// TODO Auto-generated method stub
		this.homeTeam=homeTeam;
	}

	@Override
	public Team getAwayTeam() {
		// TODO Auto-generated method stub
		return awayTeam;
	}

	@Override
	public void setAwayTeam(Team awayTeam) throws RDException {
		// TODO Auto-generated method stub
		this.awayTeam=awayTeam;
	}

	@Override
	public SportsVenue getSportsVenue() {
		// TODO Auto-generated method stub
		return sv;
	}

	@Override
	public void setSportsVenue(SportsVenue sportsVenue) {
		// TODO Auto-generated method stub
		this.sv=sportsVenue;
	}

	@Override
	public Round getRound() {
		// TODO Auto-generated method stub
		return r;
		
	}

	@Override
	public void setRound(Round r) {
		// TODO Auto-generated method stub
		this.r=r;
		
	}

	
	
	public String toString(){
		return "id"+homePoints;
	}
	
	

}
