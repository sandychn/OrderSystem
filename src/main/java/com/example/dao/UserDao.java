package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.User;


public interface UserDao {

    User getUser(String phoneNumber) throws SystemException;  //根据电话号码获取用户信息
    Status addUser(User user) throws SystemException;  //增加一个用户
    Status update(User user) throws SystemException;  //修改用户信息
    Status delete(String userId) throws SystemException; //删除一个用户
    boolean isTruePassword(String phoneNumber,String password) throws SystemException; //用户不存在和密码错误都返回false
    boolean isExist(String userId) throws SystemException;  //判断一个用户是否存在
}


