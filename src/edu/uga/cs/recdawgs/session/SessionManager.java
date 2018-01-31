package edu.uga.cs.recdawgs.session;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.persistence.Impl.DbUtils;

public class SessionManager {

    private static Map<String, Session> sessions;
    private static Map<String, Session> loggedIn;
    
    static{
        sessions = new HashMap<String, Session>();
        loggedIn = new HashMap<String, Session>();
    } 

    public static void logout(Session s) 
            throws RDException
    {
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    public static void logout(String ssid) 
            throws RDException
    {
        Session s = getSessionById(ssid);
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    
    public static Session createSession() 
            throws RDException 
    {
        Connection conn = null;
        Session s = null;
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            throw new  RDException( "SessionManager.login: Unable to get a database connection" );
        }
        s = new Session( conn );
        return s;
        
    }//session
public static Session getSessionById(String ssid){
	return sessions.get(ssid);
}

public static String secureHash( String input ) 
        throws RDException
{
    StringBuilder output = new StringBuilder();
    try {
        MessageDigest md = MessageDigest.getInstance( "MD5" );
        md.update( input.getBytes() );
        byte[] digest = md.digest();
        for( int i = 0; i < digest.length; i++ ) {
            String hex = Integer.toHexString( digest[i] );
            if( hex.length() == 1 )
                hex = "0" + hex;
            hex = hex.substring( hex.length() - 2 );
            output.append( hex );
        }
    }
    catch( Exception e ) {
        throw new RDException("SessionManager.secureHash: Invalid Encryption Algorithm" );
    }//catch
    
    return output.toString();
}

public static String storeSession( Session session ) 
        throws RDException{
	Student s=session.getUser();
	if( loggedIn.containsKey(s.getUserName()) ) {
        Session qs = loggedIn.get(s.getUserName());
        qs.setUser(s);
        return qs.getSessionId();
    }//if
	//SecureHashDoubt
	String ssid = secureHash( "STUDENT" + System.nanoTime() );
    session.setSessionId( ssid );
    
    sessions.put( ssid, session );
    loggedIn.put( s.getUserName(), session );
    session.start();
    return ssid;

   }//store

protected static void removeSession( Session s ) 
        throws RDException
{
    try { 
        s.getConnection().close();
    } 
    catch( SQLException sqe ) { 
        //log.error( "SessionManager.removeSession: cannot close connection", sqe );
        throw new RDException( "SessionManager.removeSession: Cannot close connection" );
    } // try
    sessions.remove( s.getSessionId() );
    loggedIn.remove( s.getUser().getUserName() );
}

}
