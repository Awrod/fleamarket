package com.fleamarket.service;

import com.fleamarket.domain.CommentTableEntity;

import java.util.List;

public interface CommentService {
    int inscom(String commain,int goodsid,String username);
    int delcom(int comid);
    List<CommentTableEntity> findcom(int goodsid);
}
