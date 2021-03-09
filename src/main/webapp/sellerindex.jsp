<%@ page import="com.fleamarket.domain.GoodsTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/21
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商家后台</title>
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
</head>
<body  class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">商家后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">商品管理</a></li>
        </ul>
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
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/GoodsServlet.do?hidden=seidx">我的商品</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/selleradd.jsp">发布商品</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/GoodsServlet.do?hidden=upgoods">修改商品</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/GoodsServlet.do?hidden=delgoods">下架商品</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">订单管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/OrderServlet.do?hidden=handleorder&ppage=0">待处理订单</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/OrderServlet.do?hidden=cptorder">已完成订单</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">售后管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/OrderServlet.do?hidden=sdelorder">退货订单处理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <h2>${delmsg}</h2>
        <h2>${upmsg}</h2>
        <div style="padding: 15px;">
                <%
                    List<GoodsTableEntity> goodslist = (List<GoodsTableEntity>)request.getAttribute("goodslist");
                    for (GoodsTableEntity good:goodslist) {
                %>
            <a href="${pageContext.request.contextPath}/GoodsServlet.do?hidden=showgoods&goodsId=<%out.print(good.getGoodsId());%>&goodsPhoto=<%out.print(good.getGoodsPhoto());%>
            &goodsName=<%out.print(good.getGoodsName());%>&goodsType=<%out.print(good.getGoodsType());%>&goodsQuantity=<%out.print(good.getGoodsQuantity());%>
            &goodsSynopsis=<%out.print(good.getGoodsSynopsis());%>&goodsPrice=<%out.print(good.getGoodsPrice());%>">
                <div style="float: left;width: 200px;height: 170px;margin-right: 10px">
                    <div style="width: 200px;height: 150px;border: 2px black;">
                        <img style="width: 200px;height: 150px;" src="<% out.print(good.getGoodsPhoto());%>">
                    </div>
                    <div style="width: 200px;border: 2px black;text-align: center">
                        <% out.print(good.getGoodsName());%>
                    </div>
                    <div style="width: 200px;height: 20px;border: 2px black;text-align: center">
                        数量：<% out.print(good.getGoodsQuantity());%>
                    </div>
                </div>
            </a>
                <% } %>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        ©  fleamarket - 底部固定区域
    </div>
</div>
</body>
</html>
