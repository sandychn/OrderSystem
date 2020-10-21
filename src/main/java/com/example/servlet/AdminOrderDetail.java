package com.example.servlet;

import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.OrderDao;
import com.example.dao.OrderDetailDao;
import com.example.dao.daoImpl.FoodDaoImpl;
import com.example.dao.daoImpl.OrderDaoImpl;
import com.example.dao.daoImpl.OrderDetailDaoImpl;
import com.example.pojo.Food;
import com.example.pojo.Order;
import com.example.pojo.OrderDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/AdminOrderDetail")
public class AdminOrderDetail extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("order_id");

        try {
            List<OrderDetail> orderDetails = orderDetailDao.getOrderDetails(orderId);
            StringBuilder orderDetailTableHtml = new StringBuilder();
            double total = 0;
            orderDetailTableHtml.append("<table class=\"table\">\n" +
                    "<thead class=\"thead-light\">\n" +
                    "<th scope=\"col\">菜品名</th>\n" +
                    "<th scope=\"col\">单价</th>\n" +
                    "<th scope=\"col\">份数</th>\n" +
                    "<th scope=\"col\">小计</th>\n" +
                    "</thead>\n" +
                    "<tbody>");
            for (OrderDetail orderDetail : orderDetails) {
                Food food = foodDao.getFood(orderDetail.getFoodID());
                orderDetailTableHtml.append("<tr>");
                orderDetailTableHtml.append(String.format("<td>%s</td>", food.getFoodName()));
                orderDetailTableHtml.append(String.format("<td>%.2f</td>", food.getPrice()));
                orderDetailTableHtml.append(String.format("<td>%d</td>", orderDetail.getFoodCount()));
                orderDetailTableHtml.append(String.format("<td>%.2f</td>", food.getPrice() * orderDetail.getFoodCount()));
                orderDetailTableHtml.append("</tr>");
                total += food.getPrice() * orderDetail.getFoodCount();
            }
            orderDetailTableHtml.append("</tbody></table>");
            orderDetailTableHtml.append(String.format(
                    "<div class=\"h3\" style=\"margin: 70px 0 30px 0\">总计&nbsp;&nbsp;<b>¥</b>&nbsp;" +
                            "<span class=\"order-total-price\">%.2f</span></div>",
                    total));
            orderDetailTableHtml.append(String.format(
                    "<button class=\"btn btn-secondary\" onclick=\"change_order_status('%s',1)\">标记为已完成</button>&nbsp;&nbsp;",
                    orderId));
            orderDetailTableHtml.append(String.format(
                    "<button class=\"btn btn-secondary\" onclick=\"change_order_status('%s',0)\">标记为未完成</button>&nbsp;&nbsp;",
                    orderId));
            orderDetailTableHtml.append("<a href=\"AdminOrderManage\"><button class=\"btn btn-secondary\">返回</button></a>");
            req.setAttribute("orderDetailTableHtml", orderDetailTableHtml);

            Order order = orderDao.getOrder(orderId);
            req.setAttribute("order_id", orderId);
            req.setAttribute("user_id", order.getUserId());
            req.setAttribute("order_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getStartTime() * 1000));
            req.setAttribute("table_number", order.getNumber());
            req.setAttribute("order_status", order.getStatus() == 1 ? "已完成" : "未完成");
        } catch (SystemException e) {
            e.printStackTrace();
            // todo
        }
        req.getRequestDispatcher("adminOrderDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
