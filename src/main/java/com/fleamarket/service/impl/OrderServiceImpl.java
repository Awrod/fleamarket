package com.fleamarket.service.impl;

import com.fleamarket.dao.IOrderDao;
import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.OrderDaoImpl;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.OrderTableEntity;
import com.fleamarket.domain.ShoppingcartTableEntity;
import com.fleamarket.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {
    @Override
    public int insorder(DaTableEntity da, GoodsTableEntity goods, String username,OrderTableEntity or) {
        if(da == null && goods == null && username == null)
            return -1;
        IOrderDao oDao = new OrderDaoImpl();
        int msg = oDao.insorder(da,goods,username,or);
        return msg;
    }

    @Override
    public int scinsorder(DaTableEntity da, List<ShoppingcartTableEntity> sp) {
        IOrderDao oDao = new OrderDaoImpl();
        int msg = oDao.scinsorder(da,sp);
        return msg;
    }

    @Override
    public List<OrderTableEntity> handleorder(String username,int ppage,int page) {
        if(username == null)
            return null;
        IOrderDao oDao = new OrderDaoImpl();
        List<OrderTableEntity> orderlist=oDao.handleorder(username,ppage,page);
        return orderlist;
    }

    @Override
    public List<OrderTableEntity> cptorder(String username,String hidden) {
        if(username == null)
            return null;
        IOrderDao oDao = new OrderDaoImpl();
        List<OrderTableEntity> orderlist=oDao.cptorder(username,hidden);
        return orderlist;
    }

    @Override
    public List<OrderTableEntity> checkorder(String username,String hidden,int ppage,int npage) {
        if(username == null)
            return null;
        IOrderDao oDao = new OrderDaoImpl();
        List<OrderTableEntity> orderlist=oDao.checkorder(username,hidden,ppage,npage);
        return orderlist;
    }

    @Override
    public List<OrderTableEntity> checkorder1(String username, String hidden, int ppage, int npage) {
        if(username == null)
            return null;
        IOrderDao oDao = new OrderDaoImpl();
        List<OrderTableEntity> orderlist=oDao.checkorder1(username,hidden,ppage,npage);
        return orderlist;
    }

    @Override
    public List<OrderTableEntity> checkorder2(String username, String hidden, int ppage, int npage) {
        if(username == null)
            return null;
        IOrderDao oDao = new OrderDaoImpl();
        List<OrderTableEntity> orderlist=oDao.checkorder2(username,hidden,ppage,npage);
        return orderlist;
    }

    @Override
    public List<OrderTableEntity> checkorder3(String username, String hidden, int ppage, int npage) {
        if(username == null)
            return null;
        IOrderDao oDao = new OrderDaoImpl();
        List<OrderTableEntity> orderlist=oDao.checkorder3(username,hidden,ppage,npage);
        return orderlist;
    }

    @Override
    public int Tporder(String id,String goodsid,String ordergQuantity) {
        if(id == null)
            return -1;
        IOrderDao oDao = new OrderDaoImpl();
        int msg = oDao.Tporder(id,goodsid,ordergQuantity);
        return msg;
    }

    @Override
    public int uporder(String id) {
        if(id == null)
            return -1;
        IOrderDao oDao = new OrderDaoImpl();
        int msg = oDao.uporder(id);
        return msg;
    }

    @Override
    public int delorder(String id) {
        if(id == null)
            return -1;
        IOrderDao oDao = new OrderDaoImpl();
        int msg = oDao.delorder(id);
        return msg;
    }

    @Override
    public int useruporder(String id) {
        if(id == null)
            return -1;
        IOrderDao oDao = new OrderDaoImpl();
        int msg = oDao.useruporder(id);
        return msg;
    }
}
