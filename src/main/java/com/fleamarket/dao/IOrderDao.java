package com.fleamarket.dao;

import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.OrderTableEntity;
import com.fleamarket.domain.ShoppingcartTableEntity;

import java.util.List;
import java.util.Map;

public interface IOrderDao {
    int insorder(DaTableEntity da, GoodsTableEntity goods, String username,OrderTableEntity or);
    int scinsorder(DaTableEntity da, List<ShoppingcartTableEntity> sp);
    List<OrderTableEntity> handleorder(String username,int ppage,int page);
    List<OrderTableEntity> checkorder(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> checkorder1(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> checkorder2(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> checkorder3(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> cptorder(String username,String hidden);
    int uporder(String id);
    int delorder(String id);
    int Tporder(String id,String goodsid,String ordergQuantity);
    int useruporder(String id);
}
