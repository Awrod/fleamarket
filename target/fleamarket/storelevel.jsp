<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/26
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评价商家</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="layer.js"></script>
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
<body class="parent">
<script>
    layui.use('rate', function(){
        var rate = layui.rate;

        //渲染
        var ins1 = rate.render({
            elem: '#testx',  //绑定元素
            value: 5,
            half: false,
            text:true,
            choose: function(value){
                document.getElementById('levelx').value=value;
            }
        });
    });
</script>
<form  class="layui-form child"  style="text-align: center"  method="post" action="${pageContext.request.contextPath}/UserServlet.do">
    <input type="hidden"  name="hidden" value="storelevel">
    <input type="hidden"  id="levelx" name="level" value="5">
    <input type="hidden"  id="goodsid" name="goodsid" value="<%out.print(request.getAttribute("goodsid"));%>">
    <h2>请为商家和商品打分,并给商品评价</h2>
    <h3  style="margin-top: 2%" id="testx"></h3><br>
        <label class="layui-form-label">请给本件商品评价:</label>
        <div class="layui-input-block" >
            <textarea name="comment" placeholder="请输入内容"  class="layui-textarea"></textarea>
        </div>
    <button style="margin-top: 2%" class="layui-btn"  lay-submit lay-filter="formDemo">提交</button>
</form>
</body>
</html>
