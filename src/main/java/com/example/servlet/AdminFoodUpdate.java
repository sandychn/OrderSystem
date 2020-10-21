package com.example.servlet;

import com.example.common.AdminFoodHelper;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.daoImpl.FoodDaoImpl;
import com.example.pojo.Food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/AdminFoodUpdate")
public class AdminFoodUpdate extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // get method is not accepted here, should use post to uplaod file
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> parameters = null;

        try {
            parameters = new AdminFoodHelper().uploadPicture(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (parameters == null) {
            resp.setStatus(500);
            return;
        }

        String foodId = parameters.get("editFoodId");
        String foodName = parameters.get("editFoodName");
        String foodImageURL = parameters.get("foodImageURL");
        String foodDescription = parameters.get("editFoodDescription");
        double foodPrice = Double.parseDouble(parameters.get("editFoodPrice"));
        int foodKindId = Integer.parseInt(parameters.get("editFoodKindId"));

        try {
            Food food = new Food();
            food.setFoodID(foodId);
            food.setFoodName(foodName);
            food.setKind(foodKindId);
            food.setPrice(foodPrice);
            food.setDescription(foodDescription);
            if (foodImageURL == null) {
                food.setImageUrl(foodDao.getFood(foodId).getImageUrl());
            } else {
                food.setImageUrl(foodImageURL);
            }

            if (foodDao.update(food) == Status.FOOD_UPDATE_SUCCESS) {
                resp.sendRedirect("AdminFoodManage");
            } else {
                resp.setStatus(500);
            }
        } catch (SystemException | IOException e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }
}
