<%@ page import="java.util.List" %>
<%@ page import="com.fleamarket.domain.ShoppingcartTableEntity" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/1/9
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
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
    <script>
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;

        });
        function onloadshop(q) {
            var x=document.getElementById("countprice");
            x.value=parseInt(0)
            for (i=1;i<=q;i++){
                var price=document.getElementById("shoppingPrice"+i+"");
                x.value=(parseInt(price.value)+parseInt(x.value));
            }
        }
        function jia(num) {
            var maxQuantity=document.getElementById("maxQuantity"+num+"");
            var inprice=document.getElementById("inprice"+num+"");
            var price=document.getElementById("shoppingPrice"+num+"");
            var Quantity=document.getElementById("shoppingQuantity"+num+"");
            var countprice=document.getElementById("countprice");
            if (parseInt(maxQuantity.value)>parseInt(Quantity.value)){
                Quantity.value=parseInt(Quantity.value)+1;
                price.value=parseInt(price.value)+parseInt(inprice.value);
                countprice.value=parseInt(countprice.value)+parseInt(inprice.value);
            }
        }
        function jian(num) {
            var inprice=document.getElementById("inprice"+num+"");
            var price=document.getElementById("shoppingPrice"+num+"");
            var Quantity=document.getElementById("shoppingQuantity"+num+"");
            var countprice=document.getElementById("countprice");
            if (1<parseInt(Quantity.value)){
                Quantity.value=parseInt(Quantity.value)-1;
                price.value=parseInt(price.value)-parseInt(inprice.value);
                countprice.value=parseInt(countprice.value)-parseInt(inprice.value);
            }
        }
    </script>
    <style type="text/css">
        .parent {position: relative;}
        .child {
            position: absolute;
            top: 50%;
            left: 50%;
            height: 100%;
            width: 50%;
            margin: -17% 60% 0 -17%;
        }
    </style>
</head>
<%
    List<ShoppingcartTableEntity> shoplist=(List<ShoppingcartTableEntity>) session.getAttribute("shoplist");
    int x=shoplist.size();
%>
<body onload="onloadshop(<% out.print(x);%>)" class="parent">
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
</div>

<form class="layui-form child" action="${pageContext.request.contextPath}/GoodsServlet.do" >
    <%  int q=0;
        int six=0;
        if (shoplist.size()>0){
    %>
    <input type="hidden" name="hidden" value="scbuygoods">
    <div style="width: 100%; position:absolute;">
        <h3 style="float: left">商品图片</h3><h3 style="margin-left: 7px;float: left">商品名</h3>
        <h3 style="margin-left: 75px;float: left">商品数量</h3><h3 style="margin-left: 70px;float: left;">商品价格</h3>
    </div>
    <%

            for (ShoppingcartTableEntity shop:shoplist){
                q+=1;
                six+=12;
    %>
    <input type="hidden" id="maxQuantity<%out.print(q);%>" value="<%out.print(shop.getShoppingQuantity());%>">
    <input type="hidden" name="shoppingGid" value="<%out.print(shop.getShoppingGid());%>">
    <input type="hidden" name="shoppingSname" value="<%out.print(shop.getShoppingSname());%>">
    <input type="hidden" id="inprice<%out.print(q);%>" value="<%out.print(shop.getShoppingPrice());%>">
    <input type="hidden" name="spid" value="<%out.print(shop.getShoppingId());%>">
    <div style="width: 1500px;margin-top: <%out.print(six);%>%;position:absolute;" >
        <img src="<%out.print(shop.getShoppingGphoto());%>" style="width: 60px;height: 60px;float: left">
        <input type="text" style="border: 0px;width: 100px;float: left" readonly="readonly" name="shoppingGname" value="<%out.print(shop.getShoppingGname());%>"  class="layui-input">
        <input type="button" style="float: left;" class="layui-btn" onclick="jian(<%out.print(q);%>)" id="jian<%out.print(q);%>" value="-">
        <input type="text" value="1" readonly="readonly" style="border: 0px;width: 60px;float: left;text-align: center" id="shoppingQuantity<%out.print(q);%>" name="shoppingQuantity"  class="layui-input">
        <input style="float: left;" type="button" class="layui-btn" onclick="jia(<%out.print(q);%>)" id="jia<%out.print(q);%>" value="+">
        <input type="text" readonly="readonly" style="border: 0px;float: left;width: 100px;text-align: center" id="shoppingPrice<%out.print(q);%>"  value="<%out.print(shop.getShoppingPrice());%>" name="shoppingPrice"  class="layui-input">
        <a href="${pageContext.request.contextPath}/ShoppingCartServlet.do?hidden=delshop&id=<%out.print(shop.getShoppingId());%>"><input type="button"  style="float: left" class="layui-btn" value="移除"></a>
    </div>
    <%}}else{out.print("无物品！");}%>
    <div  style="position:absolute;margin-top: <%out.print(six+10);%>%;margin-left: 170px">
        <h3 style="float: left">总价:</h3><input type="text" style="border: 0px;width: 100px" id="countprice" name="countprice"  class="layui-input">
    </div>
    <div  style="position:absolute;margin-top: <%out.print(six+20);%>%;margin-left: 170px">
        <button class="layui-btn" lay-submit lay-filter="formDemo">购买</button>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
