package com.example.servlet;

import com.example.common.SystemException;
import com.example.dao.UserDao;
import com.example.dao.daoImpl.UserDaoImpl;
import com.example.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginUserPhoneNumber = (String)req.getSession().getAttribute("login_user_phone_number");
        if (loginUserPhoneNumber != null) {
            UserDao dao = new UserDaoImpl();
            try {
                User user = dao.getUser(loginUserPhoneNumber);
                req.setAttribute("login_user_username", user.getUserName());
                req.setAttribute("login_user_balance", String.format("%.2f", user.getBalance()));
            } catch (SystemException e) {
                e.printStackTrace();
                req.setAttribute("login_user_username", "获取信息时出现错误");
                req.setAttribute("login_user_balance", "获取信息时出现错误");
            }
        } else {
            req.setAttribute("login_user_username", "获取信息时出现错误");
            req.setAttribute("login_user_balance", "获取信息时出现错误");
        }
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
