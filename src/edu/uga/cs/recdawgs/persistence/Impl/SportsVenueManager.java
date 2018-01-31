package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class SportsVenueManager {
	
	Connection con=null;
	ObjectLayer ob=null;
	public SportsVenueManager( Connection conn,ObjectLayer ob){
		this.con=conn;
		this.ob=ob;
	}
	
	public void storeSportsVenue( SportsVenue sportsVenue ) throws RDException, SQLException{
		String insertSql="insert into sportsVenue(name,address,isIndoor)values(?,?,?)";
		String updateSql="update sportsVenue set name=?,address=?,isIndoor=? where id =?";
		PreparedStatement    stmt = null;
        int                  inscnt;
        long                 svId;
        
        System.out.println("sv_id"+sportsVenue.getId());
        System.out.println("sv_ispersistent"+sportsVenue.isPersistent());
			try {
				if(!sportsVenue.isPersistent() ){
				stmt = (PreparedStatement) con.prepareStatement(insertSql);
				}//if
				else{
					System.out.println("Update");
					stmt =(PreparedStatement)con.prepareStatement(updateSql);
				}//else
				
				if(sportsVenue.getName() != null ) // name is unique unique and non null
	                stmt.setString( 1, sportsVenue.getName() );
	            else 
	                throw new RDException( "SportsVenue.save: can't save a SportsVenue:name undefined" );
                  
				if( sportsVenue.getAddress() != null )
	                stmt.setString(2,sportsVenue.getAddress() );
	            else
	                stmt.setNull(2,java.sql.Types.VARCHAR );
				
				stmt.setBoolean(3,sportsVenue.getIsIndoor());
				if(sportsVenue.isPersistent()){
					stmt.setLong(4,sportsVenue.getId());
				}//if

				inscnt=stmt.executeUpdate();
				if( inscnt >= 1 ) {
					String sql = "select last_insert_id()";
	                if( stmt.execute( sql ) ) { // statement returned a result

	                    // retrieve the result
	                    ResultSet r = stmt.getResultSet();
	                    while( r.next() ) {

	                        // retrieve the last insert auto_increment value
	                        svId = r.getLong( 1 );
	                        if( svId > 0 )
	                            sportsVenue.setId(svId); // set this membership's db id (proxy object)
	                    }//while
	               }//if
	                    
	              }//if
				else{
					throw new RDException( "SportsVenue.save: failed to save a SportsVenue");
		            
				}//else
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RDException( "SportsVenue.save:failed to save a SportsVenue: " + e );
			       
			}finally{
				//stmt.close();
			//	con.close();
			}
		


		
	       
	}//storeSportsVenue
	
	public void deleteSportsVenue(SportsVenue sv) throws RDException, SQLException{
		System.out.println("In delete Method");
		System.out.println("id::"+sv.getId());
		 String deleteSql = "delete from sportsVenue where id = ?";              
		 PreparedStatement    stmt = null;
		 int del;
		 if( !sv.isPersistent()) // is the Match object persistent?  If not, nothing to actually delete
	            return;
		   try {
			stmt = (PreparedStatement) con.prepareStatement( deleteSql );
			stmt.setLong(1,sv.getId());
			 del=stmt.executeUpdate();
			 if( del == 1 ) {
	                return;
	            }
	            else{
	                throw new RDException( "SportsVenueManager.delete: failed to delete a Match" );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RDException( "SportsVenueManager.delete: failed to delete a Match: " + e );        
			
		    
		}finally{
			//stmt.close();
			//con.close();
		}
	 
}//delete
	
	public Iterator<SportsVenue> restoreSportsVenue( SportsVenue modelSportsVenue ) throws RDException, SQLException
	{
		//String n=modelSportsVenue.getName();
		//System.out.println("SportsVenue"+modelSportsVenue.getName());
		String   selectSql="select id,name,address,isIndoor from sportsVenue";
		StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        condition.setLength(0);
        query.append(selectSql);
        if(modelSportsVenue!= null ) {
        	System.out.println("modelSports!=null");
        	if(modelSportsVenue.getId()>=0){// id is unique, so it is sufficient to get a person
                System.out.println("id"+modelSportsVenue.getId());
        		query.append( " where id = " + modelSportsVenue.getId());
        	}
        	 else if(modelSportsVenue.getName()!= null){ // userName is unique, so it is sufficient to get a person
               System.out.println("where name");
        		 query.append( " where name = '" + modelSportsVenue.getName() + "'" );
        }//else
        	 else {
                   if( modelSportsVenue.getAddress() != null )
                     query.append( " where address = '" + modelSportsVenue.getAddress() + "'" );
                   
        	 }//else

        }//if
        
        Statement    stmt = null;
        try {
			stmt =(Statement) con.createStatement();
			if( stmt.execute( query.toString() ) ) { // statement returned a result
			    //stmt.executeQuery(query.toString());
				ResultSet rs=stmt.getResultSet();
                return new  SportsVenueIterator(rs,ob);
			}//if
            
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RDException( "SportsVenue.restore: Could not restore persistent SportsVenue object; Root cause: " + e );

		}finally{
			//stmt.close();
			//con.close();
		}
        
   return null;
		
	}//restore
	

	
	
}
