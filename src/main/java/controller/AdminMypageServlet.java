package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminMypageServlet")
public class AdminMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String loginUserId = (String) session.getAttribute("userId");
		String loginUserName = (String) session.getAttribute("userName");
		Integer userPrivilege = (Integer) session.getAttribute("userPrivilege");

		if (loginUserId == null || loginUserName == null || userPrivilege == null || userPrivilege != 1) {
			response.sendRedirect("login.jsp");
			return;
		}

		request.setAttribute("userId", loginUserId);
		request.setAttribute("userName", loginUserName);

		if ("register".equals(request.getParameter("action"))) {
			request.getRequestDispatcher("AdminRegister.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("AdminMypage.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}