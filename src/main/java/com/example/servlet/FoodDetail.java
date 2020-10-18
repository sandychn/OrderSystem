package com.example.servlet;

import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.OrderCartDao;
import com.example.dao.daoImpl.FoodDaoImpl;
import com.example.dao.daoImpl.OrderCartDaoImpl;
import com.example.pojo.Food;
import com.example.pojo.OrderCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FoodDetail")
public class FoodDetail extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();
    private final OrderCartDao orderCartDao = new OrderCartDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = (String)req.getSession().getAttribute("login_user_id");
        String foodId = req.getParameter("food_id");

        try {
            Food food = foodDao.getFood(foodId);
            req.setAttribute("food_id", food.getFoodID());
            req.setAttribute("food_name", food.getFoodName());
            req.setAttribute("food_image_url", food.getImageUrl());
            req.setAttribute("food_description", food.getDescription());
            req.setAttribute("food_price", String.format("%.2f", food.getPrice()));
            OrderCart orderCart = orderCartDao.getOrderCart(userId, foodId);
            int foodCount = orderCart == null ? 0 : orderCart.getFoodCount();
            req.setAttribute("food_count", String.format("%d", foodCount));
        } catch (SystemException e) {
            e.printStackTrace();
            req.setAttribute("food_name", "error");
        }
        req.getRequestDispatcher("foodDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
