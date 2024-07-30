package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.StoreDao;
import util.FileUpload;

@WebServlet("/CsvCon")
public class CsvCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CsvCon() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// WebContent/files フォルダのパスを取得
		String path = getServletContext().getRealPath("files");

		// FileUploadクラスのFileUpメソッドを呼び出して、ItemBeanを取得
		FileUpload fileUpload = new FileUpload();
		ItemBean itemBean;
		try {
			itemBean = fileUpload.FileUp(request, path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "ファイルのアップロードに失敗しました。");
			ServletContext app = this.getServletContext();
			RequestDispatcher dispatcher = app.getRequestDispatcher("/Register.jsp");
			dispatcher.forward(request, response);
			return;
		}

		String filePath = itemBean.getCsvFilePath();

		if (filePath == null) {
			// ファイル形式が無効だった場合の処理
			request.setAttribute("result", "無効なファイル形式です。");
			ServletContext app = this.getServletContext();
			RequestDispatcher dispatcher = app.getRequestDispatcher("/Register.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// CSVファイルを読み込んでItemBeanのリストを作成
		List<ItemBean> items = readCsv(filePath);

		StoreDao dao = new StoreDao();
		int registeredCount = 0;
		int duplicateCount = 0;
		for (ItemBean item : items) {
			int count;
			try {
				count = dao.registerCheckDao(item);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result", "データベースのチェックに失敗しました。");
				ServletContext app = this.getServletContext();
				RequestDispatcher dispatcher = app.getRequestDispatcher("/Register.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if (count == 0) {
				try {
					dao.registerItem(item);
					registeredCount++;
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("result", "データベースの登録に失敗しました。");
					ServletContext app = this.getServletContext();
					RequestDispatcher dispatcher = app.getRequestDispatcher("/Register.jsp");
					dispatcher.forward(request, response);
					return;
				}
			} else {
				duplicateCount++;
			}
		}

		request.setAttribute("registeredCount", registeredCount);
		request.setAttribute("duplicateCount", duplicateCount);

		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/Register.jsp");
		dispatcher.forward(request, response);
	}

	private List<ItemBean> readCsv(String filePath) {
		List<ItemBean> items = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length == 5) {
					ItemBean item = new ItemBean();
					item.setItemName(values[0]);
					item.setItemPrice(Integer.parseInt(values[1]));
					item.setStockCount(Integer.parseInt(values[2]));
					item.setSpecialItem(Integer.parseInt(values[3]));
					item.setUpdateTime(new Timestamp(System.currentTimeMillis()));
					items.add(item);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}
}