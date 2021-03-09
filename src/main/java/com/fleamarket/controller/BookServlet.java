package com.fleamarket.controller;

import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;
import com.fleamarket.service.IBookService;
import com.fleamarket.service.IUserService;
import com.fleamarket.service.impl.BookServiceImpl;
import com.fleamarket.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/BookServlet.do")
@MultipartConfig(location="D:\\fleamarketimage",maxFileSize=1024*1024*30,maxRequestSize=40*1024*1024, fileSizeThreshold=1024*3)
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if("bookup".equals(hidden)){
            bookup(request,response);
        }else if("allbook".equals(hidden)){
            int page =Integer.parseInt(request.getParameter("page")) ;
            allbook(request,response,null,page);
        }else if("adownbook".equals(hidden)){
            adownbook(request,response);
        }else if("lendbook".equals(hidden)){
            int page =Integer.parseInt(request.getParameter("page")) ;
            allbook(request,response,hidden,page);
        }
    }

    private void adownbook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        String id=request.getParameter("bookId");
        IBookService ibook=new BookServiceImpl();
        int mes=ibook.delbook(id);
        if(mes>0) {
            request.setAttribute("mes","下架书籍成功！");
            allbook(request,response,null,0);
        }else {
            request.setAttribute("mes","下架书籍失败！");
            allbook(request,response,null,0);
        }
    }

    private void allbook(HttpServletRequest request, HttpServletResponse response,String hidden, int page)throws ServletException, IOException {
        IBookService ibook=new BookServiceImpl();
        List<BookTableEntity> booklist=ibook.allbook(0,99);
        int pages=0;
        for (BookTableEntity or:booklist){
            pages+=1;
        }
        if (pages%5 !=0){
            pages=(pages/5)+1;
        }else {
            pages=(pages/5);
        }
        booklist=ibook.allbook(page,20);
        request.setAttribute("page",pages);
        request.setAttribute("booklist",booklist);
        if ("lendbook".equals(hidden)){
            request.getRequestDispatcher("/lendbook.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/adminbookdown.jsp").forward(request, response);
        }
    }

    private void bookup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String,String[]> map=request.getParameterMap();
        BookTableEntity book=new BookTableEntity();
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        String path=savephoto(request,response,book.getBookName(),username);
        IBookService ibook=new BookServiceImpl();
        int mes=ibook.addbook(book,path,username);
        if(mes>0) {
            request.setAttribute("mes","上架书籍成功！");
            request.getRequestDispatcher("/adminbookup.jsp").forward(request, response);
        }else {
            request.setAttribute("mes","上架书籍失败,已存在！");
            request.getRequestDispatcher("/adminbookup.jsp").forward(request, response);
        }
    }

    //保存商品图片
    private String savephoto(HttpServletRequest request, HttpServletResponse response,String goodsname,String username)throws ServletException, IOException {
        Date date=new Date();
        String photoname=goodsname+username+date.getTime()+".jpg";
        Part part=request.getPart("bookPhoto");
        String path="D:\\fleamarketimage\\goodsphoto\\"+photoname;
        part.write(path);
        String path2="/fleamarketimage/goodsphoto/"+photoname;
        return path2;
    }
}
