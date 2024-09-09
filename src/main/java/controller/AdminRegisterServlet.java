package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserLogicDao;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションを取得
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		String loginUserName = (String) session.getAttribute("userName");

		// フォームからの入力を取得
		String email = request.getParameter("userMailAddress");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String NewUserName = request.getParameter("NewUserName");
		String address = request.getParameter("userAddress");
		String phone = request.getParameter("userPhoneNumber");
		String privilege = request.getParameter("privilege");

		// パスワード確認
		if (!password.equals(confirmPassword)) {
			request.setAttribute("userError", "パスワードが一致しません。");

			// 再度ログインユーザー情報をリクエストスコープにセット
			request.setAttribute("userId", loginUserId);
			request.setAttribute("userName", loginUserName);

			request.setAttribute("userMailAddress", email);
			request.setAttribute("NewUserName", NewUserName);
			request.setAttribute("userAddress", address);
			request.setAttribute("userPhoneNumber", phone);
			request.setAttribute("privilege", privilege);
			request.getRequestDispatcher("AdminRegister.jsp").forward(request, response);
			return;
		}

		// 全てのフィールドが一致するユーザーの存在チェック
		UserLogicDao userDao = new UserLogicDao();
		boolean userExists = userDao.checkUserExists(email, password, NewUserName, address, phone, privilege);

		// 全てのフィールドが一致するユーザーが既に存在する場合はエラーメッセージを表示
		if (userExists) {

			// 再度ログインユーザー情報をリクエストスコープにセット
			request.setAttribute("userId", loginUserId);
			request.setAttribute("userName", loginUserName);

			request.setAttribute("userError", "そのユーザーIDは既に存在します。");
			request.setAttribute("userMailAddress", email);
			request.setAttribute("NewUserName", NewUserName);
			request.setAttribute("userAddress", address);
			request.setAttribute("userPhoneNumber", phone);
			request.setAttribute("privilege", privilege);
			request.getRequestDispatcher("AdminRegister.jsp").forward(request, response);
			return;
		}

		// ログインユーザー情報をリクエストスコープにセット
		request.setAttribute("userId", loginUserId);
		request.setAttribute("userName", loginUserName);

		// 入力内容を次の画面に渡す
		request.setAttribute("userMailAddress", email);
		request.setAttribute("password", password);
		request.setAttribute("NewUserName", NewUserName);
		request.setAttribute("userAddress", address);
		request.setAttribute("userPhoneNumber", phone);
		request.setAttribute("privilege", privilege);

		// 確認画面にフォワード
		request.getRequestDispatcher("AdminConfirmation.jsp").forward(request, response);
	}
}