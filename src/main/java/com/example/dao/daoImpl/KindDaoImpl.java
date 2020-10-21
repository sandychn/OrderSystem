package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.KindDao;
import com.example.pojo.Kind;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KindDaoImpl implements KindDao {
    @Override
    public Kind getKind(int kindID) throws SystemException {
        try{
            Kind kind = null;
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_kind where k_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,kindID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                kind =new Kind();
                kind.setKindID(rs.getInt("k_id"));
                kind.setKindName(rs.getString("k_type"));
            }
            JdbcUtil.close(rs,ps);
            return kind;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public List<Kind> getAllKind() throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_kind";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Kind> list = new ArrayList<>();
            while(rs.next()){
                Kind kind = new Kind();
                kind.setKindID(rs.getInt("k_id"));
                kind.setKindName(rs.getString("k_type"));
                list.add(kind);
            }
            JdbcUtil.close(rs,statement);
            return list;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status addKind(Kind kind) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_kind where k_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,kind.getKindID());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return Status.KIND_EXISTS;
            }

            sql = "insert into t_kind(k_id,k_type) " +
                    "values('"+ kind.getKindID() +"',"+ kind.getKindName() +")";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.KIND_ADD_SUCCESS;
            }else{
                return Status.KIND_ADD_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status update(Kind kind) throws SystemException {
        try {
            if(!isExist(kind.getKindID())){
                return Status.KIND_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "update t_kind set k_type ='"+ kind.getKindName()  +
                    " where k_id="+ kind.getKindID()+"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.KIND_UPDATE_SUCCESS;
            }else{
                return Status.KIND_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status delete(int kindID) throws SystemException {
        try{
            if(!isExist(kindID)){
                return Status.KIND_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "delete from t_kind where k_id = '"+kindID+"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.KIND_DELETE_SUCCESS;
            }else {
                return Status.KIND_DELETE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean isExist(int kindID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_kind where k_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
           ps.setInt(1,kindID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return true;
            }else {
                JdbcUtil.close(rs,ps);
                return false;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }
}
