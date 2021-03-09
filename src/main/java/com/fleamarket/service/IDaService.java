package com.fleamarket.service;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;

import java.util.List;

public interface IDaService {
    List<DaTableEntity> alladdr(String username);
    int insaddr(DaTableEntity da);
    int updateaddr(DaTableEntity da);
    int deladdr(String daid);
}
