var phoneNumberInputID = "phoneNumInput";
var vericodeInputID = "vericodeInput";

function init(userinfo) {

    var authDesc = document.getElementById("auth-div");
    var authSpan = document.getElementById("auth-div-sp");
    var authA = document.getElementById("member-auth-a");

    if (userinfo[0].auth == "unAuth") {
        authDesc.innerHTML = "会员登录，未认证"
        authSpan.innerHTML = "未认证,前往认证>>"
        authSpan.setAttribute("font-color","c9302c");
    }else{
        authDesc.innerHTML = "会员登录，已认证"
        authSpan.innerHTML = "已认证";
    }
    
}

function getmemberInfo(userinfo,name)
{
    if(name == "level")
    {
        level =  userinfo[0].level;
        var p = document.getElementById(name);
        p.innerHTML = "Lv."+level;
    }else if(name == "integral")
    {
        integral =  userinfo[0].integral;
        var p = document.getElementById(name);
        p.innerHTML = integral+"分";
    }else if(name == "balance")
    {
        balance =  userinfo[0].balance;
        var p = document.getElementById(name);
        p.innerHTML = balance+"元";
    }else{
        return null;
    }
}

function getIcon1(obj,userInfo)
{
    var parent = document.getElementById(obj);
    var div_icon=document.createElement("div");
    if(userInfo == null || userInfo.length<1 )
    {
        return 0;
    }
    var iconUrl = userInfo[0].headIconUrl;

    div_icon.setAttribute("class","touxiang");
    addImg(div_icon,iconUrl);
    parent.appendChild(div_icon);
}

function getName(obj,userInfo)
{
    var parent = document.getElementById(obj);
    var div_userName=document.createElement("div");
    if(userInfo == null || userInfo.length<1 )
    {
        return 0;
    }
    var userName = userInfo[0].userName;
    div_userName.setAttribute("class","name");
    div_userName.innerHTML = userName;
    parent.appendChild(div_userName);
}


function addImg(div,text){
    var img_1=document.createElement("img");
    img_1.src=text;
    div.appendChild(img_1);
}

function memberRegister(div, userInfo) {
    var parent = document.getElementById(div);
    var header = document.getElementById("vipHead");
    var van = document.getElementById("vipSpan");
    var ul = document.getElementById("vipUl");
    var memberFlag = userInfo[0].isMember;
    var openid = userInfo[0].openid;

    if ( memberFlag == 1)
    {
        bindingPhone(parent,openid);
        header.style.display = "none";
        van.style.display = "none";
        ul.style.display = "none";
    }
    else if (memberFlag == 0)
    {

    }
    else{

    }

}
var bindingParent;
function bindingPhone(div,openid) {

    var bindingParent = div;
    var binding_div=document.createElement("div");

    //test
    //添加一个说明组件
    addDescDiv(binding_div);
    addPhoneNumDiv(binding_div,openid);
    //增加手机号码组件
    //增加验证码组件
    addVerifyDiv(binding_div);
    //添加提交按钮
    addSubmitDiv(binding_div,openid);

    //增加说明组件
    binding_div.setAttribute("class","bindingPhone");
    binding_div.setAttribute("id","bindingPhone");
    bindingParent.appendChild(binding_div);
}

function addDescDiv(obj)
{
    var parent = obj;
    var desc_div=document.createElement("div");
    desc_div.setAttribute("class","descDiv");
    var Tips = "尊敬的会员，在您登录前请先绑定手机号码哦。输入手机号码,点击获取验证码，输入短信提示的四位验证码即可完成绑定。";
    desc_div.innerHTML = Tips;
    parent.appendChild(desc_div);
}

function addSubmitDiv(obj,openid)
{
    var parent = obj;
    var submit_div=document.createElement("div");
    submit_div.setAttribute("class","submitDiv");
    addSubmitButton(submit_div,openid);
    parent.appendChild(submit_div);
}

function addPhoneNumDiv(obj,openid)
{
    var parent = obj;
    var phoneNum_div=document.createElement("div");
    phoneNum_div.setAttribute("class","phoneTips");
    addText(phoneNum_div,"phoneNumInput",phoneNumberInputID,"输入手机号码");
    addButton(phoneNum_div,"phoneNumVerify","获取验证码",openid);
    parent.appendChild(phoneNum_div);
}

function addVerifyDiv(obj)
{
    var parent = obj;
    var phoneNum_div=document.createElement("div");
    phoneNum_div.setAttribute("class","verifyDiv");
    addText(phoneNum_div,"verifyInput",vericodeInputID,"输入四位验证码");
    parent.appendChild(phoneNum_div);
}


var wait=60;
function addButton(obj,classname,text,openid){
    var span_1=document.createElement("button");
    var verifyUrl = "../verify/getVerify"
    span_1.setAttribute("class",classname);
    span_1.innerHTML=text;
    obj.appendChild(span_1);
    span_1.addEventListener('click',function (ev) {
        var phoneInput = document.getElementById(phoneNumberInputID);
        phoneNumber = phoneInput.value;

        if(!isPoneAvailable(phoneNumber))
        {
            alert("请填写正确的手机号码。");
            return;
        }
        $.post(
            verifyUrl,
            {
                openid:openid,
                msisdn:phoneNumber
            },
            function(callbackdata){
                if( "121000"==callbackdata)
                {
                    alert("验证码已发送，请在手机上查看短信验证码。");
                }
            }
        )

        time(span_1);
    });

}

function addSubmitButton(obj,openid){
    var span_1=document.createElement("button");
    var verifyUrl = "../verify/checkVerify"
    var reopenurl = "../member/auth"

    span_1.setAttribute("class","verifySubmit");
    span_1.innerHTML="验证并绑定手机";
    obj.appendChild(span_1);
    span_1.addEventListener('click',function (ev) {

        var phoneInput = document.getElementById(phoneNumberInputID);
        var vericodeInput = document.getElementById(vericodeInputID);
        phoneNumber = phoneInput.value;
        vericode = vericodeInput.value;

        if("" == phoneNumber)
        {
            alert("手机号还是要填的哈~");
            return;
        }

        if(!isPoneAvailable(phoneNumber))
        {
            alert("请输入正确的手机号码~");
            return;
        }
        if("" == vericode)
        {
            alert("验证码不可为空哈~");
        }
        $.post(
            verifyUrl,
            {
                openid:openid,
                msisdn:phoneNumber,
                vericode:vericode
            },
            function(callbackdata)
            {
                if("000001" == callbackdata)
                {
                    alert("绑定成功！");
                    window.location.href=reopenurl;
                }
            }
        );
    });

}

function isPoneAvailable(str) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}


function time(obj) {
    if (wait == 0) {
        obj.removeAttribute("disabled");
        obj.innerHTML="重新发送";
        wait = 60;
    } else {

        obj.setAttribute("disabled", true);
        obj.innerHTML="重新发送(" + wait + ")";
        wait--;
        setTimeout(function() {
                time(obj)
            },
            1000)
    }
}



function addSpan(obj,classname,text){
    var span_1=document.createElement("span");
    span_1.setAttribute("class",classname);
    span_1.innerHTML=text;
    obj.appendChild(span_1);
}

function addText(obj,classname,id,value){
    var span_1=document.createElement("input");
    span_1.setAttribute("class",classname);
    span_1.setAttribute("id",id);
    span_1.setAttribute("placeholder",value);
    obj.appendChild(span_1);

}

function gotoOrderList() {
    var orderUrl = "../orderList?openid="+GetUrlParam("openid")+"&shopid="+GetUrlParam("shopid");

    window.location.href = orderUrl;
}

function gotoAddress() {
    var orderUrl = "../home/addressmanagement?openid="+GetUrlParam("openid")+"&shopid="+GetUrlParam("shopid");
    window.location.href = orderUrl;
}


function authenMember() {

    var openid = GetUrlParam("openid");
    var authenMemberUrl = "./authen?openid=" + openid;
    var authSpan = document.getElementById("auth-div-sp");

    var authspantext = authSpan.innerText;
    if ( authspantext == "未认证,前往认证>>"){
        window.location.href = authenMemberUrl;
    }else {
        alert("您已是认证会员！");
    }

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