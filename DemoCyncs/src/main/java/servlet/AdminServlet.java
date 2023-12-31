package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminCheck;

/**
 * Servlet implementation class UserResistServlet
 */
@WebServlet("/UserResistServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		AdminCheck adminCheck = new AdminCheck();
		boolean adminFlag = adminCheck.getAdminFlag(user_id);
		
		if(adminFlag) {
			url = "WEB-INF/jsp/admin.jsp";
		} else {
			response.sendRedirect("WEB-INF/jsp/home.jsp");
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
