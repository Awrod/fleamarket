package com.fleamarket.controller;

import com.fleamarket.domain.BookTableEntity;
import com.fleamarket.domain.BorderTableEntity;
import com.fleamarket.domain.UserTableEntity;
import com.fleamarket.service.IBorderService;
import com.fleamarket.service.IOrderService;
import com.fleamarket.service.IUserService;
import com.fleamarket.service.impl.BorderServiceImpl;
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

@WebServlet("/BorderServlet.do")
public class BorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if("ulendbook".equals(hidden)){
            ulendbook(request,response);
        }else if("cheborder".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage"));
            cheborder(request,response,hidden,ppage);
        }else if("rebook".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage"));
            cheborder(request,response,hidden,ppage);
        }else if("cptborder".equals(hidden)){
            cptborder(request,response);
        }
    }

    private void cptborder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        String id=request.getParameter("id");
        String bname=request.getParameter("bname");
        IBorderService iborder=new BorderServiceImpl();
        iborder.upborder(id,bname);
        cheborder(request,response,"rebook",0);
    }

    private void cheborder(HttpServletRequest request, HttpServletResponse response,String hidden,int page)throws ServletException, IOException {
        IBorderService iborder=new BorderServiceImpl();
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        List<BorderTableEntity> borderlist=iborder.allborder(0,99,username,hidden);
        int pagess=0;
        for (BorderTableEntity or:borderlist){
            pagess+=1;
        }
        if (pagess%5 !=0){
            pagess=(page/5)+1;
        }else {
            pagess=(page/5);
        }
        borderlist=iborder.allborder(page,5,username,hidden);
        request.setAttribute("page",pagess);
        request.setAttribute("borderlist",borderlist);
        if ("cheborder".equals(hidden)){
            request.getRequestDispatcher("/border.jsp").forward(request, response);
        }else if ("rebook".equals(hidden)){
            request.getRequestDispatcher("/adminrt.jsp").forward(request, response);
        }
    }

    private void ulendbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String,String[]> map=request.getParameterMap();
        BookTableEntity border=new BookTableEntity();
        try {
            BeanUtils.populate(border,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IBorderService iborder=new BorderServiceImpl();
        int mes =iborder.insborder(border,username);
        String message=null;
        if(mes>0) {
            message="借阅成功！";
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }else {
            message="借阅失败！";
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }
    }
}
