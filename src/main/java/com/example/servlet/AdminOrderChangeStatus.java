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
import java.io.PrintWriter;

@WebServlet("/AdminOrderChangeStatus")
public class AdminOrderChangeStatus extends HttpServlet {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        int status = Integer.parseInt(req.getParameter("status"));
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        try {
            Order order = orderDao.getOrder(orderId);
            order.setStatus(status);
            orderDao.update(order);
            if (status == 1) {
                pw.write("已将该订单标记为已完成");
            } else {
                pw.write("已将该订单标记为未完成");
            }
        } catch (SystemException e) {
            e.printStackTrace();
            resp.setStatus(500);
            pw.write("error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
