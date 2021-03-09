package com.fleamarket.dao;

import com.fleamarket.domain.CommentTableEntity;

import java.util.List;

public interface CommentDao {
    int inscom(String commain,int goodsid,String username);
    List<CommentTableEntity> findcom(int goodsid);
    int delcom(int comid);
}
