package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerConfirmServlet")
public class UserConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 前の画面から送られてきたユーザー情報を次の画面に渡す
		request.setAttribute("userMailAddress", request.getParameter("userMailAddress"));
		request.setAttribute("password", request.getParameter("password"));
		request.setAttribute("userName", request.getParameter("userName"));
		request.setAttribute("userAddress", request.getParameter("userAddress"));
		request.setAttribute("userPhoneNumber", request.getParameter("userPhoneNumber"));

		// 登録完了画面へフォワード
		request.getRequestDispatcher("UserCompletionServlet").forward(request, response);
	}
}