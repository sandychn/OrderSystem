package com.example.servlet;

import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.dao.KindDao;
import com.example.dao.OrderCartDao;
import com.example.dao.daoImpl.FoodDaoImpl;
import com.example.dao.daoImpl.KindDaoImpl;
import com.example.dao.daoImpl.OrderCartDaoImpl;
import com.example.pojo.Food;
import com.example.pojo.Kind;
import com.example.pojo.OrderCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Menu")
public class Menu extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();
    private final KindDao kindDao = new KindDaoImpl();
    private final OrderCartDao orderCartDao = new OrderCartDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String loginUserID = (String)session.getAttribute("login_user_id");
        if (loginUserID == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Kind> kinds = kindDao.getAllKind();
            StringBuilder kindHTML = new StringBuilder();
            for (Kind kind : kinds) {
                kindHTML.append("<li class=\"nav-item\">");
                kindHTML.append(String.format("<a class=\"nav-link\" href=\"Menu?kind_id=%d\">%s</a>", kind.getKindID(), kind.getKindName()));
                kindHTML.append("</li>");
            }
            req.setAttribute("kind_html", kindHTML);
        } catch (SystemException e) {
            e.printStackTrace();
            req.setAttribute("kind_html", "error");
        }

        String foodKind = (String)req.getParameter("kind_id");
        try {
            List<Food> foods = null;
            if (foodKind == null) {
                foods = foodDao.getAllFood();
            } else {
                foods = foodDao.getFoodsByKind(Integer.parseInt(foodKind));
            }
            List<OrderCart> orderCarts = orderCartDao.getOrderCarts(loginUserID);
            Map<String, OrderCart> foodIdToOrderCartMap = new HashMap<>();
            for (OrderCart orderCart : orderCarts) {
                foodIdToOrderCartMap.put(orderCart.getFoodID(), orderCart);
            }

            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for (Food food : foods) {
                if (cnt % 4 == 0) {
                    sb.append("<div class=\"row food-row\">\n");
                }
                String foodDetailURL = "FoodDetail?food_id=" + food.getFoodID();
                OrderCart orderCart = foodIdToOrderCartMap.get(food.getFoodID());
                int foodCount = orderCart == null ? 0 : orderCart.getFoodCount();
                String html = String.format(
                        "<div class=\"col-md-3 column\">\n" +
                                "<a href=\"%s\"><img class=\"img-thumbnail\" src=\"%s\" alt=\"%s\"></a>\n" +
                                "<div class=\"item-description\">%s ¥%.2f</div>\n" +
                                "<div class=\"item-count\">" +
                                "<span class=\"fas fa-minus-circle\" onclick=\"item_modify_minus('%s','%s')\"></span>" +
                                "<div class=\"food-count no-select\" id=\"food-%s\">%d</div>" +
                                "<span class=\"fas fa-plus-circle\"  onclick=\"item_modify_plus('%s','%s')\"></span>" +
                                "</div>\n" +
                                "</div>\n",
                        foodDetailURL,
                        food.getImageUrl(),
                        food.getFoodName(),
                        food.getFoodName(),
                        food.getPrice(),
                        loginUserID,
                        food.getFoodID(),
                        food.getFoodID(),
                        foodCount,
                        loginUserID,
                        food.getFoodID());
                sb.append(html);
                if ((cnt + 1) % 4 == 0) {
                    sb.append("</div>\n");
                }
                ++cnt;
            }
            if (cnt % 4 != 0) sb.append("</div>\n");
            req.setAttribute("foods_html", sb.toString());
        } catch (SystemException e) {
            req.setAttribute("foods_html", "error");
            e.printStackTrace();
        }
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
