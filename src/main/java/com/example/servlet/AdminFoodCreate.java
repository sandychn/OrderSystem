package com.example.servlet;

import com.example.common.AdminFoodHelper;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.KindDao;
import com.example.dao.daoImpl.FoodDaoImpl;
import com.example.dao.daoImpl.KindDaoImpl;
import com.example.pojo.Food;
import com.example.pojo.Kind;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminFoodCreate")
public class AdminFoodCreate extends HttpServlet {
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

        String foodName = parameters.get("foodName");
        String foodImageURL = parameters.get("foodImageURL");
        String foodDescription = parameters.get("foodDescription");
        double foodPrice = Double.parseDouble(parameters.get("foodPrice"));
        int foodKindId = Integer.parseInt(parameters.get("foodKindId"));

        try {
            Food food = new Food();
            food.setFoodName(foodName);
            food.setKind(foodKindId);
            food.setPrice(foodPrice);
            food.setDescription(foodDescription);
            food.setImageUrl(foodImageURL);
            if (foodDao.addFood(food) == Status.FOOD_ADD_SUCCESS) {
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
