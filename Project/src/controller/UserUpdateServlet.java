package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// idを引数にして、idに紐づくユーザ情報を出力する
		
		String id = request.getParameter("id");
				
		System.out.println(id);

		UserDao userDao = new UserDao();
		User user =userDao.findById(id);

		// リクエストスコープにセット
		request.setAttribute("user", user);
		
		// jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");
		
		//入力した内容を取得
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birth_date");
		String targetId = request.getParameter("id");
		
		System.out.println(password);
		System.out.println(password1);
		
		//未入力のものがある場合登録をせずエラーメッセージを表示させる。
		if(name.equals("") || birthDate.equals("") ) {
			request.setAttribute("errmsg", "入力された内容が正しくありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdate.jsp");
			dispatcher.forward(request, response);
			return;
			}
				
		//パスワードが一致しない場合エラーを返す。
		if(!password.equals(password1)) {
			request.setAttribute("errpassword", "パスワードが一致しません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdate.jsp");
			dispatcher.forward(request, response);
			return;
			}
		
		//Daoに渡す
		UserDao userDao = new UserDao();
		userDao.UserUpdate(password,name,birthDate,targetId);
		
		// ユーザ一覧のサーブレットにリダイレクト	
		response.sendRedirect("UserListServlet");
	}
}