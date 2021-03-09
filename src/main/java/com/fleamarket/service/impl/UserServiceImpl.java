package com.fleamarket.service.impl;

import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;
import com.fleamarket.service.IUserService;

import java.sql.SQLException;
import java.util.List;
public class UserServiceImpl implements IUserService {
    @Override
    public int register(UserTableEntity user) {
        if(user == null)
            return -1;
        IUserDao uDao = new UserDaoImpl();
        int msg = uDao.adduser(user);
        return msg;
    }


    @Override
    public int updategoods(GoodsTableEntity goods,String username) {
        if(goods == null)
            return -1;
        IUserDao uDao = new UserDaoImpl();
        int msg = uDao.updategoods(goods,username);
        return msg;
    }

    @Override
    public int fzuser(int id,String hidden) {
        IUserDao uDao = new UserDaoImpl();
        int msg = uDao.fzuser(id,hidden);
        return msg;
    }

    @Override
    public int updateinfo(UserTableEntity user) {
        if(user == null)
            return -1;
        IUserDao uDao = new UserDaoImpl();
        int msg = uDao.updateinfo(user);
        return msg;
    }

    @Override
    public int findusername(String username) {
        IUserDao uDao = new UserDaoImpl();
        List<UserTableEntity> result = uDao.findusername(username);
        int msg = 0;
        if(result.isEmpty()){
            msg = 0;
        }else {
            msg = 1;
        }
        return msg;
    }


    @Override
    public int admindel(String id) {
        if(id == null)
            return -1;
        IUserDao uDao = new UserDaoImpl();
        int msg =uDao.admindel(id);
        return msg;
    }

    @Override
    public int checkuser(String username, String userpass) {
        IUserDao uDao = new UserDaoImpl();
        List<UserTableEntity> result = uDao.checkuser(username,userpass);
        int msg = 1;
        if(!result.isEmpty()){
            msg = 0;
        }else {
            msg = 1;
        }
        return msg;
    }

    @Override
    public int insterimgpath(String path,String username) {
        IUserDao uDao = new UserDaoImpl();
        int msg = uDao.insterimgpath(path,username);
        return msg;
    }

    @Override
    public UserTableEntity showinfo(String username) {
        IUserDao uDao = new UserDaoImpl();
        UserTableEntity result= null;
        try {
            result = uDao.showinfo(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }



    @Override
    public List<UserTableEntity> alluser(int ppage,int page) {
        IUserDao uDao = new UserDaoImpl();
        List<UserTableEntity> userlist=uDao.alluser(ppage,page);
        return userlist;
    }



    @Override
    public List<GoodsTableEntity> selallgoods(String username) {
        IUserDao uDao = new UserDaoImpl();
        List<GoodsTableEntity> listgoods=uDao.selallgoods(username);
        return listgoods;
    }

    @Override
    public int storelevelup(String level, String storeusername) {
        IUserDao uDao = new UserDaoImpl();
        int msg = uDao.storelevelup(level,storeusername);
        return msg;
    }


}
