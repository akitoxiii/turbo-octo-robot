package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReservationDao {
	// メンバ変数の用意
	Connection conn = null;
	PreparedStatement stmt = null;




	/**
	 * 
	 * >>予約件数検索メソッド①（一時間Ver.）
	 * 
	 * その日時に予約件数が何件あるかをセレクトするメソッド(予約可能かどうかの判定に使用)
	 * 
	 */

	public int saerchTime(String day, String time) {

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;
		// return用変数
		int num = 0;
		
		

		try {
			// OracleJDBC用ドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBに接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");

			// その日のその時間に予約が何件あるか（？年？月？日＋？（数字））
			String sql = "SELECT COUNT(*) FROM RESERVATION_TABLE WHERE RESERVATION_DATE = ? AND RESERVATION_TIME = ?";

			// SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);
			
			
			// パラメーターセット
			 stmt.setString(1, day);
			 stmt.setString(2, time);
			
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
	
	
	
	/*
	 * 
	 * 予約件数検索メソッド②（一日Ver.）
	 * その日の予約可能かどうかを判定する（カレンダー画面で使用）
	 * 
	 */
	
	public int SeachDay(String day) {
		
		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;
		// return用変数
		int num = 0;
		
		

		try {
			// OracleJDBC用ドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBに接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");

			// その日のその時間に予約が何件あるか（？年？月？日＋？（数字））
			String sql = "SELECT COUNT(*) FROM RESERVATION_TABLE WHERE RESERVATION_DATE = ?";

			// SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);
			
			
			// パラメーターセット
			 stmt.setString(1, day);
			
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
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 予約ID生成メソッド
	 * 日にちと時間を組み合わせて、String型でIDを作成する
	 * 
	 */
	
	public String generatingId(String nen, String tuki, String niti, String time) { // int timeじゃなくてString timeかも
		
		String rId ="";
		int num=0;
		
		// 以下の予約日時と時間を送ったら予約IDを作成するメソッドをDAOに作ったほうがいいかも
		// 日時と時間から予約IDを生成する
		
		// 予約時間を20240801のような形式で保存
		// 日にち(これで20240801のようになる？↓)
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// String date = sdf.format(day);
		
		// 9/1 送られた時点でもうそうなってる？
		
		
		// 二つをつなげて最後日に1か2を付ける
		String rDB = nen + "年" + tuki + "月" + niti + "日";
				
		rId =nen + tuki + niti + time;	
		
		// その時間の予約件数が０なら１、１なら２をつける
		num = saerchTime(rDB,time);
		
		if(num==0) {
			rId = rId + "1";
		}else if(num ==1) {
			rId = rId + "2";
		}
				
		
		return rId;
		
	}
	
	
	/*
	 * 
	 * 予約登録メソッド
	 * エラーを出す用
	 * 
	 */
	
	public int reservation(ReservationBean rb) {
		// returnする変数宣言
		int num = 0;
		
		try {
			// OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBに接続（URL,USER_ID,PASSWORD)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");

			// 自動コミットを無効にする
			conn.setAutoCommit(false);

			// プレースホルダでSQL作成
			String sql = "INSERT INTO RESERVATION_TABLE (RESERVATION_ID,USER_ID,RESERVATION_DATE,RESERVATION_TIME) VALUES(?,?,?,?)";
			// SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			// パラメーターセット
			stmt.setString(1,rb.getReservationId());
			stmt.setString(2,rb.getUserId());
			stmt.setString(3, rb.getReservationDate());
			stmt.setString(4, rb.getReservationTime());

			// SQLの実行
			num = stmt.executeUpdate();


			// ステートメントをクローズ
			stmt.close();

			// コミット
			conn.commit();

		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}finally {
			try{
				// ステートメント実行していたらステートメントをクローズ
				if(stmt != null) {
					stmt.close();
				}
				// データベース接続していたらデータベースをクローズ
				if(conn != null) {
					// ロールバックしてからクローズ
					conn.rollback();
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		// 試行結果のリターン
		return num;
		
	}
	
	
	
	/*
	 * 
	 * 全件検索メソッド
	 * 
	 */
	
	public ArrayList<ReservationBean> allSeachDao() {

		ResultSet rs = null;

		ArrayList<ReservationBean> allList = new ArrayList<>();

		try {
			// OracleJDBC用ドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBに接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");


			// プレースホルダでDB内の全商品をSELECTするSQL
			String sql ="SELECT * FROM RESERVATION_TABLE JOIN USER_TABLE ON RESERVATION_TABLE.USER_ID = USER_TABLE.USER_ID";

			// プリコンパイル
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();


			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setUserName(rs.getString("USER_NAME"));
				rb.setUserId(rs.getString("USER_ID"));
				rb.setReservationId(rs.getString("RESERVATION_ID"));
				rb.setUserId(rs.getString("USER_ID"));
				rb.setReservationDate(rs.getString("RESERVATION_DATE"));
				rb.setReservationTime(rs.getString("RESERVATION_TIME"));
				allList.add(rb);	
			}

			rs.close();


		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();


		}finally {

		}try {
			// ステートメント実行していたら、ステートメントをクローズ
			if(stmt != null) {
				stmt.close();
			}
			// DB接続していたら、DBをクローズ
			if(conn != null) {
				conn.close();
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return allList;
	}

	
	
	
	
	
	/*
	 * 
	 * ユーザー予約検索
	 * 
	 */
	
	public ReservationBean selectSeachDao(String userId){

		ResultSet rs = null;
		String sql = null;

		ReservationBean rb = new ReservationBean();
		
		try {
			// ドライバーのロード（ドライバーを探す）
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");



					sql="SELECT * FROM RESERVATION_TABLE WHERE USER_ID =?";

					// プリコンパイル
					stmt = conn.prepareStatement(sql);
					// パラメーターセット
					stmt.setString(1, userId);
				

			// 実行
			rs = stmt.executeQuery();


			while(rs.next()) {
				rb.setUserId(rs.getString("USER_ID"));
				rb.setReservationId(rs.getString("RESERVATION_ID"));
				rb.setUserId(rs.getString("USER_ID"));
				rb.setReservationDate(rs.getString("RESERVATION_DATE"));
				rb.setReservationTime(rs.getString("RESERVATION_TIME"));	

			}
			
			sql="SELECT * FROM USER_TABLE WHERE USER_ID =?";

			// プリコンパイル
			stmt = conn.prepareStatement(sql);
			// パラメーターセット
			stmt.setString(1, userId);
			
			rs.next();
			
			rb.setUserName(rs.getString("USER_NAME"));
			
			

			rs.close();



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

		return rb;

	}
	
	
	
	/*
	 * 
	 * 予約IDで一件だけ検索するDao（予約情報詳細画面で使う）
	 * 
	 */
	
	
	
	
	public ReservationBean idSeachDao(String reservationId){

		ResultSet rs = null;
		String sql = null;

		ReservationBean rb = new ReservationBean();
		
		try {
			// ドライバーのロード（ドライバーを探す）
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");



					sql="SELECT * FROM RESERVATION_TABLE FULL JOIN USER_TABLE ON RESERVATION_TABLE.USER_ID = USER_TABLE.USER_ID WHERE RESERVATION_TABLE.RESERVATION_ID =?";

					// プリコンパイル
					stmt = conn.prepareStatement(sql);
					// パラメーターセット
					stmt.setString(1, reservationId);
				

			// 実行
			rs = stmt.executeQuery();


			while(rs.next()) {
				rb.setUserId(rs.getString("USER_ID"));
				rb.setUserName(rs.getString("USER_NAME"));
				rb.setReservationId(rs.getString("RESERVATION_ID"));
				rb.setUserId(rs.getString("USER_ID"));
				rb.setReservationDate(rs.getString("RESERVATION_DATE"));
				rb.setReservationTime(rs.getString("RESERVATION_TIME"));	

			}

			rs.close();



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

		return rb;

	}
	
	
	
	
	
	
	
	
	
	/* 
	 * 削除機能
	 * 
	 */
	public int deleteDao(String ReservationId) {
		int num =0;

		try {
			// ドライバーのロード（ドライバーを探す）
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","RICE","OKOME");

			// 自動コミットを無効にする
			conn.setAutoCommit(false);

			// stock_tableから削除
			String sql ="DELETE FROM RESERVATION_TABLE WHERE RESERVATION_ID =?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ReservationId);			
			num = stmt.executeUpdate();


			// コミット
			conn.commit();



		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}finally {
			try{
				// ステートメント実行していたらステートメントをクローズ
				if(stmt != null) {
					stmt.close();
				}
				// データベース接続していたらデータベースをクローズ
				if(conn != null) {
					// ロールバックしてからクローズ
					conn.rollback();
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		// 試行結果のリターン
		return num;

	}

	
	
	
	


}
