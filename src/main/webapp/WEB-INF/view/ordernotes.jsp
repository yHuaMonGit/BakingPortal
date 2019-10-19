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
    <link rel="stylesheet" href="./static/css/ordernotes/order_notes.css">
    <link rel="stylesheet" href="./static/css/ordernotes/window.css">

    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/ordernotes/ordernotes.js"></script>
    <script type="text/javascript" src="./static/js/ordernotes/window.js"></script>
    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
</head>
<body>
<div class="incomplete_order">
    <p>待付款</p>
    <p>支付完成后订单才会下到厨房呦</p>
</div>
<div class="main">
    <div class="invoice">
        <div id="order-count-display"></div>
    </div>

    <ul class="food_list" id="preorder-com-list"></ul>

    <div class="invoice">
        <div>备注</div>
        <textarea class="remark" id="preorder-remark-enter" placeholder="请输入您想说的"></textarea>
    </div>

    <div class="invoice total">
        <div>商品小计：</div>
        <div class="food_price" id="preorder-com-total-amount">¥ 56.6</div>
    </div>
    <div class="invoice">
        <div>会员立减：</div>
        <div class="food_price" id="preorder-com-dec-amount">¥ 4</div>
    </div>
    <div class="total_price" >合计：<span id="preorder-com-act-amount">¥ 60.6</span></div>
</div>
<div class="pay_notice">15分钟内未支付系统将自动取消订单</div>
<div class="btn">
    <button class="btn1" onclick="cancelOrder()">取消订单</button>
    <a ><button class="btn2" onclick="submitToPayment()">付款</button></a>
</div>

<script>
    initPages(${pagesInfo});
</script>
</body>
</html>
