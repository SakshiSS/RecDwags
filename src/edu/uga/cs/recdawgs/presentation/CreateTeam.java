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

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.impl.LeagueImplementor;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class CreateTeam
 */
@WebServlet("/CreateTeam")
public class CreateTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "CreateTeam-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession sess_Student_Name = request.getSession();
//		sess_Student_Name.setAttribute("StudentName","JJosh");
//		HttpSession sessStudentId = request.getSession();
//		sessStudentId.setAttribute("StudentId", 17);
//		String studentName = (String) sess_Student_Name.getAttribute("StudentName");
		Template resultTemplate = null;
		BufferedWriter toClient = null;
        String team_name = null;
        String captain_name = null;
        String league = null;
        long team_id = 0;
        long league_id = 0;
        long captain_id = 0;
        LogicLayer logicLayer = null;
        HttpSession httpSession;
        Session session;
        String ssid;
        League modelLeague = null;
        League newLeague = null;
        
        System.out.println("Create Team entered");
        
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } 
        catch (IOException e) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        toClient = new BufferedWriter(new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() ));

        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

    //    response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        httpSession = request.getSession();
//        if( httpSession == null ) {       // assume not logged in!
//            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//            return;
//        }

        ssid = (String) httpSession.getAttribute("ssid");
//        if( ssid == null ) {       // not logged in!
//            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//            return;
//        }
        session = SessionManager.getSessionById( ssid );
//        if( session == null ) {
//            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//            return; 
//        }
        
        logicLayer = session.getLogicLayer();
//        if( logicLayer == null ) {
//            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//            return; 
//        }
        
        league = request.getParameter("ddlLeagueName").toString();
        System.out.println("League name in  dropdown :" +league);
      team_name = request.getParameter("txtTeamName");
      captain_name = request.getParameter("txtCaptainName");
      
       league_id = Long.parseLong(league);
      
      
      //i am here
      //modelLeague = new LeagueImplementor(league, null, null, false, 0, 0, 0, 0);
      try {
		newLeague = logicLayer.searchLeague(league);
	} catch (RDException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		captain_id = Long.parseLong(request.getSession().getAttribute("StudentId").toString());
	
        //get captian_id
		//league_id = newLeague.getId();
		System.out.println("League id" +league_id);
        
        if(team_name == null || captain_name == null ) {
            //ClubsError.error( cfg, toClient, "Unspecified club name or club address" );
            return;
        }

        if(league == null) {
           // ClubsError.error( cfg, toClient, "Unspecified founder_id" );
            return;
        }
        
        String submit = request.getParameter("Submit");
        System.out.println("Submit: "+submit);
        
        if(submit != null){
        	System.out.println("Submit clicked");
        try {
            team_id = logicLayer.createTeam(team_name, captain_id, league_id);
        } 
        catch ( Exception e ) {
         //   ClubsError.error( cfg, toClient, e );
        	e.printStackTrace();
            return;
        }
        }
        
        Map<String,Object> output_status = new HashMap<String,Object>();

        output_status.put( "team_name", team_name);
        output_status.put( "team_id", new Long(team_id));

        try {
            resultTemplate.process(output_status, toClient);
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();	
		
	}

}
