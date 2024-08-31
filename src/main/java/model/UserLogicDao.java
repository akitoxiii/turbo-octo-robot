package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLogicDao {

	// データベース接続情報
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // JDBC URLを適宜設定
	private static final String USER = "COFFEE"; // スキーマ名
	private static final String PASSWORD = "COFFEE_TREE"; // スキーマのパスワード

	// データベース接続を取得するメソッド
	private Connection getConnection() throws Exception {
		// Oracle JDBCドライバのロード
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// データベース接続の取得
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	// ユーザー認証メソッド
	public UserIdBean authenticateUser(String loginId, String loginPassword) {
		UserIdBean user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// データベース接続
			con = getConnection();

			// SQLクエリの準備 - データベースのカラム名に合わせる
			String sql = "SELECT * FROM USER_TABLE WHERE USER_ID = ? AND USER_PASSWORD = ?";
			System.out.println("実行されるSQLクエリ: " + sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId); // loginId は USER_ID に対応
			pstmt.setString(2, loginPassword); // loginPassword は USER_PASSWORD に対応

			// クエリの実行
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("データベースのユーザーID: " + rs.getString("USER_ID"));
				System.out.println("データベースのパスワード: " + rs.getString("USER_PASSWORD"));

				// ユーザー情報を取得し、UserIdBeanに設定
				user = new UserIdBean();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserEmail(rs.getString("USER_EMAIL"));
				user.setUserPassword(rs.getString("USER_PASSWORD"));
				user.setUserAdress(rs.getString("USER_ADDRESS"));
				user.setUserPhone(rs.getString("USER_PHONE"));
				user.setUserPrivilege(rs.getInt("USER_PRIVILEGE"));
			} else {
				System.out.println("ユーザーが見つかりませんでした: " + loginId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データベースのエラーが発生しました。");
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

		return user;
	}
}