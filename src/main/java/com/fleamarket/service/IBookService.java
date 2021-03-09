package com.fleamarket.service;

import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.GoodsTableEntity;

import java.util.List;

public interface IBookService {
    int addbook(BookTableEntity book, String path, String username);
    int delbook(String id);
    List<BookTableEntity> allbook(int ppage,int page);
}
