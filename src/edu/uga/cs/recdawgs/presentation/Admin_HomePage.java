package edu.uga.cs.recdawgs.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin_HomePage
 */
//Rediration code for Admin HomePage to other pages(i.e. Venue,League,etc)
@WebServlet("/Admin_HomePage")
public class Admin_HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Admin Home Page");
		String league=request.getParameter("league");
		String summary=request.getParameter("summary");
		String venue=request.getParameter("venue");
		String contact=request.getParameter("contact");
		String teams=request.getParameter("teams");
		String smatch=request.getParameter("sMatch");
		String logout=request.getParameter("logout");
		String findUser=request.getParameter("findUser");
		String update=request.getParameter("update");
		String CancelRegisteration=request.getParameter("CancelRegisteration");
		
		if(league!=null){
			//Redirection code for league
			System.out.println("League Successfull");
			response.sendRedirect("League_Home.html");
		}//if
		else if(summary!=null){
			//Redirection code for Summary
			System.out.println("Summary Successfull");
			response.sendRedirect("League_Home.html");
			
			
		}//if
		else if(CancelRegisteration!=null){
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/CancelRegisteration").forward(request,response);
			//response.sendRedirect("CancelRegistration.html");
	
		}//if
		else if(venue!=null){
			//Redirection code for Venue
			System.out.println("Venue Successfull");
			response.sendRedirect("Venue_HomePage.html");
					
			
		}//if
		else if(teams!=null){
			//Redirection code for Teams
			System.out.println("Team Successfull");
			response.sendRedirect("Team_HomePage.html");
			
		}//if
		else if(smatch!=null){
			System.out.println("Match successfull");
			response.sendRedirect("Match_HomePage.html");
		}//if
		else if(logout!=null){
			ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("/LogOut").forward(request,response);
	
			
		}//if
		else if(findUser!=null){
			/*ServletContext context=request.getSession().getServletContext();
            context.getRequestDispatcher("").forward(request,response);*/
			response.sendRedirect("FindUser.html");
			
			
		}
		else if(update != null){
			System.out.println("Inside update" + update);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/UpdateStudentDetails");
			rd.forward(request, response);
		}

			else{
			//Redirection to Contact
			System.out.println("Contact");
			response.sendRedirect("Contact_Page.html");
		}//else
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}*/

}
