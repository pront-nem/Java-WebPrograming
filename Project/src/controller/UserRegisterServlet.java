package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;
/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");
        
		
		//入力した内容を取得
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birth_date");
		
		System.out.println(loginId);
		System.out.println(password);
		System.out.println(password1);
		
		//入力項目を引数として渡す
		UserDao userDao1 = new UserDao();
		User user =userDao1.findByloginId(loginId);
		
		
		//未入力のものがある場合登録をせずエラーメッセージを表示させる。
		if( loginId.equals("") || password.equals("") || name.equals("") || birthDate.equals("") ) {
			request.setAttribute("errmsg", "入力された内容が正しくありません。");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
			return;
			}
		
		//パスワードが一致しない場合エラーを返す。
		if(!password.equals(password1)) {
			request.setAttribute("errpassword", "パスワードが一致しません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
			return;
			}
		
		//ログインIDが重複してる場合エラーを返す。
		if(null != userDao1.findByloginId(loginId)) {
			request.setAttribute("errId", "IDが既に登録されています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request,response);
			return;
		}

		//Daoに渡す
		UserDao userDao = new UserDao();
		userDao.UserRegister(loginId,password,name,birthDate);
		
		
		// ユーザ一覧のサーブレットにリダイレクト	
		response.sendRedirect("UserListServlet");
			
	}
}
