<%@ page import="com.fleamarket.domain.BookTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/6
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下架书籍</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="layer.js"></script>
    <script>
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;

        });
    </script>
    <script>
        function button_click(){
            var gnl=confirm("确认下架书籍?");
            if (gnl==true){
                return true;
            }else{
                return false;
            }
        }
    </script>
</head>
<body  class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                    <%
                    String login=(String) session.getAttribute("login");
                    String username=(String) session.getAttribute("username");
                    if("login".equals(login)){
                        response.setCharacterEncoding("UTF-8");
                        out.print("<img style=\"width: 35px;height: 35px\" src=\""+session.getAttribute("userphoto")+"\">"+"欢迎:"+username+"&nbsp;</li>" +
                                " <li class=\"layui-nav-item\"><a style=\"font-family: 华文中宋;font-size: 15px;\" href=\"/fleamarket_war_exploded/UserServlet.do?hidden=myinformation\">个人中心</a></li>");
                %>
            <li class="layui-nav-item"><a style="font-family: 华文中宋;font-size: 15px;" href="${pageContext.request.contextPath}/UserServlet.do?hidden=loginoff"><cite>注销</cite></a> </li>
            <%

                    out.flush();
                }else {
                    out.print("<a style=\"font-family: 华文中宋;font-size: 15px;\" id=\"login_register\"><cite><i class=\"layui-icon layui-icon-user\"> 登入/注册</i></cite></a>");
                }
            %>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="${pageContext.request.contextPath}/UserServlet.do?hidden=alluser&ppage=0">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/UserServlet.do?hidden=freeze&ppage=0">冻结账号</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/UserServlet.do?hidden=auserdel&ppage=0">删除账号</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <h2>${mes}</h2>
        <div style="padding: 15px;height: 590px">
            <%
                List<BookTableEntity> booklist = (List<BookTableEntity>)request.getAttribute("booklist");
                for (BookTableEntity book:booklist) {
            %>
            <div style="float: left;width: 200px;height: 180px;margin-right: 10px">
                <div style="width: 200px;height: 150px;border: 2px black;">
                    <img style="width: 200px;height: 150px;" src="<% out.print(book.getBookPhoto());%>">
                </div>
                <div style="width: 200px;height: 20px;border: 2px black;text-align: center">
                    <% out.print(book.getBookName());%>
                </div>
                <div style="width: 200px;height: 10px;border: 2px black;text-align: center">
                    <form  class="layui-form " onsubmit="return sumbit_form()"  method="post" action="${pageContext.request.contextPath}/BookServlet.do">
                        <input type="hidden" name="bookId" value="<%out.print(book.getBookId());%>">
                        <input type="hidden"  name="hidden" value="adownbook">
                        <button class="layui-btn" onclick="return button_click()"  lay-submit lay-filter="formDemo">下架书籍</button>
                    </form>
                </div>
            </div>
            <% } %>
        </div>

        <%   int pageall=(int)request.getAttribute("page");
            for (int i=1;i<=pageall;i++){
        %>
        <a style="margin-left: 2%;" href="${pageContext.request.contextPath}/BookServlet.do?hidden=allbook&page=<%out.print((i-1)*5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
        <%}%>
    </div>
</div>
</body>
</html>
