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
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdwags.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class DeleteTeam
 */
@WebServlet("/DeleteTeam")
public class DeleteTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "DeleteTeam-Result.ftl";

    private Configuration cfg; 

    public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Template resultTemplate = null;
		BufferedWriter toClient = null;
        LogicLayer logicLayer = null;
        Team teamObject = null;
        HttpSession httpSession;
        Session session;
        String ssid;
        
        String teamName = request.getParameter("txtTeamName");
        
        
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } 
        catch (IOException e) {
            throw new ServletException("Can't load template in: " + templateDir + ": " + e.toString());
        }
        
        toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), resultTemplate.getEncoding() ));

        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());
   
     httpSession = request.getSession();
   //  if( httpSession == null ) {       // assume not logged in!
//         ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//         return;
   //  }
     
     ssid = (String) httpSession.getAttribute("ssid");
   //  if( ssid == null ) {       // not logged in!
//         ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//         return;
   //  }

     session = SessionManager.getSessionById(ssid);
   //  if( session == null ) {
//         ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//         return; 
   //  }
     
     logicLayer = session.getLogicLayer();
   //  if( logicLayer == null ) {
//         ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
//         return; 
   //  }
     
     try {
   	  
         logicLayer.deleteTeam(teamName);

         // Build the data-model
         //
         System.out.println("Deleted the team successfully");
        

     } 
     catch( Exception e) {
//         ClubsError.error( cfg, toClient, e );
//         return;
     }
     
     Map<String,Object> output_status = new HashMap<String,Object>();
     
     output_status.put("team_name", teamName);

     // Merge the data-model and the template
     //
     try {
         resultTemplate.process(output_status, toClient );
         toClient.flush();
     } 
     catch (Exception e) {
         throw new ServletException( "Error while processing FreeMarker template", e);
     }

     toClient.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
