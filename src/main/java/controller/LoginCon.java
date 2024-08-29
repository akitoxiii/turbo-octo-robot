package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserIdBean;
import model.UserLogicDao;

@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームからの入力を取得
		String loginId = request.getParameter("loginId");
		String loginPassword = request.getParameter("loginPassword");

		// ユーザーロジックのDAOを使用して認証
		UserLogicDao userDao = new UserLogicDao();
		UserIdBean user = userDao.authenticateUser(loginId, loginPassword);

		if (user != null) {
			// ユーザーが存在する場合、セッションにユーザー情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("privilege", user.getUserPrivilege());

			// 管理者か顧客かで分岐
			if (user.getUserPrivilege() == 1) {
				// 管理者の場合
				response.sendRedirect("AdminMypage.jsp");
			} else {
				// 顧客の場合
				response.sendRedirect("UserMypage.jsp");
			}
		} else {
			// 認証に失敗した場合、エラーメッセージを設定してログイン画面に戻る
			request.setAttribute("loginError", "ログインIDまたはパスワードが正しくありません。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}