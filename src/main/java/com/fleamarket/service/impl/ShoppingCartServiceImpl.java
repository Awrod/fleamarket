package com.fleamarket.service.impl;

import com.fleamarket.dao.IShoppingCartDao;
import com.fleamarket.dao.impl.ShoppingCartDaoImpl;
import com.fleamarket.domain.ShoppingcartTableEntity;
import com.fleamarket.service.IShoppingCartService;

import java.util.List;

public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Override
    public int insshop(ShoppingcartTableEntity shop) {
        IShoppingCartDao ishop=new ShoppingCartDaoImpl();
        int msg=ishop.insshop(shop);
        return msg;
    }

    @Override
    public List<ShoppingcartTableEntity> showshop(String username) {
        IShoppingCartDao ishop=new ShoppingCartDaoImpl();
        List<ShoppingcartTableEntity> shoplist=ishop.showshop(username);
        return shoplist;
    }

    @Override
    public int del(int id) {
        IShoppingCartDao ishop=new ShoppingCartDaoImpl();
        int msg=ishop.del(id);
        return msg;
    }
}
