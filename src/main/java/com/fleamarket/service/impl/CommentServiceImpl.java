package com.fleamarket.service.impl;

import com.fleamarket.dao.CommentDao;
import com.fleamarket.dao.impl.CommentDaoImpl;
import com.fleamarket.domain.CommentTableEntity;
import com.fleamarket.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public int inscom(String commain, int goodsid, String username) {
        CommentDao com=new CommentDaoImpl();
        int mes=com.inscom(commain,goodsid,username);
        return mes;
    }

    @Override
    public int delcom(int comid) {
        CommentDao com=new CommentDaoImpl();
        int mes=com.delcom(comid);
        return mes;
    }

    @Override
    public List<CommentTableEntity> findcom(int goodsid) {
        CommentDao com=new CommentDaoImpl();
        List<CommentTableEntity> comlist=com.findcom(goodsid);
        return comlist;
    }
}
