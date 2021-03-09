package com.fleamarket.service.impl;

import com.fleamarket.dao.IGoodsDao;
import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.GoodsDaoImpl;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.service.IGoodsService;

import java.util.List;

public class GoodsServiceImpl implements IGoodsService {
    @Override
    public List<GoodsTableEntity> likeallgoods(String goodsname) {
        IGoodsDao gDao = new GoodsDaoImpl();
        List<GoodsTableEntity> listgoods=gDao.likeallgoods(goodsname);
        return listgoods;
    }
    @Override
    public int updategoods(GoodsTableEntity goods,String username) {
        if(goods == null)
            return -1;
        IGoodsDao gDao = new GoodsDaoImpl();
        int msg = gDao.updategoods(goods,username);
        return msg;
    }
    @Override
    public List<GoodsTableEntity> typegoodssel(String type) {
        IGoodsDao gDao = new GoodsDaoImpl();
        List<GoodsTableEntity> listgoods=gDao.typegoodssel(type);
        return listgoods;
    }

    @Override
    public GoodsTableEntity linkusergoods(String path) {
        IGoodsDao gDao = new GoodsDaoImpl();
        GoodsTableEntity result= null;
        result = gDao.linkusergoods(path);
        return result;
    }
    @Override
    public int goodsdel(String goodsid) {
        if(goodsid == null)
            return -1;
        IGoodsDao gDao = new GoodsDaoImpl();
        int msg =gDao.goodsdel(goodsid);
        return msg;
    }
    @Override
    public List<GoodsTableEntity> selallgoods(String username) {
        IGoodsDao gDao = new GoodsDaoImpl();
        List<GoodsTableEntity> listgoods=gDao.selallgoods(username);
        return listgoods;
    }
    @Override
    public int selleradd(GoodsTableEntity goods,String path,String username) {
        if(goods == null)
            return -1;
        IGoodsDao gDao = new GoodsDaoImpl();
        int msg = gDao.selleradd(goods,path,username);
        return msg;
    }
}
