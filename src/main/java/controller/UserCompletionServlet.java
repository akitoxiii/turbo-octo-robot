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
import model.UserLogicDao;

@WebServlet("/UserCompletionServlet")
public class UserCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 前の画面から送られてきたユーザー情報を取得
		String email = (String) request.getAttribute("userMailAddress");
		String password = (String) request.getAttribute("password");
		String name = (String) request.getAttribute("userName");
		String address = (String) request.getAttribute("userAddress");
		String phone = (String) request.getAttribute("userPhoneNumber");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// UserLogicDaoを使ってデータベース接続を取得
			UserLogicDao dao = new UserLogicDao();
			con = dao.getConnection();

			String sql = "INSERT INTO USER_TABLE (USER_ID, USER_EMAIL, USER_PASSWORD, USER_NAME, USER_ADDRESS, USER_PHONE, USER_PRIVILEGE) VALUES (?, ?, ?, ?, ?, ?, ?)";

			// USER_IDを生成する（例としてシンプルな生成方法を使用）
			String userId = generateUserId(con);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.setString(4, name);
			pstmt.setString(5, address);
			pstmt.setString(6, phone);
			pstmt.setInt(7, 0); // 権限: 0 = 一般ユーザー, 1 = 管理者

			int result = pstmt.executeUpdate();
			if (result > 0) {
				// 登録成功、ユーザーIDをリクエストに設定して完了画面へ
				request.setAttribute("userId", userId);
				request.getRequestDispatcher("UserCompletion.jsp").forward(request, response);
			} else {
				// 登録失敗、エラーメッセージを設定して再度登録画面へ
				request.setAttribute("registerError", "ユーザー登録に失敗しました。再度お試しください。");
				request.getRequestDispatcher("UserRegister.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("registerError", "システムエラーが発生しました。管理者にお問い合わせください。");
			request.getRequestDispatcher("UserRegister.jsp").forward(request, response);
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