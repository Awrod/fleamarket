package com.fleamarket.service;

import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.BorderTableEntity;
import com.fleamarket.domain.UserTableEntity;

import java.util.List;

public interface IBorderService {
    int insborder(BookTableEntity border, String username);
    int upborder(String id,String name);
    List<BorderTableEntity> allborder(int ppage, int page,String username,String hidden);
}
