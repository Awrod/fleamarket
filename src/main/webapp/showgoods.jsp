<%@ page import="com.fleamarket.domain.GoodsTableEntity" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/24
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style type="text/css">
        .parent {position: relative;}
        .child {
            position: absolute;
            top: 50%;
            left: 50%;
            height: 30%;
            width: 50%;
            margin: -17% 60% 0 -25%;
        }
    </style>
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
        <div style="padding: 15px;">
           <div style="width: 100%;height: 600px">
               <%GoodsTableEntity goods=(GoodsTableEntity)request.getAttribute("goods");%>
                <div style="width: 100%;height: 300px;float: left">
                   <img style="width: 45%;height: 300px;float: left;border:2px solid #d5d5d5" src="<%out.print(goods.getGoodsPhoto());%>">
                    <form style="width: 45%;height: 300px;float: left" class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品名称:</label>
                            <div class="layui-input-block">
                                <input style="border: 0px" readonly="readonly" type="text" class="layui-input" name="goodsPrice" value="<%out.print(goods.getGoodsName());%>" >
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
                                <input style="border: 0px" readonly="readonly"  type="text" class="layui-input" name="goodsPrice" value="<%out.print(goods.getGoodsQuantity());%>" >
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">类别:</label>
                            <div class="layui-input-block">
                                <input style="border: 0px;" readonly="readonly"  type="text" class="layui-input" name="goodsPrice" value="<%out.print(goods.getGoodsType());%>" >
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="formDemo">购买</button>
                                <input style="width: 125px" class="layui-btn" value="加入购物车">
                            </div>
                        </div>
                    </form>
                </div>
               <div style="width: 100%;height: 300px;float: left;border:1px solid #d5d5d5;margin-top: 5% ">
                   <div class="layui-card">
                       <div class="layui-card-header">商品介绍:</div>
                       <div class="layui-card-body">
                           <%out.print(goods.getGoodsSynopsis());%>
                       </div>
                   </div>
               </div>
           </div>
        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        ©  fleamarket - 底部固定区域
    </div>
    </div>

</body>
</html>
