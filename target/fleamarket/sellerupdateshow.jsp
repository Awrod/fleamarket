<%@ page import="com.fleamarket.domain.GoodsTableEntity" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品</title>
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

        function loadImageFile() {
            oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
            oFReader.onload = function (oFREvent) {
                document.getElementById("uploadPreview").src = oFREvent.target.result;
            };
            if (document.getElementById("uploadImage").files.length === 0) { return; }
            var oFile = document.getElementById("uploadImage").files[0];
            if (!rFilter.test(oFile.type)) { alert("必须是图片格式！！"); return; }
            oFReader.readAsDataURL(oFile);
        }
        function loadImageFileo() {
            oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
            oFReader.onload = function (oFREvent) {
                document.getElementById("uploadPreviewoo").src = oFREvent.target.result;
            };
            if (document.getElementById("uploadImageoo").files.length === 0) { return; }
            var oFile = document.getElementById("uploadImageoo").files[0];
            if (!rFilter.test(oFile.type)) { alert("必须是图片格式！！"); return; }
            oFReader.readAsDataURL(oFile);
        }
        function loadImageFilet() {
            oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
            oFReader.onload = function (oFREvent) {
                document.getElementById("uploadPreviewtt").src = oFREvent.target.result;
            };
            if (document.getElementById("uploadImagett").files.length === 0) { return; }
            var oFile = document.getElementById("uploadImagett").files[0];
            if (!rFilter.test(oFile.type)) { alert("必须是图片格式！！"); return; }
            oFReader.readAsDataURL(oFile);
        }
        function loadImageFiles() {
            oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
            oFReader.onload = function (oFREvent) {
                document.getElementById("uploadPreviewss").src = oFREvent.target.result;
            };
            if (document.getElementById("uploadImagess").files.length === 0) { return; }
            var oFile = document.getElementById("uploadImagess").files[0];
            if (!rFilter.test(oFile.type)) { alert("必须是图片格式！！"); return; }
            oFReader.readAsDataURL(oFile);
        }

    </script>
    <script>
        //保证表单的数量必须是数字
        function chkPrice(obj){
            var pattern = obj.replace(/[0-9\.]+/g,"");
            var price= document.getElementById("goodsPrice");
            if (pattern.length>0){
                document.getElementById("goodsPrice").value="";
                document.getElementById("goodsPricetext").value="必须为数字并且大于0！";
            }else {
                if (parseInt(price.value)>0){
                    document.getElementById("goodsPricetext").value = "";
                }else{
                    document.getElementById("goodsPrice").value="";
                    document.getElementById("goodsPricetext").value="必须为数字并且大于0！";
                }
            }
        }function chkQuantity(obj){
            var pattern = obj.replace(/[0-9\.]+/g,"");
            var price= document.getElementById("goodsQuantity");
            if (pattern.length>0){
                document.getElementById("goodsQuantity").value="";
                document.getElementById("goodsQuantitytext").value="必须为数字并且大于0！";
            }else{
                if (parseInt(price.value)>0){
                    document.getElementById("goodsQuantitytext").value = "";
                }else{
                    document.getElementById("goodsQuantity").value="";
                    document.getElementById("goodsQuantitytext").value="必须为数字并且大于0！";
                }
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
            margin: -17% 60% 0 -25%;
        }
    </style>
</head>
<body class="layui-layout-body parent">
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
            <%--修改商品--%>
            <h2>${mes}</h2>
            <div class="layui-tab-item layui-show">
                <form id="addform" class="layui-form child"  enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/GoodsServlet.do">
                    <input type="hidden" name="hidden" value="updategoods">
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="margin-right: 3%">商品图片:</label>
                        <div class="layui-input-block">
                            <% GoodsTableEntity goods=(GoodsTableEntity)request.getAttribute("goods");%>
                            <input type="hidden" name="goodsId" value="<%out.print(goods.getGoodsId());%>">
                            <img id="uploadPreview" src="<%out.print(goods.getGoodsPhoto());%>"  style="width: 150px;height: 150px" >
                            <input type="file" required="required" style="margin-left: 3%"   id="uploadImage" onchange="loadImageFile()" name="goodsPhoto">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="margin-right: 3%">商品详情图一:</label>
                        <div class="layui-input-block">
                            <img id="uploadPreviewoo"  src="<%out.print(goods.getGoodsPhotoo());%>"  style="width: 150px;height: 150px" >
                            <input type="file" style="margin-left: 3%" lay-verify="required"  id="uploadImageoo" onchange="loadImageFileo()" name="goodsPhotoo">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="margin-right: 3%">商品详情图二:</label>
                        <div class="layui-input-block">
                            <img id="uploadPreviewtt" src="<%out.print(goods.getGoodsPhotot());%>"   style="width: 150px;height: 150px" >
                            <input type="file" style="margin-left: 3%" lay-verify="required"  id="uploadImagett" onchange="loadImageFilet()" name="goodsPhotot">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="margin-right: 3%">商品详情图三:</label>
                        <div class="layui-input-block">
                            <img id="uploadPreviewss" src="<%out.print(goods.getGoodsPhotos());%>"   style="width: 150px;height: 150px" >
                            <input type="file" style="margin-left: 3%" lay-verify="required"  id="uploadImagess" onchange="loadImageFiles()" name="goodsPhotos">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称:</label>
                        <div class="layui-input-block">
                            <input type="text" style="width: 300px" value="<%out.print(goods.getGoodsName());%>"  lay-verify="required"   id="goodsName"  name="goodsName"   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label  class="layui-form-label">商品类型:</label>
                        <div style="width: 300px" class="layui-input-block">
                            <select  name="goodsType" lay-verify="required">
                                <option value=""></option>
                                <option value="男装">男装</option>
                                <option value="女装">女装</option>
                                <option value="男鞋/女鞋">男鞋/女鞋</option>
                                <option value="美妆/个人护理">美妆/个人护理</option>
                                <option value="手表/眼镜/珠宝首饰">手表/眼镜/珠宝首饰</option>
                                <option value="手机/电脑/数码">手机/电脑/数码</option>
                                <option value="零食">零食</option>
                                <option value="家电/医药保健">家电/医药保健</option>
                                <option value="图书音像">图书音像</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品数量:</label>
                        <div class="layui-input-block">
                            <input type="text" value="<%out.print(goods.getGoodsQuantity());%>" style="width: 300px;float: left"  lay-verify="required" onchange="chkQuantity(this.value);"   id="goodsQuantity"  name="goodsQuantity"   class="layui-input">
                            <input  type="text" id="goodsQuantitytext" readonly="readonly" style="border: 0px;color: red;float: left;height: 35px;margin-left: 10px">
                        </div>
                    </div><div class="layui-form-item">
                        <label class="layui-form-label">商品价格:</label>
                        <div class="layui-input-block">
                            <input type="text" value="<%out.print(goods.getGoodsPrice());%>" style="width: 300px;float: left"  lay-verify="required" onchange="chkPrice(this.value);"   id="goodsPrice"  name="goodsPrice"   class="layui-input">
                            <input  type="text" id="goodsPricetext" readonly="readonly" style="border: 0px;color: red;float: left;height: 35px;margin-left: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">商品介绍:</label>
                        <div class="layui-input-block">
                            <textarea name="goodsSynopsis"   lay-verify="required" id="goodsSynopsis"   placeholder="请输入内容" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item" style="margin-left: 15%">
                        <div class="layui-input-block">
                            <button class="layui-btn"  lay-submit lay-filter="formDemo">修改商品</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © fleamarket - 底部固定区域
    </div>
</div>
</body>
</html>
