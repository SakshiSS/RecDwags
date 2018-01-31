package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.conection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
//import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import java.sql.Connection;
 class ScoreReportManager {
	 Connection con=null;
		ObjectLayer ob=null;
		long mid=0;
		public ScoreReportManager(Connection con,ObjectLayer ob){
			this.con=con;
			this.ob=ob;
		}

    public void deleteScoreReport(ScoreReport ScoreReport) 
            throws RDException, SQLException
    {
        String               deleteScoreReportSql = "delete from ScoreReport where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
       if(!ScoreReport.isPersistent()) // is the Club object persistent?  If not, nothing to actually delete
           return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement( deleteScoreReportSql );         
            stmt.setLong( 1, ScoreReport.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RDException( "ScoreReport.delete: failed to delete a score report" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
    
            throw new RDException( "ScoreReport.delete: failed to delete a score report: " + e );        
            }/*finally{
            	stmt.close();
            	con.close();
            }*/
    }
        public void storeScoreReport(ScoreReport ScoreReport) 
                throws RDException, SQLException
        {
            String               insertScoreReportSql = "insert into scoreReport (homepoints,awaypoints,date,mid,uid ) values ( ?, ?, ?, ?, ?)";
            //String               updateScoreReportSql = "update scoreReport set  homepoints = ?, awaypoints = ?, date = ?, mid = ?, uid = ? where id = ?";
            String               updateScoreReportSql = "update scoreReport set  homepoints = ?, awaypoints = ?, date = ? where mid = ?";
            PreparedStatement    stmt = null;
            int                  inscnt;
            long                 ScoreReportId;

            /*
            if( club.getFounderId() == -1 )
                throw new ClubsException( "ClubManager.save: Attempting to save a Club without a founder" );
                */
                    
            try {
            	System.out.println("Score Report Persistencey:"+ScoreReport.isPersistent());
                if(!ScoreReport.isPersistent()){
                    stmt = (PreparedStatement) con.prepareStatement( insertScoreReportSql );
                }
                else
                    stmt = (PreparedStatement) con.prepareStatement( updateScoreReportSql );

                if(!ScoreReport.isPersistent()){
                if( ScoreReport.getHomePoint()>=0 )
                    stmt.setInt( 1, ScoreReport.getHomePoint());
                else
                    stmt.setNull( 1, java.sql.Types.INTEGER);
                if( ScoreReport.getAwayPoints()>=0)
                    stmt.setInt( 2, ScoreReport.getAwayPoints());
                else
                    stmt.setNull( 2, java.sql.Types.INTEGER);

                if( ScoreReport.getDate()!= null ) {
                    java.util.Date jDate = ScoreReport.getDate();
                    java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                    stmt.setDate(3,sDate);
                }
                else
                    stmt.setNull(3, java.sql.Types.DATE);
                
                if( ScoreReport.getMatch().getId() >=0 ){
                	int m_id=(int) ScoreReport.getMatch().getId();
                    stmt.setLong(4,m_id);
                }//if
                else
                	throw new RDException( "ScoreReport.save: can't save a ScoreReport: matchid undefined" );

                
                if( ScoreReport.getStudent().getId()>=0){
                	int s_id=(int)ScoreReport.getStudent().getId();
                    stmt.setInt( 5,s_id);
                }//if
                else
                	throw new RDException( "ScoreReport.save: can't save a ScoreReport: studentid undefined" );

                }
                else{
                	System.out.println("clause for update scoreReport");
                if( ScoreReport.isPersistent() )
                    //stmt.setLong( 6, ScoreReport.getId() );
                	 if( ScoreReport.getHomePoint()>=0 )
                         stmt.setInt( 1, ScoreReport.getHomePoint());
                     else
                         stmt.setNull( 1, java.sql.Types.INTEGER);
                     if( ScoreReport.getAwayPoints()>=0)
                         stmt.setInt( 2, ScoreReport.getAwayPoints());
                     else
                         stmt.setNull( 2, java.sql.Types.INTEGER);

                     if( ScoreReport.getDate()!= null ) {
                         java.util.Date jDate = ScoreReport.getDate();
                         java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                         stmt.setDate(3,sDate);
                     }
                     else
                         stmt.setNull(3, java.sql.Types.DATE);
                     if( ScoreReport.getMatch().getId() >=0 ){
                     	int m_id=(int) ScoreReport.getMatch().getId();
                         stmt.setLong(4,m_id);
                     }//if
                     else
                     	throw new RDException( "ScoreReport.save: can't save a ScoreReport: matchid undefined" );

                	
                }
            
                

                inscnt = stmt.executeUpdate();

                if( !ScoreReport.isPersistent() ) {
                    if( inscnt >= 1 ) {
                        String sql = "select last_insert_id()";
                        if( stmt.execute( sql ) ) { // statement returned a result

                            // retrieve the result
                            ResultSet r = stmt.getResultSet();

                            // we will use only the first row!
                            //
                            while( r.next() ) {

                                // retrieve the last insert auto_increment value
                                ScoreReportId = r.getLong( 1 );
                                if( ScoreReportId > 0 )
                                    ScoreReport.setId(ScoreReportId); // set this person's db id (proxy object)
                            }
                        }
                    }
                    else
                        throw new RDException( "ScoreReport.save: failed to save a ScoreReport" );
                }
                else {
                    if( inscnt < 1 )
                        throw new RDException( "ScoreReport.save: failed to save a ScoreReport" ); 
                }
            }
            catch( SQLException e ) {
                e.printStackTrace();
                throw new RDException( "ScoreReport.save: failed to save a ScoreReport " + e );
            }/*finally{
            	stmt.close();
            	con.close();
            }*/
        }
        
        
        

        public Iterator<ScoreReport> restoreScoreReport(ScoreReport ScoreReport) 
                throws RDException, SQLException
        {
     
        
            //String       selectClubSql = "select id, name, address, established, founderid from club";
        	//id, homepoints, awaypoints, date , mid , uid
           // String       selectScoreReportSql = "select s.id, s.homepoints, s.awaypoints, s.date, m.mid, u.uid, " +
        	//"m.id, m.homepoints, m.awaypoints, m.mdate, m.isCompleted" +
            	//	"u.id, u.username, u.email, u.firstname, u.lastname, u.address, u.password, u.studentid, u.major, u.type_user "
                  //                 +       "from ScoreReport s, _table m, user u, where s.id=m.id and s.id = u.id";
            String select="select sr.id,sr.homepoints,sr.awaypoints,sr.date,sr.mid,sr.uid from scoreReport sr,match_table m,user u "
            		+"where sr.mid=m.id and sr.uid=u.id";
        	Statement    stmt = null;
            StringBuffer query = new StringBuffer( 100 );
            StringBuffer condition = new StringBuffer( 100 );

            condition.setLength(0);
            
            // form the query based on the given Club object instance
            query.append(select);
            
            if( ScoreReport != null ) {
                if( ScoreReport.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                    query.append( " and sr.id = " + ScoreReport.getId() );
               // else if( club.getName() != null ) // userName is unique, so it is sufficient to get a person
                 //   query.append( " and name = '" + club.getName() + "'" );
                else {
                	//id, homepoints, awaypoints, date , mid , uid
                	if(ScoreReport.getMatch().getId() > 0)
                		condition.append(" and sr.mid = " + ScoreReport.getMatch().getId());
                    if( ScoreReport.getHomePoint()>0)
                        condition.append( " and sr.homepoints = " + ScoreReport.getHomePoint()); 
                    if( ScoreReport.getAwayPoints()>0)
                        condition.append( " and sr.awaypoints = " + ScoreReport.getAwayPoints() );   

                    if( ScoreReport.getDate() != null ) {
                        if( condition.length() > 0 )
                            condition.append( " and" );
                        condition.append( " sr.date = '" + ScoreReport.getDate() + "'" );
                    }
                    
                    if( condition.length() > 0 ) {
                       // query.append(  " where " );
                        query.append( condition );
                    }
                    
                }
            }
            
            try {

                stmt = con.createStatement();
                System.out.println(query);
                // retrieve the persistent Person object
                //
                if( stmt.execute( query.toString() ) ) { // statement returned a result
                    ResultSet r = stmt.getResultSet();
                    return new ScoreReportIterator( r, ob );
                }
            }
            catch( Exception e ) {      // just in case...
                throw new RDException( "ScoreReport.restore: Could not restore persistent ScoreReport object; Root cause: " + e );
            }/*finally{
            	stmt.close();
            	con.close();
            }*/

            return null;
            //throw new RDException( "ScoreReport.restore: Could not restore persistent ScoreReport object; Root cause: " + e );
        }

		
        
      
    
    
    }
    
    
    

 

