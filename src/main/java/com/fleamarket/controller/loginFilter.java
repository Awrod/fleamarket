package com.fleamarket.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "loginFilter")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        String uri = req.getRequestURI();
        String hidden = req.getParameter("hidden");
        //如果username为null，表示没有登录
        if(req.getSession().getAttribute("username")==null) {
            if (uri.contains("index.jsp") || "usersel".equals(hidden) ||"phone".equals(hidden) ||"findusername".equals(hidden) ||"goodstypesel".equals(hidden) || "login".equals(hidden) || "register".equals(hidden)|| uri.contains("showlikegoods.jsp")){
                chain.doFilter(request, response);
            }else {
                response.setContentType("text/html");
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('未登入！');window.location.href='"+req.getContextPath()+"/index.jsp';</script>)");
            }
        }else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
