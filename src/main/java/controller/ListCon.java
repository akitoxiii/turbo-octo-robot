package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ItemBean;
import model.StoreDao;

@WebServlet("/ListCon")
public class ListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListCon() {
		super();
	}

	// GETリクエストを処理するメソッド
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	// POSTリクエストを処理するメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	// GETおよびPOSTリクエストを処理する共通メソッド
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 検索条件のパラメータを取得
		String searchName = request.getParameter("searchName");
		String itemTypeStr = request.getParameter("itemType");

		StoreDao dao = new StoreDao();
		List<ItemBean> itemList;

		// 検索条件がない場合は全商品を取得
		if ((searchName == null || searchName.isEmpty()) && (itemTypeStr == null || itemTypeStr.isEmpty())) {
			itemList = dao.allSearchDao();
		} else {
			// 検索条件がある場合はフィルタリングして商品を取得
			int itemType = -1; // itemTypeフィルタを使用しないことを示すデフォルト値
			if (itemTypeStr != null && !itemTypeStr.isEmpty()) {
				itemType = Integer.parseInt(itemTypeStr);
			}
			itemList = dao.selectSearchDao(searchName, itemType);
		}

		// リクエスト属性に商品リストと検索条件をセット
		request.setAttribute("itemList", itemList);
		request.setAttribute("searchName", searchName);
		request.setAttribute("itemType", itemTypeStr);

		// JSPにフォワード
		request.getRequestDispatcher("List.jsp").forward(request, response);
	}
}