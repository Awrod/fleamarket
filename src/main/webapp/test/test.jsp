<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/1/8
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;

        });
        function cx() {
            var c=document.getElementById('c');
            var x=document.getElementById('x');
            c.value=document.getElementById('cc').value;
            x.value=document.getElementById('xx').value;
        }
        function cxcx(didi) {
            var price=document.getElementById("price"+didi+"");
            var priceinit=document.getElementById("qian"+didi+"");
            price.value=parseInt(priceinit.value)+parseInt(price.value);
        }
    </script>
</head>
<body onload="cx()">
<%
    int c=0,x=0,q=0;
    for (int i=0;i<3;i++){
        c++;
        x+=i;
    }
%>
<form class="layui-form"  action="${pageContext.request.contextPath}/TESTServlet.do" method="post">
    <input type="hidden" id="cc"  value="<%out.print(c);%>"/>
    <input type="hidden" id="xx" value="<%out.print(x);%>"/>
    <input type="text" name="cccc" value="12">
    <input type="text" name="cccc" value="34">
    <input type="text" name="cccc" value="56">
    <%for (int i=1;i<=3;i++){%>
    <input type="hidden" id="qian<%out.print(i);%>" value="56"/>
    <input type="text" name="xxxx" value="56"><input type="button" onclick="cxcx(<%out.print(i);%>)" value="-ppppppp"><input type="text"  id="price<%out.print(i);%>" name="price" value="56">
    <%}%>

   共计： <input type="text" id="c" >件 总价：<input type="text" id="x" >元
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
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
