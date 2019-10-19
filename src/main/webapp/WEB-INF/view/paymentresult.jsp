<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/6/25
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <title>支付结果</title>
    <link rel="stylesheet" type="text/css" href="./static/css/classify/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="./static/css/classify/base.css">
    <link rel="stylesheet" href="./static/css/payment/payment_results.css">
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/payment/payment.js"></script>
</head>
<body>
<div class="incomplete_order">
    <p>支付成功</p>
    <p id="payment-result-amount"></p>
    <a onclick="checkDetail()">查看详情</a>
</div>
<div class="main_info">
    <div class="hang">
        <span>门店名称</span>
        <span id="payment-result-shopName"></span>
    </div>
    <div class="hang">
        <span>交易时间</span>
        <span id="payment-result-transTime"></span>
    </div>
    <div class="hang">
        <span>交易订单</span>
        <span id="payment-result-orderid"></span>
    </div>
</div>
<div class="invoice">
    <span>买完了，查查余额？</span>
    <span onclick="wentToMemberCenter()">会员中心</span>
</div>

<script>

    initPaymemtResultPages(${pagesInfo});

</script>

</body>
</html>
