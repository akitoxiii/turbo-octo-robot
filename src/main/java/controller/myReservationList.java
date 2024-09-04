package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReservationBean;
import model.ReservationDao;

/**
 * Servlet implementation class myReservationList
 */
public class myReservationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myReservationList() {
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
		
		
		
		request.setCharacterEncoding("UTF-8");
		ReservationDao dao = new ReservationDao();
		ReservationBean contentBean = new ReservationBean();
		
		// フォームから値を取得する
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// String UserId = request.getParameter("UserId");

		// 予約検索メソッドを呼び出し、結果をBeanにつめて取得
		
		contentBean =dao.selectSeachDao(userId);

		// 	スコープへ保存
		request.setAttribute("contentBean", contentBean);
	
		// フォワード
		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/ReservationInfo.jsp");
		dispatcher.forward(request,response);
		
		
		
		
		
		
		
		
		
		
	}

}
