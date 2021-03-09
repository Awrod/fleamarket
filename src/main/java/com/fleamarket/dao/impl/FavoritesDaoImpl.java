package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IFavoritesDao;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.service.IFavoritesService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class FavoritesDaoImpl implements IFavoritesDao {
    @Override
    public List<FavoritesTableEntity> showfavo(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        List<FavoritesTableEntity> result=null;
        String sql="select * from favorites_table where FavoritesUserName=?";
        try {
            result = qr.query(sql,new BeanListHandler<FavoritesTableEntity>(FavoritesTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public List<FavoritesTableEntity> findfvo(int id,String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from favorites_table where GoodsId=? and FavoritesUserName=?";
        List<FavoritesTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<FavoritesTableEntity>(FavoritesTableEntity.class),id,username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int delFavorites(int fvaoid) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from favorites_table where FavoritesId = ?";
        int update = 0;
        try {
            update = qr.update(sql,fvaoid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int insterFavorites(FavoritesTableEntity fvao) {
        List<FavoritesTableEntity> result = findfvo(fvao.getGoodsId(),fvao.getFavoritesUserName());
        int msg = 0;
        int update = 0;
        if(result.size()==0){
            msg = 1;
        }else {
            msg = 0;
        }
        if (msg==1){
            QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "INSERT INTO favorites_table(GoodsName,GoodsPhoto,FavoritesUserName,GoodsId) VALUES(?,?,?,?)";
            try {
                update = qr.update(sql,fvao.getGoodsName(),fvao.getGoodsPhoto(),fvao.getFavoritesUserName(),fvao.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update;
    }

}
