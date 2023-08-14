package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginCheck;
import model.LoginInputCheck;
import model.UserLoginDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** Session Scopeにログイン情報があるか確認  */
		HttpSession session = request.getSession();
		Integer user_id = (Integer)session.getAttribute("user_id");
		
		/** forward先を格納するためのローカル変数 */
		String url;
		
		/** Session Scopeのログイン情報の有無でforward先を決定 */
		if(user_id != null) {
			/** ログイン情報がある場合はHome画面へ */
			url = "WEB-INF/jsp/home.jsp";
		} else {
			/** ログイン情報がない場合はlogin画面へ */
			url = "WEB-INF/jsp/login.jsp";
		}
		
		/** forward先情報を元にforward */
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/** login.jspからPOSTされたuser_id、passwordを取得 */	
		String input_user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String lineCd = System.getProperty("line.separator");
		String url;
		
		/*
		 * 入力値(user_id、password)のチェックをLoginInputCheckを用いて行う
		 */
		LoginInputCheck loginInputCheck = new LoginInputCheck();
		String inputErrorMsg;
		boolean bool_user_id = loginInputCheck.inputUserIDCheck(input_user_id);
		boolean input_password = loginInputCheck.inputPasswordCheck(password);
		
		/*
		 * 入力チェックでuser_id、passwordどちらも正しかった場合にLoginCheckを行う
		 */
		if(bool_user_id && input_password) {
			Integer user_id = Integer.parseInt(input_user_id);
			LoginCheck loginCheck = new LoginCheck();
			UserLoginDTO userLoginDTO = loginCheck.loginCheck(user_id, password);
			if(userLoginDTO.getLogin_result()) {
				/*
				 * LoginCheckによってUserLoginDTOがTRUEと判断された場合、forward先をHome画面にし、
				 * session scopeにuser_idを格納する
				 */ 
				url = "WEB-INF/jsp/home.jsp";
				HttpSession session = request.getSession();
				session.setAttribute("user_id", user_id);
			} else {
				/*
				 * UserLoginDTOがFALSEと判断された場合、forward先をlogin画面にし
				 * inputErrorMsgにエラー内容を追記し、inputErrorMsgをrequest scopeへ格納する
				 */
				url = "WEB-INF/jsp/login.jsp";
				if(userLoginDTO.getInvalid_count() > 5) {
					inputErrorMsg = "アカウントがロックされました。管理者に問い合わせてください";
				} else {
					inputErrorMsg = "パスワードが間違っております";
				}
				request.setAttribute("inputErrorMsg", inputErrorMsg);
			}
		} else {
			/*
			 * 入力値チェックでuser_id、passwordのどちらかが間違っていた場合、forward先をlogin画面にし
			 * inputErrorMsgにエラー内容を追記し、inputErrorMsgをrequest scopeへ格納する
			 */
			url = "WEB-INF/jsp/login.jsp";
			inputErrorMsg = "ログインIDが数字10桁か確認してください。" + lineCd
						+ "パスワードがローマ字大文字小文字、数字、記号(-_)のうち３種類含み、8文字以上か確認してください";
			request.setAttribute("inputErrorMsg", inputErrorMsg);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}
}
