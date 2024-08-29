package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.UserIdBean;
import utility.DatabaseConnection;

public class UserLogicDao {

    // ユーザー認証メソッド
    public UserIdBean authenticateUser(String loginId, String loginPassword) {
        UserIdBean user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // データベース接続
            con = DatabaseConnection.getConnection();

            // SQLクエリの準備 - データベースのカラム名に合わせる
            String sql = "SELECT * FROM USER_TABLE WHERE USER_ID = ? AND USER_PASSWORD = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);  // loginId は USER_ID に対応
            pstmt.setString(2, loginPassword);  // loginPassword は USER_PASSWORD に対応

            // クエリの実行
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // ユーザー情報を取得し、UserIdBeanに設定
                user = new UserIdBean();
                user.setUserId(rs.getInt("USER_ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setUserEmail(rs.getString("USER_EMAIL"));
                user.setUserPassword(rs.getString("USER_PASSWORD"));
                user.setUserAdress(rs.getString("USER_ADDRESS"));
                user.setUserPhone(rs.getString("USER_PHONE"));
                user.setUserPrivilege(rs.getInt("USER_PRIVILEGE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}