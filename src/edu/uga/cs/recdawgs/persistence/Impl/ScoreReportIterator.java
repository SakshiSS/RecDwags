package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.entity.impl.ScoreReportImplementor;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class ScoreReportIterator implements Iterator<ScoreReport>{
	ResultSet rs=null;
	ObjectLayer ob=null;
	boolean more=false;
	public ScoreReportIterator(ResultSet rs,ObjectLayer obj){
		this.rs=rs;
		this.ob=obj;
		try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            try {
				throw new RDException( "ScoreReport Iterator: Cannot create an iterator; root cause: " + e );
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

	@Override
	public ScoreReport next() {
		// TODO Auto-generated method stub
		long id=0;
		int home_points=0;
		int away_points=0;
		Date date=null;
		//boolean isCompleted=false;
		long mid= 0;
		long uid=0;
		ScoreReport sr=null;
		//String username=null;
		//String email=null;
		//String firstname=null;
	//	String lastname=null;
		//String address=null;
		//String password=null;
		//long studentid=0;
		//String majors=null;
		//String usertype=null;
		//select id,homepoints,awaypoints,date,mid,uid from scoreReport sr,match_table m,user u"
		
		
		try {
			id=rs.getLong(1);
			home_points=rs.getInt(2);
			away_points=rs.getInt(3);
			date=rs.getDate(4);
			//isCompleted=rs.getBoolean(5);
			mid=rs.getLong(5);
			uid=rs.getLong(6);
		    //username=rs.getString(8);
		    //email=rs.getString(9);
		    //firstname=rs.getString(10);
		    //lastname=rs.getString(11);
		    //address=rs.getString(12);
		    //password=rs.getString(13);
		    //studentid=rs.getLong(14);
		    //majors=rs.getString(15);
		    //usertype=rs.getString(16);
		   
			more=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//match student 
		
	//	attributes
		 //h1=new TeamImplementor(h_name);
		 //h2=new TeamImplementor(a_name);
		 //s.setId(s_id);
		 //s.setName(s_name);
		 
		 //h1.setId(h_id);
		 //h2.setId(a_id);
		 //r.setId(r_id);
		 //r.setNumber(r_number);
		try {
			sr=ob.createScoreReport(home_points, away_points, date,null, null);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sr.setId(0);
		return sr;
		/*
		 
	private long id;
	private String name;
	private Student captain;
	private League participant;
	private League winner;
		 */
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}



