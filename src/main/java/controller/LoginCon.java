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

		// 古いセッションがある場合は無効化
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 新しいセッションを開始
		session = request.getSession(true);

		try {
			// ユーザーロジックのDAOを使用して認証
			UserLogicDao userDao = new UserLogicDao();
			UserIdBean user = userDao.authenticateUser(loginId, loginPassword);

			if (user != null) {
				// ユーザーが存在する場合、セッションにユーザー情報を保存
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userPrivilege", user.getUserPrivilege());

				// 管理者か顧客かで分岐
				if (user.getUserPrivilege() == 1) {
					response.sendRedirect("AdminMypageServlet"); // AdminMypageServlet にリダイレクト
				} else {
					response.sendRedirect("UserMypageServlet"); // UserMypageServlet にリダイレクト
				}
			} else {
				request.setAttribute("loginError", "ログインIDまたはパスワードが正しくありません。");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("loginError", "システムエラーが発生しました。管理者にお問い合わせください。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}