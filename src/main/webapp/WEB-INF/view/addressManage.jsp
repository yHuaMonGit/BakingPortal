<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增邮寄地址</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <link href="./static/css/addressmanage/addAddress/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!--

-->
<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed b-line">
        <a href="javascript:history.go(-1)" class="aui-navBar-item">
            <i class="icon icon-return"></i>
        </a>
        <div class="aui-center">
            <span class="aui-center-title">新增邮寄地址</span>
        </div>
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-sys"></i>
        </a>
    </header>
    <section class="aui-scrollView">
        <div class="aui-address-box">
            <div class="aui-address-cell-item">
                <div class="aui-cell-name">收件人</div>
                <div class="aui-cell-input">
                    <input type="text" class="cell-input" id="input-name" placeholder="姓名" autocomplete="off">
                </div>
            </div>
            <div class="aui-address-cell-item">
                <div class="aui-cell-name">联系手机</div>
                <div class="aui-cell-input">
                    <input type="text" class="cell-input" id="input-phone" placeholder="收件人电话号码" autocomplete="off">
                </div>
            </div>
            <div class="aui-address-cell-item">
                <div class="aui-cell-name">所在地区</div>
                <div class="aui-cell-input cell-input-text">
                    <input type="text" class="cell-input" readonly id="J_Address" placeholder="北京 北京市">
                </div>
            </div>
            <div class="aui-address-cell-item">
                <div class="aui-cell-name">详细地址</div>
                <div class="aui-cell-input">
                    <input type="text" class="cell-input" id="input-detail" placeholder="详细地址信息" autocomplete="off">
                </div>
            </div>
            <div class="aui-address-cell-item">
                <div class="aui-cell-name">邮政编号</div>
                <div class="aui-cell-input">
                    <input type="text" class="cell-input" id="input-ecode" placeholder="邮政编码号" autocomplete="off">
                </div>
            </div>
            <div class="aui-address-btn" >
                <a onclick="addAdressment()">确定</a>
            </div>
        </div>
    </section>
</section>
<script src="./static/js/addressmanage/jquery.min.js"></script>
<script src="./static/js/addressmanage/city.js"></script>
<script src="./static/js/addressmanage/address.js"></script>
<script src="./static/js/addressmanage/addressManage.js"></script>
<script>
    /**
     * 默认调用
     */
    !function() {
        var $target = $('#J_Address');

        $target.citySelect();

        $target.on('click', function(event) {
            event.stopPropagation();
            $target.citySelect('open');
        });

        $target.on('done.ydui.cityselect', function(ret) {
            $(this).val(ret.provance + ' ' + ret.city + ' ' + ret.area);
        });
    }();
    /**
     * 设置默认值
     */
</script>
</body>
</html>
