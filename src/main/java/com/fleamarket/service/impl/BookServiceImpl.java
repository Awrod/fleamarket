package com.fleamarket.service.impl;

import com.fleamarket.dao.IBookDao;
import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.BookDaoImpl;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    @Override
    public int addbook(BookTableEntity book, String path, String username) {
        if(book == null)
            return -1;
        IBookDao bDao = new BookDaoImpl();
        int msg = bDao.addbook(book,path,username);
        return msg;
    }

    @Override
    public int delbook(String id) {
        if(id == null)
            return -1;
        IBookDao bDao = new BookDaoImpl();
        int msg = bDao.delbook(id);
        return msg;
    }

    @Override
    public List<BookTableEntity> allbook(int ppage, int page) {
        IBookDao bDao = new BookDaoImpl();
        List<BookTableEntity> result=bDao.allbook(ppage,page);
        return result;
    }
}
