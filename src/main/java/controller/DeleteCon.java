package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StoreDao;

@WebServlet("/DeleteCon")
public class DeleteCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteCon() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemNo = Integer.parseInt(request.getParameter("itemNo"));
        StoreDao dao = new StoreDao();
        int num = dao.deleteItemDao(itemNo);

        request.setAttribute("num", num);

        ServletContext app = this.getServletContext();
        RequestDispatcher dispatcher = app.getRequestDispatcher("/List.jsp");
        dispatcher.forward(request, response);
    }
}