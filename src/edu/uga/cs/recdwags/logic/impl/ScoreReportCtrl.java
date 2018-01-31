package edu.uga.cs.recdwags.logic.impl;

import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.ScoreReportImplementor;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class ScoreReportCtrl {
private ObjectLayer objectLayer = null;
    
    public ScoreReportCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }

    public String createScoreReport(String hscore, String ascore,Date m_date, long id, Match m) throws RDException{
    	ScoreReport scoreReport = null;
    	ScoreReport scoreReportFinal = null;
    	Iterator<ScoreReport> scoreReportIter = null;
    	Match match = null;
    	Student student = null;
    	
    	student = objectLayer.createStudent();
    	student.setId(id);
    	
    	match = objectLayer.createMatch();
    	match.setId(m.getId());
    	int awaypoints = Integer.parseInt(ascore);
    	int homepoints = Integer.parseInt(hscore);
    	
    	//scoreReport = objectLayer.createScoreReport();
    	scoreReport=new ScoreReportImplementor(0,0,m_date,student,m); 
    	System.out.println("Score report object:"+scoreReport);
    	
    	scoreReport.setId(-1);
    	scoreReport.setDate(m_date);
    	scoreReport.setMatch(match);
    	scoreReport.setStudent(student);
    	System.out.println("ScoreReportCtrl Sudent Id: "+student.getId());
    	
    	scoreReportIter = objectLayer.findScoreReport(scoreReport);
//
    	if(scoreReportIter.hasNext()){ 
    		scoreReportFinal = scoreReportIter.next();
    		
    		System.out.println("ScoreReportCtrl awaypoins:"+scoreReportFinal.getAwayPoints());
    		System.out.println("Awaypoints:"+awaypoints);
    		System.out.println("ScoreReportCtrl homepoints"+scoreReportFinal.getAwayPoints());
    		System.out.println("HomePoints:"+homepoints);
    		if(scoreReportFinal.getAwayPoints() != awaypoints || scoreReportFinal.getHomePoint() != homepoints){
    			scoreReport.setAwayPoints(awaypoints);
    			scoreReport.setHomePoint(homepoints);
//    			System.out.println("ScoreReportFinal id:"+scoreReportFinal.getId());
//    			System.out.println("Score report student id:"+scoreReportFinal.getStudent().getId());
//    			if(scoreReport.getStudent().getId() == scoreReportFinal.getStudent().getId()){
//    				scoreReport.setId(scoreReportFinal.getId());
//    			}
    			objectLayer.storeScoreReport(scoreReport);
    			return "The record is stored. But discrepancy in the score. Please ask Administrator to resolve the scores";
    		}
    		else{
    			scoreReport.setAwayPoints(awaypoints);
    			scoreReport.setHomePoint(homepoints);
    			objectLayer.storeScoreReport(scoreReport);
    			return "Score is set";
    		}
    		
    		
    	}
    	else{
    		System.out.println( "CHeck this other case: ");
    		scoreReport.setAwayPoints(awaypoints);
    		scoreReport.setHomePoint(homepoints);
    		objectLayer.storeScoreReport(scoreReport);
    		return "Stored new score";
    	}
    	
    	
//    	Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
//    	s.setId(id);
//    	int h_score=Integer.parseInt(hscore);
//    	 
//    	int a_score=Integer.parseInt(ascore);
//    	ScoreReport sc=objectLayer.createScoreReport(h_score,a_score,m_date,s,m);
//    	sc.setId(-1);
//    	System.out.println("ScoreReport::"+sc.getAwayPoints());
//    	objectLayer.storeScoreReport(sc);
            	
    	
    }//create

}
