package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			request.getRequestDispatcher("UserRegister.jsp").forward(request, response);
			return;
		}

		// フォームの入力内容を次の画面に渡す
		request.setAttribute("userMailAddress", email);
		request.setAttribute("password", password);
		request.setAttribute("userName", name);
		request.setAttribute("userAddress", address);
		request.setAttribute("userPhoneNumber", phone);

		// 確認画面にフォワード
		request.getRequestDispatcher("UserConfirmation.jsp").forward(request, response);
	}
}