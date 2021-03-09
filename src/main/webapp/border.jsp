<%@ page import="com.fleamarket.domain.BorderTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/6
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已借阅书籍</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="layer.js"></script>
    <script>
        layui.use('form', function() {
            //刷新layui-input
            var form = layui.form;
            form.render();
        });
        //添加导航依赖element
        layui.use('element', function(){
            var element = layui.element;
            //一些事件监听
            element.on('tab(demo)', function(data){
                console.log(data);
            });
        });
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-header">
    <ul  class="layui-nav" >
        <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
                <%
                    String login=(String) session.getAttribute("login");
                    String username=(String) session.getAttribute("username");
                    if("login".equals(login)){
                        response.setCharacterEncoding("UTF-8");
                                               out.print("<img style=\"width: 35px;height: 35px\" src=\""+session.getAttribute("userphoto")+"\">"+"欢迎:"+username+"&nbsp;</li>" +
                                " <li class=\"layui-nav-item\"><a style=\"font-family: 华文中宋;font-size: 15px;\" href=\"/fleamarket_war_exploded/UserServlet.do?hidden=myinformation\">个人中心</a> <dl class=\"layui-nav-child\">" +
                                 "<dd><a href=\"/fleamarket_war_exploded/DaServlet.do?hidden=conaddr\">管理收货地址</a></dd><dd><a href=\"/fleamarket_war_exploded/OrderServlet.do?hidden=ordercheck\">查看订单</a></dd>" +
                                 "</dl></li>" +
                                 "<li class=\"layui-nav-item\"><a style=\"font-family: 华文中宋;font-size: 15px;\" href=\"/fleamarket_war_exploded//FavoritesServlet.do?hidden=showfavorites\">收藏夹</a></li>"+
                                 "<li class=\"layui-nav-item\"><a style=\"font-family: 华文中宋;font-size: 15px;\" href=\"/fleamarket_war_exploded//ShoppingCartServlet.do?hidden=showshop\">购物车</a></li>");
                %>
        <li class="layui-nav-item"><a style="font-family: 华文中宋;font-size: 15px;" href="${pageContext.request.contextPath}/UserServlet.do?hidden=loginoff"><cite>注销</cite></a> </li>
        <%

                out.flush();
            }else {
                out.print("<a style=\"font-family: 华文中宋;font-size: 15px;\"  id=\"login_register\"><cite><i class=\"layui-icon layui-icon-user\"> 登入/注册</i></cite></a>");
            }
        %>
        </li>
    </ul>
</div>
<div style="height: 500px">
    <h3>借阅后可凭订单和学生证在图书馆取书，请于30天内归还，逾期一天扣1元!!!!</h3>
<table class="layui-table">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>编号</th>
        <th>借阅人</th>
        <th>借阅书籍名</th>
        <th>书籍分区</th>
        <th>状态</th>
        <th>借阅时间</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<BorderTableEntity> borderlist = (List<BorderTableEntity>)request.getAttribute("borderlist");
        for (BorderTableEntity border:borderlist) {
    %>
    <tr>
        <td><%out.print(border.getBorderId());%></td>
        <td><%out.print(border.getBorderbName());%></td>
        <td><%out.print(border.getBorderbokName());%></td>
        <td><%out.print(border.getBorderSubarea());%></td>
        <td><%out.print(border.getBorderState());%></td>
        <td><%out.print(border.getBorderTime());%></td>
    </tr>
    <% } %>
    </tbody>
</table>
</div>
<%   int pageall=(int)request.getAttribute("page");
    for (int i=1;i<=pageall;i++){
%>
<a style="margin-left: 2%" href="${pageContext.request.contextPath}/BorderServlet.do?hidden=cheborder&ppage=<%out.print((i-1)*5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
<%}%>
</div>
</body>
</html>
