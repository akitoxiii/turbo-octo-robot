package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReservationDao {
	// メンバ変数の用意
	Connection conn = null;
	PreparedStatement stmt = null;







	/**
	 * 
	 * >>予約件数検索メソッド
	 * その日時に予約件数が何件あるかをセレクトするメソッド(予約可能かどうかの判定に使用)
	 * ーーーーsql文と引数がまだ不明ーーーー
	 * 
	 */

	public int saerchTime(int rday, int rtime) {

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;
		// return用変数
		int num = 0;

		try {
			// OracleJDBC用ドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBに接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");

			String sql = "";

			// SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);
			// パラメーターセット
			stmt.setString(1, regiBean.getItemName());
			// SQL実行
			rs = stmt.executeQuery();


			// 結果取得
			rs.next();
			num = rs.getInt(1);

		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				// ステートメントを実行していたらクローズ
				if(stmt != null) {
					stmt.close();
				}
				// データベース接続していたらクローズ
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}


}
