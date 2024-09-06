package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		String loginUserName = (String) session.getAttribute("userName");

		// フォームからの入力を取得
		String email = request.getParameter("userMailAddress");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("NewUserName");
		String address = request.getParameter("userAddress");
		String phone = request.getParameter("userPhoneNumber");
		String privilege = request.getParameter("privilege");

		// パスワード確認
		if (!password.equals(confirmPassword)) {
			request.setAttribute("userError", "パスワードが一致しません。");
			request.getRequestDispatcher("AdminRegister.jsp").forward(request, response);
			return;
		}

		// セッションから取得したログインユーザー情報をリクエストスコープにセット
		request.setAttribute("userId", loginUserId);
		request.setAttribute("userName", loginUserName);

		// フォームの入力内容を次の画面に渡す
		request.setAttribute("userMailAddress", email);
		request.setAttribute("password", password);
		request.setAttribute("NewUserName", name);
		request.setAttribute("userAddress", address);
		request.setAttribute("userPhoneNumber", phone);
		request.setAttribute("privilege", privilege);

		// 確認画面にフォワード
		request.getRequestDispatcher("AdminConfirmation.jsp").forward(request, response);
	}
}