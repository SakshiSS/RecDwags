package edu.uga.cs.recdawgs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;
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
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.impl.MatchImplementor;
import edu.uga.cs.recdawgs.entity.impl.StudentImplementor;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import edu.uga.cs.recdwags.logic.LogicLayer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class AssignScores
 */
@WebServlet("/AssignScores")
public class AssignScores extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static String templateDir = "WEB-INF/templates";
    static String resultTemplateName = "EnterMatchScore-Result.ftl";

    private Configuration cfg; 

    @SuppressWarnings("deprecation")
	public void init() 
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignScores() {
        super();
        // TODO Auto-generated constructor
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
//    static  String         templateDir = "WEB-INF/templates";
//    static  String         resultTemplateName = "AssignScores-Result.ftl";
//
//    private Configuration  cfg; 
//
//    public void init() 
//    {
//        // Prepare the FreeMarker configuration;
//        // - Load templates from the WEB-INF/templates directory of the Web app.
//        //
//        cfg = new Configuration();
//        cfg.setServletContextForTemplateLoading(
//                getServletContext(), 
//                "WEB-INF/templates"
//                );
//    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Template       resultTemplate = null;
        BufferedWriter toClient = null;
        
		HttpSession httpSession=null;
		String ssid=null;
		Session session=null;
		LogicLayer logicLayer=null;
		//System.out.println("JoinTeams");
		
		 Match match = null;
	     ScoreReport scoreReport = null;
	     List<ScoreReport> scoreReportList = null;
	     
	     List<Team> teamList = null;
	     List<List<Object>> teams = null;
	     List<Object> teamObject = null;
	     Team team  = null;
		
		System.out.println( "Assign scores servlet ");
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
        
       
        DateFormat dateFormat = null;
//        Date date = null;
//        try {
//			date = dateFormat.parse(request.getParameter("date"));
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			date = null;
//			e1.printStackTrace();
//		}
		
        
		 String team1=new String("team1");
		 String team2=new String("team2");
		 String id=new String("id");
		 String team1Name=(String) httpSession.getAttribute("team1");
		 String team2Name=(String) httpSession.getAttribute("team2");
		 String id1=(String) httpSession.getAttribute("id");
		 System.out.println("Id1 : "+id1); //what is this id
		 System.out.println("team1-->team2-->id"+team1Name+"-->"+team2Name+"-->"+id1);
		 String Name=new String("Name");
		 //httpsession.setAttribute(Name,username);
		 String studentName=(String) httpSession.getAttribute(Name);
		 System.out.println("StudentName::"+studentName);
		 Student s=new StudentImplementor(null,null,null,null,null,null,null,null);
		 s.setUserName(studentName);
		 s.setId(-1);
		 List<Student> l=null;
		 try {
			l=logicLayer.findStudent(s);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 long sid=0;
		 for(int k=0;k<l.size();k++){
			 Student s1=l.get(k);
			 System.out.println("id::"+s1.getId());
			 sid=s1.getId();
		 }//for
		 long m_id=Long.parseLong(id1);
		 String HomeScores=request.getParameter("HomeScores");
		 String AwayScores=request.getParameter("AwayScores");
		 int home_scores=Integer.parseInt(HomeScores);
		 int away_scores=Integer.parseInt(AwayScores);
		 Date m_date=null;
		 Match m=new MatchImplementor(home_scores,away_scores,m_date,false,null,null);
		 m.setId(m_id);
//FInd if the given score report is already present or not
		 
		 //scoreReportList = logicLayer.getScoreReport(); // here now
		 
		 String status = null; 
// uncomment the below code when u want to create a new score report		 
		 try {
			 
			status =  logicLayer.createScoreReport(HomeScores,AwayScores,m_date,sid,m);
		} catch (RDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 Map<String,Object> root = new HashMap<String,Object>();
		 System.out.println("The status of scores assigned is :" +status);
		 root.put("status", status);

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
