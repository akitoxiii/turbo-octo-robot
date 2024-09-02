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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		/*
		 *  ================================
		 *  =========三回目の遷移===========
		 *  ================================
		 */

		// 文字コードのセットとインスタンス化
		request.setCharacterEncoding("UTF-8");

		// 登録か修正かで遷移先を分ける
		String screen = "";

		// ========================================
		HttpSession session = request.getSession();
		// ========================================

		// セッションから予約情報の入ったBeanを取り出す
		ReservationBean reseBean = (ReservationBean) session.getAttribute("reseBean");

		// // Date date = new Date(reseBean.getReservationDate()); // //

		// 三回目の遷移で予約IDを取得し、一回目と二回目にsessionにしまった予約Beanを取り出し、入れる。
		// また、DBへの登録をする

		// action=OKが送られていれば、登録
		String action = request.getParameter("action");

		if (action.equals("ok")) {

			// 登録
			ReservationDao dao = new ReservationDao();

			int num = dao.reservation(reseBean);

			if (num == 1) {
				// 登録後、セッションスコープの内容（reseBean）は削除？
				session.removeAttribute("reseBean");
				// numはリクエストに保存
				request.setAttribute("num", num);

				screen = "/ReservationConfirm.jsp";

				// 送られてこなければセッションスコープの内容（reseBean）を削除
			} else {

				screen = "/Reservation.jsp";

			}

			ServletContext app = this.getServletContext();
			RequestDispatcher dispatcher = app.getRequestDispatcher("/ReservationConfirm.jsp");
			dispatcher.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// 文字コードのセットとインスタンス化
		request.setCharacterEncoding("UTF-8");
		// ========================================
		HttpSession session = request.getSession();
		// ========================================
		ReservationBean reseBean = new ReservationBean();
		String screen = "";

		// 時間選択画面から送られる時間情報を入れる
		// int reservationTime= (int) request.getAttribute("time");

		/*
		 *  ================================
		 *  =========一回目の遷移===========
		 *  ================================
		 */
		// ①時間情報が空なら一回目の遷移なので、日付をBeanに入れて保存する
		if (request.getParameter("time") == null) {

			// =================DB変更と月のデータも受けて、Beanに月と合わせて送るように変更する=======================

			String nen = request.getParameter("yearId");
			String tuki = request.getParameter("monthId");
			String niti = request.getParameter("dayId");

			// 確認画面に表示用に整える
			String reservationDate = nen + "年" + tuki + "月" + niti + "日";

			// 分割したものをID作成用にしまっておく
			session.setAttribute("nen", nen);
			session.setAttribute("tuki", tuki);
			session.setAttribute("niti", niti);

			reseBean.setReservationDate(reservationDate);
			session.setAttribute("reseBean", reseBean);

			// 遷移先画面の設定
			screen = "/TimeReservation.jsp";

			/*
			 *  ================================
			 *  =========二回目の遷移===========
			 *  ================================
			 */

			// ②時間情報が送られている場合は、それも保存する（二回目の遷移）(時間は”９”という形式になっている？)
			//  組み合わせて予約IDも生成する
			// 会員IDも送られていているから、それも保存する
		} else {
			// まずスコープにある予約Beanを取る
			reseBean = (ReservationBean) session.getAttribute("reseBean");

			// そこに予約時間を追加する
			String reservationTime = request.getParameter("time");
			reseBean.setReservationTime(reservationTime);

			// 予約ID
			String yoyakuId = (String) session.getAttribute("nen") + (String) session.getAttribute("tuki")
					+ (String) session.getAttribute("niti");

			ReservationDao dao = new ReservationDao();

			String rId = dao.generatingId(yoyakuId, reservationTime);

			// 予約IDもしまっておく
			reseBean.setReservationId(rId);

			// リクエストから会員IDの取得
			String userId = "";
			// 会員IDが飛んできていなければ、ログイン情報を使用する。飛んできていれば、飛んできたものをそのまま保存する
			if (request.getParameter("userId") == null) {
				userId = (String) session.getAttribute("loginUserId");
			} else {
				userId = request.getParameter("userId");
			}

			reseBean.setUserId(userId);
			// もう一度sessionにしまう（この時点で（二回目の遷移）予約ID、会員ID、予約日、予約時間が埋まっている）
			session.setAttribute("reseBean", reseBean);

			screen = "/ReservationConfirmation.jsp";
		}

		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher(screen);
		dispatcher.forward(request, response);

	}

}
