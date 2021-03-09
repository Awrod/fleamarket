<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/29
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加收货地址</title>
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
        //弹出登入注册页面
        layui.use(['layer','jquery'],function () {
            var layer=layui.layer;
            var $=layui.jquery;
            $("#login_register").click(function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['470px', '550px'], //宽高
                    title:"登入-注册",
                    content: $('#re_lg')
                });
            })
        })
        //轮播图
        layui.use('carousel', function(){
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#test1'
                ,width: '85%' //设置容器宽度
                ,height:'100%'
                ,indicator :"hover"//悬停显示
                ,arrow: 'hover' //悬停显示
                //,anim: 'updown' //切换动画方式
            });
        });
    </script>
    <script>
        //保证表单的数量必须是数字
        function chkPrice(obj){
            var pattern = obj.replace(/[0-9\.]+/g,"");
            if (pattern.length>0){
                document.getElementById("userMoblie").value="";
                document.getElementById("userMoblietext").value="必须为数字！";
            }else{
                document.getElementById("userMoblietext").value="";
            }
        }
    </script>
    <style type="text/css">
        .parent {position: relative;}
        .child {
            position: absolute;
            top: 50%;
            left: 50%;
            height: 30%;
            width: 50%;
            margin: -9% 60% 0 -15%;
        }
    </style>
</head>
<body class="layui-layout-body parent">
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
                out.print("<a style=\"font-family: 华文中宋;font-size: 15px;\" id=\"login_register\"><cite><i class=\"layui-icon layui-icon-user\"> 登入/注册</i></cite></a>");
            }
        %>
        </li>
    </ul>
</div>
</div>
<%--添加收件地址--%>
<form  class="layui-form child"   action="${pageContext.request.contextPath}/DaServlet.do" method="post">
    <input type="hidden" name="hidden" value="${hidden}">
    <input type="hidden" name="daId" value="${id}">
    <div class="layui-form-item">
        <label class="layui-form-label">收件人名称:</label>
        <div class="layui-input-block">
            <input type="text" style="width: 300px" value="${name}"  lay-verify="required"   id="daName"  name="daName"   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号:</label>
        <div class="layui-input-block">
            <input type="text" style="width: 300px;float: left" value="${moblie}" onchange="chkPrice(this.value)"  lay-verify="required"  minlength="11" maxlength="11"  id="userMoblie"  name="userMoblie"   class="layui-input">
            <input  type="text" id="userMoblietext" readonly="readonly" style="border: 0px;color: red;float: left;height: 35px;margin-left: 10px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">收件人地址:</label>
        <div class="layui-input-block">
            <input type="text" style="width: 300px" value="${addr}"  lay-verify="required"   id="userAddr"  name="userAddr"   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-left: 15%">
        <div class="layui-input-block">
            <button class="layui-btn"  lay-submit lay-filter="formDemo">添加</button>
        </div>
    </div>
</form>
</body>
</html>
