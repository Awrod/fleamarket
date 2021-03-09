<%@ page import="com.fleamarket.domain.BorderTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/6
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>归还书籍</title>
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
    function button_clickt(){
        var gnl=confirm("确定书籍已经归还?");
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
        <div style="height: 500px">
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
                    <th>确认归还</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<BorderTableEntity> borderlist = (List<BorderTableEntity>)request.getAttribute("borderlist");
                    for (BorderTableEntity border:borderlist) {
                        if ("借阅中".equals(border.getBorderState())){
                %>
                <tr>
                    <td><%out.print(border.getBorderId());%></td>
                    <td><%out.print(border.getBorderbName());%></td>
                    <td><%out.print(border.getBorderbokName());%></td>
                    <td><%out.print(border.getBorderSubarea());%></td>
                    <td><%out.print(border.getBorderState());%></td>
                    <td><a href="${pageContext.request.contextPath}/BorderServlet.do?hidden=cptborder&id=<%out.print(border.getBorderId());%>&bname=<%out.print(border.getBorderbokName());%>"><input type="button" onclick="return button_clickt()" class="layui-btn" value="确认归还"></a></td>
                </tr>
                <% }} %>
                </tbody>
            </table>
        </div>
        <%   int pageall=(int)request.getAttribute("page");
            for (int i=1;i<=pageall;i++){
        %>
        <a style="margin-left: 2%" href="${pageContext.request.contextPath}/BorderServlet.do?hidden=rebook&ppage=<%out.print((i-1)*5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
        <%}%>
    </div>
</div>
</body>
</html>
