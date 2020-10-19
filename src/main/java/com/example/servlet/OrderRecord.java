package com.example.servlet;

import com.example.common.SystemException;
import com.example.dao.OrderDao;
import com.example.dao.daoImpl.OrderDaoImpl;
import com.example.pojo.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/OrderRecord")
public class OrderRecord extends HttpServlet {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String userId = (String)req.getSession().getAttribute("login_user_id");
            List<Order> orders = orderDao.getUserAllOrdersSortedByTime(userId);

            if (orders.isEmpty()) {
                // todo
            } else {
                StringBuilder orderTableHtml = new StringBuilder();
                orderTableHtml.append("<table class=\"table\">\n" +
                        "<thead class=\"thead-light\">\n" +
                        "<th scope=\"col\">订单号</th>\n" +
                        "<th scope=\"col\">时间</th>\n" +
                        "<th scope=\"col\">桌号</th>\n" +
                        "<th scope=\"col\">状态</th>\n" +
                        "<th scope=\"col\">查看</th>\n" +
                        "</thead>\n" +
                        "<tbody>");
                for (Order order : orders) {
                    orderTableHtml.append("<tr>");
                    orderTableHtml.append(String.format("<td>%s</td>", order.getOrderID()));
                    orderTableHtml.append(String.format("<td>%s</td>", sdf.format(order.getStartTime() * 1000)));
                    orderTableHtml.append(String.format("<td>%d</td>", order.getNumber()));
                    orderTableHtml.append(String.format("<td>%s</td>", order.getStatus() == 1 ? "已完成" : "未完成"));
                    orderTableHtml.append(String.format(
                            "<td><a href=\"ShowOrderDetail?order_id=%s\"><button class=\"btn-sm btn-secondary\">查看</button></a></td>",
                            order.getOrderID()));
                    orderTableHtml.append("</tr>");
                }
                orderTableHtml.append("</tbody></table>");
                req.setAttribute("orderTableHtml", orderTableHtml);
            }
       } catch (SystemException e) {
            e.printStackTrace();
            // todo
        }

        req.getRequestDispatcher("orderRecord.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
