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

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("login_phone_number");
        String password = req.getParameter("login_user_password");

        UserDao dao = new UserDaoImpl();

        String resultMessage;

        try {
            User user = dao.getUser(phoneNumber);
            if (user == null) {
                resultMessage = "输入的用户不存在";
            } else {
                boolean isPasswordCorrect = dao.isTurePassword(phoneNumber, password);
                if (isPasswordCorrect) {
                    resultMessage = phoneNumber + " 用户登录成功";
                    req.getSession().setAttribute("login_user_phone_number", phoneNumber);
                } else {
                    resultMessage = "密码不正确或用户名不正确，请重试";
                }
            }
        } catch (SystemException e) {
            resultMessage = "登录时发生错误，请重试";
            e.printStackTrace();
        }

        req.setAttribute("result_message", resultMessage);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

}
