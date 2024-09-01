package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserLogicDao;

@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"customer_list.csv\"");

		PrintWriter out = response.getWriter();

		try {
			// データベースからユーザー情報を取得
			UserLogicDao dao = new UserLogicDao();
			Connection con = dao.getConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT USER_ID, USER_NAME, USER_EMAIL, USER_ADDRESS, USER_PHONE, USER_PRIVILEGE FROM USER_TABLE";
			ResultSet rs = stmt.executeQuery(query);

			// CSVのヘッダー行を書き込み
			out.println("USER_ID,USER_NAME,USER_EMAIL,USER_ADDRESS,USER_PHONE,USER_PRIVILEGE");

			// データを書き込み
			while (rs.next()) {
				out.println(rs.getString("USER_ID") + "," +
						rs.getString("USER_NAME") + "," +
						rs.getString("USER_EMAIL") + "," +
						rs.getString("USER_ADDRESS") + "," +
						rs.getString("USER_PHONE") + "," +
						rs.getInt("USER_PRIVILEGE"));
			}

			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("エラーが発生しました。");
		} finally {
			out.close();
		}
	}
}