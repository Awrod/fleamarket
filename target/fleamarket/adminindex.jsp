<%@ page import="com.fleamarket.domain.UserTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/5
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
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
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>密码</th>
                <th>性别</th>
                <th>真实姓名</th>
                <th>身份证</th>
                <th>手机号</th>
                <th>类型</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<UserTableEntity> userlist = (List<UserTableEntity>)request.getAttribute("userlist");
                if (userlist.size()>0){
                for (UserTableEntity user:userlist) {
            %>
            <tr>
                <td><%out.print(user.getUserId());%></td>
                <td><%out.print(user.getUserName());%></td>
                <td><%out.print(user.getUserPass());%></td>
                <td><%out.print(user.getUserSex());%></td>
                <td><%out.print(user.getUserReallyn());%></td>
                <td><%out.print(user.getUseridcard());%></td>
                <td><%out.print(user.getUserMoblie());%></td>
                <td><%out.print(user.getUserType());%></td>
                <td><%out.print(user.getUserState());%></td>
            </tr>
            <% }} %>
            </tbody>
        </table>
        <%   int pageall=(int)request.getAttribute("page");
            for (int i=1;i<=pageall;i++){
        %>
        <a style="margin-left: 2%" href="${pageContext.request.contextPath}/UserServlet.do?hidden=alluser&ppage=<%out.print((i-1)*5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
        <%}%>
    </div>
</div>
</body>
</html>
