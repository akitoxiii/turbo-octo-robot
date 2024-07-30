package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StoreDao {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "AKITO";
	private static final String PASSWORD = "AKITO_KURODA";

	static {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	// 商品を登録するメソッド
	public int registerItem(ItemBean regiBean) {
		int num = 0;
		Connection conn = null;
		PreparedStatement itemStmt = null;
		PreparedStatement stockStmt = null;
		ResultSet generatedKeys = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);

			// 商品名の重複チェック
			String checkSql = "SELECT COUNT(*) FROM ITEM_TABLE WHERE ITEM_NAME = ?";
			try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
				checkStmt.setString(1, regiBean.getItemName());
				ResultSet rs = checkStmt.executeQuery();

				if (rs.next() && rs.getInt(1) > 0) {
					throw new SQLException("Item name already exists.");
				}
			}

			// ITEM_TABLEへの挿入
			String itemSql = "INSERT INTO ITEM_TABLE (ITEM_NO, ITEM_NAME, ITEM_PRICE, ITEM_IMAGE_PATH, SPECIAL_ITEM, UPDATE_TIME) VALUES (ITEM_SEQUENCE.nextval, ?, ?, ?, ?, ?)";
			itemStmt = conn.prepareStatement(itemSql, new String[] { "ITEM_NO" });
			itemStmt.setString(1, regiBean.getItemName());
			itemStmt.setInt(2, regiBean.getItemPrice());
			itemStmt.setString(3, regiBean.getItemImagePath());
			itemStmt.setInt(4, regiBean.getSpecialItem());
			itemStmt.setTimestamp(5, regiBean.getUpdateTime());
			num = itemStmt.executeUpdate();

			// 生成されたITEM_NOを取得
			generatedKeys = itemStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				regiBean.setItemNo(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Failed to retrieve generated ITEM_NO.");
			}

			// STOCK_TABLEへの挿入
			String stockSql = "INSERT INTO STOCK_TABLE (ID, ITEM_NO, COUNT) VALUES (STOCK_SEQUENCE.nextval, ?, ?)";
			stockStmt = conn.prepareStatement(stockSql);
			stockStmt.setInt(1, regiBean.getItemNo());
			stockStmt.setInt(2, regiBean.getStockCount());
			num += stockStmt.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (generatedKeys != null)
					generatedKeys.close();
				if (itemStmt != null)
					itemStmt.close();
				if (stockStmt != null)
					stockStmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}

	// 重複チェック用メソッド
	public int registerCheckDao(ItemBean regiBean) throws Exception {
		int count = 0;

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stat = conn
						.prepareStatement("SELECT COUNT(*) FROM ITEM_TABLE WHERE ITEM_NAME = ? AND ITEM_NO != ?")) {
			stat.setString(1, regiBean.getItemName());
			stat.setInt(2, regiBean.getItemNo());
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return count;
	}

	// 全商品を検索するメソッド
	public ArrayList<ItemBean> allSearchDao() {
		ArrayList<ItemBean> itemList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stat = conn.prepareStatement(
						"SELECT i.ITEM_NO, i.ITEM_NAME, i.ITEM_PRICE, i.ITEM_IMAGE_PATH, i.SPECIAL_ITEM, s.COUNT AS STOCK_COUNT, i.UPDATE_TIME FROM ITEM_TABLE i JOIN STOCK_TABLE s ON i.ITEM_NO = s.ITEM_NO");
				ResultSet rs = stat.executeQuery()) {
			while (rs.next()) {
				ItemBean item = new ItemBean();
				item.setItemNo(rs.getInt("ITEM_NO"));
				item.setItemName(rs.getString("ITEM_NAME"));
				item.setItemPrice(rs.getInt("ITEM_PRICE"));
				item.setItemImagePath(rs.getString("ITEM_IMAGE_PATH"));
				item.setSpecialItem(rs.getInt("SPECIAL_ITEM"));
				item.setStockCount(rs.getInt("STOCK_COUNT"));
				item.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemList;
	}

	// 商品を検索するメソッド
	public List<ItemBean> selectSearchDao(String itemName, int specialItem) {
		List<ItemBean> itemList = new ArrayList<>();
		String sql = "SELECT i.ITEM_NO AS ITEM_NO, i.ITEM_NAME AS ITEM_NAME, i.ITEM_PRICE AS ITEM_PRICE, " +
				"i.ITEM_IMAGE_PATH AS ITEM_IMAGE_PATH, i.SPECIAL_ITEM AS SPECIAL_ITEM, " +
				"s.COUNT AS STOCK_COUNT " +
				"FROM ITEM_TABLE i " +
				"JOIN STOCK_TABLE s ON i.ITEM_NO = s.ITEM_NO " +
				"WHERE i.ITEM_NAME LIKE ? ";
		if (specialItem != -1) {
			sql += "AND i.SPECIAL_ITEM = ?";
		}

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + itemName + "%");
			if (specialItem != -1) {
				pstmt.setInt(2, specialItem);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ItemBean item = new ItemBean();
					item.setItemNo(rs.getInt("ITEM_NO"));
					item.setItemName(rs.getString("ITEM_NAME"));
					item.setItemPrice(rs.getInt("ITEM_PRICE"));
					item.setItemImagePath(rs.getString("ITEM_IMAGE_PATH"));
					item.setSpecialItem(rs.getInt("SPECIAL_ITEM"));
					item.setStockCount(rs.getInt("STOCK_COUNT"));
					itemList.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}

	// 商品を取得するメソッド
	public ItemBean selectItemDao(int itemNo) {
		ItemBean item = null;

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stat = conn.prepareStatement(
						"SELECT ITEM_TABLE.ITEM_NO AS ITEM_NO, ITEM_TABLE.ITEM_NAME AS ITEM_NAME, ITEM_TABLE.ITEM_PRICE AS ITEM_PRICE, ITEM_TABLE.ITEM_IMAGE_PATH AS ITEM_IMAGE_PATH, ITEM_TABLE.SPECIAL_ITEM AS SPECIAL_ITEM, STOCK_TABLE.COUNT AS STOCK_COUNT, ITEM_TABLE.UPDATE_TIME AS UPDATE_TIME "
								+
								"FROM ITEM_TABLE " +
								"JOIN STOCK_TABLE ON ITEM_TABLE.ITEM_NO = STOCK_TABLE.ITEM_NO " +
								"WHERE ITEM_TABLE.ITEM_NO = ?")) {
			stat.setInt(1, itemNo);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					item = new ItemBean();
					item.setItemNo(rs.getInt("ITEM_NO"));
					item.setItemName(rs.getString("ITEM_NAME"));
					item.setItemPrice(rs.getInt("ITEM_PRICE"));
					item.setItemImagePath(rs.getString("ITEM_IMAGE_PATH"));
					item.setSpecialItem(rs.getInt("SPECIAL_ITEM"));
					item.setStockCount(rs.getInt("STOCK_COUNT"));
					item.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;
	}

	// 商品を更新するメソッド
	public boolean updateDao(ItemBean updateBean, Timestamp before) throws Exception {
		int num = 0;
		boolean isUpdated = false;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);

			String selectSql = "SELECT UPDATE_TIME FROM ITEM_TABLE WHERE ITEM_NO = ?";
			stat = conn.prepareStatement(selectSql);
			stat.setInt(1, updateBean.getItemNo());
			rs = stat.executeQuery();

			if (rs.next()) {
				Timestamp currentUpdateTime = rs.getTimestamp("UPDATE_TIME");

				if (!before.equals(currentUpdateTime)) {
					throw new SQLException("他のユーザによりデータが更新されました。再度更新してください。");
				}
			} else {
				throw new SQLException("指定された商品番号が見つかりません。");
			}

			rs.close();
			stat.close();

			String sql = "UPDATE ITEM_TABLE SET ITEM_NAME = ?, ITEM_PRICE = ?, ITEM_IMAGE_PATH = ?, SPECIAL_ITEM = ?, UPDATE_TIME = ? WHERE ITEM_NO = ?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, updateBean.getItemName());
			stat.setInt(2, updateBean.getItemPrice());
			stat.setString(3, updateBean.getItemImagePath());
			stat.setInt(4, updateBean.getSpecialItem());
			stat.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stat.setInt(6, updateBean.getItemNo());
			num = stat.executeUpdate();
			stat.close();

			String stockSql = "UPDATE STOCK_TABLE SET COUNT = ? WHERE ITEM_NO = ?";
			try (PreparedStatement stockStmt = conn.prepareStatement(stockSql)) {
				stockStmt.setInt(1, updateBean.getStockCount());
				stockStmt.setInt(2, updateBean.getItemNo());
				num += stockStmt.executeUpdate();
			}

			conn.commit();
			isUpdated = (num > 0);
		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	// 新しいレビューを追加するメソッド
	public void addReview(ReviewBean review) {
		String sql = "INSERT INTO reviews (item_no, user_id, review_text, rating, review_date) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, review.getItemNo());
			ps.setInt(2, review.getUserId());
			ps.setString(3, review.getReviewText());
			ps.setInt(4, review.getRating());
			ps.setTimestamp(5, review.getReviewDate());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 商品の全てのレビューを取得するメソッド
	public List<ReviewBean> getReviewsByItem(int itemNo) {
		List<ReviewBean> reviews = new ArrayList<>();
		String sql = "SELECT review_id, item_no, user_id, review_text, rating, review_date FROM reviews WHERE item_no = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemNo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ReviewBean review = new ReviewBean();
					review.setReviewId(rs.getInt("review_id"));
					review.setItemNo(rs.getInt("item_no"));
					review.setUserId(rs.getInt("user_id"));
					review.setReviewText(rs.getString("review_text"));
					review.setRating(rs.getInt("rating"));
					review.setReviewDate(rs.getTimestamp("review_date"));
					reviews.add(review);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	// 商品の平均評価を取得するメソッド
	public double getAverageRatingByItem(int itemNo) {
		double averageRating = 0;
		String sql = "SELECT AVG(rating) AS average_rating FROM reviews WHERE item_no = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemNo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					averageRating = rs.getDouble("average_rating");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return averageRating;
	}

	// 商品を削除するメソッド
	public int deleteItemDao(int itemNo) {
		int num = 0;
		Connection conn = null;
		PreparedStatement stat = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);

			String sql = "DELETE FROM ITEM_TABLE WHERE ITEM_NO = ?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, itemNo);
			num = stat.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}
}