package edu.uga.cs.recdawgs.persistence.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class StudentIterator implements Iterator<Student>{
	
	ResultSet rs=null;
	ObjectLayer obj=null;
	boolean more=false;
	public StudentIterator(ResultSet rs,ObjectLayer ob){
		this.rs=rs;
		this.obj=ob;
		try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            try {
				throw new RDException( "SportsVenueIterator: Cannot create an iterator; root cause: " + e );
			} catch (RDException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return more;
	}
	@Override
	public Student next() {
		// TODO Auto-generated method stub
		 
		 String firstName=null;
		 String lastName=null;
		 String userName=null;
	     String password=null;
		 String email=null;
		 String studentid=null;
	     long id=0;
	     Student s=null;
	 	
		 String majors=null;
	     String address=null;
	     if(more){
		 try {
			id=rs.getLong(1);
			userName=rs.getString(2);
			email=rs.getString(3);
			firstName=rs.getString(4);
			lastName=rs.getString(5);
			address=rs.getString(6);
			password=rs.getString(7);
			studentid=rs.getString(8);
			majors=rs.getString(9);
			
			more=rs.next();
			System.out.println(id+"-"+userName+"-"+email+"-"+firstName+"-"+lastName+"-"+address+"-"+password+"-"+studentid+"-"+majors);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("Obj"+obj);
		 try {
		s=obj.createStudent(firstName,lastName,userName,password,email,studentid,majors,address);
		s.setId(id);
	} catch (RDException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
	     }//if
	     return s;
		
		
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
		
	}

}
