function initPaymemtPages(obj) {
    var paymemtAmount = document.getElementById("paymemt-act-acmount");
    paymemtAmount.innerHTML = "￥ "+obj.actAmount;
    paymemtAmount.setAttribute("act-amount",obj.actAmount);
    var paymentType = document.getElementById("payment-vip-type");
    paymentType.innerHTML = "会员卡支付 （余额："+obj.memberBalance+"）";
    paymentType.setAttribute("mb-blc",obj.memberBalance);
    var paymentAddress = document.getElementById("payment-address-detail");
    paymentAddress.innerHTML = obj.AdressOut;
    paymentAddress.setAttribute("ad-id",obj.AdressId);
}


function initPaymemtResultPages(obj) {

    var paymemtAmount = document.getElementById("payment-result-amount");
    paymemtAmount.innerHTML = "￥ "+obj.actAmount;
    paymemtAmount.setAttribute("act-amount",obj.actAmount);

    var paymentShop = document.getElementById("payment-result-shopName");
    paymentShop.innerHTML = obj.ShopName;

    var paymentTransTime = document.getElementById("payment-result-transTime");
    paymentTransTime.innerHTML = obj.transTime;

    var paymentorder = document.getElementById("payment-result-orderid");
    paymentorder.innerHTML = GetUrlParam("orderid");


}

function changeAddress() {

    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    var orderid = GetUrlParam("orderid");
    var addressUrl = "./addressmanagement?openid="+openid+"&shopid="+shopid+"&orderid="+orderid+"&lastStep=payment";

    window.location.href = addressUrl;
}

function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");

            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    }
    else {
        return "";
    }
}

function payOrder() {

    if (!checkAdreesExist()) {
        alert("您还没有设置地址哦，请先设置地址！");
        changeAddress();
        return;
    }


    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    var orderid = GetUrlParam("orderid");
    var paymentType = CheckType();
    var payUrl = "./payOrder"

    var actAmount = document.getElementById("paymemt-act-acmount").getAttribute("act-amount");
    var memberBlance = document.getElementById("payment-vip-type").getAttribute("mb-blc");

    if(paymentType == 0)
    {
        alert("请选择支付方式！");
        return;
    }
    if (paymentType == 1)
    {
        pay();
        return;
    }

    if((memberBlance-actAmount)<0){
        alert("会员卡余额不足，请联系店家充值后支付哈~");
        return;
    }else  {
        $.post(
            payUrl,
            {
                openid:openid,
                shopid:shopid,
                orderid:orderid,
                paymentyType:paymentType
            },
            function (result) {

                var res_arr = result.split(",");

                if (res_arr[0] == 200){
                    window.location.href = "./paymentSuccess?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
                }
                else if (res_arr[0] == 201){
                    alert("您的订单已经支付！请不要重复提交！");
                    window.location.href="./auth2/authredirect?callType=3";
                }
                else if (res_arr[0] == 501){
                    alert("抱歉，您所选购的商品："+res_arr[1]+" 已经被人抢光啦~请您重新下单购买！");
                    window.location.href="./auth2/authredirect?callType=3";
                }
                else if (res_arr[0] == 500){
                    alert("抱歉，页面出错了！");
                    window.location.href="./auth2/authredirect?callType=3";
                }
            }

        )
    }

}

function CheckType() {
    var wx = document.getElementById("wx-input");
    var vip = document.getElementById("vip-input");

    if (wx.checked){
        return 1;
    } else if (vip.checked){
        return 2;
    } else return 0 ;

}

function checkAdreesExist() {
    var paymentAddress = document.getElementById("payment-address-detail");
    var adId = paymentAddress.getAttribute("ad-id");

    if (null == adId || adId == ""){
        return false;
    }else return true;

}


function wentToMemberCenter() {
        window.location.href = "./auth2/authredirect?callType=1";
}

function checkDetail() {

    var historyURL = "./historyOrder"
    var shopid = GetUrlParam("shopid");
    var openid = GetUrlParam("openid");
    var orderid = GetUrlParam("orderid");

    window.location.href = historyURL+"?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;

}

//***WeChat pay
var prepay_id ;
var sign = getcookie("sign");
var appId = getcookie("appId");
var timeStamp = getcookie("timeStamp");
var nonceStr = getcookie("nonceStr");
var packageStr =getcookie("packageStr");
var signType = getcookie("signType");
var shopid = GetUrlParam("shopid");
var openid = GetUrlParam("openid");
var orderid = GetUrlParam("orderid");

function pay(){

    callpay();

}

function onBridgeReady(){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appId,     //公众号名称，由商户传入
            "paySign":sign,         //微信签名
            "timeStamp":timeStamp, //时间戳，自1970年以来的秒数
            "nonceStr":nonceStr , //随机串
            "package":packageStr,  //预支付交易会话标识
            "signType":signType     //微信签名方式
        },
        function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                //window.location.replace("index.html");

                window.location.href = "./paymentSuccess?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
                alert('支付成功');
            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                alert('支付取消');
            }else if(res.err_msg == "get_brand_wcpay_request:fail" ){
                alert('支付失败');
            }
            //使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    );
}

function callpay(){
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
}