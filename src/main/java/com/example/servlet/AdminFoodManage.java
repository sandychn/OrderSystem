package com.example.servlet;

import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.KindDao;
import com.example.dao.UserDao;
import com.example.dao.daoImpl.FoodDaoImpl;
import com.example.dao.daoImpl.KindDaoImpl;
import com.example.dao.daoImpl.UserDaoImpl;
import com.example.pojo.Food;
import com.example.pojo.Kind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminFoodManage")
public class AdminFoodManage extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();
    private final KindDao kindDao = new KindDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    private static String getFoodWithKindNameString(String foodID, String foodName,
                                                    String imageUrl, String description,
                                                    double price, String kindName) {
        return String.format("[\"%s\",\"%s\",\"%s\",\"%s\",%.2f,\"%s\"]", foodID, foodName, imageUrl, description, price, kindName);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String loginUserID = (String)session.getAttribute("login_user_id");

        if (loginUserID == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int loginUserIdentity = (int)session.getAttribute("login_user_identity");

        // not an administrator
        if (loginUserIdentity != 1) {
            resp.sendRedirect("Menu");
            return;
        }

        try {
            List<Kind> kinds = kindDao.getAllKind();
            Map<Integer, String> kindIdToKindNameMap = new HashMap<>();
            StringBuilder foodIdToKindIdDictBuilder = new StringBuilder();
            StringBuilder kindOptionStringBuilder = new StringBuilder();
            StringBuilder foodTableArrayStringBuilder = new StringBuilder();

            for (Kind kind : kinds) {
                kindIdToKindNameMap.put(kind.getKindID(), kind.getKindName());
                kindOptionStringBuilder.append(String.format("<option value=\"%s\">%s</option>\n",
                        kind.getKindID(), kind.getKindName()));
            }

            for (Food food : foodDao.getAllFood()) {
                String kindName = kindIdToKindNameMap.get(food.getKind());
                if (foodTableArrayStringBuilder.length() != 0)
                    foodTableArrayStringBuilder.append(',');
                foodTableArrayStringBuilder.append(getFoodWithKindNameString(food.getFoodID(),
                        food.getFoodName(), food.getImageUrl(),
                        food.getDescription(), food.getPrice(), kindName));
                if (foodIdToKindIdDictBuilder.length() != 0)
                    foodIdToKindIdDictBuilder.append(',');
                foodIdToKindIdDictBuilder.append(String.format("%s:%s", food.getFoodID(), food.getKind()));
            }
            req.setAttribute("food_id_to_kind_id_dict", "{" + foodIdToKindIdDictBuilder.toString() + "}");
            req.setAttribute("kind_options", kindOptionStringBuilder.toString());
            req.setAttribute("food_table_array", "[" + foodTableArrayStringBuilder.toString() + "]");
        } catch (SystemException e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
        req.getRequestDispatcher("adminFoodManage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
