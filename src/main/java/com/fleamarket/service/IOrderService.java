package com.fleamarket.service;

import com.fleamarket.domain.*;

import java.util.List;

public interface IOrderService {
    int insorder(DaTableEntity da, GoodsTableEntity goods,String username,OrderTableEntity or);
    int scinsorder(DaTableEntity da, List<ShoppingcartTableEntity> sp);
    List<OrderTableEntity> handleorder(String username,int ppage,int page);
    List<OrderTableEntity> cptorder(String username,String hidden);
    List<OrderTableEntity> checkorder(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> checkorder1(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> checkorder2(String username,String hidden,int ppage,int npage);
    List<OrderTableEntity> checkorder3(String username,String hidden,int ppage,int npage);
    int Tporder(String id,String goodsid,String ordergQuantity);
    int uporder(String id);
    int delorder(String id);
    int useruporder(String id);
}
