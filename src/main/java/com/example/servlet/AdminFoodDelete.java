package com.example.servlet;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.daoImpl.FoodDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminFoodDelete")
public class AdminFoodDelete extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String foodId = req.getParameter("deleteFoodId");
        try {
            Status status = foodDao.delete(foodId);
            if (status == Status.FOOD_DELETE_SUCCESS) {
                resp.sendRedirect("AdminFoodManage");
            } else {
                resp.setStatus(500);
            }
        } catch (SystemException e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
