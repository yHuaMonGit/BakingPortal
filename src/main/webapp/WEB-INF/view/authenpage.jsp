<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/4/21
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>友佳烘焙会员认证</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <link href="../static/css/authenpage/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../static/js/authenpage/jquery.min.js"></script>
    <script type="text/javascript" src="../static/js/authenpage/authenpage.js"></script>

</head>
<body>


<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed">
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-return"></i>
        </a>
        <div class="aui-center">
            <span class="aui-center-title">实名认证</span>
        </div>
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-class"></i>
        </a>
    </header>
    <section class="aui-scrollView">
        <div class="aui-banner">
            <div class="aui-banner-text">
                <h1>实名认证</h1>
                <h2>为了保护您的会员卡不被他人使用</h2>
                <h3>请您完成实名认证</h3>
            </div>
        </div>
        <div class="aui-flex" style="padding-top:8px; padding-bottom:8px;">
            <div class="aui-tag">
                <img src="../static/img/authenpage/tag.png" alt="">
            </div>
            <div class="aui-flex-box">
                <p>请填写你的真实信息,通过后将不能修改</p>
            </div>
        </div>
        <div class="aui-name-form">
            <form action="">
                <div class="aui-flex aui-flex-color b-line">
                    <div class="aui-flex-box">
                        <input type="text" placeholder="真实姓名" id='t1' onkeyup="myText()" >
                    </div>
                </div>

                <div class="aui-flex aui-flex-color">
                    <div class="aui-flex-box">
                        <input type="text" placeholder="身份证" id='t2' >
                    </div>
                </div>

                <div class="aui-flex aui-flex-color">
                    <div class="aui-flex-box">
                        <input type="text" placeholder="手机号码" id='t3' onkeyup="myText()">
                        <button type="button" class="yanzhengma" onclick="sendMessage1()">发送验证码</button>
                    </div>
                </div>

                <div class="aui-flex aui-flex-color">
                    <div class="aui-flex-box">
                        <input type="text" placeholder="验证码" id='t4' onkeyup="myText()">
                    </div>
                </div>

                <div class="aui-flex">
                    <div class="aui-flex-box">
                        <button  type="button" id='btn' disabled="disabled" value="submit" onclick="submitAuth()">提交</button>
                    </div>
                </div>
                <div class="aui-flex" style="padding-top:0">
                    <div class="aui-flex-box">
                        <p>以上信息用户身份验证</p>
                    </div>
                </div>
            </form>
        </div>
        <div class="aui-name-list">
            <div class="aui-name-title">
                <h2>实名认证优势</h2>
            </div>
            <div class="aui-palace">
                <a href="javascript:;" class="aui-palace-grid">
                    <div class="aui-palace-grid-icon">
                        <img src="../static/img/authenpage/icon-itme-001.png" alt="">
                    </div>
                    <div class="aui-palace-grid-text">
                        <h2>保障账户安全</h2>
                    </div>
                </a>
                <a href="javascript:;" class="aui-palace-grid">
                    <div class="aui-palace-grid-icon">
                        <img src="../static/img/authenpage/icon-itme-002.png" alt="">
                    </div>
                    <div class="aui-palace-grid-text">
                        <h2>专属优惠</h2>
                    </div>
                </a>
                <a href="javascript:;" class="aui-palace-grid">
                    <div class="aui-palace-grid-icon">
                        <img src="../static/img/authenpage/icon-itme-003.png" alt="">
                    </div>
                    <div class="aui-palace-grid-text">
                        <h2>支付更便捷</h2>
                    </div>
                </a>
            </div>
        </div>
    </section>
</section>
<script type="text/javascript">
    $(document).ready(function(){
        $('#t1').on('keyup', function(){
            var v=$('#t1').val();

            if(!v){
                $('#btn').attr('disabled', true)
            }
            else{
                $('#btn').attr('disabled', false)
            }
        });

        $('#t2').on('keyup', function(){
            var v=$('#t2').val();

            if(!v){
                $('#btn').attr('disabled', true)
            }
            else{
                $('#btn').attr('disabled', false)
            }
        });

    });


    $('.yanzhengma').on('click',function(){

        if(checkPhone(msisdn)){
            var msisdn = document.getElementById("t3").value;
            $(this).prop('disabled',true);
            roof();
        }

    });


</script>

</body>
</html>
