package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class AssignDate
 */
@WebServlet("/AssignDate")
public class AssignDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "AssignDate-Result.ftl";

    private Configuration  cfg; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignDate() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), 
                "WEB-INF/templates"
                );
    }

  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Template       resultTemplate = null;
        BufferedWriter toClient = null;
        
		HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		
        resultTemplate = cfg.getTemplate( resultTemplateName );
	    
	    
	    
	    toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),resultTemplate.getEncoding()));
         
	    response.setContentType("text/html; charset=" + resultTemplate.getEncoding());


		httpSession = request.getSession();

		ssid = (String) httpSession.getAttribute( "ssid" );

        if( ssid == null ) {       // not logged in!
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("ssid=nul");
            return;
        }

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
        	System.out.println("session==null");
            return; 
        }
        
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
            //ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            System.out.println("logiclayer=null");
        	return; 
        }
        
        String team1=new String("team1");
		 String team2=new String("team2");
		 String id=new String("id");
		 String team1Name=(String) httpSession.getAttribute("team1");
		 String team2Name=(String) httpSession.getAttribute("team2");
		 String id1=(String) httpSession.getAttribute("id");
		 
		 System.out.println("team1-->team2-->id"+team1Name+"-->"+team2Name+"-->"+id1);
		 long m_id=Long.parseLong(id1);
		 String date=request.getParameter("Match_Date");
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 Date d=null;
		 try {
			d=formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 Match m=new MatchImplementor(0,0,null,false,null,null);
		 m.setId(m_id);
		 try {
			m.setDate(d);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 logicLayer.scheduleMatch(m);
		 
		 Map<String,Object> root = new HashMap<String,Object>();
         
		 try {
	            resultTemplate.process( root, toClient );
	            toClient.flush();
	        } 
	        catch (TemplateException e) {
	            throw new ServletException( "Error while processing FreeMarker template", e);
	        }

	        toClient.close();



	}//post

}
