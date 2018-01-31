package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

//import com.mysql.jdbc.conection;
//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
//import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import java.sql.Connection;
public class RoundManager {
	

	Connection con=null;
	ObjectLayer ob=null;
	long mid=0;
	public RoundManager(Connection con,ObjectLayer ob){
		this.con=con;
		this.ob=ob;
	}

    
    public void storeRound(Round Round) throws RDException, SQLException
    {
        String               insertRoundSql = "insert into Round ( id, number, lid) values ( ?, ?, ? )";
        String               updateRoundSql = "update Round set id = ?, number = ?, lid = ?";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 RoundId;

        /*
        if( Round.getFounderId() == -1 )
            throw new RoundsException( "RoundManager.save: Attempting to save a Round without a founder" );
            */
                 
        try {

            if( !Round.isPersistent() )
                stmt = (PreparedStatement) con.prepareStatement( insertRoundSql );
            else
                stmt = (PreparedStatement) con.prepareStatement( updateRoundSql );

            if( Round.getId()>=0) // name is unique unique and non null
                stmt.setLong( 1, Round.getId() );
            else 
                throw new RDException( "RoundManager.save: can't save a Round: name undefined" );

            if( Round.getNumber()>=0)
                stmt.setInt( 2, Round.getNumber() );
            else
                stmt.setNull( 2, java.sql.Types.INTEGER);


            if( Round.getLeague().getId()>=0 && Round.getLeague().isPersistent()){
                int lid=(int)Round.getLeague().getId();
                stmt.setLong(3,lid);
            }
            else 
                throw new RDException( "RoundManager.save: can't save a Round: league id is not set or not persistent" );
            
            if( Round.isPersistent() )
                stmt.setLong(4,Round.getId());

            inscnt = stmt.executeUpdate();

            if( !Round.isPersistent() ) {
                if( inscnt >= 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while( r.next() ) {

                            // retrieve the last insert auto_increment value
                            RoundId = r.getLong( 1 );
                            if( RoundId > 0 )
                                Round.setId( RoundId ); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RDException( "RoundManager.save: failed to save a Round" );
            }
            else {
                if( inscnt < 1 )
                    throw new RDException( "RoundManager.save: failed to save a Round" ); 
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "RoundManager.save: failed to save a Round: " + e );
        }
    }

    public Iterator<Round> restoreRound(Round Round) throws RDException, SQLException
    {
        //String       selectRoundSql = "select id, name, address, established, founderid from Round";
    	
    	//String   selectSql="select m.id,m.home_points,m.away_points,m.mdate,m.isCompleted,s.id,s.name,"+
 	  // "s.address,s.isIndoor,r.id,r.number,t.id,t.name,t.id,t.name"+ 
	   // "from match m,sportsVenue s,round r,team t,where m.id=s.id and m.id=r.id and m.id=t.id";
       
    	String       selectSql = "select r.id, r.number, l.id, l.name," + 
    	"l.leagueRules, l.matchRules, l.isIndoor, l.minTeams, l.maxTeams, l.minMembers , l.maxMembers " + 
    	"from round r, league l where r.lid=l.id";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        System.out.println("In restore round");
        query.append(selectSql);
        /*if(!Round.isPersistent()){
        throw  new RDException("RoundRestore::Round is not persistent");
        }*/
        if(Round!=null){
        	//if(Round.isPersistent()){
        		System.out.println("rnumber");
        	  query.append(" and r.number="+Round.getNumber());	
        	//}//if
        }//if
        try {
			stmt = con.createStatement();
			if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new RoundIterator(r,ob);
            }//if
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
	
    public void deleteRound( Round round ) throws RDException, SQLException{
    	String del="delete from Round where id=?";
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
			ps=(PreparedStatement) con.prepareStatement(del);
			ps.setLong(1,round.getId());
			int del1=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }//delete
	 
}
        