package com.fleamarket.controller;

import com.fleamarket.domain.FavoritesTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.service.IFavoritesService;
import com.fleamarket.service.IUserService;
import com.fleamarket.service.impl.FavoritesServiceImpl;
import com.fleamarket.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/FavoritesServlet.do")
public class FavoritesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if("showfavorites".equals(hidden)){
            showfavorites(request,response);
        }else if("Favorites".equals(hidden)){
            Favorites(request,response);
        }else if("delfavo".equals(hidden)){
            delfavo(request,response);
        }
    }
    //移除收藏
    private void delfavo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        IFavoritesService ifavo=new FavoritesServiceImpl();
        ifavo.delFavorites(Integer.parseInt(request.getParameter("id")));
        showfavorites(request,response);
    }

    //加入收藏夹
    private void Favorites(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        FavoritesTableEntity favo=new FavoritesTableEntity();
        try {
            BeanUtils.populate(favo,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session=request.getSession();
        favo.setFavoritesUserName((String)session.getAttribute("username"));
        IFavoritesService ifavo=new FavoritesServiceImpl();
        int msg=ifavo.insterFavorites(favo);
        String message=null;
        if (msg>0){
            message="加入成功";
        }else {
            message="已加入收藏夹！";
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/usershowgoods.jsp';</script>)");
    }
    //显示收藏夹内容
    private void showfavorites(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<FavoritesTableEntity> favo=null;
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IFavoritesService ifavo=new FavoritesServiceImpl();
        favo=ifavo.showfavo(username);
        request.setAttribute("fvaolist",favo);
        request.getRequestDispatcher("/showfavorites.jsp").forward(request, response);
    }
}
