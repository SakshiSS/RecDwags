package edu.uga.cs.recdwags.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class GetAllScoreReports {
	
	private ObjectLayer objLayer  = null;
	
	public GetAllScoreReports(ObjectLayer objLayer){
		this.objLayer = objLayer;
	}
	
	public List<ScoreReport> findAllScoreReports() throws RDException{
		
		List<ScoreReport> scoreReports = null;
        Iterator<ScoreReport> scoreReportItr = null;
        ScoreReport scoreReport = null;
        ScoreReport modelScoreReport = null;
        System.out.println("In findallScoreReportsCtrl");
        scoreReports = new LinkedList<ScoreReport>();
        modelScoreReport = objLayer.createScoreReport();
        scoreReportItr = objLayer.findScoreReport(modelScoreReport);
        
        System.out.println("Check to see:"+scoreReportItr.hasNext());
        while(scoreReportItr.hasNext()) {
        	
        	scoreReport = scoreReportItr.next();
        	System.out.println("In GetAllScoreReports Id:"+scoreReport.getId());
        	System.out.println("In GetAllScoreReports:"+scoreReport.getMatch());
        	scoreReports.add(scoreReport);
        }
        
        return scoreReports;
        
        
        
        
		
		
	}
}
