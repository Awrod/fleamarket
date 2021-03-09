<%@ page import="com.fleamarket.domain.OrderTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/2
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看订单</title>
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
            xmlhttp.send(null);
        }
        function startRequest2(){
            createXmlHttpRequest();
            xmlhttp.onreadystatechange = handleStateChange2;
            var userMoblie = document.getElementById("userMoblie");
            var url = "${pageContext.request.contextPath}/UserServlet.do?hidden=phone&phone="+userMoblie.value;
            xmlhttp.open("GET",url,true);
            xmlhttp.send(null);
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
    <script>
        function sumbit_click(){
            var gnl=confirm("确认已收到商品?");
            if (gnl==true){
                return true;
            }else{
                return false;
            }
        } function sumbit_delclick(){
            var gnl=confirm("确认退货?");
            if (gnl==true){
                return true;
            }else{
                return false;
            }
        }
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
<div class="layui-tab" style="width: 100%;height: 600px">
    <ul class="layui-tab-title">
        <li class="layui-this">待发货</li>
        <li>待收货</li>
        <li>已完成</li>

    </ul>
    <div class="layui-tab-content">
        <%List<OrderTableEntity> orderlist=(List<OrderTableEntity>)request.getAttribute("orderlistshipped");
        List<OrderTableEntity> orderlist2=(List<OrderTableEntity>)request.getAttribute("orderlistdispatched");
        List<OrderTableEntity> orderlist3=(List<OrderTableEntity>)request.getAttribute("orderlistover");
            OrderTableEntity ordern;
            OrderTableEntity ordery;
            OrderTableEntity orderf;
            OrderTableEntity ordert;
            OrderTableEntity orderok;
        %>
        <div class="layui-tab-item layui-show">
            <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>订单标号</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>购买时间</th>
                    <th>购买数量</th>
                    <th>手机号</th>
                    <th>收货地址</th>
                    <th>订单状态</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (OrderTableEntity order:orderlist){
                        if ("等待发货".equals(order.getOrderbState())){
                            ordern=order;
                        %>

                <tr>
                    <td><%out.print(ordern.getOrderId());%></td>
                    <td><%out.print(ordern.getOrdergName());%></td>
                    <td><%out.print(ordern.getOrderPrice());%></td>
                    <td><%out.print(ordern.getOrderDate());%></td>
                    <td><%out.print(ordern.getOrdergQuantity());%></td>
                    <td><%out.print(ordern.getOrderMoblie());%></td>
                    <td><%out.print(ordern.getOrderAddr());%></td>
                    <td><%out.print(ordern.getOrdersState());%></td>
                </tr>
                <%}}%>
                </tbody>
            </table>
            <%   int pageall=(int)request.getAttribute("page1");
                for (int i=1;i<=pageall;i++){
            %>
            <a style="margin-left: 2%" href="${pageContext.request.contextPath}/OrderServlet.do?hidden=page&ppage1=<%out.print((i-1)*5);%>&ppage2=<%out.print(0);%>&ppage3=<%out.print(0);%>&npage=<%out.print(5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
            <%}%>
        </div>
        <div class="layui-tab-item">
            <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>订单标号</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>购买时间</th>
                    <th>购买数量</th>
                    <th>手机号</th>
                    <th>收货地址</th>
                    <th>订单状态</th>
                    <th>确认收货</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (OrderTableEntity order:orderlist2){
                        if ("等待确认收货".equals(order.getOrderbState())){
                            ordery=order;
                        %>
                <tr>
                    <td><%out.print(ordery.getOrderId());%></td>
                    <td><%out.print(ordery.getOrdergName());%></td>
                    <td><%out.print(ordery.getOrderPrice());%></td>
                    <td><%out.print(ordery.getOrderDate());%></td>
                    <td><%out.print(ordery.getOrdergQuantity());%></td>
                    <td><%out.print(ordery.getOrderMoblie());%></td>
                    <td><%out.print(ordery.getOrderAddr());%></td>
                    <td><%out.print(ordery.getOrdersState());%></td>
                    <td><a href="${pageContext.request.contextPath}/OrderServlet.do?hidden=useruporder&orderId=<%out.print(ordery.getOrderId());%>&orderGid=<%out.print(ordery.getOrderGid());%>&storeusername=<%out.print(ordery.getOrdersName());%>"><input type="button" onclick="return sumbit_click()" class="layui-btn" value="确认收货"></a></td>

                </tr>
                <%}}%>
                </tbody>
            </table>
            <%   int pageall2=(int)request.getAttribute("page2");
                for (int i=1;i<=pageall2;i++){
            %>
            <a style="margin-left: 2%" href="${pageContext.request.contextPath}/OrderServlet.do?hidden=page&ppage1=<%out.print(0);%>&ppage2=<%out.print((i-1)*5);%>&ppage3=<%out.print(0);%>&npage=<%out.print(5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
            <%}%>
        </div>
        <div class="layui-tab-item">
            <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>订单标号</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>购买时间</th>
                    <th>购买数量</th>
                    <th>手机号</th>
                    <th>收货地址</th>
                    <th>订单状态</th>
                    <th>退货</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (OrderTableEntity order:orderlist3){
                        if ("已收货".equals(order.getOrderbState())){
                            orderf=order;
                %>
                <tr>
                    <td><%out.print(orderf.getOrderId());%></td>
                    <td><%out.print(orderf.getOrdergName());%></td>
                    <td><%out.print(orderf.getOrderPrice());%></td>
                    <td><%out.print(orderf.getOrderDate());%></td>
                    <td><%out.print(orderf.getOrdergQuantity());%></td>
                    <td><%out.print(orderf.getOrderMoblie());%></td>
                    <td><%out.print(orderf.getOrderAddr());%></td>
                    <td><%out.print(orderf.getOrdersState());%></td>
                    <td><a href="${pageContext.request.contextPath}/OrderServlet.do?hidden=delorder&orderId=<%out.print(orderf.getOrderId());%>&ordergQuantity=<%out.print(orderf.getOrdergQuantity());%>"><input type="button" onclick="return sumbit_delclick()" class="layui-btn" value="退货"></a></td>
                </tr>
                <tr>
                <%}else if("退货中".equals(order.getOrderbState())){
                    ordert=order;
                        %>
                <td><%out.print(ordert.getOrderId());%></td>
                <td><%out.print(ordert.getOrdergName());%></td>
                <td><%out.print(ordert.getOrderPrice());%></td>
                <td><%out.print(ordert.getOrderDate());%></td>
                <td><%out.print(ordert.getOrdergQuantity());%></td>
                <td><%out.print(ordert.getOrderMoblie());%></td>
                <td><%out.print(ordert.getOrderAddr());%></td>
                <td><%out.print(ordert.getOrdersState());%></td>
                <td>退货中</td>
                </tr>
                    <%}else if("已退货".equals(order.getOrderbState())){
                        orderok=order;
                    %>
                <tr>
                    <td><%out.print(orderok.getOrderId());%></td>
                    <td><%out.print(orderok.getOrdergName());%></td>
                    <td><%out.print(orderok.getOrderPrice());%></td>
                    <td><%out.print(orderok.getOrderDate());%></td>
                    <td><%out.print(orderok.getOrdergQuantity());%></td>
                    <td><%out.print(orderok.getOrderMoblie());%></td>
                    <td><%out.print(orderok.getOrderAddr());%></td>
                    <td><%out.print(orderok.getOrdersState());%></td>
                    <td>已退货</td>
                </tr>
                    <%}}%>
                </tbody>
            </table>
            <%   int pageall3=(int)request.getAttribute("page3");
                for (int i=1;i<=pageall3;i++){
            %>
            <a style="margin-left: 2%" href="${pageContext.request.contextPath}/OrderServlet.do?hidden=page&ppage1=<%out.print(0);%>&ppage2=<%out.print(0);%>&ppage3=<%out.print((i-1)*5);%>&npage=<%out.print(5);%>"><input type="button" class="layui-btn" value="<%out.print(i);%>"></a>
            <%}%>
        </div>
    </div>
</div>

</body>
</html>
