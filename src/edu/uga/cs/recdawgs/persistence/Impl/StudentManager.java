package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.TeamImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class StudentManager {
	
	private ResultSet rs = null;
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	private boolean more = false;
	
	public StudentManager(Connection conn, ObjectLayer objLayer){
		System.out.println("In StudentManager"+objLayer);
		this.conn = conn;
		this.objectLayer = objLayer;
	}
	
	public Iterator<Student> restoreStudent( Student modelStudent ) throws RDException{
		
		//long id = modelStudent.getId();
		System.out.println("In restore Student"+modelStudent.getUserName());
		String getStudentSql = "select s.id,s.username,s.email,s.firstname,s.lastname,s.address,"
			+"s.password,s.studentid,s.major,s.type_user from user s";
		
		Statement statement = null;
		StringBuffer query = new StringBuffer(500);
		StringBuffer condition = new StringBuffer(500);
		
		condition.setLength(0);
		query.append(getStudentSql);
		
		if(modelStudent != null){
			if(modelStudent.getId() >= 0)
				query.append(" where s.id =" +modelStudent.getId());
			
			else if(modelStudent.getUserName() != null){
				System.out.println("in Username");
				query.append(" where s.username = '"+modelStudent.getUserName()+"'");
			}
			else{
				 if( modelStudent.getPassword() != null )
	                    condition.append( " s.password = '" + modelStudent.getPassword() + "'" );

	                if( modelStudent.getEmailAddress() != null ) {
	                    if( condition.length() > 0 )
	                        condition.append( " and" );
	                    condition.append( " s.email = '" + modelStudent.getEmailAddress() + "'" );
	                }
	                if(modelStudent.getFirstName() != null){
	                	if(condition.length() > 0)
	                		condition.append(" and");
	                	condition.append(" s.firstname ='" +modelStudent.getFirstName()+"'");
	                }
	                if(modelStudent.getLastName()!= null){
	                	if(condition.length() > 0)
	                		condition.append(" and");
	                	condition.append(" s.firstname ='" +modelStudent.getFirstName()+"'");
	                }
	                if(modelStudent.getStudentId() != null){
	                	if(condition.length() > 0)
	                		condition.append(" and");
	                	condition.append(" s.studentid =" +modelStudent.getStudentId());
	                }
	                if( condition.length() > 0 ) {
	                    query.append(  " where " );
	                    query.append( condition );
	                }
			}
		}
		try{
			 
				statement = conn.createStatement();
				if( statement.execute( query.toString() ) ) { // statement returned a result
			         ResultSet r = statement.getResultSet();
			         System.out.println("In restore"+objectLayer);
			         return new StudentIterator( r, objectLayer );
					
				 }
			}
				 catch(SQLException e){
					 e.printStackTrace();
					 throw new RDException( "Student:restore Could not restore student object; Root cause: " + e );
				 }	 
			return null;
	}
	
   
    public void storeStudent( Student student ) throws RDException, SQLException{
    	
    	String storeStudentSql = "insert into user (username,email,firstname,lastname,address,password,studentid,major,type_user) "
    		+"values(?,?,?,?,?,?,?,?,?)";
		String updateStudentSql = "update user set username=?,email=?,firstname=?,lastname=?,address=?,password=?,studentid=?,major=?,type_user=? where id=?";
		PreparedStatement pStatement = null;	
		int executeStatus ;
		long userId;
		try{
		
		if(!student.isPersistent() ){
			//throw new RDException( "TeamManagement: Attempting to save a team with team not defined" );
			System.out.println("Insert");
			pStatement = (PreparedStatement) conn.prepareStatement(storeStudentSql);
		}
		else
			pStatement = (PreparedStatement) conn.prepareStatement(updateStudentSql);
		
		if(student.getUserName() != null)
			pStatement.setString(1, student.getUserName());
		else
			throw new RDException("Cannot insert student object");
						 
		if(student.getEmailAddress() != null)
			pStatement.setString(2, student.getEmailAddress());
		else
			throw new RDException("Cannot insert student object");
		
		if(student.getFirstName() != null)
			pStatement.setString(3, student.getFirstName());
		else
			throw new RDException("Cannot insert student object");
		
		if(student.getLastName() != null)
			pStatement.setString(4, student.getLastName());
		else
			throw new RDException("Cannot insert student object");
		
		if(student.getAddress() != null)
			pStatement.setString(5, student.getAddress());
		else
			pStatement.setNull(5, java.sql.Types.NULL);
		
		if(student.getPassword() != null)
			pStatement.setString(6, student.getPassword());
		else
			throw new RDException("Cannot insert student object");
		
		if(student.getStudentId() != null)
			pStatement.setString(7, student.getStudentId());
		else
			throw new RDException("Cannot insert student object");
		
		if(student.getMajor() != null)
			pStatement.setString(8, student.getMajor());
		else
			throw new RDException("Cannot insert student object");
		  pStatement.setString(9,"Student");
		
		if(student.isPersistent()){
			pStatement.setLong(10,student.getId());
			
		}//if
		
	            
	        executeStatus = pStatement.executeUpdate();
	        
	        if( executeStatus >= 1 ) {
                String sql = "select last_insert_id()";
                if( pStatement.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = pStatement.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        userId = r.getLong( 1 );
                        if( userId > 0 )
                            student.setId( userId ); 
                    }
                }
            }
            else
                throw new RDException( "StudentManagement: failed to create a student" );
		
		
	}
		catch(SQLException e){
		e.printStackTrace();
		//throw new RDException( "League.storeLeague: failed to save a League information: " + e );			
		}
		
    	
    }
    
    
    public void deleteStudent( Student student ) throws RDException{
    	
    	String deleteTeamSql = "delete from user where id=?";
		PreparedStatement pStatement = null;
		int executeStatus;
		
		if(!student.isPersistent()){
			return;
		}
		try{
		pStatement =conn.prepareStatement(deleteTeamSql);
		
		if(student.getId() >= 0)
		pStatement.setLong(1, student.getId());
		else
			throw new RDException("deleteTeam: trying to delete a student that doesnot exist");
		
		executeStatus = pStatement.executeUpdate();
		
		 if( executeStatus == 1 ) {
             return;
         }
         else
             throw new RDException( "Student:delete failed to delete a student" );
		
     }
     catch( SQLException e ) {
         e.printStackTrace();
         throw new RDException( "Student:deleteStudent: failed to delete a student: " + e );        
         }
 }
    	
    }
	


