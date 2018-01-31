package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException; 

import edu.uga.cs.recdawgs.RDException; 
//import edu.uga.cs.recDawgs.entity.Administrator; 
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.object.ObjectLayer; 

public class AdministratorIterator implements Iterator<Administrator> {
	
	private ResultSet rs = null; 
	private ObjectLayer objectLayer = null;
	private boolean more = false; 
	
	//constructor
	public AdministratorIterator(ResultSet rs, ObjectLayer objectModel) throws RDException{
			this.rs= rs;
			this.objectLayer = objectModel; 
			try {
				more=rs.next();
			}
			catch (Exception e) {
				throw new RDException("AdministratorIterator: Cannot create Administrator iterator; root cause: " + e);
			}	
	} //constructor	
	
	public boolean hasNext() {
		return more; 
	}
	
	public Administrator next() {
		int id; 
		String username; 
		String password; 
		String email; 
		String firstname; 
		String lastname; 
		String type_user; 
		Administrator administrator = null; 
		
		if(more) {
			try {
				id = rs.getInt(1);
				username = rs.getString(2);
				password = rs.getString(3);
				email = rs.getString(4);
				firstname = rs.getString(5);
				lastname = rs.getString(6);
                type_user=rs.getString(7);
				more = rs.next(); 
			} //try 
			catch(Exception e) {
				throw new NoSuchElementException("AdministratorIterator: No next Administrator object; root cause: " + e); 
			} //catch
			
			try {
				administrator = objectLayer.createAdministrator(firstname, lastname, username, password,email);
				administrator.settype_user();
				administrator.setId(id); 
			} //try			
			catch (RDException e) {
				//safe to ignore 
			}
			return administrator; 
		} //if more 
		else {
			throw new NoSuchElementException("AdministratorIterator: No next Administrator object");
		} //else no more 
	} //next
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
} //AdministratorIterator class 