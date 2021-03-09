package com.fleamarket.dao;

import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.BorderTableEntity;

import java.util.List;

public interface IBorderDao {
    int insborder(BookTableEntity border, String username);
    List<BorderTableEntity> allborder(int ppage, int page,String username,String hidden);
    int upborder(String id,String name);
}
