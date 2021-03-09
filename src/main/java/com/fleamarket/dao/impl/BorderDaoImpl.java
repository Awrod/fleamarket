package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IBorderDao;
import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.BorderTableEntity;
import com.fleamarket.domain.UserTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.naming.Name;
import java.awt.print.Book;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BorderDaoImpl implements IBorderDao {
    @Override
    public int insborder(BookTableEntity border, String username) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO border_table(BorderbName,BorderbokName,BorderSubarea,BorderState,BorderTime) VALUES(?,?,?,?,?)";
        int update = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            update = qr.update(sql,username,border.getBookName(),border.getBookSubarea(),"借阅中",df.format(new Date()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BookTableEntity mes=bookq(border.getBookId(),1,null);
        int q=mes.getBookQuantity();
        if (mes.getBookQuantity()>0){
            q-=1;
            bookcut(border.getBookId(),q,1,null);
        }
        return update;
    }

    @Override
    public List<BorderTableEntity> allborder(int ppage, int page,String username,String hidden) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        List<BorderTableEntity> result=null;
        String sql=null;
        if ("rebook".equals(hidden)){
            sql="select * from border_table LIMIT ?,?";
        }else {
            sql="select * from border_table where BorderbName=? LIMIT ?,?";
        }
        try {
            if ("rebook".equals(hidden)){
                result = qr.query(sql,new BeanListHandler<BorderTableEntity>(BorderTableEntity.class),ppage,page);
            }else {
                result = qr.query(sql,new BeanListHandler<BorderTableEntity>(BorderTableEntity.class),username,ppage,page);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int upborder(String id,String name) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE border_table SET BorderState=?  WHERE BorderId=?";
        int update = 0;
        try {
            update = qr.update(sql,"已归还",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BookTableEntity mes=bookq(Integer.parseInt(id),2,name);
        bookcut(1,mes.getBookQuantity()+1,2,name);
        return update;
    }

    public BookTableEntity bookq(int id, int i, String name){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        BookTableEntity result=null;
        String sql=null;
        if (i>1){
            sql="select * from book_table where BookName=?";
        }else{
            sql="select * from book_table where BookId=?";
        }
        try {
            if (i>1){
                result = qr.query(sql,new BeanHandler<BookTableEntity>(BookTableEntity.class),name);

            }else{
                result = qr.query(sql,new BeanHandler<BookTableEntity>(BookTableEntity.class),id);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public  int bookcut(int id,int q,int i,String name){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql=null;
        if (i>1){
            sql="UPDATE book_table SET BookQuantity=?  WHERE BookName=?";
        }else{
            sql="UPDATE book_table SET BookQuantity=?  WHERE BookId=?";
        }
        int update = 0;
        try {
            if (i>1){
                update = qr.update(sql,q,name);

            }else{
                update = qr.update(sql,q,id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
