package com.fleamarket.controller;

import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.*;
import com.fleamarket.service.IGoodsService;
import com.fleamarket.service.impl.GoodsServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet("/GoodsServlet.do")
@MultipartConfig(location="D:\\fleamarketimage",maxFileSize=1024*1024*30,maxRequestSize=40*1024*1024, fileSizeThreshold=1024*3)
public class GoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if("usersel".equals(hidden)){
            usersel (request,response);
        }else if("usershowgoods".equals(hidden)){
            showgoods (request,response,hidden);
        }else if("showgoods".equals(hidden)){
            showgoods (request,response,hidden);
        }else if("buygoods".equals(hidden)){
            buygoods(request,response);
        }else if("seladdr".equals(hidden)){
            seladdr(request,response);
        }else if("scseladdr".equals(hidden)){
            seladdr(request,response);
        }else if("goodstypesel".equals(hidden)){
            goodstypesel (request,response);
        }else if("linkusergoods".equals(hidden)){
            linkusergoods(request,response);
        }else if("seidx".equals(hidden)){
            selallgoods (request,response,null);
        }else if("upgoods".equals(hidden)){
            selallgoods (request,response,"upgoods");
        }else if("delgoods".equals(hidden)){
            selallgoods (request,response,hidden);
        }else if("goodsupdate".equals(hidden)){
            goodsupdate (request,response);
        }else if("updategoods".equals(hidden)){
            updategoods (request,response);
        }else if("goodsdel".equals(hidden)){
            goodsdel(request,response);
        }else if("selleradd".equals(hidden)){
            selleradd(request,response);
        }else if("scbuygoods".equals(hidden)){
            scbuygoods(request,response);
        }
    }
    //商品添加
    private void selleradd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        String path=savephoto(request,response,goods.getGoodsName(),username);
        IGoodsService igoods=new GoodsServiceImpl();
        goods.setGoodsPhotoo(savephotoo(request,response,goods.getGoodsName(),username));
        goods.setGoodsPhotot(savephotot(request,response,goods.getGoodsName(),username));
        goods.setGoodsPhotos(savephotos(request,response,goods.getGoodsName(),username));
        int mes = igoods.selleradd(goods,path,username);
        if(mes>0) {
            request.setAttribute("mes","上架商品成功！");
            request.getRequestDispatcher("/selleradd.jsp").forward(request, response);
        }else {
            request.setAttribute("mes","上架商品失败！");
            request.getRequestDispatcher("/selleradd.jsp").forward(request, response);
        }

    }
    //删除商品
    private void goodsdel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String goodsid=request.getParameter("goodsid");
        IGoodsService igoods=new GoodsServiceImpl();
        int msg=igoods.goodsdel(goodsid);
        if (msg>0){
            request.setAttribute("delmsg","下架成功！");
            selallgoods (request,response,null);
        }else {
            request.setAttribute("delmsg","下架失败！");
            selallgoods (request,response,null);
        }
    }
    //保存商品图片
    private String savephoto(HttpServletRequest request, HttpServletResponse response,String goodsname,String username)throws ServletException, IOException {
        Date date=new Date();
        String photoname=goodsname+username+date.getTime()+".jpg";
        Part part=request.getPart("goodsPhoto");
        String path="D:\\fleamarketimage\\goodsphoto\\"+photoname;
        part.write(path);
        String path2="/fleamarketimage/goodsphoto/"+photoname;
        return path2;
    }
    //保存商品图片
    private String savephotoo(HttpServletRequest request, HttpServletResponse response,String goodsname,String username)throws ServletException, IOException {
        Date date=new Date();
        String photoname=goodsname+username+"o"+date.getTime()+".jpg";
        Part part=request.getPart("goodsPhotoo");
        String path="D:\\fleamarketimage\\goodsphoto\\"+photoname;
        part.write(path);
        String path3="/fleamarketimage/goodsphoto/"+photoname;
        return path3;
    }
    //保存商品图片
    private String savephotot(HttpServletRequest request, HttpServletResponse response,String goodsname,String username)throws ServletException, IOException {
        Date date=new Date();
        String photoname=goodsname+username+"t"+date.getTime()+".jpg";
        Part part=request.getPart("goodsPhotot");
        String path="D:\\fleamarketimage\\goodsphoto\\"+photoname;
        part.write(path);
        String path4="/fleamarketimage/goodsphoto/"+photoname;
        return path4;
    }
    //保存商品图片
    private String savephotos(HttpServletRequest request, HttpServletResponse response,String goodsname,String username)throws ServletException, IOException {
        Date date=new Date();
        String photoname=goodsname+username+"s"+date.getTime()+".jpg";
        Part part=request.getPart("goodsPhotos");
        String path="D:\\fleamarketimage\\goodsphoto\\"+photoname;
        part.write(path);
        String path5="/fleamarketimage/goodsphoto/"+photoname;
        return path5;
    }
    //更新商品信息
    private void updategoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        String path=savephoto(request,response,goods.getGoodsName(),username);
        goods.setGoodsPhotoo(savephotoo(request,response,goods.getGoodsName(),username));
        goods.setGoodsPhotot(savephotot(request,response,goods.getGoodsName(),username));
        goods.setGoodsPhotos(savephotos(request,response,goods.getGoodsName(),username));
        goods.setGoodsPhoto(path);
        IGoodsService igoods=new GoodsServiceImpl();
        int mes = igoods.updategoods(goods,username);
        if(mes>0) {
            request.setAttribute("upmsg","修改商品成功！");
            selallgoods (request,response,null);
        }else {
            request.setAttribute("upmsg","修改商品失败！");
            selallgoods (request,response,null);
        }
    }
    //跳转到商品修改详情页
    private void goodsupdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/sellerupdateshow.jsp").forward(request, response);
    }
    //显示所有商品
    private void selallgoods(HttpServletRequest request, HttpServletResponse response,String hidden)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IGoodsService igoods=new GoodsServiceImpl();
        List<GoodsTableEntity> goodslist=igoods.selallgoods(username);
        if (!goodslist.isEmpty()){
            if("delgoods".equals(hidden)){
                request.setAttribute("goodslist",goodslist);
                request.getRequestDispatcher("/sellerdel.jsp").forward(request, response);
            }else if("upgoods".equals(hidden)) {
                request.setAttribute("goodslist",goodslist);
                request.getRequestDispatcher("/sellerupdate.jsp").forward(request, response);
            }else {
                request.setAttribute("goodslist",goodslist);
                request.getRequestDispatcher("/sellerindex.jsp").forward(request, response);
            }
        }else {
            request.getRequestDispatcher("/sellerindex.jsp").forward(request, response);
        }
    }
    //跳转到商品详情页
    private void linkusergoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String path = request.getParameter("path");
        IGoodsService igoods=new GoodsServiceImpl();
        GoodsTableEntity goods=new GoodsTableEntity();
        goods=igoods.linkusergoods(path);
        HttpSession session=request.getSession();
        IUserDao userDao=new UserDaoImpl();
        List<UserTableEntity> levellist=userDao.findusername(goods.getUserName());
        UserTableEntity level=new UserTableEntity();
        for (UserTableEntity le:levellist){
            level=le;
        }
        CommentServlet com=new CommentServlet();
        List<CommentTableEntity> comlist=com.findcom(request,response,goods.getGoodsId());
        request.setAttribute("comlist",comlist);
        session.setAttribute("comlist",comlist);
        session.setAttribute("goods",goods);
        request.setAttribute("goods",goods);
        session.setAttribute("level",level);
        request.setAttribute("level",level);
        request.getRequestDispatcher("/usershowgoods.jsp").forward(request, response);
    }
    //分类查询商品
    private void goodstypesel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String type=request.getParameter("type");
        IGoodsService igoods=new GoodsServiceImpl();
        List<GoodsTableEntity> goodslist=igoods.typegoodssel(type);
        if (!goodslist.isEmpty()){
            request.setAttribute("goodslist",goodslist);
            request.getRequestDispatcher("/showlikegoods.jsp").forward(request, response);
        }else {
            request.setAttribute("goodslist",goodslist);
            request.setAttribute("selmsg","未找到商品");
            request.getRequestDispatcher("/showlikegoods.jsp").forward(request, response);
        }
    }
    //查询地址
    private void seladdr(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        DaTableEntity da=new DaTableEntity();
        try {
            BeanUtils.populate(da,map);
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DaServlet ds=new DaServlet();
        List<DaTableEntity> dalist=ds.readdr(request,response);
        request.setAttribute("goods",goods);
        request.setAttribute("dalist",dalist);
        request.setAttribute("da",da);
        if ("scseladdr".equals(request.getParameter("hidden"))){
            request.getRequestDispatcher("/scbuygoods.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/buygoods.jsp").forward(request, response);
        }
    }

    //支付页面
    private void buygoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DaServlet ds=new DaServlet();
        List<DaTableEntity> dalist=ds.readdr(request,response);
        request.setAttribute("goods",goods);
        request.setAttribute("dalist",dalist);
        request.getRequestDispatcher("/buygoods.jsp").forward(request, response);
    }
    private void scbuygoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String shoppingGidlist[]=request.getParameterValues("shoppingGid");
        String shoppingGnamelist[]=request.getParameterValues("shoppingGname");
        String shoppingSnamelist[]=request.getParameterValues("shoppingSname");
        String shoppingQuantitylist[]=request.getParameterValues("shoppingQuantity");
        String shoppingPricelist[]=request.getParameterValues("shoppingPrice");
        String spidlist[]=request.getParameterValues("spid");
        String countprice=request.getParameter("countprice");
        String username=(String)request.getSession().getAttribute("username");
        List<ShoppingcartTableEntity> shop=new ArrayList<>();
        int num=0;
        for (int i=0;i<shoppingGidlist.length;i++)
        {
           ShoppingcartTableEntity sp=new ShoppingcartTableEntity();
           sp.setShoppingUsername(username);
           sp.setShoppingGid(Integer.parseInt(shoppingGidlist[i]));
           sp.setShoppingSname(shoppingSnamelist[i]);
           sp.setShoppingId(Integer.parseInt(spidlist[i]));
           sp.setShoppingQuantity(Integer.parseInt(shoppingQuantitylist[i]));
           num=num+Integer.parseInt(shoppingQuantitylist[i]);
           sp.setShoppingGname(shoppingGnamelist[i]);
           BigDecimal bd=new BigDecimal(shoppingPricelist[i]);
           sp.setShoppingPrice(bd);
           shop.add(sp);
        }
        for (ShoppingcartTableEntity sc:shop){
            goods.setGoodsId(sc.getShoppingGid());
            goods.setUserName(sc.getShoppingSname());
            goods.setGoodsName(sc.getShoppingGname());
            goods.setGoodsQuantity(sc.getShoppingQuantity());
            goods.setGoodsPrice(sc.getShoppingPrice());
        }
        DaServlet ds=new DaServlet();
        List<DaTableEntity> dalist=ds.readdr(request,response);
        request.setAttribute("goods",goods);
        request.getSession().setAttribute("num",num);
        request.getSession().setAttribute("countprice",countprice);
        request.setAttribute("dalist",dalist);
        request.getSession().setAttribute("shopli",shop);
        request.getRequestDispatcher("/scbuygoods.jsp").forward(request, response);
    }
    //用户查询商品
    private void usersel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String goodsname=request.getParameter("select");
        IGoodsService igoods=new GoodsServiceImpl();
        List<GoodsTableEntity> goodslist=igoods.likeallgoods(goodsname);
        if (!goodslist.isEmpty()){
            request.setAttribute("goodslist",goodslist);
            request.getRequestDispatcher("/showlikegoods.jsp").forward(request, response);
        }else {
            request.setAttribute("goodslist",goodslist);
            request.setAttribute("selmsg","未找到商品");
            request.getRequestDispatcher("/showlikegoods.jsp").forward(request, response);
        }
    }
    //显示商品信息
    public void showgoods(HttpServletRequest request, HttpServletResponse response,String hidden)throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        GoodsTableEntity goods=new GoodsTableEntity();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if ("usershowgoods".equals(hidden)){
            HttpSession session=request.getSession();
            IUserDao userDao=new UserDaoImpl();
            List<UserTableEntity> levellist=userDao.findusername(goods.getUserName());
            UserTableEntity level=new UserTableEntity();
            for (UserTableEntity le:levellist){
                level=le;
            }
            session.setAttribute("goods",goods);
            request.setAttribute("goods",goods);
            session.setAttribute("level",level);
            request.setAttribute("level",level);
            CommentServlet com=new CommentServlet();
            List<CommentTableEntity> comlist=com.findcom(request,response,goods.getGoodsId());
            request.setAttribute("comlist",comlist);
            session.setAttribute("comlist",comlist);
            request.getRequestDispatcher("/usershowgoods.jsp").forward(request, response);
        }else {
            request.setAttribute("goods",goods);
            request.getRequestDispatcher("/showgoods.jsp").forward(request, response);
        }
    }


}
