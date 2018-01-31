package edu.uga.cs.recdwags.logic.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImplementor;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;
import edu.uga.cs.recdawgs.persistence.Impl.Persistance_Layer_Impl;
import edu.uga.cs.recdawgs.presentation.AppointCaptain;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdwags.logic.impl.LoginCtrl;

public class LogicLayerImpl implements LogicLayer{
	
	ObjectLayer ob=null;
	public LogicLayerImpl(ObjectLayer ob){
		this.ob=ob;
	}
	public LogicLayerImpl( Connection conn )
    {
        ob = new ObjectLayerImplementor();
        PersistenceLayer persistenceLayer = new Persistance_Layer_Impl( conn,ob);
        ob.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
	
	
	public String createScoreReport(String hscore, String ascore,Date m_date, long id, Match m) throws RDException {
		// TODO Auto-generated method stub
		ScoreReportCtrl sctrl=new ScoreReportCtrl(ob);
		return sctrl.createScoreReport(hscore,ascore, m_date,id,m);
		//return null;
	}//createScoreReport
	
    public long createLeague(String lName,String lRules,String mRules,boolean isIndoor,int minTeams,int maxTeams,int minMembers,int maxMembers) throws RDException{
    	CreateLeagueCtrl ctrlCreateClub = new CreateLeagueCtrl( ob);
    	
			return ctrlCreateClub.createLeague(lName, lRules, mRules, isIndoor, maxTeams, minTeams, maxMembers, minMembers);
					// TODO Auto-generated catch block
			
    	
    }//createLeague
    
    @Override
	public long editTeam(String teamName, Long captainId, Long leagueName)
			throws RDException {
		EditTeamCtrl editTeamCtrl = new EditTeamCtrl(ob);
		return editTeamCtrl.editTeam(teamName, captainId, leagueName);
	}
	
    
    public List<Team> findAllTeams() 
            throws RDException
    {
        FindAllTeamsCtrl ctrlFindAllTeams = new FindAllTeamsCtrl(ob);
        System.out.println("LogicLayer Find All Teams");
        return ctrlFindAllTeams.findAllTeams();
    }
	
    @Override
	public List<League> getLeagues() throws RDException {
		// TODO Auto-generated method stub
		GetLeaguesCtrl getLeague = new GetLeaguesCtrl(ob);
		return getLeague.getLeagues();
    	//return null;
	}

 
    public long createTeam(String teamName, Long captainId, Long leagueName) throws RDException
	{
		CreateTeamCtrl createTeamCtrl = new CreateTeamCtrl(ob);
		return createTeamCtrl.createTeam(teamName, captainId, leagueName);
		
	}
	
    
	public Team searchTeam(String teamName) throws RDException
	{
		SearchTeamCtrl searchTeamCtrl = new SearchTeamCtrl(ob);
		return searchTeamCtrl.searchTeam(teamName);
		
	}

	public void logout(String ssid) throws RDException{
		SessionManager.logout(ssid);
	}//logout
	public String login( Session session, String userName, String password ) 
            throws RDException
    {
        LoginCtrl ctrlVerifyPerson = new LoginCtrl(ob);
        return ctrlVerifyPerson.login( session, userName, password );
    }
	@Override
	public long createSportsVenue(String sv_name, String sv_address, boolean isIndoor) throws RDException {
		// TODO Auto-generated method stub
		CreateVenueCtrl ctrlCreateVenue = new CreateVenueCtrl( ob );
        return ctrlCreateVenue.createVenue(sv_name, sv_address, isIndoor);
		//return 0;
	}
	
	public List<SportsVenue> findSportsVenue(SportsVenue sv1) throws RDException{
		FindSportsVenueCtrl ctrlFindSportsVenue = new FindSportsVenueCtrl(ob);
        return ctrlFindSportsVenue.findVenue(sv1);
        //return null;
	}//find
	@Override
	public void updateSportsVenue(SportsVenue sv) throws RDException {
		// TODO Auto-generated method stub
		UpdateVenueCtrl ctrlUpdateVenue = new UpdateVenueCtrl( ob );
        //return ctrlUpdateVenue.
		ctrlUpdateVenue.createVenue(sv);
		//return 0;
	}
	@Override
	public void deleteVenue(SportsVenue sv1) throws RDException {
		// TODO Auto-generated method stub
		DeleteVenueCtrl ctrlDeleteVenue = new DeleteVenueCtrl( ob );
        //return ctrlUpdateVenue.
		ctrlDeleteVenue.deleteVenue(sv1);
		
		
	}
	@Override
	public List<League> findLeague(League l) throws RDException {
		// TODO Auto-generated method stub
		FindLeagueCtrl ctrlFindLeague = new FindLeagueCtrl( ob );
        //return ctrlUpdateVenue.
		return ctrlFindLeague.findLeague(l);
		//return null;
	}
	@Override
	public void joinTeam(Long id, String tName) {
		// TODO Auto-generated method stub
		System.out.println("JoinTeam");
		JoinTeamCtrl ctrlJoinTeam = new JoinTeamCtrl(ob);
        //return ctrlUpdateVenue.
		//ctrlDeleteVenue.deleteVenue(sv1);
		try {
			ctrlJoinTeam.jointeam(id,tName);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//joinTeam
	@Override
	public void leaveTeam(Long id, String tName) {
		// TODO Auto-generated method stub
		LeaveTeamCtrl ctrlLeaveTeam = new LeaveTeamCtrl(ob);
        //return ctrlUpdateVenue.
		//ctrlDeleteVenue.deleteVenue(sv1);
		try {
			ctrlLeaveTeam.leaveteam(id,tName);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//leaveTeam
	@Override
	public List<Student> findStudent(Student s) throws RDException {
		// TODO Auto-generated method stub
		FindStudentCtrl ctrlStudent = new FindStudentCtrl(ob);
        return ctrlStudent.findStudent(s);
        
		//return null;
	}
	@Override
	public void appointCaptain(Student s, Team t) throws RDException {
		// TODO Auto-generated method stub
		AppointCaptainCtrl ctrlStudent = new AppointCaptainCtrl(ob);
        //return ctrlStudent.findStudent(s);
         ctrlStudent.appointCaptain(s, t);
	}
	@Override
	public void createStudent(String fname, String lname, String uname, String pwd, String email, String studentId,
			String major, String address) {
		// TODO Auto-generated method stub
		
   StudentCtrl sc=new StudentCtrl(ob);
   sc.createStudent(fname, lname, uname, pwd, email, studentId, major, address);
		
	}//apointcaptain
	/*@Override
	public void createScoreReport(String hscore, String ascore,Date m_date, long id, Match m) throws RDException {
		// TODO Auto-generated method stub
		ScoreReportCtrl sctrl=new ScoreReportCtrl(ob);
		sctrl.createScoreReport(hscore,ascore, m_date,id,m);
	}//createScoreReport*/
	@Override
	public List<Team> findTeam(League l1) throws RDException {
		// TODO Auto-generated method stub
		TeamLeaguectrl sctrl=new TeamLeaguectrl(ob);
		return sctrl.findTeam(l1);
		//return null;
	}
	@Override
	public void leaguewinner(Long tid1, Long lid) throws RDException {
		// TODO Auto-generated method stub
		LeagueWinnerCtrl lw=new LeagueWinnerCtrl(ob);
		lw.setWinner(tid1, lid);
		
	}
	@Override
	public List<Team> findStudentforTeam(String t_name) throws RDException {
		// TODO Auto-generated method stub
		FindStudentTeamCtrl fctrl=new FindStudentTeamCtrl(ob);
		return fctrl.findTeam(t_name);
	}
	@Override
	public List<Student> findStudentforTeam(Team t) throws RDException {
		// TODO Auto-generated method stub
	
		FindStudentforTeam fctrl=new FindStudentforTeam(ob);
		return fctrl.findStudent(t);
	
		//return null;
	}
	@Override
	public List<Match> getAwayTeamMatches(long id,String t_name) {
		// TODO Auto-generated method stub
		AwayTeamMatchCtrl actr=new AwayTeamMatchCtrl(ob);
		return actr.findawaymatch(id,t_name);
		//return null;
	}
	@Override
	public List<Match> getHomeTeamMatches(long id, String t_name) {
		// TODO Auto-generated method stub
		HomeTeamCtrl hctrl=new HomeTeamCtrl(ob);
		return hctrl.hometeam(id, t_name);
		//return null;
	}
	@Override
	public Team findTeamAsAwayTeam(Match m) {
		// TODO Auto-generated method stub
		AwayTeamForMatch actrl=new AwayTeamForMatch(ob);
		Team t=actrl.findTeam(m);
		return t;
	}
	@Override
	public Team findTeamAsHomeTeam(Match m1) {
		// TODO Auto-generated method stub
		System.out.println("Logic layer::HomeTeamid"+m1.getHomeTeam().getId());
		HomeTeamForMatch hctrl=new HomeTeamForMatch(ob);
		return hctrl.findTeamAway(m1);
		//return null;
	}
	@Override
	public long updateStudentDetails(long id,String firstName, String lastName, String username, String password, String email,
			String studentId, String major, String address) throws RDException {
		// TODO Auto-generated method stub
		
		ProfileCtrl profileCtrl = new ProfileCtrl(ob);
		return profileCtrl.updateStudentDetails(id,firstName, lastName, username, password, email, studentId, major, address);	
		//return 0;
	}
	@Override
	public void cancelRegis(Student s1) {
		// TODO Auto-generated method stub
		CancelCtrl cc=new CancelCtrl(ob);
		try {
			cc.cancelregis(s1);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public League searchLeague(String leagueName) throws RDException {
		// TODO Auto-generated method stub
		SearchLeagueCtrl searchLeagueCtrl = new SearchLeagueCtrl(ob);
		return searchLeagueCtrl.searchLeague(leagueName);
	}
	
	@Override
	public long editLeague(String leagueName, String leagueRules,
			String matchRules, boolean isIndoor, int minTeams, int maxTeams,
			int minMembers, int maxMembers) throws RDException {
		// TODO Auto-generated method stub
		EditLeagueCtrl editLeagueCtrl = new EditLeagueCtrl(ob);
		return editLeagueCtrl.editLeague(leagueName, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minMembers, maxMembers);
	}
	
	@Override
	public long createleague(String leagueName, String leagueRules,
			String matchRules, boolean isIndoor, int minTeams, int maxTeams,
			int minMembers, int maxMembers) throws RDException {
		// TODO Auto-generated method stub
		CreateLeagueCtrl leagueCtrl = new CreateLeagueCtrl(ob);
		
		return leagueCtrl.createLeague(leagueName, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minMembers, maxMembers);
	}
	
	
	@Override
	public void deleteLeague(String	leagueName) throws RDException {
		// TODO Auto-generated method stub
		DeleteLeagueCtrl deleteLeagueCtrl = new DeleteLeagueCtrl(ob);
		deleteLeagueCtrl.deleteLeague(leagueName);
		
	}
	@Override
	public void deleteTeam(String teamName) throws RDException
	{
		DeleteTeamCtrl deleteTeamCtrl = new DeleteTeamCtrl(ob);
		deleteTeamCtrl.deleteTeam(teamName);
	}
	@Override
	public List<Integer> getAllRoundsForLeague(String leagueName) throws RDException {
		// TODO Auto-generated method stub
		GetRoundsForLeagueCtrl roundForLeague = new GetRoundsForLeagueCtrl(ob);
		return roundForLeague.findRoundsForLeague(leagueName);
		
	}
	@Override
	public List<Match> getAllMatchesForRound(Integer roundNumber) throws RDException {
		// TODO Auto-generated method stub
		GetAllMatchesForRoundCtrl getAllMatchesForRoundCtrl = new GetAllMatchesForRoundCtrl(ob);
		return getAllMatchesForRoundCtrl.getAllMatchesForRound(roundNumber);
		
	}
	@Override
	public void scheduleMatch(Match m) {
		// TODO Auto-generated method stub
		System.out.println("match"+m.getId()+"-->"+m.getDate());
		ScheduleMatchCtrl sctrl=new ScheduleMatchCtrl(ob);
		sctrl.scheduleMatch(m);
		
		
	}
	@Override
	public List<ScoreReport> findAllScoreReports() throws RDException {
		// TODO Auto-generated method stub
		GetAllScoreReports getAllScoreReports = new GetAllScoreReports(ob);
		return getAllScoreReports.findAllScoreReports();
		
	}
	@Override
	public LinkedList<ScoreReport> findAllScoreReports(Match match) throws RDException {
		// TODO Auto-generated method stub
		
		GetAllScoreReportsMatchCtrl getAllScoreReportsMatch = new GetAllScoreReportsMatchCtrl(ob);
		return getAllScoreReportsMatch.findAllScoreReports(match);
		
	}

	
	
	
	
	
	
	
}



