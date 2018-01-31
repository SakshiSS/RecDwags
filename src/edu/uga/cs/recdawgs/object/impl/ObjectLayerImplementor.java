package edu.uga.cs.recdawgs.object.impl;

import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.AdministratorImpl;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.entity.impl.RoundImplementor;
import edu.uga.cs.recdawgs.entity.impl.ScoreReportImplementor;
import edu.uga.cs.recdawgs.entity.impl.SportsVenueImplementor;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;

public class ObjectLayerImplementor implements ObjectLayer{
	
	PersistenceLayer persistence = null;
	
	public ObjectLayerImplementor(){
		this.persistence = null;
		System.out.println("ObjectLayerImplementor.ObjectLayerImplementor(): initialized");
	}
	
	public ObjectLayerImplementor(PersistenceLayer persistence){
		this.persistence = persistence;
		System.out.println( "ObjectLayerImplementor.ObjectLayerImplementor(persistence): initialized" );
	}

	@Override
	public Administrator createAdministrator(String firstName, String lastName,
			String userName, String password, String emailAddress)
			throws RDException {
		
		Administrator admin = new AdministratorImpl(firstName, lastName,userName, password, emailAddress);
		return admin;
	}

	@Override
	public Administrator createAdministrator() {
		// TODO Auto-generated method stub
		Administrator admin = new AdministratorImpl(null,null,null,null,null);
		admin.setId(-1);
		return admin;
	}

	@Override
	public Iterator<Administrator> findAdministrator(
			Administrator modelAdministrator) throws RDException {
		// TODO Auto-generated method stub
		System.out.println("In ObjectLayer");
		return persistence.restoreAdministrator(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeAdministrator(administrator);
		
	}

	@Override
	public void deleteAdministrator(Administrator administrator)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteAdministrator(administrator);
	}

	@Override
	public Student createStudent(String firstName, String lastName,
			String userName, String password, String emailAddress,
			String studentId, String major, String address) throws RDException {
		// TODO Auto-generated method stub
		
		Student student = new StudentImplementor(firstName,lastName,userName,password,emailAddress,studentId,major,address);
		student.setId(-1);
		return student;
	}

	@Override
	public Student createStudent() {
		// TODO Auto-generated method stub
		Student student = new StudentImplementor(null,null,null,null,null,null,null,null);
		student.setId(-1);
		return student;
	}

	@Override
	public Iterator<Student> findStudent(Student modelStudent)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreStudent(modelStudent);
	}

	@Override
	public void storeStudent(Student student) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeStudent(student);
		
	}

	@Override
	public void deleteStudent(Student student) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteStudent(student);
		
	}

	@Override
	public League createLeague(String name, String leagueRules,
			String matchRules, boolean isIndoor, int minTeams, int maxTeams,
			int minPlayers, int maxPlayers) throws RDException {
		// TODO Auto-generated method stub
		
		League league = new LeagueImplementor(name,leagueRules,matchRules,isIndoor,minTeams,maxTeams, minPlayers,maxPlayers);
		league.setId(-1);
		return league;
	}

	@Override
	public League createLeague() {
		// TODO Auto-generated method stub
		League league = new LeagueImplementor(null,null,null,false,0,0,0,0);
		return league;
	}

	@Override
	public Iterator<League> findLeague(League modelLeague) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreLeague(modelLeague);
	}

	@Override
	public void storeLeague(League league) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeLeague(league);
		
	}

	@Override
	public void deleteLeague(League league) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteLeague(league);
		
	}

	@Override
	public Team createTeam(String name) throws RDException {
		// TODO Auto-generated method stub
		Team team = new TeamImplementor(name);
		return team;
	}

	@Override
	public Team createTeam() {
		// TODO Auto-generated method stub
		Team team = new TeamImplementor(null);
		team.setId(-1);
		return team;
	}

	@Override
	public Iterator<Team> findTeam(Team modelTeam) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeam(modelTeam);
	}

	@Override
	public void storeTeam(Team team) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeTeam(team);
	}

	@Override
	public void deleteTeam(Team team) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteTeam(team);
	}

	@Override
	public SportsVenue createSportsVenue(String name, String address,
			boolean isIndoor) throws RDException {
		// TODO Auto-generated method stub
		SportsVenue sportsVenue = new SportsVenueImplementor(name,address,isIndoor);
		//sportsVenue.setId(-1);
		return sportsVenue;
	}

	@Override
	public SportsVenue createSportsVenue() {
		// TODO Auto-generated method stub
		SportsVenue sportsVenue = new SportsVenueImplementor(null,null,false);
		return sportsVenue;
	}

	@Override
	public Iterator<SportsVenue> findSportsVenue(SportsVenue modelSportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreSportsVenue(modelSportsVenue);
	}

	@Override
	public void storeSportsVenue(SportsVenue sportsVenue) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeSportsVenue(sportsVenue);
		
	}

	@Override
	public void deleteSportsVenue(SportsVenue sportsVenue) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteSportsVenue(sportsVenue);
		
	}

	@Override
	public Match createMatch(int homePoints, int awayPoints, Date date,
			boolean isCompleted, Team homeTeam, Team awayTeam)
			throws RDException {
		// TODO Auto-generated method stub
		Match match = new MatchImplementor(homePoints, awayPoints,date,isCompleted,homeTeam,awayTeam);
		return match;
	}

	@Override
	public Match createMatch() {
		// TODO Auto-generated method stub
		Match match = new MatchImplementor(0,0,null,false,null,null);
		return match;
	}

	@Override
	public Iterator<Match> findMatch(Match modelMatch) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreMatch(modelMatch);
	}

	@Override
	public void storeMatch(Match match) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeMatch(match);
		
	}

	@Override
	public void deleteMatch(Match match) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteMatch(match);
	}

	@Override
	public Round createRound(int number) throws RDException {
		// TODO Auto-generated method stub
		Round round = new RoundImplementor(number);
		return round;
	}

	@Override
	public Round createRound() {
		// TODO Auto-generated method stub
		Round round = new RoundImplementor(0);
		return round;
	}

	@Override
	public Iterator<Round> findRound(Round modelRound) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreRound(modelRound);
	}

	@Override
	public void storeRound(Round round) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeRound(round);
		
	}

	@Override
	public void deleteRound(Round round) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteRound(round);
	}

	@Override
	public ScoreReport createScoreReport(int homePoints, int awayPoints,
			Date date, Student student, Match match) throws RDException {
		// TODO Auto-generated method stub
		ScoreReport scoreReport = new ScoreReportImplementor(homePoints, awayPoints,date,student,match);
		return scoreReport;
	}

	@Override
	public ScoreReport createScoreReport() {
		// TODO Auto-generated method stub
		ScoreReport scoreReport = new ScoreReportImplementor(0,0,null,null,null);
		return null;
	}

	@Override
	public Iterator<ScoreReport> findScoreReport(ScoreReport modelScoreReport)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreScoreReport(modelScoreReport);
	}

	@Override
	public void storeScoreReport(ScoreReport scoreReport) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeScoreReport(scoreReport);
	}

	@Override
	public void deleteScoreReport(ScoreReport scoreReport) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteScoreReport(scoreReport);
	}

	@Override
	public void createStudentCaptainOfTeam(Student student, Team team)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeStudentCaptainOfTeam(student, team);
	}

	@Override
	public Student restoreStudentCaptainOfTeam(Team team) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreStudentCaptainOfTeam(team);
	}

	@Override
	public Iterator<Team> restoreStudentCaptainOfTeam(Student student)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreStudentCaptainOfTeam(student);
	}

	@Override
	public void deleteStudentCaptainOfTeam(Student student, Team team)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteStudentCaptainOfTeam(student, team);
	}

	@Override
	public void createStudentMemberOfTeam(Student student, Team team)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeStudentMemberOfTeam(student, team);
		
	}

	@Override
	public Iterator<Student> restoreStudentMemberOfTeam(Team team)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreStudentMemberOfTeam(team);
	}

	@Override
	public Iterator<Team> restoreStudentMemberOfTeam(Student student)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreStudentMemberOfTeam(student);
	}

	@Override
	public void deleteStudentMemberOfTeam(Student student, Team team)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteStudentMemberOfTeam(student, team);
	}

	@Override
	public void createTeamHomeTeamMatch(Team team, Match match)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeTeamHomeTeamMatch(team, match);
	}

	@Override
	public Team restoreTeamHomeTeamMatch(Match match) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamHomeTeamMatch(match);
	}

	@Override
	public Iterator<Match> restoreTeamHomeTeamMatch(Team team)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamHomeTeamMatch(team);
	}

	@Override
	public void deleteTeamHomeTeamMatch(Team team, Match match)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteTeamHomeTeamMatch(team, match);
	}

	@Override
	public void createTeamAwayTeamMatch(Team team, Match match)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeTeamAwayTeamMatch(team, match);
	}

	@Override
	public Team restoreTeamAwayTeamMatch(Match match) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamAwayTeamMatch(match);
	}

	@Override
	public Iterator<Match> restoreTeamAwayTeamMatch(Team team)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamAwayTeamMatch(team);
	}

	@Override
	public void deleteTeamAwayTeamMatch(Team team, Match match)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteTeamAwayTeamMatch(team, match);
		
	}

	@Override
	public void createTeamParticipatesInLeague(Team team, League league)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeTeamParticipatesInLeague(team, league);
	}

	@Override
	public League restoreTeamParticipatesInLeague(Team team) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamParticipatesInLeague(team);
	}

	@Override
	public Iterator<Team> restoreTeamParticipatesInLeague(League league)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamParticipatesInLeague(league);
	}

	@Override
	public void deleteTeamParticipatesInLeague(Team team, League league)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteTeamParticipatesInLeague(team, league);
	}

	@Override
	public void createTeamWinnerOfLeague(Team team, League league)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeTeamWinnerOfLeague(team, league);
	}

	@Override
	public League restoreTeamWinnerOfLeague(Team team) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamWinnerOfLeague(team);
	}

	@Override
	public Team restoreTeamWinnerOfLeague(League league) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreTeamWinnerOfLeague(league);
	}

	@Override
	public void deleteTeamWinnerOfLeague(Team team, League league)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteTeamWinnerOfLeague(team, league);
	}

	@Override
	public void createLeagueSportsVenue(League league, SportsVenue sportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeLeagueSportsVenue(league, sportsVenue);
	}

	@Override
	public Iterator<SportsVenue> restoreLeagueSportsVenue(League league)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreLeagueSportsVenue(league);
	}

	@Override
	public Iterator<League> restoreLeagueSportsVenue(SportsVenue sportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		//persistence.restore
		return persistence.restoreLeagueSportsVenue(sportsVenue);
		//return null;
	}

	@Override
	public void deleteLeagueSportsVenue(League league, SportsVenue sportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteLeagueSportsVenue(league, sportsVenue);
		
	}

	@Override
	public void createLeagueRound(League league, Round round)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeLeagueRound(league, round);
	}

	@Override
	public Iterator<Round> restoreLeagueRound(League league) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreLeagueRound(league);
	}

	@Override
	public void deleteLeagueRound(League league, Round round)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteLeagueRound(league, round);
	}

	@Override
	public void createRoundMatch(Round round, Match match) throws RDException {
		// TODO Auto-generated method stub
		persistence.storeRoundMatch(round, match);
	}

	@Override
	public Iterator<Match> restoreRoundMatch(Round round) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreRoundMatch(round);
	}

	@Override
	public void deleteRoundMatch(Round round, Match match) throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteRoundMatch(round, match);
	}

	@Override
	public void createMatchSportsVenue(Match match, SportsVenue sportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.storeMatchSportsVenue(match, sportsVenue);
	}

	@Override
	public SportsVenue restoreMatchSportsVenue(Match match) throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreMatchSportsVenue(match);
	}

	@Override
	public Iterator<Match> restoreMatchSportsVenue(SportsVenue sportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		return persistence.restoreMatchSportsVenue(sportsVenue);
	}

	@Override
	public void deleteMatchSportsVenue(Match match, SportsVenue sportsVenue)
			throws RDException {
		// TODO Auto-generated method stub
		persistence.deleteMatchSportsVenue(match, sportsVenue);
		
	}

	@Override
	public void setPersistence(PersistenceLayer pl) {
		// TODO Auto-generated method stub
		this.persistence=pl;
		
	}

	/*@Override
	public League restoreLeagueSportsVenue(SportsVenue sportsVenue) throws RDException {
		// TODO Auto-generated method stub
	     return persistence.restoreLeagueSportsVenue(sportsVenue);
	}*/

}
