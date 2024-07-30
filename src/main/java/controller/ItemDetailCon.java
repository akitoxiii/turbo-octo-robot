package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.ReviewBean;
import model.StoreDao;

@WebServlet("/ItemDetailCon")
public class ItemDetailCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ItemDetailCon() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemNo = Integer.parseInt(request.getParameter("itemNo"));
        StoreDao dao = new StoreDao();
        ItemBean item = dao.selectItemDao(itemNo);
        List<ReviewBean> reviews = dao.getReviewsByItem(itemNo);
        double averageRating = dao.getAverageRatingByItem(itemNo);

        item.setReviews(reviews);
        item.setAverageRating(averageRating);
        
        request.setAttribute("item", item);

        ServletContext app = this.getServletContext();
        RequestDispatcher dispatcher = app.getRequestDispatcher("/Detail.jsp"); // 商品詳細ページのJSPファイルへ
        dispatcher.forward(request, response);
    }
}