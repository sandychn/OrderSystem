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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("login_phone_number");
        String password = req.getParameter("login_user_password");

        String resultMessage;

        try {
            User user = userDao.getUser(phoneNumber);
            if (user == null) {
                resultMessage = "输入的用户不存在";
                req.setAttribute("result_message", resultMessage);
                req.getRequestDispatcher("result.jsp").forward(req, resp);
            } else {
                boolean isPasswordCorrect = userDao.isTruePassword(phoneNumber, password);
                if (isPasswordCorrect) {
                    HttpSession session = req.getSession();
                    session.setAttribute("login_user_phone_number", phoneNumber);
                    session.setAttribute("login_user_id", user.getUserID());
                    session.setAttribute("login_user_identity", user.getIdentity());

                    String pageTo;
                    if (user.getIdentity() == 0) {
                        pageTo = "Menu";
                    } else if (user.getIdentity() == 1) {
                        pageTo = "admin.jsp";
                    } else {
                        pageTo = "Menu";
                    }

                    req.getRequestDispatcher(pageTo).forward(req, resp);
                } else {
                    resultMessage = "密码不正确或用户名不正确，请重试";
                    req.setAttribute("result_message", resultMessage);
                    req.getRequestDispatcher("result.jsp").forward(req, resp);
                }
            }
        } catch (SystemException e) {
            resultMessage = "登录时发生错误，请重试";
            e.printStackTrace();

            req.setAttribute("result_message", resultMessage);
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        }
    }

}
