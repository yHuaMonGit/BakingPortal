<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/5/25
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <title>友佳外卖点餐系统</title>
    <link rel="stylesheet" type="text/css" href="./static/css/classify/base.css">
    <link rel="stylesheet" href="./static/css/classify/classify.css">
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/classify/classify.js"></script>
    <script type="text/javascript" src="./static/js/classify/add.js"></script>
</head>
<body>

<script>
    checkCertification(${memberFlg})
</script>

<div class="header">
    <div class="content-wrapper">
        <div class="avatar">
            <img width="64" height="64" src="./static/img/classify/head_icon.jpg">
        </div>
        <div class="content">
            <div class="content_title">
                <span class="brand"></span>
                <span class="name">友佳烘焙（候车大厅店）</span>
            </div>
            <div class="description"> 专注品质，匠心烘焙 </div>
            <div class="support">
                <span class="icon decrease"></span>
                <span class="text"></span>
            </div>
        </div>
    </div>
    <div class="bulletin-wrapper">
        <span class="bulletin-title"></span>
        <span class="bulletin-text" id="announcement-text">
            <script>
                getNotice();
            </script>
        </span>
        <i class="icon-keyboard_arrow_right"></i>
    </div>
    <div class="background">
        <img width="100%" height="100%" src="./static/img/classify/head_icon.jpg">
    </div>
    <!--loading 提示信息-->

</div>
<div class="main">
    <div class="left-menu" id="left">
        <ul id="com-list">
            <script>
                initComList();
            </script>

        </ul>
    </div>
    <div class="con">
        <div class="right-con con-active" style="display: none;">
            <ul id="com-menu-ul">
                <script>initFirstBasicCom()</script>
            </ul>
        </div>

    </div>
    <div class="up1"></div>
    <div class="shopcart-list fold-transition">
        <div class="list-header">
            <h1 class="title">购物车</h1>
            <span class="empty">清空所有</span>
        </div>
        <div class="list-content">
            <ul id="shoppingcart-list-table"></ul>
        </div>
    </div>
    <div class="footer">
        <div class="left">
            <div class="count_num"><span id="totalcountshow"></span></div>
            <span id="cartN" class="nowrap">总计：<span id="totalpriceshow">0</span>元</span>
        </div>
        <div class="right">
            <a id="btnselect" class="xhlbtn"  onclick="submitOrder()">去结算</a>
        </div>
    </div>
</div>
<!--商品信息 - 弹出框-->
<div class="subFly">
    <div class="up"></div>
    <div class="down">
        <a class="close" href="javascript:">
            <img src="./static/img/classify/close.png" alt="">
        </a>
        <dl class="subName">
            <dt><img class="imgPhoto" src="./static/img/classify/demo.jpg"></dt>
            <dd>
                <p data-icon="" id="classify-addGoods-prama"></p>
                <p><span>¥ </span><span class="pce" id="stander-pce"></span></p>
                <p>
                    <span>库存：</span>
                    <span class="choseValue" id="stander-choseValue"></span>
                    <span></span>
                </p>
            </dd>
        </dl>

        <dl class="subChose" id="classify-chose-stander"> </dl>

        <dl class="subCount" style="">
            <dt>购买数量</dt>
            <dd class="mydd">
                <div class="btn" style="padding-top:5px;">
                    <button class="ms" style="display: inline-block;">
                        <strong></strong>
                    </button>
                    <i class="mask_number">1</i>
                    <button class="ad">
                        <strong></strong>
                    </button>
                    <i class="price">0</i>
                </div>
            </dd>
        </dl>
        <div class="foot">
            <span>加入购物车</span>
        </div>
    </div>
</div>


</body>
</html>
