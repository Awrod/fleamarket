package com.fleamarket.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fleamarket.domain.*;
import com.fleamarket.service.impl.UserServiceImpl;
import com.fleamarket.service.IUserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/UserServlet.do")
@MultipartConfig(location="D:\\fleamarketimage",maxFileSize=1024*1024*30,maxRequestSize=40*1024*1024, fileSizeThreshold=1024*3)
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String hidden = request.getParameter("hidden");
        if ("register".equals(hidden)){
            register(request,response);
        }else if("findusername".equals(hidden)){
            FindUserName(request,response);
        }else if("phone".equals(hidden)){
            phone(request,response);
        }else if("login".equals(hidden)){
            login(request,response);
        }else if ("loginoff".equals(hidden)){
            HttpSession session=request.getSession();
            session.removeAttribute("username");
            session.removeAttribute("login");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else if("myinformation".equals(hidden)){
            showmyinformation(request,response);
        }else if("updateimg".equals(hidden)){
            updateimg(request,response);
        }else if("updateinfo".equals(hidden)){
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            updateinfo(request,response);
        }else if("seidx".equals(hidden)){
            selallgoods (request,response,null);
        }else if("alluser".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage"));
            alluser(request,response,hidden,ppage);
        }else if("freeze".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage"));
            alluser(request, response,hidden,ppage);
        }else if("freezeuser".equals(hidden)){
            freezeuser(request, response,null);
        }else if("unfreeze".equals(hidden)){
            freezeuser(request, response,hidden);
        }else if("admindel".equals(hidden)){
            admindel(request, response);
        }else if("auserdel".equals(hidden)){
            int ppage=Integer.parseInt(request.getParameter("ppage"));
            alluser(request, response,hidden,ppage);
        }else if("storelevel".equals(hidden)){
            storelevel(request, response);
        }
    }

    //更新用户等级
    private void storelevel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String level=request.getParameter("level");
        String storeusername=(String)request.getSession().getAttribute("storeusername");
        IUserService iuser=new UserServiceImpl();
        iuser.storelevelup(level,storeusername);
        OrderServlet os=new OrderServlet();
        CommentServlet com=new CommentServlet();
        com.inscom(request,response,request.getParameter("comment"),Integer.parseInt(request.getParameter("goodsid")));
        os.ordercheck(request,response,0,0,0,5,"ordercheck");
    }

    //管理员删除用户
    private void admindel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String userid=request.getParameter("userid");
        IUserService iuser=new UserServiceImpl();
        iuser.admindel(userid);
        alluser(request, response,"alluser",0);
    }
//冻结用户
    private void freezeuser(HttpServletRequest request, HttpServletResponse response,String hidden) throws ServletException, IOException {
        int userid=Integer.parseInt(request.getParameter("userid"));
        IUserService iuser=new UserServiceImpl();
        iuser.fzuser(userid,hidden);
        alluser(request, response,"alluser",0);
    }
//查询所有用户
    private void alluser(HttpServletRequest request, HttpServletResponse response,String hidden,int ppage) throws ServletException, IOException{
        IUserService iuser=new UserServiceImpl();
        List<UserTableEntity> userlist=iuser.alluser(0,99);
        int page=0;
        for (UserTableEntity or:userlist){
            page+=1;
        }
        if (page%5 !=0){
            page=(page/5)+1;
        }else {
            page=(page/5);
        }
        userlist=iuser.alluser(ppage,5);
        request.setAttribute("page",page);
        request.setAttribute("userlist",userlist);
        System.out.println(page);
        for (UserTableEntity usss:userlist){
            System.out.println(usss.getUserSex());
        }
        if("alluser".equals(hidden)){
            request.getRequestDispatcher("/adminindex.jsp").forward(request, response);
        }else if("freeze".equals(hidden)){
            request.getRequestDispatcher("/adminfreeze.jsp").forward(request, response);
        }else if("auserdel".equals(hidden)){
            request.getRequestDispatcher("/admindel.jsp").forward(request, response);
        }
    }


    //显示所有商品
    private void selallgoods(HttpServletRequest request, HttpServletResponse response,String hidden)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IUserService iuser=new UserServiceImpl();
        List<GoodsTableEntity> goodslist=iuser.selallgoods(username);
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
    //更新个人信息
    private void updateinfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String message = "";
        Map<String,String[]> map=request.getParameterMap();
        UserTableEntity user = new UserTableEntity();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        IUserService userService = new UserServiceImpl();
        int mes = userService.updateinfo(user);
        if(mes>0) {
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");
            user= userService.showinfo(username);
            request.setAttribute("name",user.getUserName());
            request.setAttribute("pass",user.getUserPass());
            request.setAttribute("photo",user.getUserPhoto());
            request.setAttribute("sex",user.getUserSex());
            request.setAttribute("moblie",user.getUserMoblie());
            request.setAttribute("ren",user.getUserReallyn());
            request.setAttribute("idc",user.getUseridcard());
            request.setAttribute("mes","更新成功!");
            request.getRequestDispatcher("/myinformation.jsp").forward(request, response);
        }else {
            request.setAttribute("mes","更新失败!");
            request.getRequestDispatcher("/myinformation.jsp").forward(request, response);
        }
    }
    //显示个人信息
    private void showmyinformation(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        IUserService iUser=new UserServiceImpl();
        UserTableEntity result=iUser.showinfo(username);
        request.setAttribute("name",result.getUserName());
        request.setAttribute("pass",result.getUserPass());
        request.setAttribute("photo",result.getUserPhoto());
        request.setAttribute("sex",result.getUserSex());
        request.setAttribute("moblie",result.getUserMoblie());
        request.setAttribute("ren",result.getUserReallyn());
        request.setAttribute("idc",result.getUseridcard());
        request.getRequestDispatcher("/myinformation.jsp").forward(request, response);
    }
    //更换头像
    private void updateimg(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        String photoname=username+".jpg";
        Part part=request.getPart("file_upload");
        String path="D:\\fleamarketimage\\userphoto\\"+photoname;
        part.write(path);
        String path2="/fleamarketimage/userphoto/"+photoname;
        IUserService iUser=new UserServiceImpl();
        int m=iUser.insterimgpath(path2,username);
        if (m>0){
            request.setAttribute("photosrc",path2);
            request.setAttribute("mes","修改成功");
        }else {
            request.setAttribute("mes","修改失败");
        }
        showmyinformation(request,response);
    }
    //登入
    private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String username=request.getParameter("userName");
        String userpass=request.getParameter("userPass");
        String message=null;
        String validationCode=request.getParameter("validationCode");
        HttpSession session=request.getSession();
        String randomcode=(String) session.getAttribute("randomCode");
        IUserService iUser=new UserServiceImpl();
        UserTableEntity result=iUser.showinfo(username);
        if (!validationCode.equals(randomcode)){
            message="验证码错误";
        }else if (!username.isEmpty() && !userpass.isEmpty()){
            IUserService userService = new UserServiceImpl();
            int msg = userService.checkuser(username,userpass);
            if(msg == 0 && !"freeze".equals(result.getUserState())){
                message = "登入成功";
                session.setAttribute("userphoto",result.getUserPhoto());
                session.setAttribute("login","login");
                session.setAttribute("username",username);
            }else{
                message = "用户名或密码错误";
            }
        }
        UserTableEntity user=iUser.showinfo(username);
        if ("freeze".equals(user.getUserState())){
            message = "账户因违规操作已被冻结!解冻请联系管理员";
        }
        request.setAttribute("msg",message);
       if (message.equals("验证码错误") || message.equals("用户名或密码错误")|| message.equals("账户因违规操作已被冻结!解冻请联系管理员") ){
           response.setContentType("text/html");
           PrintWriter out=response.getWriter();
           out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
       }else {
           if ("买家".equals(user.getUserType())){
               request.getRequestDispatcher("/index.jsp").forward(request, response);
           }else   if ("卖家".equals(user.getUserType())){
               selallgoods(request, response,null);
           }else  if ("admin".equals(user.getUserType())){
                alluser(request, response,"alluser",0);
           }
       }

    }
    //Ajax验证手机号
    private void phone(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String usermoblie=request.getParameter("phone");
        String message="";
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (usermoblie.length() ==11){
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(usermoblie);
            boolean isMatch = m.matches();
            if(isMatch){
                message="3:手机号格式正确！";
            }else{
                message="1:手机号格式错误！";
            }
        }else {
            message="2:手机号应为11位数！";
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(message);
        out.flush();
    }
    //Ajax查询用户名是否可用
    private void FindUserName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String username =request.getParameter("userName");
        String messsage = "";
        if(username != null && !username.isEmpty()){
            IUserService userService = new UserServiceImpl();
            int msg = userService.findusername(username);
            if(msg == 1){
                messsage = "1:用户名已经被注册，请输入不同的用户名！";
            }else{
                messsage = "3:恭喜你，用户名可注册！";
            }
        }else{
            messsage = "2:用户名不能为空！";
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(messsage);
        out.flush();
    }
    //注册
    private void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String message = "";
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String,String[]> map=request.getParameterMap();
        UserTableEntity user = new UserTableEntity();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        IUserService userService = new UserServiceImpl();
        int mes = userService.register(user);
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        if(mes>0) {
            message="注册成功!";
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }else {
            message="注册失败，用户名或手机号不符合规范!";
            out.print("<script language='javascript'>alert('"+message+"');window.location.href='"+request.getContextPath()+"/index.jsp';</script>)");
        }

    }
}
