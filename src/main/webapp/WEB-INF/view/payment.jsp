<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/6/24
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <title>确认支付</title>
    <link rel="stylesheet" type="text/css" href="./static/css/classify/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="./static/css/classify/base.css">
    <link rel="stylesheet" href="./static/css/payment/payment.css">
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/wxpay/wxpayment.js" ></script>
    <script type="text/javascript" src="./static/js/payment/payment.js"></script>

</head>
<body>
<div class="pay_top">
    <p>实付金额</p>
    <p id="paymemt-act-acmount">¥ 60.4</p>
</div>
<div class="invoice-address">
    <div>
        <span>收货地址</span>
    </div>
    <span id="payment-address-detail" onclick="changeAddress()"></span>
</div>
<div class="invoice" >
    <div class="wxlogo">微信支付</div>
    <input type="radio" name="payment" class="payment" id="wx-input">
</div>
<div class="invoice">
    <div class="viplogo" id="payment-vip-type">会员卡支付</div>
    <input type="radio" name="payment" class="payment" id="vip-input">
</div>
<div class="paydiv">
    <a ><button class="paybtn" onclick="payOrder()">确定付款</button></a>
</div>

<script>

    initPaymemtPages(${pagesInfo});

</script>

</body>
</html>
