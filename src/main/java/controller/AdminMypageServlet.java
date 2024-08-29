package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userId") == null || (int) session.getAttribute("privilege") != 1) {
			// セッションが無効、または管理者ではない場合、ログインページにリダイレクト
			response.sendRedirect("login.jsp");
			return;
		}

		// 管理者が存在する場合、管理者マイページにフォワード
		request.getRequestDispatcher("AdminMypage.jsp").forward(request, response);
	}
}