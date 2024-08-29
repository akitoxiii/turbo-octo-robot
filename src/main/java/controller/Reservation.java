package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReservationBean;
import model.ReservationDao;

/**
 * Servlet implementation class Reservation
 */
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// 文字コードのセットとインスタンス化
		request.setCharacterEncoding("UTF-8");
		// ========================================
		HttpSession session = request.getSession();
		// ========================================

		// セッションから予約情報の入ったBeanを取り出す
		ReservationBean reseBean = (ReservationBean) session.getAttribute("reseBean");

		// // Date date = new Date(reseBean.getReservationDate()); // //


		// 三回目の遷移で予約IDを取得し、一回目と二回目にsessionにしまった予約Beanを取り出し、入れる。
		// また、DBへの登録をする

		// action=OKが送られていれば、登録
		String action =request.getParameter("action");

		if(action.equals("ok")) {

			// 予約件数を取得するメソッドを使って、その日時の予約が その日のその時間ってどうやって渡す？どう探す？
			int num = 0;

			//=========





			// 登録後、セッションスコープの内容（reseBean）は削除？





			// 送られてこなければセッションスコープの内容（reseBean）を削除
		}else {

		}

	}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// 文字コードのセットとインスタンス化
		request.setCharacterEncoding("UTF-8");
		// ========================================
		HttpSession session = request.getSession();
		// ========================================
		ReservationBean reseBean = new ReservationBean();
		String screen= "";

		// 時間選択画面から送られる時間情報を入れる
		int reservationTime= (int) request.getAttribute("time");

		
		// ①時間情報が空なら一回目の遷移なので、日付をBeanに入れて保存する
		if(reservationTime ==0) {
			Timestamp reservationDate = (Timestamp)request.getAttribute("dayId");

			reseBean.setReservationDate(reservationDate);
			session.setAttribute("reseBean", "reseBean");

			// 遷移先画面の設定
			screen= "TimeReservation.jsp";

			// ②時間情報が送られている場合は、それも保存する（二回目の遷移）
			// 会員IDも送られていているから、それも保存する
		}else {
			// まずスコープにある予約Beanを取る
			reseBean = (ReservationBean)session.getAttribute("reseBean");
			// そこに予約時間を追加する
			reseBean.setReservationTime(reservationTime);
			
			
			
			
			
			// 予約ID
			Timestamp day = reseBean.getReservationDate();
			ReservationDao dao = new ReservationDao();
			
			String rId = dao.generatingId(day, reservationTime);
			
			
			
			
			// 予約IDもしまっておく
			reseBean.setReservationId(rId);
			
			// もう一度sessionにしまう
			session.setAttribute("reseBean", "reseBean");
			
			
		




			// リクエストから会員IDの取得
			String userId = (String) request.getAttribute("userId");
			// 会員IDが飛んできていなければ、ログイン情報を使用する
			if(userId == null) {
				session.getAttribute("userId");
			}else {

			}


		}




	}

}
