package com.fleamarket.service;

import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;

import java.util.List;

public interface IUserService {
    int register(UserTableEntity user);
    int updategoods(GoodsTableEntity goods,String username);
    int fzuser(int id,String hidden);
    int updateinfo(UserTableEntity user);
    int findusername(String username);
    int admindel(String id);
    int checkuser(String username,String userpass);
    int insterimgpath(String path,String username);
    UserTableEntity showinfo(String username);
    List<UserTableEntity> alluser(int ppage,int page);
    List<GoodsTableEntity> selallgoods(String username);
    int storelevelup(String level,String storeusername);
}
