<%@ page import="com.fleamarket.domain.UserTableEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/6
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上架书籍</title>
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
        oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

        oFReader.onload = function (oFREvent) {
            document.getElementById("uploadPreview").src = oFREvent.target.result;
        };

        function loadImageFile() {
            if (document.getElementById("uploadImage").files.length === 0) { return; }
            var oFile = document.getElementById("uploadImage").files[0];
            if (!rFilter.test(oFile.type)) { alert("必须是图片格式！！"); return; }
            oFReader.readAsDataURL(oFile);
        }

    </script>
    <script>
        //保证表单的数量必须是数字
        function chkPrice(obj){
            var pattern = obj.replace(/[0-9\.]+/g,"");
            var price= document.getElementById("bookQuantity");
            if (pattern.length>0){
                document.getElementById("bookQuantity").value="";
                document.getElementById("bookQuantitytext").value="必须为数字并且大于0！";
            }else{
                if (parseInt(price.value)>0){
                    document.getElementById("bookQuantitytext").value = "";
                }else{
                    document.getElementById("bookQuantity").value="";
                    document.getElementById("bookQuantitytext").value="必须为数字并且大于0！";
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
<body  class="layui-layout-body parent">
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
                <li class="layui-nav-item layui-nav-itemed">
                    <a>书籍管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/adminbookup.jsp">上架新书籍</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/BookServlet.do?hidden=allbook&page=0">下架书籍</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/BorderServlet.do?hidden=rebook&ppage=0">书籍返还</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <h2>${mes}</h2>
        <div class="layui-tab-item layui-show">
            <form  class="layui-form child"  enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/BookServlet.do">
                <input type="hidden" name="hidden" value="bookup">
                <div class="layui-form-item">
                    <label class="layui-form-label" style="margin-right: 3%">书籍图片:</label>
                    <div class="layui-input-block">
                        <img id="uploadPreview"  style="width: 150px;height: 150px" >
                        <input type="file" style="margin-left: 3%" lay-verify="required"  id="uploadImage" onchange="loadImageFile()" name="bookPhoto">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍名称:</label>
                    <div class="layui-input-block">
                        <input type="text" style="width: 300px"  lay-verify="required"   id="bookName"  name="bookName"   class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所在分区:</label>
                    <div class="layui-input-block">
                        <input type="text" style="width: 300px"  lay-verify="required"   id="bookSubarea"  name="bookSubarea"   class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">书籍类别:</label>
                    <div style="width: 300px" class="layui-input-block">
                        <select  name="bookType" lay-verify="required">
                            <option value=""></option>
                            <option value="社会科学">社会科学</option>
                            <option value="中文图书">中文图书</option>
                            <option value="自然科学图书">自然科学图书</option>
                            <option value="儿童读物">儿童读物</option>
                            <option value="摄影绘画集">摄影绘画集</option>
                            <option value="专业书">专业书</option>
                            <option value="外文图书">外文图书</option>
                            <option value="其他">其他</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍数量:</label>
                    <div class="layui-input-block">
                        <input type="text" style="width: 300px;float: left"  lay-verify="required" onchange="chkPrice(this.value);"   id="bookQuantity"  name="bookQuantity"   class="layui-input">
                        <input  type="text" id="bookQuantitytext" readonly="readonly" style="border: 0px;color: red;float: left;height: 35px;margin-left: 10px">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">书籍介绍:</label>
                    <div class="layui-input-block">
                        <textarea name="bookSynopsis" lay-verify="required" id="bookSynopsis"   placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: 15%">
                    <div class="layui-input-block">
                        <button class="layui-btn"  lay-submit lay-filter="formDemo">上架书籍</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>
</div>
</body>
</html>
