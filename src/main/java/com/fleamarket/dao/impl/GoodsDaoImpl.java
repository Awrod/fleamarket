package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IGoodsDao;
import com.fleamarket.domain.GoodsTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements IGoodsDao {
    @Override
    public List<GoodsTableEntity> likeallgoods(String goodsname) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from goods_table where GoodsName LIKE ?";
        String newgoodsname="%"+goodsname+"%";
        List<GoodsTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<GoodsTableEntity>(GoodsTableEntity.class),newgoodsname);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    @Override
    public GoodsTableEntity linkusergoods(String path) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        GoodsTableEntity result=null;
        String sql="select * from goods_table where GoodsId=?";
        try {
            result = qr.query(sql,new BeanHandler<GoodsTableEntity>(GoodsTableEntity.class),path);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    @Override
    public int selleradd(GoodsTableEntity goods,String path,String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO goods_table(GoodsPhoto,GoodsName,GoodsType,GoodsQuantity,GoodsSynopsis,GoodsState,GoodsPrice,UserName,GoodsPhotoo,GoodsPhotot,GoodsPhotos) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        int update = 0;
        try {
            update = qr.update(sql,path,goods.getGoodsName(),goods.getGoodsType(),goods.getGoodsQuantity(),goods.getGoodsSynopsis(),"up",goods.getGoodsPrice(),username,goods.getGoodsPhotoo(),goods.getGoodsPhotot(),goods.getGoodsPhotos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    @Override
    public int goodsdel(String goodsid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from goods_table where GoodsId = ?";
        int update = 0;
        try {
            update = qr.update(sql,goodsid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    @Override
    public int updategoods(GoodsTableEntity goods,String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE goods_table SET GoodsPhoto=?,GoodsName=?,GoodsType=?,GoodsQuantity=?,GoodsSynopsis=?,GoodsState=?,GoodsPrice=?,UserName=?,GoodsPhotoo=?,GoodsPhotot=?,GoodsPhotos=?  WHERE GoodsId=?";
        int update = 0;
        try {
            update = qr.update(sql,goods.getGoodsPhoto(),goods.getGoodsName(),goods.getGoodsType(),goods.getGoodsQuantity(),goods.getGoodsSynopsis(),"up",goods.getGoodsPrice(),username,goods.getGoodsPhotoo(),goods.getGoodsPhotot(),goods.getGoodsPhotos(),goods.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    @Override
    public List<GoodsTableEntity>selallgoods(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from goods_table where UserName=?";
        List<GoodsTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<GoodsTableEntity>(GoodsTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    @Override
    public List<GoodsTableEntity> typegoodssel(String type) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        List<GoodsTableEntity> result=null;
        String sql="select * from goods_table where GoodsType=?";
        try {
            result = qr.query(sql,new BeanListHandler<GoodsTableEntity>(GoodsTableEntity.class),type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
