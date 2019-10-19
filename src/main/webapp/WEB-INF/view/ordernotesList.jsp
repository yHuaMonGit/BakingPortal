<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/6/26
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>

    <link rel="stylesheet" href="./static/css/ordernotes/order_list.css">
    <link rel="stylesheet" href="./static/css/ordernotes/window.css">

    <script type="text/javascript" src="./static/js/ordernotes/ordernotesList.js"></script>


</head>
<body>


<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed">
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-return"></i>
        </a>
        <div class="aui-center">
            <span class="aui-center-title">我的订单</span>
        </div>
        <a href="javascript:;" class="aui-navBar-item">会员中心
        </a>
    </header>
    <section class="aui-scrollView">
        <div class="aui-order-title">
            <h2>全部订单</h2>
        </div>
    </section>
</section>

<script>initListPages(${pagesInfo})</script>

</body>
</html>
