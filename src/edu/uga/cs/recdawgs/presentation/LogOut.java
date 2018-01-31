package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	     System.out.println("In Logout");
	     HttpSession httpsession=null;
		Session session=null;
		String ssid=null;
		LogicLayer  logicLayer = null;
		httpsession=request.getSession(false);
		if( httpsession != null ) {
			ssid = (String) httpsession.getAttribute( "ssid" );
			if(ssid!=null){
		    session = SessionManager.getSessionById( ssid );
		    if( session == null ) {
               // ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
                System.out.println("Session is null");
		    	return; 
            }//if
		    logicLayer=session.getLogicLayer();
		    try {
				logicLayer.logout(ssid);
				httpsession.removeAttribute("ssid");
	            httpsession.invalidate();
	            System.out.println( "Invalidated http session" );
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}//if
			else{
				System.out.println("ssid is Null");
			}//else
			response.sendRedirect("Login_Page.html");    
			
 	}//if
		else{
			System.out.println("No Session");
		}//else
	        
    	
	 }//doget

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In Logout");
		//doGet(request, response);
	}*/

}
