package com.fleamarket.dao;

import com.fleamarket.domain.FavoritesTableEntity;

import java.util.List;

public interface IFavoritesDao {
    List<FavoritesTableEntity> showfavo(String username);
    int insterFavorites(FavoritesTableEntity fvao);
    int delFavorites(int fvaoid);
}
