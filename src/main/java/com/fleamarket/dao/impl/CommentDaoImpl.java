package com.fleamarket.dao.impl;

import com.fleamarket.dao.CommentDao;
import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.domain.CommentTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public int inscom(String commain, int goodsid, String username) {
        int update = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "INSERT INTO comment_table(CommentMain,CommentUsername,CommentGoodsid,CommentDate) VALUES(?,?,?,?)";
            try {
                update = qr.update(sql,commain,username,goodsid,df.format(new Date()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return update;
    }

    @Override
    public List<CommentTableEntity> findcom(int goodsid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from comment_table where CommentGoodsid=?";
        List<CommentTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<CommentTableEntity>(CommentTableEntity.class),goodsid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int delcom(int comid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from comment_table where CommentId = ?";
        int update = 0;
        try {
            update = qr.update(sql,comid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
