package com.example.servlet;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderCartDao;
import com.example.dao.OrderDao;
import com.example.dao.OrderDetailDao;
import com.example.dao.daoImpl.OrderCartDaoImpl;
import com.example.dao.daoImpl.OrderDaoImpl;
import com.example.dao.daoImpl.OrderDetailDaoImpl;
import com.example.pojo.Order;
import com.example.pojo.OrderCart;
import com.example.pojo.OrderDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
    private final OrderCartDao orderCartDao = new OrderCartDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderDetailDao detailDao = new OrderDetailDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("user_id");
        int number = Integer.parseInt(req.getParameter("number"));
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resultMessage;
        try{
            List<OrderCart> orderCartList = orderCartDao.getOrderCarts(userId);
            if(orderCartList.isEmpty()){
                resultMessage = "购物车为空，未选择餐品";
            }else{
                String time = sd.format(new Date());
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
                long startTime = date.getTime() / 1000;
                Order order = new Order();
                order.setNumber(number);
                order.setStatus(0);
                order.setStartTime(startTime);
                order.setUserId(userId);
                String orderId = orderDao.addOrder(order);
                if(orderId == null){
                    resultMessage = "下单失败";
                }else{
                    for(OrderCart orderCart : orderCartList){
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrderID(orderId);
                        orderDetail.setFoodID(orderCart.getFoodID());
                        orderDetail.setFoodCount(orderCart.getFoodCount());
                        Status status = detailDao.addOrderDetail(orderDetail);
                        if(status != Status.ORDERDETAIL_ADD_SUCCESS){
                            resultMessage = "下单失败";
                            break;
                        }
                    }
                    if (orderCartDao.deleteByUserID(userId) == Status.ORDERCART_DELETE_SUCCESS) {
                        resultMessage = "下单成功";
                        resp.sendRedirect("ShowOrderDetail?order_id=" + orderId);
                        return;
                    } else {
                        resultMessage = "下单失败";
                    }
                }
            }
        }catch(SystemException | ParseException e){
            resultMessage = "下单时发生错误，请重试";
            e.printStackTrace();
        }
        req.setAttribute("result_message", resultMessage);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
