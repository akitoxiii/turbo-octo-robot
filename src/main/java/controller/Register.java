package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/Register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// WebContent/files フォルダのパスを取得
		String path = getServletContext().getRealPath("files");

		// FileUploadクラスのFileUpメソッドを呼び出して、ItemBeanを取得
		FileUpload fileUpload = new FileUpload();
		ItemBean regiBean;
		try {
			regiBean = fileUpload.FileUp(request, path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "ファイルアップロードに失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Register.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 現在のタイムスタンプを設定
		Timestamp currentTime = new Timestamp(new Date().getTime());
		regiBean.setUpdateTime(currentTime);

		StoreDao dao = new StoreDao();
		int duplicateCount = 0;
		try {
			duplicateCount = dao.registerCheckDao(regiBean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "データベースチェックに失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Register.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if (duplicateCount > 0) {
			request.setAttribute("result", "既に同じ商品名が存在します");
		} else {
			int num = dao.registerItem(regiBean);
			if (num > 0) {
				request.setAttribute("result", "商品登録に成功しました。");
				request.setAttribute("itemNo", regiBean.getItemNo()); // itemNoをリクエストに設定
			} else {
				request.setAttribute("result", "商品登録に失敗しました。");
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/Register.jsp");
		dispatcher.forward(request, response);
	}
}