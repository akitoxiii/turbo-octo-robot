package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserMypageServlet")
public class UserMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userId") == null) {
			// セッションが無効の場合、ログインページにリダイレクト
			response.sendRedirect("login.jsp");
			return;
		}

		// Optional: 顧客の場合のみマイページにアクセスできるようにする
		Integer userPrivilege = (Integer) session.getAttribute("userPrivilege");
		if (userPrivilege != null && userPrivilege != 1) { // 1が顧客
			response.sendRedirect("login.jsp");
			return;
		}

		// ユーザーが存在する場合、マイページにフォワード
		request.getRequestDispatcher("UserMypage.jsp").forward(request, response);
	}
}