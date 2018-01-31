package edu.uga.cs.recdwags.logic;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.session.Session;

public interface LogicLayer {
public String login(Session session,String uname,String pwd) throws RDException;
public void logout(String ssid) throws RDException;
public long createLeague(String lName,String lRules,String mRules,boolean isIndoor,int minTeams,int maxTeams,int minMembers,int maxMembers) throws RDException;
public long createSportsVenue(String sv_name,String sv_address,boolean isIndoor) throws RDException;
public List<SportsVenue>findSportsVenue(SportsVenue sv1)throws RDException;
public void updateSportsVenue(SportsVenue sv) throws RDException;
public void deleteVenue(SportsVenue sv1) throws RDException;
public List<League> findLeague(League l)throws RDException;
public void joinTeam(Long id,String tName);
public void leaveTeam(Long id,String tName);


public String createScoreReport(String hscore, String ascore, Date m_date, long id, Match m) throws RDException;

public List<Student> findStudent(Student s)throws RDException;
public void appointCaptain(Student s,Team t)throws RDException;
public void createStudent(String fname,String lname,String uname,String pwd, String email,String studentId,String major,String address);
//public void createScoreReport(String hscore, String ascore, Date m_date, long id, Match m) throws RDException;
public List<Team> findTeam(League l1)throws RDException;
public void leaguewinner(Long tid1, Long lid) throws RDException;
public List<Team> findStudentforTeam(String t_name)throws RDException;
public List<Student> findStudentforTeam(Team t)throws RDException;
public List<Match> getAwayTeamMatches(long id,String t_name);
public List<Match> getHomeTeamMatches(long id, String t_name);
public Team findTeamAsAwayTeam(Match m);
public Team searchTeam(String teamName) throws RDException;
public List<League> getLeagues() throws RDException;
public List<Team> findAllTeams() throws RDException;
public void deleteTeam(String teamName) throws RDException;
public long createTeam(String teamName, Long captainId, Long leagueName) throws RDException;
public long editTeam(String teamName, Long captainId, Long leagueName) throws RDException;

public Team findTeamAsHomeTeam(Match m1);
public long createleague(String leagueName, String leagueRules, String matchRules, boolean isIndoor, int minTeams,int maxTeams, int minMembers, int maxMembers) throws RDException; // creation of league
public void deleteLeague(String leagueName) throws RDException;
public void scheduleMatch(Match m);
public long updateStudentDetails(long id,String firstName, String lastName, String username, String password, String email, String studentId, String major, String address) throws RDException;
public void cancelRegis(Student s1);  

public League searchLeague(String leagueName) throws RDException;
public long editLeague(String leagueName, String leagueRules,String matchRules, boolean isIndoor, int minTeams,int maxTeams, int minMembers, int maxMembers) throws RDException;

public List<Integer> getAllRoundsForLeague(String leagueName) throws RDException;
public List<Match> getAllMatchesForRound(Integer roundNumber) throws RDException;
public List<ScoreReport> findAllScoreReports() throws RDException;
public List<ScoreReport> findAllScoreReports(Match match) throws RDException;


}
 