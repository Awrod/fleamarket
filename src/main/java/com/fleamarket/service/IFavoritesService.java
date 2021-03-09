package com.fleamarket.service;

import com.fleamarket.domain.FavoritesTableEntity;

import java.util.List;

public interface IFavoritesService {
    List<FavoritesTableEntity> showfavo(String username);
    int insterFavorites(FavoritesTableEntity fvao);
    int delFavorites(int fvaoid);
}
