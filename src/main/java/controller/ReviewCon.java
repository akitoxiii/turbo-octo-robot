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

import model.ReviewBean;
import model.StoreDao;

@WebServlet("/ReviewCon")
public class ReviewCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewCon() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String itemNoStr = request.getParameter("itemNo");
        if (itemNoStr == null) {
            response.sendRedirect("ListCon?result=missingParameters");
            return;
        }

        int itemNo = Integer.parseInt(itemNoStr);

        StoreDao dao = new StoreDao();
        List<ReviewBean> reviews = dao.getReviewsByItem(itemNo);

        request.setAttribute("reviews", reviews);
        request.setAttribute("itemNo", itemNo);

        ServletContext app = this.getServletContext();
        RequestDispatcher dispatcher = app.getRequestDispatcher("/Detail.jsp?itemNo=" + itemNo);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String itemNoStr = request.getParameter("itemNo");
        String userIdStr = request.getParameter("userId");
        String reviewText = request.getParameter("reviewText");
        String ratingStr = request.getParameter("rating");

        if (itemNoStr == null || reviewText == null || ratingStr == null) {
            response.sendRedirect("ListCon?result=missingParameters");
            return;
        }

        int itemNo = Integer.parseInt(itemNoStr);
        int userId = Integer.parseInt(userIdStr);
        int rating = Integer.parseInt(ratingStr);

        ReviewBean review = new ReviewBean();
        review.setItemNo(itemNo);
        review.setUserId(userId);
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setReviewDate(new java.sql.Timestamp(System.currentTimeMillis()));

        StoreDao dao = new StoreDao();
        dao.addReview(review);

        response.sendRedirect("Detail.jsp?itemNo=" + itemNo + "&result=reviewAdded");
    }
}