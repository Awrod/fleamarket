package com.fleamarket.dao;

import com.fleamarket.domain.GoodsTableEntity;

import java.util.List;

public interface IGoodsDao {
    List<GoodsTableEntity> likeallgoods(String goodsname);
    List<GoodsTableEntity> typegoodssel(String type);
    GoodsTableEntity linkusergoods(String path);
    List<GoodsTableEntity> selallgoods(String username);
    int updategoods(GoodsTableEntity goods,String username);
    int goodsdel(String goodsid);
    int selleradd(GoodsTableEntity goods,String path,String username);
}
