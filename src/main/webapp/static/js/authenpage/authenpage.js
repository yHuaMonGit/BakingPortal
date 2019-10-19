function sendMessage1() {

    var times = 60;
    function roof(){
        if(times == 0){
            $('.yanzhengma').text('发送验证码('+times+'s)');
            $('.yanzhengma').prop('disabled',false);
            $('.yanzhengma').text('发送验证码');
            times = 60;
            return
        }
        $('.yanzhengma').text('发送验证码('+times+'s)');
        times--;

        setTimeout(roof,1000);
    }

    var getVerifyCode = "./getVerify"
    var msisdn = document.getElementById("t3").value;
    var openid = GetUrlParam("openid");
    openid=openid.replace("#","");
    if (checkPhone(msisdn)){

        $('.yanzhengma').prop('disabled',true);
        roof();

        $.post(
            getVerifyCode,
            {
                openid:openid,
                msisdn:msisdn
            }
        )

    } else {
        alert("请输入正确的手机号码");
        return;
    }

    
}

function myText() {

    //校验函数
    //身份证校验
    var idcard = document.getElementById("t2");
    var idcardNum = idcard.value;
    idcard.setCustomValidity("");
    if (isCardNo(idcardNum)){
        idcard.setCustomValidity("");
    } else {
        idcard.setCustomValidity("身份证号格式错误");
    }

}

function submitAuth() {

    var submitUrl = "./submitauthen"
    //校验
    var idcard = document.getElementById("t2").value;

    if (!isCardNo(idcard)){
        alert("身份证号格式错误");
        return;
    }

    var msisdn = document.getElementById("t3").value;
    if(!checkPhone(msisdn)){
        alert("请输入正确的手机号码");
        return;
    }

    var name = document.getElementById("t1").value;
    var verifyCode = document.getElementById("t4").value;
    var openid = GetUrlParam("openid");

    //2019-07-10 增加防连点
    $("#btn").attr("disabled", true);

    $.post(
        submitUrl,
        {
            openid:openid,
            idcard:idcard,
            msisdn:msisdn,
            name:name,
            verifyCode:verifyCode
        },
        function (result) {

            if (result == "120002"){
                alert("验证码错误或过期！请重新获取验证码。");
                $("#btn").attr("disabled", false);
            } else if (result == "130001"){
                alert("身份证校验错误，请检查身份证号码。");
                $("#btn").attr("disabled", false);
            }else if (result == "200000"){
                alert("您已经成功注册并绑定友佳烘焙会员！");
                $("#btn").attr("disabled", false);
                window.location.href = "../auth2/authredirect?callType=1"
            }else if (result == "200010"){
                alert("您的会员卡已经绑定成功！");
                $("#btn").attr("disabled", false);
                window.location.href = "../auth2/authredirect?callType=1"
            }else {
                alert("未知错误，请联系管理员！");
                $("#btn").attr("disabled", false);
            }

        }

    )


}

function checkPhone(msisdn){

    if(!(/^1[34578]\d{9}$/.test(msisdn))){
        return false;
    }else return true;
}

function isCardNo(card)
{
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;


    if(reg.test(card) == false)
    {
        return  false;
    }else return true;
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