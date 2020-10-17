package com.example.servlet;

import com.example.common.Status;
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

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("phone_number");
        String username = req.getParameter("username");
        String password = req.getParameter("user_password");

        UserDao dao = new UserDaoImpl();
        User user = new User();

        // user id is set by UserDao.addUser
        user.setPhoneNumber(phoneNumber);
        user.setUserName(username);
        user.setPassword(password); // original, not encrypted password
        user.setIdentity(0);
        user.setBalance(0.0);

        String resultMessage;

        try {
            Status status = dao.addUser(user);
            switch (status) {
                case USER_ADD_SUCCESS:
                    resultMessage = "注册成功";
                    break;
                case USER_EXISTS:
                    resultMessage = "该手机号已被使用";
                    break;
                case USER_ADD_FAIL:
                    resultMessage = "注册时发生错误，请重试";
                    break;
                default:
                    resultMessage = "注册时发生未知错误，请重试";
                    break;
            }
        } catch (SystemException e) {
            resultMessage = "注册时发生错误，请重试";
            e.printStackTrace();
        }
        req.setAttribute("result_message", resultMessage);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
