package com.fleamarket.controller;

import com.fleamarket.domain.CommentTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.UserTableEntity;
import com.fleamarket.service.CommentService;
import com.fleamarket.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/CommentServlet.do")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if ("delcom".equals(hidden)) {
            delcom(request,response);
        }
    }

    private void delcom(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        CommentService commentService=new CommentServiceImpl();
        commentService.delcom(Integer.parseInt(request.getParameter("delid")));
        HttpSession session=request.getSession();
        GoodsTableEntity goods=(GoodsTableEntity)session.getAttribute("goods");
        UserTableEntity level=(UserTableEntity)session.getAttribute("level");
        session.setAttribute("goods",goods);
        request.setAttribute("goods",goods);
        session.setAttribute("level",level);
        request.setAttribute("level",level);
        List<CommentTableEntity> comlist=findcom(request,response,goods.getGoodsId());
        request.setAttribute("comlist",comlist);
        session.setAttribute("comlist",comlist);
        request.getRequestDispatcher("/usershowgoods.jsp").forward(request, response);

    }

    public void inscom(HttpServletRequest request, HttpServletResponse response,String commain,int goodsid) throws ServletException, IOException{
        String username=(String)request.getSession().getAttribute("username");
        CommentService commentService=new CommentServiceImpl();
        commentService.inscom(commain,goodsid,username);
    }
    public List<CommentTableEntity> findcom(HttpServletRequest request, HttpServletResponse response, int goodsid) throws ServletException, IOException{
        CommentService commentService=new CommentServiceImpl();
        List<CommentTableEntity> comlist=commentService.findcom(goodsid);
        return comlist;
    }
}
