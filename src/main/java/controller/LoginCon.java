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

		// デバッグ情報を出力
		System.out.println("Login ID: " + loginId);
		System.out.println("Login Password: " + loginPassword);

		try {
			// ユーザーロジックのDAOを使用して認証
			UserLogicDao userDao = new UserLogicDao();
			UserIdBean user = userDao.authenticateUser(loginId, loginPassword);

			if (user != null) {
				// ユーザーが存在する場合、セッションにユーザー情報を保存
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userPrivilege", user.getUserPrivilege());

				// 管理者か顧客かで分岐
				if (user.getUserPrivilege() == 1) {
					// 管理者の場合
					response.sendRedirect("AdminMypage.jsp");
				} else {
					// 顧客の場合
					response.sendRedirect("UserMypage.jsp");
				}
				return; // sendRedirectを使用した後に処理を終了するため、ここでreturnを追加
			} else {
				// 認証に失敗した場合
				request.setAttribute("loginError", "ログインIDまたはパスワードが正しくありません。");
				System.out.println("Authentication failed for Login ID: " + loginId);
			}
		} catch (Exception e) {
			// 例外が発生した場合
			e.printStackTrace();
			request.setAttribute("loginError", "システムエラーが発生しました。管理者にお問い合わせください。");
			System.out.println("Exception occurred: " + e.getMessage());
		}

		// エラーメッセージがある場合、ログイン画面に戻る
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}