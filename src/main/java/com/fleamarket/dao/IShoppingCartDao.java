package com.fleamarket.dao;

import com.fleamarket.domain.ShoppingcartTableEntity;

import java.util.List;

public interface IShoppingCartDao {
    int insshop(ShoppingcartTableEntity shop);
    List<ShoppingcartTableEntity> showshop(String username);
    int del(int id);
}
