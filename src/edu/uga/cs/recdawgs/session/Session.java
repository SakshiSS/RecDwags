package edu.uga.cs.recdawgs.session;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImplementor;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;
import edu.uga.cs.recdawgs.persistence.Impl.Persistance_Layer_Impl;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdwags.logic.impl.LogicLayerImpl;

public class Session extends Thread {
	 
	private Connection conn;
    private ObjectLayer objectLayer;
    private LogicLayer logicLayer;
   // private Person person;
    private Student student;
    private String id;
    private Date expiration;
   // private static Logger log = SessionManager.getLog();
    
    public void run(){
    	long diff = expiration.getTime() - System.currentTimeMillis();
    	while (diff > 0) {
    		try {
                sleep(diff);
            } 
            catch( Exception e ) {
                //e.printStackTrace();
                break;
            }
    		diff = expiration.getTime() - System.currentTimeMillis();

    	}//while
    	try {
            SessionManager.removeSession( this );
        } 
        catch( RDException e ) {
            //log.error( e.toString(), e );
            try {
                throw e;
            } 
            catch (RDException e1) {
                e1.printStackTrace();
            }
        }

    	
    }//run
	public Session( Connection conn )
    {
        this.conn = conn;
        objectLayer = new ObjectLayerImplementor();
        PersistenceLayer persistence = new Persistance_Layer_Impl( conn, objectLayer ); 
        objectLayer.setPersistence( persistence ); 
        logicLayer = new LogicLayerImpl( objectLayer );
          extendExpiration();
    }//Session
	public Connection getConnection()
    {
       extendExpiration();
        return conn;
    }
	private void extendExpiration(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 30);
        this.expiration = c.getTime();
    }
    
    

    public LogicLayer getLogicLayer()
    {
        return logicLayer;
    }

    public void setUser(Student student) 
            throws RDException
    {
        extendExpiration();
        this.student=student;
    }

    public Student getUser()
    {
       extendExpiration();
        return student;
    }
    

    public String getSessionId()
    {
       extendExpiration();
        return id;
    }
    
    public void setSessionId( String id )
    {
        this.id = id;
    }
    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }
    
    





    
	
    

}
