package com.fleamarket.controller;

import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.service.IDaService;
import com.fleamarket.service.IUserService;
import com.fleamarket.service.impl.DaServiceImpl;
import com.fleamarket.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/DaServlet.do")
public class DaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if("conaddr".equals(hidden)){
            conaddr(request,response);
        }else if("addaddrtow".equals(hidden)){
            addaddrtow(request,response);
        }else if("upaddr".equals(hidden)){
            upaddr(request,response);
        }else if("updateaddr".equals(hidden)){
            updateaddr(request,response);
        }else if("deladdr".equals(hidden)){
            deladdr(request,response);
        }else if("addaddr".equals(hidden)){
            request.setAttribute("hidden","addaddrtow");
            request.getRequestDispatcher("/addaddr.jsp").forward(request, response);
        }
    }

    private void deladdr(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        String daid=request.getParameter("daId");
        IDaService daService=new DaServiceImpl();
        int msg=daService.deladdr(daid);
        if (msg>0){
            request.setAttribute("msg","???????????????");
            conaddr(request,response);
        }else {
            request.setAttribute("msg","???????????????");
            conaddr(request,response);
        }
    }

    //??????????????????
    private void updateaddr(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        DaTableEntity da=new DaTableEntity();
        HttpSession session=request.getSession();
        da.setDaUserName((String)session.getAttribute("username"));
        try {
            BeanUtils.populate(da,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        IDaService daService=new DaServiceImpl();
        int msg=daService.updateaddr(da);
        if (msg>0){
            request.setAttribute("msg","???????????????");
            conaddr(request,response);
        }else {
            request.setAttribute("msg","???????????????");
            conaddr(request,response);
        }
    }

    //??????????????????????????????
    private void upaddr(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        Map<String,String[]> map=request.getParameterMap();
        DaTableEntity da=new DaTableEntity();
        HttpSession session=request.getSession();
        da.setDaUserName((String)session.getAttribute("username"));
        try {
            BeanUtils.populate(da,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        request.setAttribute("hidden","updateaddr");
        request.setAttribute("id",da.getDaId());
        request.setAttribute("name",da.getDaName());
        request.setAttribute("addr",da.getUserAddr());
        request.setAttribute("moblie",da.getUserMoblie());
        request.getRequestDispatcher("/addaddr.jsp").forward(request, response);
    }

    //?????????????????????
    private void addaddrtow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        DaTableEntity da=new DaTableEntity();
        HttpSession session=request.getSession();
        da.setDaUserName((String)session.getAttribute("username"));
        try {
            BeanUtils.populate(da,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        IDaService daService=new DaServiceImpl();
        int msg=daService.insaddr(da);
        if (msg>0){
            request.setAttribute("msg","???????????????");
            conaddr(request,response);
        }else {
            request.setAttribute("msg","???????????????");
            conaddr(request,response);
        }
    }

    //????????????????????????
    private void conaddr(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IDaService iuser=new DaServiceImpl();
        List<DaTableEntity> dalist=iuser.alladdr(username);
        request.setAttribute("dalist",dalist);
        if (dalist.isEmpty()){
            request.setAttribute("msg","??????????????????");
        }
        request.getRequestDispatcher("/conaddr.jsp").forward(request, response);
    }
    //????????????????????????
    public List<DaTableEntity> readdr(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IDaService iuser=new DaServiceImpl();
        List<DaTableEntity> dalist=iuser.alladdr(username);
        return dalist;
    }

}
