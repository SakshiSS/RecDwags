package edu.uga.cs.recdwags.logic.impl;



import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class DeleteTeamCtrl {
	
private ObjectLayer objectLayer = null;
Team deleteTeam = null;
    
    public DeleteTeamCtrl(ObjectLayer objectModel)
    {
        this.objectLayer = objectModel;
    }
    
    public void deleteTeam(String deleteTeamName) throws RDException{
        System.out.println("in ctrl");
        Team team = objectLayer.createTeam();
        
        team.setName(deleteTeamName); 
        team.setId(-1);
        System.out.println("name::"+deleteTeamName);
        objectLayer.deleteTeam(team);
    
    	
    }

}
