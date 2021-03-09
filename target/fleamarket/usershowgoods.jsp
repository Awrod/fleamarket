<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/25
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fleamarket.domain.GoodsTableEntity" %>
<%@ page import="com.fleamarket.domain.UserTableEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fleamarket.domain.CommentTableEntity" %>
<html>
<head>
    <title>商品详情</title>
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
    </script>
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
<div>
    <!-- 内容主体区域 -->
        <div style="width: 100%;height: 900px">
            <%GoodsTableEntity goods=(GoodsTableEntity)request.getAttribute("goods");
                GoodsTableEntity goodss=(GoodsTableEntity)session.getAttribute("goods");
                UserTableEntity level=(UserTableEntity)request.getAttribute("level");
                UserTableEntity levels=(UserTableEntity)session.getAttribute("level");
                List<CommentTableEntity> comlist=(List<CommentTableEntity>)request.getAttribute("comlist");
                List<CommentTableEntity> comlists=(List<CommentTableEntity>)session.getAttribute("comlist");
                if (goods==null || level ==null || comlist.size()==0){
                    goods=goodss;
                    level=levels;
                    comlist=comlists;
                }else{
                    level=level;
                    goods=goods;
                    comlist=comlist;
                }
            %>
            <hr>
            <div style="width: 100%;height: 420px;float: left">
                <img style="width: 45%;height: 300px;float: left;border:2px solid #d5d5d5"  src="<%out.print(goods.getGoodsPhoto());%>">
                <form style="width: 45%;height: 300px;float: left;" class="layui-form" action="${pageContext.request.contextPath}/GoodsServlet.do">
                    <input type="hidden" value="buygoods" name="hidden">
                    <input type="hidden" value="<%out.print(goods.getGoodsId());%>" name="goodsId">
                    <input type="hidden" value="<%out.print(goods.getGoodsPhoto());%>" name="goodsPhoto">
                    <input type="hidden" value="<%out.print(goods.getGoodsSynopsis());%>" name="goodsSynopsis">
                    <input type="hidden" value="<%out.print(goods.getUserName());%>" name="userName">
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称:</label>
                        <div class="layui-input-block">
                            <input style="border: 0px" readonly="readonly" type="text" class="layui-input" name="goodsName" value="<%out.print(goods.getGoodsName());%>" >
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">价格:</label>
                        <div class="layui-input-block">
                            <input style="border: 0px" readonly="readonly" type="text" class="layui-input" name="goodsPrice" value="<%out.print(goods.getGoodsPrice());%>" >
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">剩余数量:</label>
                        <div class="layui-input-block">
                            <input style="border: 0px" readonly="readonly"  type="text" class="layui-input" name="goodsQuantity" value="<%out.print(goods.getGoodsQuantity());%>" >
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">类别:</label>
                        <div class="layui-input-block">
                            <input style="border: 0px;" readonly="readonly"  type="text" class="layui-input" name="goodsType" value="<%out.print(goods.getGoodsType());%>" >
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo">购买</button>
                            <a href="${pageContext.request.contextPath}/FavoritesServlet.do?hidden=Favorites&goodsId=<%out.print(goods.getGoodsId());%>&goodsPhoto=<%out.print(goods.getGoodsPhoto());%>&goodsName=<%out.print(goods.getGoodsName());%>"><input style="width: 125px" readonly="readonly" class="layui-btn" value="加入收藏夹"></a>
                            <a href="${pageContext.request.contextPath}/ShoppingCartServlet.do?hidden=inssho&shoppingGid=<%out.print(goods.getGoodsId());%>&shoppingGname=<%out.print(goods.getGoodsName());%>&shoppingPrice=<%out.print(goods.getGoodsPrice());%>&shoppingSname=<%out.print(goods.getUserName());%>&shoppingQuantity=<%out.print(goods.getGoodsQuantity());%>&shoppingGphoto=<%out.print(goods.getGoodsPhoto());%>"><input style="width: 125px" readonly="readonly" class="layui-btn" value="加入购物车"></a>
                        </div>
                    </div>
                </form>
            </div>
            <script>
                layui.use('rate', function(){
                    var rate = layui.rate;

                    //渲染
                    var ins1 = rate.render({
                        elem: '#sj', //绑定元素
                        value: document.getElementById('level').value,
                        text: true,
                        readonly:true
                    });
                });
            </script>
            <input type="hidden" id="level" value="<%out.print(level.getUserLevel());%>">
            <div style="width: 100%;height: 100%;float: left;border:1px solid #d5d5d5 ;margin-top: 2% ">
                <div class="layui-card" style="width: 15%;height: 100%;float: left;margin-top: 2% ">
                    <div class="layui-card-header layui-bg-gray"><%out.print(goods.getUserName());%>的店铺</div>
                    <div class="layui-card-body">
                        <h3  id="sj"></h3>
                    </div>
                </div>
                <div style="width: 81%;height: 100%;float: left;margin-top: 21px ">

                    <div class="layui-tab" style="width: 100%;height: 50%;float: left;margin-left: 5%">
                        <ul class="layui-tab-title">
                            <li class="layui-this">商品介绍</li>
                            <li>用户评价</li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <div class="layui-card" style="width: 100%;height: 50%;float: left">
                                    <div class="layui-card-body">
                                        <h3><%out.print(goods.getGoodsSynopsis());%></h3>
                                        <img style="width: 75%;margin-top: 3%" src="<%out.print(goods.getGoodsPhotoo());%>">
                                        <img style="width: 75%;margin-top: 3%" src="<%out.print(goods.getGoodsPhotot());%>">
                                        <img style="width: 75%;margin-top: 3%" src="<%out.print(goods.getGoodsPhotos());%>">
                                    </div>
                                </div>

                            </div>
                            <div class="layui-tab-item">
                                <%if (comlist.size()>0){for (CommentTableEntity com:comlist){ %>
                                <div class="layui-card" style="width: 100%;float: left">
                                    <div class="layui-card-header">用户:<%out.print(com.getCommentUsername());%>  发表于 <%out.print(com.getCommentDate());%> <%if (username.equals(com.getCommentUsername())){%> <a href="${pageContext.request.contextPath}/CommentServlet.do?hidden=delcom&delid=<%out.print(com.getCommentId());%>" style="margin-left: 60%"><input type="button" class="layui-btn" value="删除"></a><%}%></div>
                                    <div class="layui-card-body">
                                        <%out.print(com.getCommentMain());%>
                                    </div>
                                </div>
                                <% }}else{out.print("暂无评价");}%>
                            </div>
                        </div>
                    </div>


            </div>
        </div>
        </div>
</div>
</body>
</html>
