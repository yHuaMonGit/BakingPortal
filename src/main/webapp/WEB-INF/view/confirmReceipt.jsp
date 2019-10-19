<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/6/26
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <title>确认收货</title>
    <link rel="stylesheet" type="text/css" href="./static/css/classify/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="./static/css/classify/base.css">
    <link rel="stylesheet" href="./static/css/ordernotes/order_notes.css">
    <link rel="stylesheet" href="./static/css/ordernotes/window.css">

    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/ordernotes/ordernotes.js"></script>
    <script type="text/javascript" src="./static/js/ordernotes/window.js"></script>

</head>
<body>
<div class="incomplete_order success_back">
    <p>确认收货</p>
    <p>请您在收到货品后确认收货哦</p>
</div>
<div class="main">
    <div class="invoice">
        <div id="order-count-display">已点3个菜品</div>
    </div>

    <ul class="food_list" id="preorder-com-list"></ul>


    <div class="invoice">
        <div>商品小计：</div>
        <div class="food_price" id="preorder-com-total-amount">¥ 56.6</div>
    </div>
    <div class="invoice">
        <div>会员立减：</div>
        <div class="food_price"  id="preorder-com-dec-amount">¥ 4</div>
    </div>
    <div class="invoice beizhu">
        <div>备注</div>
        <span id="preorder-remark"></span>
    </div>
    <div class="total_price">合计：<span id="preorder-com-act-amount">¥ 60.6</span></div>
</div>

<div class="btn">
    <button class="btn1" onclick="backToMemberCenter()">会员中心</button>
    <a ><button class="btn2" onclick="confirmReceipt()">确认收货</button></a>
</div>

<script>
    initPages(${pagesInfo});
</script>

</body>
</html>
