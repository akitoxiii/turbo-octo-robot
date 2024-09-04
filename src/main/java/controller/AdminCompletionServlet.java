package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserLogicDao;

@WebServlet("/AdminCompletionServlet")
public class AdminCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		String loginUserName = (String) session.getAttribute("userName");

		// 前の画面から送られてきたユーザー情報を取得
		String email = (String) request.getParameter("userMailAddress");
		String password = (String) request.getParameter("password");
		String name = (String) request.getParameter("userName");
		String address = (String) request.getParameter("userAddress");
		String phone = (String) request.getParameter("userPhoneNumber");
		String privilege = (String) request.getParameter("privilege");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// UserLogicDaoを使ってデータベース接続を取得
			UserLogicDao dao = new UserLogicDao();
			con = dao.getConnection();

			String sql = "INSERT INTO USER_TABLE (USER_ID, USER_EMAIL, USER_PASSWORD, USER_NAME, USER_ADDRESS, USER_PHONE, USER_PRIVILEGE) VALUES (?, ?, ?, ?, ?, ?, ?)";

			// USER_IDを生成する
			String UserID = generateUserId(con);
			System.out.println("生成されたユーザーID: " + UserID); // デバッグ用

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, UserID);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.setString(4, name);
			pstmt.setString(5, address);
			pstmt.setString(6, phone);
			pstmt.setInt(7, Integer.parseInt(privilege)); // 権限: 0 = 顧客, 1 = 管理者

			int result = pstmt.executeUpdate();
			if (result > 0) {
				// 登録成功、ユーザーIDをリクエストに設定して完了画面へ
				request.setAttribute("NewUserId", UserID);

				// セッションから取得したログインユーザー情報をリクエストスコープにセット
				request.setAttribute("userId", loginUserId);
				request.setAttribute("userName", loginUserName);

				request.getRequestDispatcher("AdminCompletion.jsp").forward(request, response);
			} else {
				// 登録失敗、エラーメッセージを設定して再度登録画面へ
				request.setAttribute("registerError", "ユーザー登録に失敗しました。再度お試しください。");
				request.getRequestDispatcher("AdminRegister.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("registerError", "システムエラーが発生しました。管理者にお問い合わせください。");
			request.getRequestDispatcher("AdminRegister.jsp").forward(request, response);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// USER_IDを生成するメソッド
	private String generateUserId(Connection con) throws Exception {
		String userId = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT 'USER' || LPAD(SEQ_USER_ID.NEXTVAL, 4, '0') FROM DUAL";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userId = rs.getString(1);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return userId;
	}
}