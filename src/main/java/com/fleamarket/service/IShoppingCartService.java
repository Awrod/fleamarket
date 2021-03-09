package com.fleamarket.service;

import com.fleamarket.domain.ShoppingcartTableEntity;

import java.util.List;

public interface IShoppingCartService {
    int insshop(ShoppingcartTableEntity shop);
    List<ShoppingcartTableEntity> showshop(String username);
    int del(int id);
}
