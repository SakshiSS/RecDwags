package edu.uga.cs.recdwags.logic.impl;


import java.util.Iterator;
import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class GetAllScoreReportsMatchCtrl {
	
	private ObjectLayer objLayer = null;
	public GetAllScoreReportsMatchCtrl(ObjectLayer objLayer){
		this.objLayer = objLayer;
	}
	
	public LinkedList<ScoreReport> findAllScoreReports(Match inputMatch) throws RDException{
		LinkedList<ScoreReport> scoreReports = null;
		scoreReports = new LinkedList<ScoreReport>();
		ScoreReport modelScoreReport = null;
		ScoreReport scoreReportFinal = null;
    	Iterator<ScoreReport> scoreReportIter = null;
    	MatchImplementor match = null;
    	match = (MatchImplementor) objLayer.createMatch();
    	
    	match.setId(inputMatch.getId());
    	modelScoreReport.setMatch(match);
    	
    	scoreReportIter = objLayer.findScoreReport(modelScoreReport);
    	
    	
    	while(scoreReportIter.hasNext()){
    		scoreReportFinal = scoreReportIter.next();
    		scoreReports.add(scoreReportFinal);
    		
    	}
    	
    	return scoreReports;
    	
    	
    	
    	
    	
		
		
		
	}

}
