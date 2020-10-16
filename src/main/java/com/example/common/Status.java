package com.example.common;

import com.example.pojo.User;

public enum Status {
    USER_EXISTS, //用户已存在
    USER_NOT_EXISTS, //用户不存在
    USER_ADD_SUCCESS, //用户添加成功
    USER_ADD_FAIL, //用户添加失败
    USER_UPDATE_SUCCESS, //用户修改成功
    USER_UPDATE_FAIL, //用户修改失败
    USER_DELETE_SUCCESS, //用户删除成功
    USER_DELETE_FAIL, //用户删除失败
}
