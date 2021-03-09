package com.fleamarket.dao;

import com.fleamarket.domain.BookTableEntity;

import java.util.List;

public interface IBookDao {
    int addbook(BookTableEntity book, String path, String username);
    List<BookTableEntity> allbook(int ppage, int page);
    int delbook(String id);

}
