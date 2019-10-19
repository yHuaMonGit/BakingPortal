<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/6/23
  Time: 6:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <title>订单备注信息</title>
    <link rel="stylesheet" type="text/css" href="./static/css/classify/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="./static/css/classify/base.css">
    <link rel="stylesheet" href="./static/css/ordernotes/order_notes_without.css">

    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/ordernotes/ordernotes.js"></script>
</head>
<body>
<div class="incomplete_order">
    <p>您还未选购商品！</p>
    <p>请将商品添加至购物车再下单哦！</p>
</div>
<div class="main">
    <img src="./static/img/classify/emptyShoppinCart.png">
</div>

<div class="btn">
    <button class="without-com-btn" onclick="backToClassify()">返回购买商品</button>
</div>





</body>
</html>
