package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;

public class LeagueImplementor implements League{

	private long id;
	private String name;
	private String leagueRules;
	private String matchRules;
	private boolean isIndoor;
	private int minTeams;
	private int maxTeams;
	private int minMembers;
	private int maxMembers;
	private Team winner;
	
	public LeagueImplementor(String name,String leagueRules,String matchRules,boolean isIndoor,int minTeam,int maxTeam,int minMembers,int maxMembers){
	
	this.name=name;
	this.leagueRules=leagueRules;
	this.matchRules=matchRules;
	this.isIndoor=isIndoor;
	this.maxTeams=maxTeams;
	this.minTeams=minTeams;
	this.minMembers=minMembers;
	this.maxMembers=maxMembers;
	
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
		return id>=0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public String getLeagueRules() {
		// TODO Auto-generated method stub
		return leagueRules;
	}

	@Override
	public void setLeagueRules(String leagueRules) {
		// TODO Auto-generated method stub
		this.leagueRules=leagueRules;
	}

	@Override
	public String getMatchRules() {
		// TODO Auto-generated method stub
		return matchRules;
	}

	@Override
	public void setMatchRules(String matchRules) {
		// TODO Auto-generated method stub
		this.matchRules=matchRules;
	}

	@Override
	public boolean getIsIndoor() {
		// TODO Auto-generated method stub
		return isIndoor;
	}

	@Override
	public void setIsIndoor(boolean isIndoor) {
		// TODO Auto-generated method stub
		this.isIndoor=isIndoor;
	}

	@Override
	public int getMinTeams() {
		// TODO Auto-generated method stub
		return minTeams;
	}

	@Override
	public void setMinTeams(int minTeams) throws RDException {
		// TODO Auto-generated method stub
		this.minTeams=minTeams;
	}

	@Override
	public int getMaxTeams() {
		// TODO Auto-generated method stub
		return maxTeams;
	}

	@Override
	public void setMaxTeams(int maxTeams) throws RDException {
		// TODO Auto-generated method stub
		this.maxTeams=maxTeams;
	}

	@Override
	public int getMinMembers() {
		// TODO Auto-generated method stub
		return minMembers;
	}

	@Override
	public void setMinMembers(int minMembers) throws RDException {
		// TODO Auto-generated method stub
		this.minMembers=minMembers;
	}

	@Override
	public int getMaxMembers() {
		// TODO Auto-generated method stub
		return maxMembers;
	}

	@Override
	public void setMaxMembers(int maxMembers) throws RDException {
		// TODO Auto-generated method stub
		this.maxMembers=maxMembers;
	}

	@Override
	public Team getWinnerOfLeague() {
		// TODO Auto-generated method stub
		return winner;
	}

	@Override
	public void setWinnerOfLeague(Team team) throws RDException {
		// TODO Auto-generated method stub
		this.winner=team;
	}
	

}
