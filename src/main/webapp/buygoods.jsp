<%@ page import="com.fleamarket.domain.GoodsTableEntity" %>
<%@ page import="com.fleamarket.domain.DaTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/26
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="layer.js"></script>
    <script>
        //JavaScript代码区域
        layui.use('form', function () {
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
    <script type="text/javascript">
        var xmlhttp ;
        function createXmlHttpRequest() {
            if(window.ActiveXObject){
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }else if(window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();
            }
        }

        function startRequest(){
            createXmlHttpRequest();
            xmlhttp.onreadystatechange = handleStateChange;
            var username = document.getElementById("username");
            var url = "${pageContext.request.contextPath}/UserServlet.do?hidden=findusername&userName="+username.value;
            xmlhttp.open("GET",url,true);
            xmlhttp.send(null );
        }
        function startRequest2(){
            createXmlHttpRequest();
            xmlhttp.onreadystatechange = handleStateChange2;
            var userMoblie = document.getElementById("userMoblie");
            var url = "${pageContext.request.contextPath}/UserServlet.do?hidden=phone&phone="+userMoblie.value;
            xmlhttp.open("GET",url,true);
            xmlhttp.send(null );
        }
        function handleStateChange() {
            if(xmlhttp.readyState == 4){
                if(xmlhttp.status == 200){
                    var content = document.getElementById("userNameMsg");
                    var text = xmlhttp.responseText;
                    var num = text.substr(0,1);
                    text = text = text.substring(2,text.length-1);
                    if(num <=2) {
                        content.value=text
                        content.style = "font-size:15px;color:#ff2233;border: 0px;width: 300px";
                    }else{
                        content.value=text
                        content.style = "font-size:15px;color:#22ff33;border: 0px;width: 300px";
                    }
                    content.value = text;
                }
            }
        }
        function handleStateChange2() {
            if(xmlhttp.readyState == 4){
                if(xmlhttp.status == 200){
                    var content = document.getElementById("usermoblieMsg");
                    var text = xmlhttp.responseText;
                    var num = text.substr(0,1);
                    text = text = text.substring(2,text.length-1);
                    if(num <=2) {
                        content.value=text
                        content.style = "font-size:15px;color:#ff2233;border: 0px;width: 300px";
                    }else{
                        content.value=text
                        content.style = "font-size:15px;color:#22ff33;border: 0px;width: 300px";
                    }
                    content.value = text;
                }
            }
        }
        //加载验证码图片，后面加时间可以保证每次页面刷新时验证码也刷新
        function loadimage(){
            document.getElementById("validationCode_img").src= "validate.jsp?time=" + new Date().getTime();
        }
        //弹出地址选择页面
        layui.use(['layer','jquery'],function () {
            var layer=layui.layer;
            var $=layui.jquery;
            $("#seladdrbtn").click(function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['470px', '550px'], //宽高
                    title:"登入-注册",
                    content: $('#re_lg')
                });
            })
        })
    </script>
    <script>
        function pr_click() {
            var Quantity=document.getElementById('ordergQuantity');
            var goodsPrice=document.getElementById('goodsPrice');
            var Quantityhidden=document.getElementById('Quantityhidden');
            if (parseInt(Quantity.value)>1){
                Quantity.value=parseInt(Quantity.value)-1;
                goodsPrice.value=parseInt(goodsPrice.value)-parseInt(Quantityhidden.value);
            }
        }
        function next_click() {
            var all=document.getElementById('goodsQuantity');
            var Quantity=document.getElementById('ordergQuantity');
            var goodsPrice=document.getElementById('goodsPrice');
            var Quantityhidden=document.getElementById('Quantityhidden');
            if (parseInt(Quantity.value)<parseInt(all.value)){
                Quantity.value=parseInt(Quantity.value)+1;
                goodsPrice.value=parseInt(goodsPrice.value)+parseInt(Quantityhidden.value);
            }
        }
    </script>
    <style type="text/css">
        .parent {position: relative;}
        .child {
            position: absolute;
            top: 50%;
            left: 50%;
            height: 50%;
            width: 50%;
            margin: -9% 50% 0 -25%;
        }
    </style>
</head>
<body class="parent">
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
<%--  商品搜索  --%>
<div style="height: 20%;background-color: white;">
    <form class="layui-form" action="${pageContext.request.contextPath}/GoodsServlet.do">
        <div class="layui-form-item">
            <div style="margin-left: 29%" class="layui-input-block">
                <br>
                <input type="hidden"name="hidden" value="usersel">
                <input type="text" style="width: 50%;float: left" name="select" required  lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
                <button style="float: left;margin-left: 1%" class="layui-btn" lay-submit lay-filter="formDemo"><i class="layui-icon layui-icon-search"></i></button>
            </div>
        </div>
    </form>
</div>
<!-- 内容主体区域 -->
<div style="width: 100%;height: 70%">
        <%GoodsTableEntity goods=(GoodsTableEntity)request.getAttribute("goods");
          DaTableEntity da=(DaTableEntity)request.getAttribute("da");
        %>
        <div style="width: 50%;height: 70%;float: left" class="child">
            <img style="width: 25%;height: 50%;float: left" src="/image/paywx.jpg">
            <img style="width: 25%;height: 50%;float: left" src="/image/payzfb.jpg">
            <form style="width: 45%;height: 300px;float: left" class="layui-form" action="${pageContext.request.contextPath}/OrderServlet.do">
                <input type="hidden" value="buygoods" name="hidden">
                <input type="hidden" value="<%out.print(goods.getGoodsId());%>" name="goodsId">
                <input type="hidden" value="<%out.print(goods.getGoodsPhoto());%>" name="goodsPhoto">
                <input type="hidden" value="<%out.print(goods.getGoodsSynopsis());%>" name="goodsSynopsis">
                <input type="hidden" value="<%out.print(goods.getUserName());%>" name="userName">
                <%if (da !=null){%>
                <input type="hidden" value="<%out.print(da.getDaId());%>" name="daId">
                <div class="layui-form-item">
                    <label class="layui-form-label">收件人:</label>
                    <div class="layui-input-block">
                        <input style="border: 0px" readonly="readonly"  required="required" type="text" class="layui-input" name="daName" value="<%out.print(da.getDaName());%>" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号:</label>
                    <div class="layui-input-block">
                        <input style="border: 0px" readonly="readonly" type="text" class="layui-input" name="userMoblie" value="<%out.print(da.getUserMoblie());%>" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">收件地址:</label>
                    <div class="layui-input-block">
                        <input style="border: 0px" readonly="readonly" type="text" class="layui-input" name="userAddr" value="<%out.print(da.getUserAddr());%>" >
                    </div>
                </div>
                <%}%>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品名称:</label>
                    <div class="layui-input-block">
                        <input style="border: 0px" readonly="readonly" type="text" class="layui-input" name="goodsName" value="<%out.print(goods.getGoodsName());%>" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">价格:</label>
                    <div class="layui-input-block">
                        <input type="hidden" id="Quantityhidden" value="<%out.print(goods.getGoodsPrice());%>">
                        <input style="border: 0px" readonly="readonly" type="text" class="layui-input" id="goodsPrice" name="goodsPrice" value="<%out.print(goods.getGoodsPrice());%>" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">剩余数量:</label>
                    <div class="layui-input-block">
                        <input style="border: 0px" readonly="readonly" id="goodsQuantity"  type="text" class="layui-input" name="goodsQuantity" value="<%out.print(goods.getGoodsQuantity());%>" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">类别:</label>
                    <div class="layui-input-block">
                        <input style="border: 0px;" readonly="readonly"  type="text" class="layui-input" name="goodsType" value="<%out.print(goods.getGoodsType());%>" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">购买数量:</label>
                    <div class="layui-input-block">
                        <input  style="float: left;" onclick="pr_click()" type="button" id="prev" class="layui-btn" value="-">
                        <input style="border: 0px;float: left;width: 40px" id="ordergQuantity" readonly="readonly"  type="text" class="layui-input" name="ordergQuantity" value="1" >
                        <input  style=";float: left;" onclick="next_click()" type="button" id="next" class="layui-btn" value="+">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: 15%">

                        <button class="layui-btn" style="float: left" lay-submit lay-filter="formDemo">支付</button> <input type="button" style="float: left"  class="layui-btn" value="选择收货地址" id="seladdrbtn">

                </div>
            </form>
        </div>
</div>
</body>
<%-- 选择地址 --%>
<div class="layui-tab" id="re_lg" style="display: none">
    <ul class="layui-tab-title">
        <li class="layui-this">地址选择</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <% List<DaTableEntity> dalist=(List<DaTableEntity>)request.getAttribute("dalist");
               GoodsTableEntity goodss=(GoodsTableEntity)request.getAttribute("goods");
                if(dalist != null && goodss != null){
            %>
            <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>收件人名称</th>
                    <th>手机号</th>
                    <th>收货地址</th>
                    <th>选择</th>
                </tr>
                </thead>
                <tbody>
                <%for (DaTableEntity das:dalist){%>
                <tr>
                    <td> <%out.print(das.getDaName());%></td>
                    <td><%out.print(das.getUserMoblie());%></td>
                    <td><%out.print(das.getUserAddr());%></td>
                    <td><a href="${pageContext.request.contextPath}/GoodsServlet.do?hidden=seladdr&daId=<%out.print(das.getDaId());%>&daName=<%out.print(das.getDaName());%>&userMoblie=<%out.print(das.getUserMoblie());%>&userAddr=<%out.print(das.getUserAddr());%>
                    &goodsId=<%out.print(goodss.getGoodsId());%>&goodsName=<%out.print(goodss.getGoodsName());%>&goodsType=<%out.print(goodss.getGoodsType());%>&goodsQuantity=<%out.print(goodss.getGoodsQuantity());%>&userName=<%out.print(goodss.getUserName());%>
                    &goodsPrice=<%out.print(goodss.getGoodsPrice());%>"><input type="button" class="layui-btn" value="选择"></a></td>
                </tr>
                <% }%>
                </tbody>
            </table>
            <% }%>
        </div>
    </div>
</div>
</html>
