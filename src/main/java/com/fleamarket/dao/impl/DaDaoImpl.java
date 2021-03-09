package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IDaDao;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.UserTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DaDaoImpl implements IDaDao {
    @Override
    public List<DaTableEntity> alladdr(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from da_table where DaUserName=?";
        List<DaTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<DaTableEntity>(DaTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int insaddr(DaTableEntity da) {
        int update = 0;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO da_table(DaUserName,UserMoblie,UserAddr,DaName) VALUES(?,?,?,?)";
        try {
            update = qr.update(sql, da.getDaUserName(), da.getUserMoblie(), da.getUserAddr(), da.getDaName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int updateaddr(DaTableEntity da) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE da_table SET DaUserName=?,UserMoblie=?,UserAddr=?,DaName=?  WHERE DaId=?";
        int update = 0;
        try {
            update = qr.update(sql,da.getDaUserName(),da.getUserMoblie(),da.getUserAddr(),da.getDaName(),da.getDaId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int deladdr(String daid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from da_table where DaId = ?";
        int update = 0;
        try {
            update = qr.update(sql,daid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
