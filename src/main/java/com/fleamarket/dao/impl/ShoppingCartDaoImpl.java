package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IShoppingCartDao;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.ShoppingcartTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCartDaoImpl implements IShoppingCartDao {
    @Override
    public int insshop(ShoppingcartTableEntity shop) {
        List<ShoppingcartTableEntity> goodslist=findshop(shop.getShoppingGid());
        int update = 0,msg=0;
        if (goodslist.size()==0){
            msg=1;
        }
        if (msg==1){
            QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "INSERT INTO shoppingcart_table(ShoppingGid,ShoppingGname,ShoppingPrice,ShoppingSname,ShoppingQuantity,ShoppingGphoto,ShoppingUsername) VALUES(?,?,?,?,?,?,?)";
            try {
                update = qr.update(sql, shop.getShoppingGid(),shop.getShoppingGname(),shop.getShoppingPrice(),shop.getShoppingSname(),shop.getShoppingQuantity(),shop.getShoppingGphoto(),shop.getShoppingUsername());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update;
    }

    @Override
    public List<ShoppingcartTableEntity> showshop(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        List<ShoppingcartTableEntity> result=null;
        String sql="select * from shoppingcart_table where ShoppingUsername=?";
        try {
            result = qr.query(sql,new BeanListHandler<ShoppingcartTableEntity>(ShoppingcartTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int del(int id) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from shoppingcart_table where ShoppingId = ?";
        int update = 0;
        try {
            update = qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    public List<ShoppingcartTableEntity> findshop(int gid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from shoppingcart_table where ShoppingGid=? ";
        List<ShoppingcartTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<ShoppingcartTableEntity>(ShoppingcartTableEntity.class),gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
