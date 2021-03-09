package com.fleamarket.controller;

import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.ShoppingcartTableEntity;
import com.fleamarket.service.IShoppingCartService;
import com.fleamarket.service.impl.ShoppingCartServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/ShoppingCartServlet.do")
public class ShoppingCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if ("inssho".equals(hidden)){
            inssho(request,response);
        }else if ("showshop".equals(hidden)) {
            showshop(request, response);
        }else if ("delshop".equals(hidden)) {
            delshop(request, response);
        }
    }

    private void delshop(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        IShoppingCartService ishop=new ShoppingCartServiceImpl();
        ishop.del(Integer.parseInt(request.getParameter("id")));
        showshop(request,response);
    }

    private void showshop(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        IShoppingCartService ishop=new ShoppingCartServiceImpl();
        List<ShoppingcartTableEntity> shoplist=ishop.showshop((String)request.getSession().getAttribute("username"));
        request.getSession().setAttribute("shoplist",shoplist);
        request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
    }
    private void inssho(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        ShoppingcartTableEntity shoping=new ShoppingcartTableEntity();
        try {
            BeanUtils.populate(shoping,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        shoping.setShoppingUsername((String) request.getSession().getAttribute("username"));
        IShoppingCartService ishop=new ShoppingCartServiceImpl();
        int msg=ishop.insshop(shoping);
        String message=null;
        if (msg>0){
            message="加入成功";
        }else {
            message="已加入购物车！";
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/usershowgoods.jsp';</script>)");
    }
}
