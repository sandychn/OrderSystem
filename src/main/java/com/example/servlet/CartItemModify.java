package com.example.servlet;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderCartDao;
import com.example.dao.daoImpl.OrderCartDaoImpl;
import com.example.pojo.OrderCart;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CartItemModify")
public class CartItemModify extends HttpServlet {
    private final OrderCartDao orderCartDao = new OrderCartDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String foodId = req.getParameter("foodId");
        int foodCount = Integer.parseInt(req.getParameter("foodCount"));
        OrderCart orderCart = new OrderCart();
        orderCart.setUserID(userId);
        orderCart.setFoodID(foodId);
        orderCart.setFoodCount(foodCount);

        resp.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = resp.getWriter();
            Status status = orderCartDao.update(orderCart);
            switch (status) {
                case ORDERCART_UPDATE_SUCCESS:
                    pw.write("success");
                    break;
                case ORDERCART_UPDATE_FAIL:
                    resp.setStatus(500);
                    pw.write("error");
                    break;
                default:
                    resp.setStatus(500);
                    pw.write("error");
                    break;
            }
        } catch (SystemException | IOException e) {
            resp.setStatus(500);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
