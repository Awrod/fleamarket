package com.fleamarket.dao;

import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    int updategoods(GoodsTableEntity goods,String username);
    int admindel(String id);
    int adduser(UserTableEntity user);
    int updateinfo(UserTableEntity user);
    int fzuser(int id,String hidden);
    List<UserTableEntity> findusername(String username);
    List<UserTableEntity> checkuser(String username,String userpass);
    int insterimgpath(String path,String username);
    UserTableEntity showinfo(String username) throws SQLException;
    List<UserTableEntity> alluser(int ppage,int page);
    List<GoodsTableEntity> selallgoods(String username);
    int storelevelup(String level,String storeusername);

}
