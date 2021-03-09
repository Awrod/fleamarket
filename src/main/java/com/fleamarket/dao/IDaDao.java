package com.fleamarket.dao;

import com.fleamarket.domain.DaTableEntity;

import java.util.List;

public interface IDaDao {
    List<DaTableEntity> alladdr(String username);
    int insaddr(DaTableEntity da);
    int updateaddr(DaTableEntity da);
    int deladdr(String daid);
}
