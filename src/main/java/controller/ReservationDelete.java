package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReservationDao;

/**
 * Servlet implementation class ReservationDelete
 */
public class ReservationDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// ========================================
		HttpSession session = request.getSession();
		// ========================================
		
		int pri = session.getAttribute("");
		
		
		request.setCharacterEncoding("UTF-8");
		
		// フォームから値を取得し、intに変換する
		String ReservationId = request.getParameter("ReservationId");
		
		// デリート
		ReservationDao dao = new ReservationDao();
		int count = dao.deleteDao(ReservationId);
		
		// リストを作ってList.jspに送る
		// ArrayList<ReservationBean> allList = new ArrayList<>();
		
		// allList = dao.allSeachDao();
		
		// スコープへ保存
		request.setAttribute("count",count);
		// request.setAttribute("allList",allList);
		
		// フォワード
		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/List.jsp");
		dispatcher.forward(request,response);
		
		
		
		
		
		
		
		
		
		
		
	}

}
