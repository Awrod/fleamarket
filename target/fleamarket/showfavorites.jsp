<%@ page import="com.fleamarket.domain.FavoritesTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/27
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收藏夹</title>
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
                out.print("<a style=\"font-family: 华文中宋;font-size: 15px;\" id=\"login_register\"><cite><i class=\"layui-icon layui-icon-user\"> 登入/注册</i></cite></a>");
            }
        %>
        </li>
    </ul>
</div>

</div>
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
        <%
            List<FavoritesTableEntity> favolist = (List<FavoritesTableEntity>)request.getAttribute("fvaolist");
            if (!favolist.isEmpty()){
                for (FavoritesTableEntity favo:favolist) {
        %>
        <a href="${pageContext.request.contextPath}/GoodsServlet.do?hidden=linkusergoods&path=<%out.print(favo.getGoodsId());%>">
            <div style="float: left;width: 200px;height: 170px;margin-right: 10px">
                <div style="width: 200px;height: 150px;border: 2px black;">
                    <img style="width: 200px;height: 150px;" src="<% out.print(favo.getGoodsPhoto());%>">
                </div>
                <div style="width: 200px;border: 2px black;text-align: center">
                    <% out.print(favo.getGoodsName());%>
                </div>
                <div style="width: 200px;height: 20px;border: 2px black;text-align: center">
                    <a href="${pageContext.request.contextPath}/FavoritesServlet.do?hidden=delfavo&id=<%out.print(favo.getFavoritesId());%>"><input type="button" class="layui-btn" value="移出收藏夹"></a>
                </div>
            </div>
        </a>
        <% } }else{out.print("暂无收藏");}%>
    </div>
</div>
</body>
<%-- 登入/注册 --%>
<div class="layui-tab" id="re_lg" style="display: none">
    <ul class="layui-tab-title">
        <li class="layui-this">登入</li>
        <li>注册</li>
    </ul>
    <div class="layui-tab-content">

        <div class="layui-tab-item layui-show">
            <form id="login" class="layui-form" style="margin-top: 6%"  action="<%=request.getContextPath()%>/UserServlet.do" method="post">
                <input type="hidden" name="hidden" value="login">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名:</label>
                    <div class="layui-input-block">
                        <input type="text"  style="width: 300px" name="userName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        <input type="text" readonly="readonly" style="border: 0px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码:</label>
                    <div class="layui-input-inline">
                        <input type="password" style="width: 300px" name="userPass" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                        <input  type="text" readonly="readonly" style="border: 0px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">验证码:</label>
                    <div class="layui-input-block">
                        <input type="text" style="width: 200px;float: left" name="validationCode" id="validationCode" placeholder="请输入验证码" lay-verify="required" autocomplete="off" class="layui-input">
                        <img src="validate.jsp" style="float: left" id="validationCode_img" title="看不清?换一个" onclick="loadimage();return false;" name="validationCode_img" align="middle">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: 20%">
                    <div class="layui-input-block">
                        <button id="login_click"  class="layui-btn" lay-submit lay-filter="formDemo">登入</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="layui-tab-item ">
            <form id="register" class="layui-form"  style="margin-top: 2%" action="<%=request.getContextPath()%>/UserServlet.do" method="post">
                <input type="hidden" name="hidden" value="register">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名:</label>
                    <div class="layui-input-block">
                        <input type="text" style="width: 300px" maxlength="12" id="username" onchange="startRequest()" name="userName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        <input  id="userNameMsg"  readonly="readonly"   type="text" style="border: 0px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码:</label>
                    <div class="layui-input-inline">
                        <input type="password" style="width: 300px" maxlength="11" name="userPass" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                        <input  type="text" style="border: 0px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号:</label>
                    <div class="layui-input-block">
                        <input type="text" style="width: 300px" maxlength="11" onchange="startRequest2()" name="userMoblie" id="userMoblie" required  lay-verify="required" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                        <input id="usermoblieMsg" readonly="readonly"  type="text" style="border: 0px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">注册类型:</label>
                    <div style="width: 300px" class="layui-input-block">
                        <select   name="userType" lay-verify="required">
                            <option value=""></option>
                            <option value="买家">买家</option>
                            <option value="卖家">卖家</option>
                        </select>
                    </div>
                    <input  type="text" style="border: 0px;">
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">单选框:</label>
                    <div class="layui-input-block">
                        <input type="radio" name="userSex" value="男" title="男">
                        <input type="radio" name="userSex" value="女" title="女" checked>
                    </div>
                    <input  type="text" style="border: 0px;">
                </div>
                <div class="layui-form-item" style="margin-left: 15%">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">注册</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</html>