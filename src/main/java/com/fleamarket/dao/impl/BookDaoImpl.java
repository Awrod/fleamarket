package com.fleamarket.dao.impl;

import com.fleamarket.dao.DataSourceUtils;
import com.fleamarket.dao.IBookDao;
import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.UserTableEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements IBookDao {
    @Override
    public int addbook(BookTableEntity book, String path, String username) {
        BookTableEntity msg=findbook(book.getBookName(),book.getBookType());
        if (msg != null){
            return  0;
        }
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO book_table(BookName,BookType,BookSubarea,BookQuantity,BookPhoto,BookSynopsis,BookState) VALUES(?,?,?,?,?,?,?)";
        int update = 0;
        try {
            update = qr.update(sql,book.getBookName(),book.getBookType(),book.getBookSubarea(),book.getBookQuantity(),path,book.getBookSynopsis(),"可借阅");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    public BookTableEntity findbook(String name,String type) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        BookTableEntity result=null;
        String sql="select * from book_table where BookName=? ";
        try {
            result = qr.query(sql,new BeanHandler<BookTableEntity>(BookTableEntity.class),name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    @Override
    public List<BookTableEntity> allbook(int ppage, int page) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        List<BookTableEntity> result=null;
        String sql="select * from book_table LIMIT ?,?";
        try {
            result = qr.query(sql,new BeanListHandler<BookTableEntity>(BookTableEntity.class),ppage,page);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int delbook(String id) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from book_table where BookId = ?";
        int update = 0;
        try {
            update = qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
