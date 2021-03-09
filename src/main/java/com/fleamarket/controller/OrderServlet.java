package com.fleamarket.controller;

import com.fleamarket.dao.IOrderDao;
import com.fleamarket.dao.impl.OrderDaoImpl;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.domain.GoodsTableEntity;
import com.fleamarket.domain.OrderTableEntity;
import com.fleamarket.domain.ShoppingcartTableEntity;
import com.fleamarket.service.IOrderService;
import com.fleamarket.service.impl.OrderServiceImpl;
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

@WebServlet("/OrderServlet.do")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if("buygoods".equals(hidden)){
            buygoods(request,response);
        }else if("scbuygoods".equals(hidden)){
            scbuygoods(request,response);
        }else if("handleorder".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage"));
            handleorder(request,response,ppage);
        }else if("uporder".equals(hidden)){
            uporder(request,response);
        }else if("ordercheck".equals(hidden)){
            ordercheck(request,response,0,0,0,5,"ordercheck");
        }else if("page".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage1"));
            int ppage2=Integer.parseInt(request.getParameter("ppage2"));
            int ppage3=Integer.parseInt(request.getParameter("ppage3"));
            int npage=Integer.parseInt(request.getParameter("npage"));
            ordercheck(request,response,ppage,ppage2,ppage3,npage,"ordercheck");
        }else if("useruporder".equals(hidden)){
            useruporder(request,response);
        }else if("cptorder".equals(hidden)){
            cptorder(request,response,hidden);
        }else if("delorder".equals(hidden)){
            delorder(request,response,hidden);
        }else if("sdelorder".equals(hidden)){
            sdelorder(request,response,hidden);
        }else if("Torder".equals(hidden)){
            Torder(request,response,hidden);
        }
    }
//商家确认退货
    private void Torder(HttpServletRequest request, HttpServletResponse response, String hidden) throws ServletException, IOException{
        String id=request.getParameter("orderId");
        String goodsid=request.getParameter("goodsId");
        String ordergQuantity=request.getParameter("ordergQuantity");
        IOrderService iorder=new OrderServiceImpl();
        iorder.Tporder(id,goodsid,ordergQuantity);
        handleorder(request,response,0);
    }

    //商家查看售后
    private void sdelorder(HttpServletRequest request, HttpServletResponse response, String hidden)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        IOrderService iorder=new OrderServiceImpl();
        List<OrderTableEntity> orderlist=iorder.checkorder(username,"sdelorder",0,5);
        request.setAttribute("orderlist",orderlist);
        request.getRequestDispatcher("/sdelorder.jsp").forward(request, response);
    }

    //退货
    private void delorder(HttpServletRequest request, HttpServletResponse response, String hidden)throws ServletException, IOException  {
        String id=request.getParameter("orderId");
        IOrderService iorder=new OrderServiceImpl();
        iorder.delorder(id);
        ordercheck(request,response,0,0,0,5,"ordercheck");
    }

    //已完成订单
    private void cptorder(HttpServletRequest request, HttpServletResponse response,String hidden)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        IOrderService iorder=new OrderServiceImpl();
        List<OrderTableEntity> orderlist=null;
        if ("cptorder".equals(hidden)){
            orderlist=iorder.cptorder(username,hidden);
        }
        request.setAttribute("orderlistx",orderlist);
        request.getRequestDispatcher("/sellercptorder.jsp").forward(request, response);
    }

    //用户确认收货
    private void useruporder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String id=request.getParameter("orderId");
        String storeusername=request.getParameter("storeusername");
        IOrderService iorder=new OrderServiceImpl();
        iorder.useruporder(id);
        HttpSession session=request.getSession();
        session.setAttribute("storeusername",storeusername);
        request.setAttribute("goodsid",request.getParameter("orderGid"));
        request.getRequestDispatcher("/storelevel.jsp").forward(request, response);
       // ordercheck(request,response,0,0,0,5,"ordercheck");
    }

    //用户查看/处理订单
    public void ordercheck(HttpServletRequest request, HttpServletResponse response,int ppage,int ppage2,int ppage3,int npage,String hidden)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        IOrderService iorder=new OrderServiceImpl();
        if ("ordercheck".equals(hidden)){
            List<OrderTableEntity> orderlistshipped=iorder.checkorder1(username,null,0,0);
            List<OrderTableEntity> orderlistdispatched=iorder.checkorder2(username,null,0,0);
            List<OrderTableEntity> orderlistover=iorder.checkorder3(username,null,0,0);
            int page1=0;
            for (OrderTableEntity or:orderlistshipped){
                page1+=1;
            }
            if (page1%5 !=0){
                page1=(page1/5)+1;
            }else {
                page1=(page1/5);
            }
            int page2=0;
            for (OrderTableEntity or:orderlistdispatched){
                page2+=1;
            }
            if (page2%5 !=0){
                page2=(page2/5)+1;
            }else {
                page2=(page2/5);
            }
            int page3=0;
            for (OrderTableEntity or:orderlistover){
                page3+=1;
            }
            if (page3%5 !=0){
                page3=(page3/5)+1;
            }else {
                page3=(page3/5);
            }
            orderlistshipped=iorder.checkorder1(username,null,(ppage),npage);
            orderlistdispatched=iorder.checkorder2(username,null,(ppage2),npage);
            orderlistover=iorder.checkorder3(username,null,(ppage3),npage);
            request.setAttribute("page1",page1);
            request.setAttribute("orderlistshipped",orderlistshipped);
            request.setAttribute("page2",page2);
            request.setAttribute("orderlistdispatched",orderlistdispatched);
            request.setAttribute("page3",page3);
            request.setAttribute("orderlistover",orderlistover);
        }else {
            List<OrderTableEntity> orderlist=iorder.checkorder(username,null,0,0);
            int page=0;
            for (OrderTableEntity or:orderlist){
                page+=1;
            }
            if (page%5 !=0){
                page=(page/5)+1;
            }else {
                page=(page/5);
            }
            orderlist=iorder.checkorder(username,null,(ppage),npage);
            request.setAttribute("page",page);
            request.setAttribute("orderlist",orderlist);
        }
        request.getRequestDispatcher("/checkorder.jsp").forward(request, response);
    }

    //发货
    private void uporder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String id=request.getParameter("orderId");
        IOrderService iorder=new OrderServiceImpl();
        iorder.uporder(id);
        handleorder(request,response,0);
    }
//处理未完成订单
    private void handleorder(HttpServletRequest request, HttpServletResponse response,int ppage)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        IOrderService iorder=new OrderServiceImpl();
        List<OrderTableEntity> orderlist=iorder.handleorder(username,ppage,99);
        int page=0;
        for (OrderTableEntity or:orderlist){
            page+=1;
        }
        if (page%5 !=0){
            page=(page/5)+1;
        }else {
            page=(page/5);
        }
        orderlist=iorder.handleorder(username,ppage,5);
        request.setAttribute("page",page);
        request.setAttribute("orderlist",orderlist);
        request.getRequestDispatcher("/handleorder.jsp").forward(request, response);
    }
//添加订单信息
    private void buygoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        DaTableEntity da=new DaTableEntity();
        GoodsTableEntity goods=new GoodsTableEntity();
        OrderTableEntity or=new OrderTableEntity();
        HttpSession session=request.getSession();
        da.setDaUserName((String)session.getAttribute("username"));
        try {
            BeanUtils.populate(da,map);
            BeanUtils.populate(goods,map);
            BeanUtils.populate(or,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        IOrderService iorder=new OrderServiceImpl();
        int msg=iorder.insorder(da,goods,da.getDaUserName(),or);
        goods=(GoodsTableEntity) session.getAttribute("goods");
        if (goods.getGoodsQuantity()==or.getOrdergQuantity()){
            msg=2;
        }
        String message=null;
        if (msg>0){
            message="购买成功!";
            goods.setGoodsQuantity(goods.getGoodsQuantity()-or.getOrdergQuantity());
            session.setAttribute("goods",goods);
        }else {
            message="失败！未填写收货地址";
            session.setAttribute("goods",goods);
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        if (msg==2){
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }else {
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/usershowgoods.jsp';</script>)");
        }
    }
    //添加订单信息
    private void scbuygoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
        IOrderService iorder=new OrderServiceImpl();
        int msg=iorder.scinsorder(da,(List<ShoppingcartTableEntity>) request.getSession().getAttribute("shopli"));
        String message=null;
        if (msg>0){
            message="购买成功!";
        }else {
            message="失败！未填写收货地址";
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        if (msg>0){
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }else {
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }
    }
}
