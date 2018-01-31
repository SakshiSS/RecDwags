package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import edu.uga.cs.recdawgs.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class EditLeague
 */
@WebServlet("/EditLeague")
public class EditLeague extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "EditLeague-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
    /**
     * Default constructor. 
     */
    public EditLeague() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("doPost of EditLeague");
		Template resultTemplate = null;
		BufferedWriter toClient = null;
		String leagueName = null;
		String leagueRules = null;
		String matchRules = null;
		boolean isIndoor = false;
		int minTeams = 0;
		int maxTeams = 0;
		int minMembers = 0;
		int maxMembers = 0;
		long leagueId = 0;
		LogicLayer logicLayer = null;
		HttpSession httpSession ;
		Session session ;
		String ssid;
		
		
        
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } 
        catch (IOException e) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        
        toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), resultTemplate.getEncoding() ));

        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        httpSession = request.getSession();
		if(httpSession == null) { //not logged in session expired
			//ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
			System.out.println("No session  for the user");
	          return;

		}
		
		ssid = (String) httpSession.getAttribute("ssid");
		if(ssid == null){
			//not logged in
			System.out.println("Not logged in");
			return;
		}
		session = SessionManager.getSessionById(ssid);
		if(session == null){
			//show error
			System.out.println("Session expired, not logged ");
			return;
		}
		
		 logicLayer = session.getLogicLayer();
		
		leagueName = request.getParameter("lName");
		leagueRules = request.getParameter("lRules");
		matchRules = request.getParameter("mRules");
		isIndoor = Boolean.getBoolean(request.getParameter("venueType"));
		//String minTeamsString = request.getParameter("minTeams");
		//System.out.println("Min Teams: "+minTeamsString);
		minTeams = Integer.parseInt(request.getParameter("minTeams"));
		//String maxTeamsString = request.getParameter("maxTeams");
		maxTeams = Integer.parseInt(request.getParameter("maxTeams"));
		minMembers = Integer.parseInt(request.getParameter("minMembers"));
		maxMembers = Integer.parseInt(request.getParameter("maxMembers"));
		
		String submit = request.getParameter("Submit");
		System.out.println("Submit value :" +submit);
		
		if( leagueName == null){
			System.out.println( "no league name");
			return;
		}
		if( leagueRules == null){
			//System.out.println( "no league rules");
			//return;
			leagueRules = "";
		}
		if( matchRules == null){
			//System.out.println( "no match Rules");
			//return;
			matchRules = "";
		}
		if( minTeams <= 0 ){
			System.out.println( "Please specify an appropriate number");
			return;
		}
		if( maxTeams <= 0 ){
			System.out.println( "Please specify an appropriate number");
			return;
		}
		if( minMembers <= 0 ){
			System.out.println( "Please specify an appropriate number");
			return;
		}
		if( maxMembers <= 0 ){
			System.out.println( "Please specify an appropriate number");
			return;
		}
		
		if(submit != null){
			System.out.println("Edit button clicked");
        
        try {
            leagueId = logicLayer.editLeague(leagueName, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minMembers, maxMembers);
            
        } 
        catch ( Exception e ) {
         //   ClubsError.error( cfg, toClient, e );
            return;
        }
        
        Map<String,Object> output_status = new HashMap<String,Object>();

        // Build the data-model
        //
        output_status.put( "league_name", leagueName);
        output_status.put( "league_id", new Long(leagueId));
        
        System.out.println( "data assigned to template successfully");

        // Merge the data-model and the template
        //
        try {
            resultTemplate.process(output_status, toClient);
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();
		
	}
		
		else{
			System.out.println("invalid");
		}

}
}