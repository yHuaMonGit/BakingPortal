<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/4/27
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name = "format-detection" content ="telephone=no">
    <title>代理</title>
    <link rel="stylesheet" type="text/css" href="../static/css/memprivilege/global.css">
    <link rel="stylesheet" type="text/css" href="../static/css/memprivilege/swiper.min.css">

    <script src="../static/js/memprivilege/jquery-1.8.3.min.js"></script>
    <script src="../static/js/memprivilege/swiper.jquery.min.js"></script>
    <script src="../static/js/memprivilege/memprivilege.js"></script>
</head>
<body>
<header>代理级别</header>
<div>
    <!--切换-->
    <section class="swiper-container" >
        <section class="swiper-wrapper">
            <section class="swiper-slide">
                <section class="agent_level">
                    <section class="levelbox level01">
                        <p class="mb10 clearfix"><span class="fl"><img alt="" id="member-head-img" src="../static/img/memprivilege/toux.jpg"></span><span class="white f15 name" id="member-name">
                            <script>getmemberheadname('${member_info}')</script>
                        </span></p>
                        <p class="f15 white" id="member-level"><script>getmemberlevel('${member_info}')</script></p>
                        <p class="f12 white">您的当前级别</p>
                        <p class="levelIcon"><img alt="" src="../static/img/memprivilege/levelIcon.png"></p>
                    </section>
                </section>
            </section>


        </section>
    </section>
    <!--等级内容-->
    <section class="level_con">
        <section class="pd15">
            <h4 class="f16" style="color:#2274CF" id="member-privilege-q1">
                <script>getmemberQ1('${member_info}')</script>
            </h4>
            <p class="f14" id="member-privilege-a1">
                <script>getmemberA1('${member_info}')</script>
            </p>
            <h4 class="f16" style="color:#2274CF" id="member-privilege-q2">您当前的会员权益：</h4>
            <p class="f14" id="member-privilege-a2">
                <script>getmemberA2('${member_info}')</script>
                <br>注1：会员认证后消费会有积分
                <br>注2：会员等级上升，积分获取更多
            </p>
            <a href="javascript:void(0)" class="button">我知道了</a> </section>
    </section>

</div>
<script>
    $(function(){
        var mySwiper = new Swiper(".swiper-container", {
            slidesPerView: "auto",
            centeredSlides: !0,
            watchSlidesProgress: !0,
            pagination: ".swiper-pagination",
            onProgress: function(a) {
                var b, c, d;
                for (b = 0; b < a.slides.length; b++) c = a.slides[b],
                    d = c.progress,
                    scale = 1 - Math.min(Math.abs(.2 * d), 1),
                    es = c.style,
                    //透明度的改变，原代码如下   es.opacity = 1 - Math.min(Math.abs(d / 2), 1),
                    es.opacity = 1,
                    es.webkitTransform = es.MsTransform = es.msTransform = es.MozTransform = es.OTransform = es.transform = "translate3d(0px,0," + -Math.abs(150 * d) + "px)"
            },
            onSetTransition: function(a, b) {
                for (var c = 0; c < a.slides.length; c++) es = a.slides[c].style,
                    es.webkitTransitionDuration = es.MsTransitionDuration = es.msTransitionDuration = es.MozTransitionDuration = es.OTransitionDuration = es.transitionDuration = b + "ms"
            },
            onTransitionEnd:function(swiper){
                console.log(swiper.activeIndex)
                $(".level_con").hide();
                $(".level_con").eq(swiper.activeIndex).show();
            }
        });
    });
</script>
</body>
</html>
