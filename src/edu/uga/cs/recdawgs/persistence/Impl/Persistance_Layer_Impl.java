package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.SQLException;
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
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import java.sql.Connection;
//import com.mysql.jdbc.Connection;
public class Persistance_Layer_Impl implements PersistenceLayer{


	AdministratorManager am = null; 
	StudentCaptainManager cm = null;
	HomeTeamManager ht = null;
	LeagueManager lm = null; 
    ParticipationManager lp = null;
	LeagueSportsVenueManager lsvm = null;
	LeagueWinnerManager lw = null;
	MatchManager matchManager = null;
	MembershipManager membershipManager=null;
	MatchSportsVenueManager msvm = null;
	RoundMatchManager rmm = null;
	RoundManager rm=null;
	ScoreReportManager scm =null;
	SportsVenueManager sv = null;
	StudentManager sm = null; 
	TeamManager tm = null; 
	AwayTeamManager atm=null;
	LeagueRoundManager lrm=null;
   // MatchSportsVenueManager msvm=null;
	
    public Persistance_Layer_Impl( Connection conn, ObjectLayer objectLayer )
    {
    	am = new AdministratorManager(conn,objectLayer);
    	sm = new StudentManager(conn,objectLayer);
        matchManager = new MatchManager( conn, objectLayer );
        sv = new SportsVenueManager( conn, objectLayer );
        tm = new TeamManager(conn,objectLayer);
        cm = new StudentCaptainManager(conn,objectLayer);
        lm = new LeagueManager(conn, objectLayer);
        lp = new ParticipationManager(conn,objectLayer);
        lw = new LeagueWinnerManager(conn,objectLayer);
        ht = new HomeTeamManager(conn,objectLayer);
        membershipManager = new MembershipManager(conn,objectLayer);
        msvm = new MatchSportsVenueManager(conn,objectLayer);
        rm = new RoundManager(conn,objectLayer);
        lsvm = new LeagueSportsVenueManager(conn,objectLayer);
        scm = new ScoreReportManager(conn,objectLayer);
        rmm = new RoundMatchManager(conn,objectLayer);
        tm = new TeamManager(conn, objectLayer); 
        atm=new AwayTeamManager(conn,objectLayer);
        lrm=new LeagueRoundManager(conn,objectLayer);
        //membershipManager = new MembershipManager( conn, objectLayer );
        System.out.println( "PersistenceLayerImpl.PersistenceLayerImpl(conn,objectLayer): initialized" );
    }

	@Override
	public Iterator<Administrator> restoreAdministrator(
			Administrator modelAdministrator) throws RDException {
		try {
			return am.restoreAdministrator(modelAdministrator); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void storeAdministrator(Administrator administrator)
			throws RDException {
		try {
			am.storeAdministrator(administrator); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}		
		
	}

	@Override
	public void deleteAdministrator(Administrator administrator)
			throws RDException {
		try{ 
			am.deleteAdministrator(administrator) ;
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public Iterator<Student> restoreStudent(Student modelStudent)
			throws RDException {
		try {
			return sm.restoreStudent(modelStudent);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void storeStudent(Student student) throws RDException {
		try {
			sm.storeStudent(student);
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void deleteStudent(Student student) throws RDException {
		try {
			sm.deleteStudent(student);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public Iterator<Match> restoreMatch(Match modelMatch) throws RDException {
		try {
			return matchManager.restoreMatch(modelMatch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void storeMatch(Match match) throws RDException {
		try {
			matchManager.storeMatch(match);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMatch(Match match) throws RDException {
		try {
			matchManager.deleteMatch(match);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<SportsVenue> restoreSportsVenue(SportsVenue modelSportsVenue)
			throws RDException {
		try {
			return sv.restoreSportsVenue(modelSportsVenue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void storeSportsVenue(SportsVenue sportsVenue) throws RDException {
		try {
			sv.storeSportsVenue(sportsVenue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSportsVenue(SportsVenue sportsVenue) throws RDException {
		try {
			sv.deleteSportsVenue(sportsVenue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Team> restoreTeam(Team modelTeam) throws RDException {
		try {
			return tm.restoreTeam(modelTeam);
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void storeTeam(Team team) throws RDException {
		try {
			tm.storeTeam(team);
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void deleteTeam(Team team) throws RDException {
		try {
			System.out.println("In persistent delete");
			tm.deleteTeam(team);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public Iterator<League> restoreLeague(League modelLeague)
			throws RDException {
		System.out.println("In Persistent IMpl");
		try {
			
			return lm.restoreLeague(modelLeague); 
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void storeLeague(League league) throws RDException {
		try {
			lm.storeLeague(league); 
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void deleteLeague(League league) throws RDException {
		try {
			lm.deleteLeague(league);	
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public Iterator<ScoreReport> restoreScoreReport(ScoreReport modelScoreReport)
			throws RDException {
		try{ 
			return scm.restoreScoreReport(modelScoreReport);
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void storeScoreReport(ScoreReport scoreReport) throws RDException {
		try {
			scm.storeScoreReport(scoreReport);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteScoreReport(ScoreReport scoreReport) throws RDException {
		try {
			scm.deleteScoreReport(scoreReport);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Round> restoreRound(Round modelRound) throws RDException {
		try {

			
			return rm.restoreRound(modelRound);

			//return rmm.restoreRound(modelRound);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void storeRound(Round round) throws RDException {
		 try {
			rm.storeRound(round);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRound(Round round) throws RDException {
		try {
			rm.deleteRound(round);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeStudentCaptainOfTeam(Student student, Team team)
			throws RDException {
		try {
			cm.storeStudentCaptainOfTeam(student, team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Student restoreStudentCaptainOfTeam(Team team) throws RDException {
		try {
			return cm.restoreStudentCaptainOfTeam(team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterator<Team> restoreStudentCaptainOfTeam(Student student)
			throws RDException {
		try {
			return cm.restoreStudentCaptainOfTeam(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteStudentCaptainOfTeam(Student student, Team team)
			throws RDException {
		try{ 
			cm.deleteStudentCaptainOfTeam(student, team);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void storeStudentMemberOfTeam(Student student, Team team)
			throws RDException {
		try {

			membershipManager.storeStudentMemberOfTeam(student, team);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Student> restoreStudentMemberOfTeam(Team team)
			throws RDException {
		try {
			return membershipManager.restoreStudentMemberOfTeam(team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterator<Team> restoreStudentMemberOfTeam(Student student)
			throws RDException {
		try {
			return membershipManager.restoreStudentMemberOfTeam(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteStudentMemberOfTeam(Student student, Team team)
			throws RDException {

		try {
			membershipManager.deleteStudentMemberOfTeam(student, team);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void storeTeamHomeTeamMatch(Team team, Match match)
			throws RDException {
		try {
			ht.storeTeamHomeTeamMatch(team, match);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Team restoreTeamHomeTeamMatch(Match match) throws RDException {
		try {
			return ht.restoreTeamHomeTeamMatch(match);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Iterator<Match> restoreTeamHomeTeamMatch(Team team)
			throws RDException {
		try {
			return ht.restoreTeamHomeTeamMatch(team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteTeamHomeTeamMatch(Team team, Match match)
			throws RDException {
		try{ 
			ht.deleteTeamHomeTeamMatch(team, match);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void storeTeamAwayTeamMatch(Team team, Match match)
			throws RDException {
		try {
			atm.storeTeamAwayTeamMatch(team, match);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public Team restoreTeamAwayTeamMatch(Match match) throws RDException {
		try {
			return atm.restoreTeamAwayTeamMatch(match);
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public Iterator<Match> restoreTeamAwayTeamMatch(Team team)
			throws RDException {
		try {
			return atm.restoreTeamAwayTeamMatch(team); 
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void deleteTeamAwayTeamMatch(Team team, Match match)
			throws RDException {
		try {
			atm.deleteTeamAwayTeamMatch(team, match); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void storeTeamParticipatesInLeague(Team team, League league)
			throws RDException {
		try {
			lp.storeTeamParticipatesInLeague(team, league);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Team> restoreTeamParticipatesInLeague(League league)
			throws RDException {
		try {
			return lp.restoreTeamParticipatesInLeague(league);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public League restoreTeamParticipatesInLeague(Team team) throws RDException {
		try {
			return lp.restoreTeamParticipatesInLeague(team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteTeamParticipatesInLeague(Team team, League league)
			throws RDException {
		try {
			lp.deleteTeamParticipatesInLeague(team, league);
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void storeTeamWinnerOfLeague(Team team, League league)
			throws RDException {
		try {
			lw.storeTeamWinnerOfLeague(team, league);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Team restoreTeamWinnerOfLeague(League league) throws RDException {
		try {
			return lw.restoreTeamWinnerOfLeague(league);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public League restoreTeamWinnerOfLeague(Team team) throws RDException {
		try {
			 return lw.restoreTeamWinnerOfLeague(team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteTeamWinnerOfLeague(Team team, League league)
			throws RDException {
		try {
			lw.deleteTeamWinnerOfLeague(team, league);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void storeLeagueSportsVenue(League league, SportsVenue sportsVenue)
			throws RDException {
		try {
			lsvm.storeLeagueSportsVenue(league, sportsVenue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<League> restoreLeagueSportsVenue(SportsVenue sportsVenue)
			throws RDException {
		try {
			return lsvm.restoreLeagueSportsVenue(sportsVenue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterator<SportsVenue> restoreLeagueSportsVenue(League league)
			throws RDException {
		try {
			return lsvm.restoreLeagueSportsVenue(league);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteLeagueSportsVenue(League league, SportsVenue sportsVenue)
			throws RDException {
		try {
			lsvm.deleteLeagueSportsVenue(league, sportsVenue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeLeagueRound(League league, Round round) throws RDException {
		try {
			lrm.storeLeagueRound(league, round); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Round> restoreLeagueRound(League league) throws RDException {
		try {
			return lrm.restoreLeagueRound(league); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void deleteLeagueRound(League league, Round round)
			throws RDException {
		try {
			lrm.deleteLeagueRound(league, round); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void storeRoundMatch(Round round, Match match) throws RDException {
		try {
			rmm.storeRoundMatch(round, match);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}

	}

	@Override
	public Iterator<Match> restoreRoundMatch(Round round) throws RDException {
		try {
			return rmm.restoreRoundMatch(round); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void deleteRoundMatch(Round round, Match match) throws RDException {
		try {
			rmm.deleteRoundMatch(round, match);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		//return null;
	}

	@Override
	public void storeMatchSportsVenue(Match match, SportsVenue sportsVenue){
		try {
			msvm.storeMatchSportsVenue(match, sportsVenue); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public Iterator<Match> restoreMatchSportsVenue(SportsVenue sportsVenue)
			throws RDException {
		try {
			return msvm.restoreMatchSportsVenue(sportsVenue); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public SportsVenue restoreMatchSportsVenue(Match match) throws RDException {
		try {
		 	return msvm.restoreMatchSportsVenue(match); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void deleteMatchSportsVenue(Match match, SportsVenue sportsVenue)
			throws RDException {
		try {
			msvm.deleteMatchSportsVenue(match, sportsVenue);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}

}
