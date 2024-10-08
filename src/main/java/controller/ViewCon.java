package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.StoreDao;

@WebServlet("/ViewCon")
public class ViewCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewCon() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		StoreDao dao = new StoreDao();
		ItemBean item = dao.selectItemDao(itemNo);
		request.setAttribute("item", item);

		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/View.jsp");
		dispatcher.forward(request, response);
	}
}