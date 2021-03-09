package com.fleamarket.service.impl;

import com.fleamarket.dao.IFavoritesDao;
import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.FavoritesDaoImpl;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.service.IFavoritesService;

import java.util.List;

public class FavoritesServiceImpl implements IFavoritesService {
    @Override
    public List<FavoritesTableEntity> showfavo(String username) {
        IFavoritesDao fDao = new FavoritesDaoImpl();
        List<FavoritesTableEntity> result= null;
        result = fDao.showfavo(username);
        return result;
    }
    @Override
    public int insterFavorites(FavoritesTableEntity fvao) {
        IFavoritesDao fDao = new FavoritesDaoImpl();
        int msg = fDao.insterFavorites(fvao);
        return msg;
    }

    @Override
    public int delFavorites(int fvaoid) {
        IFavoritesDao fDao = new FavoritesDaoImpl();
        int msg = fDao.delFavorites(fvaoid);
        return msg;
    }


}
