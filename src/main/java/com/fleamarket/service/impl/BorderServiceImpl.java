package com.fleamarket.service.impl;

import com.fleamarket.dao.IBookDao;
import com.fleamarket.dao.IBorderDao;
import com.fleamarket.dao.impl.BookDaoImpl;
import com.fleamarket.dao.impl.BorderDaoImpl;
import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.BorderTableEntity;
import com.fleamarket.service.IBookService;
import com.fleamarket.service.IBorderService;

import java.util.List;

public class BorderServiceImpl implements IBorderService {
    @Override
    public int insborder(BookTableEntity border, String username) {
        IBorderDao bDao = new BorderDaoImpl();
        int msg = bDao.insborder(border,username);
        return msg;
    }

    @Override
    public int upborder(String id,String name) {
        IBorderDao bDao = new BorderDaoImpl();
        int msg = bDao.upborder(id,name);
        return msg;
    }

    @Override
    public List<BorderTableEntity> allborder(int ppage, int page,String username,String hidden) {
        IBorderDao bDao = new BorderDaoImpl();
        List<BorderTableEntity> result=bDao.allborder(ppage,page,username,hidden);
        return result;
    }
}
