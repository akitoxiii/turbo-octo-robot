package controller;

import java.io.IOException;
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

@WebServlet("/UpdateCon")
public class UpdateCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateCon() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		StoreDao dao = new StoreDao();
		ItemBean item = dao.selectItemDao(itemNo);
		request.setAttribute("item", item);
		request.setAttribute("lastUpdateTime", item.getUpdateTime());

		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/Update.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			FileUpload fileUpload = new FileUpload();
			ItemBean updateBean = fileUpload.FileUp(request,
					"C:\\Eclipse\\pleiades\\workspace\\Akito_Store\\WebContent\\files");

			if (updateBean.getUpdateTime() == null) {
				throw new IllegalArgumentException("Datetime parameter is missing or empty");
			}

			StoreDao dao = new StoreDao();
			int count = dao.registerCheckDao(updateBean);
			if (count == 0) {
				// もう一度DBから商品情報を取ってきて現在の値と比較する
				ItemBean afterItemBean = dao.selectItemDao(updateBean.getItemNo());
				if (updateBean.getUpdateTime().equals(afterItemBean.getUpdateTime())) {
					dao.updateDao(updateBean, updateBean.getUpdateTime());
					request.setAttribute("message", "更新が完了しました。");
				} else {
					request.setAttribute("error", "他のユーザによりデータが更新されました。再度更新してください。");
				}
			} else {
				request.setAttribute("error", "商品名が重複しています。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "更新中にエラーが発生しました。");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/ListCon");
		dispatcher.forward(request, response);
	}
}