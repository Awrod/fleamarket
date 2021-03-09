package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IOrderDao;
import com.fleamarket.domain.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.omg.CORBA.INTERNAL;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements IOrderDao {
    @Override
    public int insorder(DaTableEntity da, GoodsTableEntity goods, String username,OrderTableEntity or) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO order_table(OrderGid,OrdergName,OrderPrice,OrderbName,OrdersName,OrderAddr,OrderMoblie,OrderDate,OrderbState,OrdersState,OrderUserN,OrdergQuantity) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int update = 0;
        try {
            update = qr.update(sql,goods.getGoodsId(),goods.getGoodsName(),goods.getGoodsPrice(),da.getDaName(),goods.getUserName(),da.getUserAddr(),da.getUserMoblie(),df.format(new Date()),"等待发货","等待发货",username,or.getOrdergQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (update>0){
            GoodsTableEntity goodss=upgoodsq(goods.getGoodsId());
            int msg=0;
            if (goodss.getGoodsQuantity()>=or.getOrdergQuantity()){
                msg=updategoods(goodss.getGoodsId(),goodss.getGoodsQuantity()-or.getOrdergQuantity());
            }
        }
        return update;
    }

    @Override
    public int scinsorder(DaTableEntity da, List<ShoppingcartTableEntity> sp) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO order_table(OrderGid,OrdergName,OrderPrice,OrderbName,OrdersName,OrderAddr,OrderMoblie,OrderDate,OrderbState,OrdersState,OrderUserN,OrdergQuantity) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int update = 0;
        for (ShoppingcartTableEntity shop:sp){
        try {
            update = qr.update(sql,shop.getShoppingGid(),shop.getShoppingGname(),shop.getShoppingPrice(),da.getDaName(),shop.getShoppingSname(),da.getUserAddr(),da.getUserMoblie(),df.format(new Date()),"等待发货","等待发货",shop.getShoppingUsername(),shop.getShoppingQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        if (update>0){
            for (ShoppingcartTableEntity shop:sp) {
                GoodsTableEntity goodss=upgoodsq(shop.getShoppingGid());
                int msg=0;
                if (goodss.getGoodsQuantity()>=shop.getShoppingQuantity()){
                    msg=updategoods(goodss.getGoodsId(),goodss.getGoodsQuantity()-shop.getShoppingQuantity());
                }
                del(shop.getShoppingId());
            }
        }
        return update;
    }
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

    //修改商品数量
    public int goodsdel(int goodsid) {
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
    //修改商品数量
    public int updategoods(int id,int Quantity) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE goods_table SET GoodsQuantity=?  WHERE GoodsId=?";
        int update = 0;
        try {
            update = qr.update(sql,Quantity,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    //修改商品数量
    public GoodsTableEntity upgoodsq(int goodsid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        GoodsTableEntity result=null;
        String sql="select * from goods_table where GoodsId=?";
        try {
            result = qr.query(sql,new BeanHandler<GoodsTableEntity>(GoodsTableEntity.class),goodsid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderTableEntity> handleorder(String username,int ppage,int page) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from order_table where OrdersName=? and OrdersState=? LIMIT ?,?";
        List<OrderTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"等待发货",ppage,page);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderTableEntity> checkorder(String username,String hidden,int ppage,int npage) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql=null;
        if ("sdelorder".equals(hidden)){
            sql="select * from order_table where OrdersName=?";
        }else {
            if (ppage==0 && npage==0){
                sql="select * from order_table where OrderUserN=? ";
            }else {
                sql="select * from order_table where OrderUserN=?  LIMIT ?,?";
            }

        }
        List<OrderTableEntity> result=null;
        try {
            if (ppage==0 && npage==0){
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username);
            }else {
                if ("sdelorder".equals(hidden)){
                    result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username);
                }else {
                    result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,ppage,npage);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderTableEntity> checkorder1(String username, String hidden, int ppage, int npage) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql=null;
        if (ppage==0 && npage==0){
            sql="select * from order_table where OrderUserN=? and OrderbState=?";
        }else {
            sql="select * from order_table where OrderUserN=? and OrderbState=?  LIMIT ?,?";
        }
        List<OrderTableEntity> result=null;
        try {
            if (ppage==0 && npage==0){
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"等待发货");
            }else {
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"等待发货",ppage,npage);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderTableEntity> checkorder2(String username, String hidden, int ppage, int npage) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql=null;
        if (ppage==0 && npage==0){
            sql="select * from order_table where OrderUserN=? and OrderbState=? ";
        }else {
            sql="select * from order_table where OrderUserN=? and OrderbState=?  LIMIT ?,?";
        }
        List<OrderTableEntity> result=null;
        try {
            if (ppage==0 && npage==0){
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"等待确认收货");
            }else {
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"等待确认收货",ppage,npage);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderTableEntity> checkorder3(String username, String hidden, int ppage, int npage) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql=null;
        if (ppage==0 && npage==0){
            sql="select * from order_table where OrderUserN=? and (OrderbState=? OR OrderbState=? OR OrderbState=?) ";
        }else {
            sql="select * from order_table where OrderUserN=? and (OrderbState=? OR OrderbState=? OR OrderbState=?)  LIMIT ?,?";
        }
        List<OrderTableEntity> result=null;
        try {
            if (ppage==0 && npage==0){
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"已收货","退货中","已退货");
            }else {
                result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),username,"已收货","退货中","已退货",ppage,npage);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderTableEntity> cptorder(String username,String hidden) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql=null;
        if ("cptorder".equals(hidden)){
            sql="select * from order_table where OrdersState=? and OrdersName=?";
        }else {
            sql="select * from order_table where OrderbState=? and OrderUserN=?";
        }
        List<OrderTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<OrderTableEntity>(OrderTableEntity.class),"已收货",username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public int uporder(String id) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE order_table SET OrdersState=?,OrderbState=? WHERE OrderId=?";
        int update=0;
        try {
            update = qr.update(sql,"等待确认","等待确认收货",id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }

    @Override
    public int delorder(String id) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE order_table SET OrdersState=?,OrderbState=? WHERE OrderId=?";
        int update=0;
        try {
            update = qr.update(sql,"退货中","退货中",id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }

    @Override
    public int Tporder(String id,String goodsid,String ordergQuantity) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE order_table SET OrdersState=?,OrderbState=? WHERE OrderId=?";
        int update=0;
        try {
            update = qr.update(sql,"已退货","已退货",id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (update>0){
            int i=Integer.parseInt(goodsid);
            GoodsTableEntity goodss=upgoodsq(i);
            updategoods(goodss.getGoodsId(),goodss.getGoodsQuantity()+Integer.parseInt(ordergQuantity));
        }
        return update;
    }

    @Override
    public int useruporder(String id) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE order_table SET OrdersState=?,OrderbState=? WHERE OrderId=?";
        int update=0;
        try {
            update = qr.update(sql,"已收货","已收货",id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }
}
