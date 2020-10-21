package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.Kind;

import java.util.List;

public interface KindDao {
    Kind getKind(int kindID) throws SystemException;
    List<Kind> getAllKind() throws SystemException;
    Status addKind(Kind kind) throws SystemException;
    Status update(Kind kind) throws SystemException;
    Status delete(int kindID) throws SystemException;
    boolean isExist(int kindID) throws SystemException;
}
