package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class ReservationDao {
	// メンバ変数の用意
	Connection conn = null;
	PreparedStatement stmt = null;







	/**
	 * 
	 * >>予約件数検索メソッド
	 * 
	 * その日時に予約件数が何件あるかをセレクトするメソッド(予約可能かどうかの判定に使用)
	 * ーーーーsql文と引数がまだ不明ーーーー
	 * 
	 */

	public int saerchTime(Timestamp day, int time) {

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;
		// return用変数
		int num = 0;
		
		

		try {
			// OracleJDBC用ドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBに接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");

			// その日のその時間に予約が何件あるか（reservation_dateはtimestamp型、reservation_timrはnumber型）
			String sql = "SELECT COUNT(*) FROM RESERVATION_TABLE WHERE RESERVATION_DATE = ? AND RESERVATION_TIME = ?";

			// SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);
			
			
			// パラメーターセット
			 stmt.setTimestamp(1, day);
			 stmt.setInt(2, time);
			
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

	
	public String generatingId(Timestamp day, int time) {
		
		String rId ="";
		int num=0;
		
		// 以下の予約日時と時間を送ったら予約IDを作成するメソッドをDAOに作ったほうがいいかも
		// 日時と時間から予約IDを生成する
		
		// 予約時間を20240801のような形式で保存
		// ①日にち(これで20240801のようになる？↓)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		String date = sdf.format(day);

		
		// ②時間
		
		
		// 二つをつなげて(どうしてintのままなのにエラーがでないんだろう)、最後日に1か2を付ける
		rId = date + time;
		
		// その時間の予約件数が０なら１、１なら２をつける
		num = saerchTime(day,time);
		
		if(num==0) {
			rId = rId + "1";
		}else if(num ==1) {
			rId = rId + "2";
		}
		
		
		
		return rId;
		
	}
	
	
	
	
	

}
