package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class EditTeam
 */
@WebServlet("/EditTeam")
public class EditTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "EditTeam-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTeam() {
        super();
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
		
		Template resultTemplate = null;
		BufferedWriter toClient = null;
        String team_name = null;
        String captain_name = null;
        String league;
        Student returnStudent = null;
        long team_id = 0;
        LogicLayer logicLayer = null;
        HttpSession httpSession;
        Session session;
        String ssid;
        long newCaptainId = 0;
        
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } 
        catch (IOException e) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        
        toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), resultTemplate.getEncoding() ));

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
        
        league = request.getParameter("ddlLeagueName");
        long league_id = (Long) request.getSession().getAttribute("LeagueId");
        	//(Long) HttpSession.getAttribute("LeagueId");
        //long league_id =(Long) request.getAttribute("LeagueId");
        team_name = request.getParameter("txtTeamName");
        
        captain_name = request.getParameter("txtCaptainName");
        
       // Integer captain_id = (Integer) request.getSession().getAttribute("StudentId");
        
        Student captain = new StudentImplementor(null, null, captain_name, null, null, null, null, null);
        captain.setId(-1);
        List<Student> modelStudent = null;
        Student modStudentObject = null;
        try{
        	modelStudent = logicLayer.findStudent(captain);
        	System.out.println("size of mode : " + modelStudent.size());
        	for(int i=0;i<modelStudent.size();i++){
            	modStudentObject = modelStudent.get(i);
            }
            
        	long captain_id = modStudentObject.getId();
       
        
        
        //long captain_id_l = captain_id.longValue();
        
        
//        league = request.getParameter("txtLeagueName");
//        team_name = request.getParameter("txtTeamName");
//        captain_name = request.getParameter("txtCaptainName");
        
        if(team_name == null || captain_name == null ) {
            //ClubsError.error( cfg, toClient, "Unspecified club name or club address" );
        	System.out.println("Error in team and captain");
            return;
        }

        if(league == null) {
           // ClubsError.error( cfg, toClient, "Unspecified founder_id" );
        	System.out.println("Error in league");
            return;
        }
        
        if(!request.getParameter("txtCaptainName").equalsIgnoreCase(captain_name)){
        	String newCaptainName = request.getParameter("txtCaptainName").toString();
        	
        	Student newStudent = new StudentImplementor(null, null, newCaptainName, null, null, null, null, null);
        	
        	
        	try {
				returnStudent = (Student) logicLayer.findStudent(newStudent);
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newCaptainId = (Long) returnStudent.getId();
        }
       
        
        
        try {
           // team_id = logicLayer.createTeam(team_name, Long.parseLong(captain_name), Long.parseLong(league));
            team_id = logicLayer.editTeam(team_name, captain_id, league_id);
        } 
        catch ( Exception e ) {
         //   ClubsError.error( cfg, toClient, e );
        	e.printStackTrace();
            return;
        }
        
        Map<String,Object> output_status = new HashMap<String,Object>();

        // Build the data-model
        //
        output_status.put( "team_name", team_name);
        output_status.put( "team_id", new Long(team_id));

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
	catch(Exception e){
		e.printStackTrace();
	}

}
}
