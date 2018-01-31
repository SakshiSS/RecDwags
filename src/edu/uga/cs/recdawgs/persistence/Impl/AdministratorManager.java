package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.Iterator; 

import java.sql.PreparedStatement; 

import edu.uga.cs.recdawgs.RDException; 
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.impl.AdministratorImpl; 
import edu.uga.cs.recdawgs.object.ObjectLayer; 

public class AdministratorManager {

	private ObjectLayer objectLayer = null; 
	Connection conn = null; 
	
	public AdministratorManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer; 
	}
		
	
	public Iterator<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RDException {
		// TODO Auto-generated method stub
		System.out.println("In Restore");
		String getStudentSql = "select s.id,s.username,s.email,s.firstname,s.lastname,"
				+"s.password,s.type_user from user s";
	
		Statement statement = null;
		StringBuffer query = new StringBuffer(500);
		StringBuffer condition = new StringBuffer(500);
		
		condition.setLength(0);
		query.append(getStudentSql);
		//long id=modelAdministrator.getId();
		if(modelAdministrator != null){
			if(modelAdministrator.getId() >= 0)
				query.append(" where s.id="+modelAdministrator.getId());
			else if(modelAdministrator.getUserName() != null)
				query.append(" where s.username = '"+modelAdministrator.getUserName()+"'");
			else{
				 if( modelAdministrator.getPassword() != null )
	                    condition.append( " s.password = '" + modelAdministrator.getPassword() + "'" );

	                if( modelAdministrator.getEmailAddress() != null ) {
	                    if( condition.length() > 0 )
	                        condition.append( " and " );
	                    condition.append( " s.email = '" + modelAdministrator.getEmailAddress() + "'" );
	                }
	                if(modelAdministrator.getFirstName() != null){
	                	if(condition.length() > 0)
	                		condition.append(" and ");
	                	condition.append(" s.firstname ='" +modelAdministrator.getFirstName()+"'");
	                }
	                if(modelAdministrator.getLastName()!= null){
	                	if(condition.length() > 0)
	                		condition.append(" and ");
	                	condition.append(" s.firstname ='" +modelAdministrator.getFirstName()+"'");
	                }
	              
	                if( condition.length() > 0 ) {
	                    query.append(  " where " );
	                    query.append( condition );
	                }
			}
		}
		try{
			if(modelAdministrator==null){
			 System.out.println("Admin is null");
			}//if
				statement = conn.createStatement();
				if( statement.execute( query.toString() ) ) { // statement returned a result
			         ResultSet r = statement.getResultSet();
			         return new AdministratorIterator( r, objectLayer );
					
				 }
			}
				 catch(SQLException e){
					 e.printStackTrace();
					 throw new RDException( "Administrator:restore Could not restore administrator object; Root cause: " + e );
				 }	 
			return null;
	
		
		
		//return null;
	}
		
		
	public void storeAdministrator(Administrator administrator)	throws RDException {
		
		String insertAdministratorSql = "insert into user (firstname, lastname, username, password, email, type_user, address, studentid, major) values(?,?,?,?,?,?,?,?,?)" ; 
		String updateAdministratorSql = "update user set firstname = ?, lastname = ?, username = ?, password = ?, email = ?, type_user = ?, address = ?, studentid = ?, major = ? where id=?"; 
		PreparedStatement stmt = null; 
		int inscnt;
		long administratorID; 
		
		try{ 
		
			// set up prepared statements 
			if(!administrator.isPersistent()) {	
				stmt = (PreparedStatement) conn.prepareStatement(insertAdministratorSql); 
			}
			else {
				stmt = (PreparedStatement) conn.prepareStatement(updateAdministratorSql); 
			}
			
			//set firstName
			if (administrator.getFirstName() != null) {
				stmt.setString(1, administrator.getFirstName());
			}
			else {
				throw new RDException("AdministratorManager.store: can't store an Administrator: firstName undefined"); 
				}
				
			//set lastName 
			if (administrator.getLastName() != null) {
				stmt.setString(2, administrator.getLastName());
			}
			else {
				throw new RDException("AdministratorManager.store: can't store an Administrator: lastName undefined");
			}
			
			//set userName
			if (administrator.getUserName() != null) {
				stmt.setString(3, administrator.getUserName());
			}
			else{
				throw new RDException("AdministratorManager.store: can't store an Administrator: lastName undefined"); 	
			}
			
			//set password
			if (administrator.getPassword() != null) {
				stmt.setString(4, administrator.getPassword());
			}
			else{ 
				throw new RDException("AdministratorManager.store: can't store an Administrator: password undefined");
			}

			//set email
			if (administrator.getEmailAddress() != null) {
				stmt.setString(5, administrator.getEmailAddress());
			}
			else{
				throw new RDException("AdministratorManager.store: can't store an Administrator: email address undefined");
			}
			
			//set type_user
			stmt.setString(6, "administrator");

			//set address, studentid, and major to null
			stmt.setNull(7, java.sql.Types.VARCHAR); 
			stmt.setNull(8, java.sql.Types.VARCHAR);
			stmt.setNull(9, java.sql.Types.VARCHAR); 

			if(administrator.isPersistent()) {
				stmt.setLong(5, administrator.getId());
			}
			
			inscnt = stmt.executeUpdate();
			long administratorId=0;
			if(!administrator.isPersistent()) {
				if(inscnt >= 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) { //statement returned a result
						//retrieve the result
						ResultSet r = stmt.getResultSet();
						
						//we will only use the first row
						while(r.next()) {
							//retrieve last insert auto_increment value
							administratorId = r.getLong(1);
							if(administratorId > 0){
								administrator.setId(administratorId); //set this person's DB id
							}
						} //while 
					}
				} //if inscnt >= 1
				else {
					throw new RDException("AdministratorManager.store: failed to store an Administrator");
				}
			} //if not persistent 
			else {
				if(inscnt < 1) {
					throw new RDException("AdministratorManager.store: failed to store an Administrator");
				}
			} //else persistent
			
		} //try
		catch(SQLException e) {
			e.printStackTrace(); 
			throw new RDException("AdministratorManager.store: failed to store an Administrator: " + e); 
		} //catch
	} //storeAdministrator

	public void deleteAdministrator(Administrator administrator) throws RDException {
		String deleteAdministratorSql = "DELETE FROM user WHERE id = ?";
		PreparedStatement stmt = null; 
		int inscnt; 
		
		if(!administrator.isPersistent()) {
			return; 
		} //if not persistent 
		
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteAdministratorSql);
			
			stmt.setLong(1,administrator.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) {
				return; 
			}
			else {
				throw new RDException("AdministratorManager.delete: failed to delete an Administror");
			}
		} //try 
		catch(SQLException e) {
			e.printStackTrace();
			throw new RDException("AdministratorManager.delete: failed to delete an Administror: " + e);
		} //catch
	} //deleteAdministrator

} //AdministratorManager
