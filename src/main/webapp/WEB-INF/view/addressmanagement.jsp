<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2018/10/26
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>邮寄地址管理</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <link href="./static/css/addressmanage/style.css" rel="stylesheet" type="text/css"/>
    <script src="./static/js/addressmanage/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/addressmanage/addressManage.js"></script>
</head>
<body>


<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed b-line">

        <div class="aui-center">
            <span class="aui-center-title">收货地址管理</span>
        </div>
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-sys"></i>
        </a>
    </header>
    <section class="aui-scrollView">
        <div class="divHeight b-line"></div>
        <div class="aui-address-box b-line" id="address-list">
            <div class="aui-address-add b-line" onclick="addAddress()">+添加</div>
        </div>
        <div class="address_foot">
            <button class="address_foot_button" onclick="returnFun()" id="address-foot-button">返回订单</button>
        </div>
    </section>
</section>

<script>
    showAddressDiv(${addresslistJson});
</script>

</body>
</html>
