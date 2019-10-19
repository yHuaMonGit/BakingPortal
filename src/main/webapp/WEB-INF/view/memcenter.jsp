<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/4/17
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>友佳烘焙会员中心</title>
    <link rel="stylesheet" href="">
    <link href="../static/css/memcenter/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/memcenter/style.css" rel="stylesheet">
    <script src="../static/js/memcenter/jquery.min.js"></script>
    <script src="../static/js/memcenter/bootstrap.min.js"></script>
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="../static/css/memcenter/menu_elastic.css" />
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="../static/js/memcenter/snap.svg-min.js"></script>
    <script src="../static/js/memcenter/classie.js"></script>
    <script src="../static/js/memcenter/member.js"></script>
    <!--[if IE]>
    <script src="../static/js/memcenter/html5.js"></script>
    <![endif]-->
</head>
<body class="huibg">
<div class="vipcenter" id="vipCenter">

    <div class="vipheader" id="vipHead">


        <script>
            getIcon1("vipHead",${userInfoList})
            getName("vipHead",${userInfoList})

        </script>
        <div class="gztt" id="auth-div"></div>

    </div>
    <div class="vipsan" id="vipSpan">
        <div class="col-xs-4 text-center"><a ><h4>等级</h4><p id="level">
            <script>getmemberInfo(${userInfoList},"level")</script>
        </p></a></div>
        <div class="col-xs-4 text-center"><a ><h4>积分</h4><p id="integral">
            <script>getmemberInfo(${userInfoList},"integral")</script>
        </p></a></div>
        <div class="col-xs-4 text-center"><a ><h4>账户余额</h4><p id="balance">
            <script>getmemberInfo(${userInfoList},"balance")</script>
        </p></a></div>
    </div>
    <ul class="vipul" id="vipUl">
        <li>
            <a href="#" id="member-auth-a" onclick="authenMember()">
                <div class="icc"><i class="fa fa-address-card-o" ></i></div>
                <div class="lzz">会员认证</div>
                <div class="rizi lvzi" id="auth-div-sp"></div>
                <script>
                    init(${userInfoList})
                </script>
            </a>
        </li>

        <li>
            <a onclick="gotoVipCenter()">
                <div class="icc"><i class="fa fa-user-circle-o" ></i></div>
                <div class="lzz">会员权益</div>
                <div class="rizi"><i class="fa fa-angle-double-right"></i></div>
            </a>
        </li>

        <li>
            <a onclick="gotoAddress()">
                <div class="icc"><i class="fa fa-location-arrow" ></i></div>
                <div class="lzz">地址编辑</div>
                <div class="rizi"><i class="fa fa-angle-double-right"></i></div>
            </a>
        </li>

        <li>
            <a onclick="gotoShop()">
                <div class="icc"><i class="fa fa-shopping-bag" ></i></div>
                <div class="lzz">在线商城</div>
                <div class="rizi"><i class="fa fa-angle-double-right"></i></div>
            </a>
        </li>

        <li>
            <a onclick="gotoOrderList()">
                <div class="icc"><i class="fa fa-list-alt" ></i></div>
                <div class="lzz">订单查询</div>
                <div class="rizi"><i class="fa fa-angle-double-right"></i></div>
            </a>
        </li>


    </ul>

    <script>
        memberRegister("vipCenter",${userInfoList})
    </script>

</div>
<div class="footnav">

</div>



</body>
</html>
