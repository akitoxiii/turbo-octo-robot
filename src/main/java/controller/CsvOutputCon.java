package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.StoreDao;

@WebServlet("/CsvOutputCon")
public class CsvOutputCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CsvOutputCon() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"item_list.csv\"");

        StoreDao dao = new StoreDao();
        List<ItemBean> itemList = dao.allSearchDao();

        try (PrintWriter out = response.getWriter()) {
            out.println("商品名,値段,在庫数,目玉商品");
            for (ItemBean item : itemList) {
                out.printf("%s,%d,%d,%d%n", item.getItemName(), item.getItemPrice(), item.getStockCount(), item.getSpecialItem());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}