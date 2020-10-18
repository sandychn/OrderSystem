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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private final FoodDao foodDao = new FoodDaoImpl();
    private final OrderCartDao orderCartDao = new OrderCartDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = (String)req.getSession().getAttribute("login_user_id");

        try {
            List<OrderCart> orderCarts = orderCartDao.getOrderCarts(userId);
            StringBuilder cartTableHtml = new StringBuilder();
            cartTableHtml.append("<table class=\"table\">\n" +
                    "<thead class=\"thead-light\">\n" +
                    "<th scope=\"col\">菜品名</th>\n" +
                    "<th scope=\"col\">单价</th>\n" +
                    "<th scope=\"col\">份数</th>\n" +
                    "<th scope=\"col\">小计</th>\n" +
                    "<th scope=\"col\">删除</th>\n" +
                    "</thead>\n" +
                    "<tbody>");
            for (OrderCart orderCart : orderCarts) {
                Food food = foodDao.getFood(orderCart.getFoodID());
                cartTableHtml.append(String.format("<tr class=\"food-table-row\" id=\"food-table-row-%s\">", food.getFoodID()));
                cartTableHtml.append(String.format("<td class=\"align-middle\">%s</td>", food.getFoodName()));
                cartTableHtml.append(String.format(
                        "<td class=\"align-middle\"><span id=\"food-price-single-%s\">%.2f</span></td>",
                        food.getFoodID(),
                        food.getPrice()));
                cartTableHtml.append("<td class=\"align-middle no-select\">");
                cartTableHtml.append(String.format(
                        "<span class=\"fas fa-minus-circle\" onclick=\"item_modify_minus_in_cart('%s','%s')\"></span>",
                        userId,
                        food.getFoodID()));
                cartTableHtml.append(String.format(
                        "<div class=\"food-count\" id=\"food-%s\">%d</div>",
                        food.getFoodID(),
                        orderCart.getFoodCount()));
                cartTableHtml.append(String.format(
                        "<span class=\"fas fa-plus-circle\" onclick=\"item_modify_plus_in_cart('%s','%s')\"></span>",
                        userId,
                        food.getFoodID()));
                cartTableHtml.append("</td>");
                cartTableHtml.append(String.format(
                        "<td class=\"align-middle\"><span id=\"food-price-total-%s\">%.2f</span></td>",
                        food.getFoodID(),
                        food.getPrice() * orderCart.getFoodCount()));
                cartTableHtml.append(String.format(
                        "<td class=\"align-middle\">" +
                                "<button class=\"btn-sm btn-primary\" onclick=\"item_modify_zero_in_cart('%s','%s')\">删除</button>" +
                                "</td>",
                        userId,
                        food.getFoodID()));
                cartTableHtml.append("</tr>");
            }
            cartTableHtml.append("</tbody></table>");

            req.setAttribute("cartTableHtml", cartTableHtml);
        } catch (SystemException e) {
            req.setAttribute("cartTableHtml", "<div class=\"alert alert-primary\">error</div>");
            e.printStackTrace();
        }
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
