package edu.uga.cs.recdawgs.entity.impl;

import java.util.Date;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Student;

public class ScoreReportImplementor implements ScoreReport{

	private long id;
	private int homePoints;
	private int awayPoints;
	private Match match;
	private Date match_date;
	private Student std;
	
	public ScoreReportImplementor(int homePoints,int awayPoints,Date match_date,Student std,Match match){
		
		this.homePoints=homePoints;
		this.awayPoints=awayPoints;
		this.match=match;
		this.match_date=match_date;
		this.std=std;
		this.match=match;
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
		
		/*if(this.id >= 0)
			return true;
		else
			return false;*/
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
		return match_date;
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		this.match_date=date;
	}

	@Override
	public Match getMatch() {
		// TODO Auto-generated method stub
		return match;
	}

	@Override
	public void setMatch(Match match) throws RDException {
		// TODO Auto-generated method stub
		this.match=match;
	}

	@Override
	public Student getStudent() {
		// TODO Auto-generated method stub
		return std;
	}

	@Override
	public void setStudent(Student student) throws RDException {
		// TODO Auto-generated method stub
		this.std=student;
	}

}
