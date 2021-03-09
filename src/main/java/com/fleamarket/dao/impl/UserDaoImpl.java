package com.fleamarket.dao.impl;

import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public int updategoods(GoodsTableEntity goods,String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE goods_table SET GoodsPhoto=?,GoodsName=?,GoodsType=?,GoodsQuantity=?,GoodsSynopsis=?,GoodsState=?,GoodsPrice=?,UserName=?  WHERE GoodsId=?";
        int update = 0;
        try {
            update = qr.update(sql,goods.getGoodsPhoto(),goods.getGoodsName(),goods.getGoodsType(),goods.getGoodsQuantity(),goods.getGoodsSynopsis(),"up",goods.getGoodsPrice(),username,goods.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    @Override
    public int admindel(String id) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from user_table where userId = ?";
        int update = 0;
        try {
            update = qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int adduser(UserTableEntity user) {
        List<UserTableEntity> userlist=findusername(user.getUserName());
        int update = 0;
        if (userlist.size()==0){
            QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
            String sql=null;
            if("卖家".equals(user.getUserType())){
                sql= "INSERT INTO user_table(userName,userPass,userSex,userMoblie,userType,userState,userLevel,userReallyn,useridcard) VALUES(?,?,?,?,?,?,?,?,?)";
            }else {
                sql = "INSERT INTO user_table(userName,userPass,userSex,userMoblie,userType,userState,userReallyn,useridcard) VALUES(?,?,?,?,?,?,?,?)";
            }
            try {
                if("卖家".equals(user.getUserType())){
                    update = qr.update(sql, user.getUserName(),user.getUserPass(),user.getUserSex(),user.getUserMoblie(),user.getUserType(),"normal",5,user.getUserReallyn(),user.getUseridcard());
                }else {
                    update = qr.update(sql, user.getUserName(),user.getUserPass(),user.getUserSex(),user.getUserMoblie(),user.getUserType(),"normal",user.getUserReallyn(),user.getUseridcard());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update;

    }

    @Override
    public int updateinfo(UserTableEntity user) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE user_table SET userPass=?,userSex=?,userMoblie=?,userReallyn=?,useridcard=?  WHERE userName=?";
        int update = 0;
        try {
            update = qr.update(sql,user.getUserPass(),user.getUserSex(),user.getUserMoblie(),user.getUserReallyn(),user.getUseridcard(),user.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int fzuser(int id,String hidden) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE user_table SET userState=?  WHERE userId=?";
        int update = 0;
        try {
            if ("unfreeze".equals(hidden)){
                update = qr.update(sql,"normal",id);
            }else {
                update = qr.update(sql,"freeze",id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<UserTableEntity> findusername(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user_table where userName=?";
        List<UserTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<UserTableEntity>(UserTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public List<UserTableEntity> checkuser(String username, String userpass) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user_table where userName=? and userPass=?";
        List<UserTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<UserTableEntity>(UserTableEntity.class),username,userpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int insterimgpath(String path,String username) {
        List<UserTableEntity> result = findusername(username);
        int msg = 0;
        int update = 0;
        if(result.isEmpty()){
            msg = 0;
        }else {
            msg = 1;
        }
        if (msg==1){
            QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "UPDATE user_table SET userPhoto=? WHERE userName=?";
            try {
                update = qr.update(sql,path,username);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return update;
    }


    @Override
    public UserTableEntity showinfo(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        UserTableEntity result=null;
        String sql="select * from user_table where userName=?";
        try {
            result = qr.query(sql,new BeanHandler<UserTableEntity>(UserTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<UserTableEntity> alluser(int ppage,int page) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        List<UserTableEntity> result=null;
        String sql="select * from user_table LIMIT ?,?";
        try {
            result = qr.query(sql,new BeanListHandler<UserTableEntity>(UserTableEntity.class),ppage,page);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<GoodsTableEntity>selallgoods(String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from goods_table where UserName=?";
        List<GoodsTableEntity> result=null;
        try {
            result = qr.query(sql,new BeanListHandler<GoodsTableEntity>(GoodsTableEntity.class),username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int storelevelup(String level, String storeusername) {
        List<UserTableEntity> list=  findusername(storeusername);
        UserTableEntity user =new UserTableEntity();
        for (UserTableEntity us:list){
            user=us;
        }
        double lebelt=0;
        if (user.getUserLevel()>Integer.parseInt(level)){
            lebelt=user.getUserLevel()-(Integer.parseInt(level)*0.25);
        }else {
            lebelt=user.getUserLevel()+(Integer.parseInt(level)*0.25);
        }
        if (lebelt>=5){
            lebelt=5;
        }
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE user_table SET userLevel=?  WHERE userName=?";
        int update = 0;
        try {
            update = qr.update(sql,lebelt,storeusername);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }


}
