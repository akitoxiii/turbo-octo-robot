package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserLogicDao;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォームからの入力を取得
		String email = request.getParameter("userMailAddress");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("userName");
		String address = request.getParameter("userAddress");
		String phone = request.getParameter("userPhoneNumber");

		// パスワード確認
		if (!password.equals(confirmPassword)) {
			request.setAttribute("userError", "パスワードが一致しません。");

			// 入力したデータを保持する
			request.setAttribute("userMailAddress", email);
			request.setAttribute("userName", name);
			request.setAttribute("userAddress", address);
			request.setAttribute("userPhoneNumber", phone);

			request.getRequestDispatcher("UserRegister.jsp").forward(request, response);
			return;
		}

		// 全てのフィールドが一致するユーザーの存在チェック
		UserLogicDao userDao = new UserLogicDao();
		boolean userExists = userDao.checkUserExists(email, password, name, address, phone, "");

		// ユーザーが既に存在する場合
		if (userExists) {
			request.setAttribute("userError", "そのユーザーIDは既に存在します。");

			// 入力したデータを保持する
			request.setAttribute("userMailAddress", email);
			request.setAttribute("userName", name);
			request.setAttribute("userAddress", address);
			request.setAttribute("userPhoneNumber", phone);

			request.getRequestDispatcher("UserRegister.jsp").forward(request, response);
			return;
		}

		// 入力内容を次の画面に渡す
		request.setAttribute("userMailAddress", email);
		request.setAttribute("password", password);
		request.setAttribute("userName", name);
		request.setAttribute("userAddress", address);
		request.setAttribute("userPhoneNumber", phone);

		// 確認画面にフォワード
		request.getRequestDispatcher("UserConfirmation.jsp").forward(request, response);
	}
}