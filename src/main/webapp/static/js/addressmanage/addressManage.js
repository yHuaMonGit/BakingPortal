function addAddress()
{

    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    var addUrl = "./addAdress?openid="+openid+"&shopid="+shopid;

    var paramaNumber = GetUrlParamNumber();
    if (paramaNumber == 2){
        addUrl = "./addAdress?openid="+openid+"&shopid="+shopid;
    }else if(paramaNumber == 3){
        var orderid = GetUrlParam("orderid");
        addUrl = "./addAdress?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
    }else if (paramaNumber == 4 && GetUrlParam("lastStep")=="payment"){
        var orderid = GetUrlParam("orderid");
        addUrl = "./addAdress?openid="+openid+"&shopid="+shopid+"&orderid="+orderid+"&lastStep=payment";
    }
    window.location.href = addUrl;
}

function getOpenid(obj){

    var index=obj.lastIndexOf("=");
    obj=obj.substring(index+1,obj.length);
//  console.log(obj);
    return obj;
}

function showAddressDiv(addressListJson) {
    //create assembly
    var parent_div = document.getElementById("address-list");
    var paramaNumber = GetUrlParamNumber();
    var buttonP = document.getElementById("address-foot-button");
    if (paramaNumber == 2){
        buttonP.innerHTML = "返回商城"
        buttonP.setAttribute("innerType","1");
    }else if(paramaNumber == 3){
        buttonP.innerHTML = "返回订单"
        buttonP.setAttribute("innerType","0");
    }else if (paramaNumber == 4 && GetUrlParam("lastStep")=="payment"){
        buttonP.innerHTML = "确认修改";
        buttonP.setAttribute("innerType","2");
    }


    for (var i = 0 ; i < addressListJson.length ; i++)
    {
        var this_div = document.createElement("div");
        var son_edit_div = document.createElement("div");
        var son_detail_div = document.createElement("div");


        //set div
        this_div.setAttribute("class","aui-flex");
        son_edit_div.setAttribute("class","aui-address-edit");

        son_detail_div.setAttribute("class","aui-flex-box");

        //set Grandson
        grandson_h3 = document.createElement("h3");
        grandson_p1 = document.createElement("p");
        grandson_p2 = document.createElement("p");

        //set value
        //set id for edit button for to change default address
        son_edit_div.setAttribute("id",addressListJson[i].addressid);
        grandson_h3.innerHTML = addressListJson[i].username +" "+addressListJson[i].msisdn;
        grandson_p1.innerHTML = addressListJson[i].province
            +addressListJson[i].city
            +addressListJson[i].area
            +addressListJson[i].addreesdetail;
        grandson_p2.innerHTML = addressListJson[i].ecode;

        if(addressListJson[i].isChoice == "" || null == addressListJson[i].isChoice || 0 == addressListJson[i].isChoice  )
        {
            son_edit_div.innerHTML = "设为默认"
        }
        else
            {
                son_edit_div.innerHTML = "默认"
                son_edit_div.setAttribute("class","aui-address-default");
            }

        son_detail_div.appendChild(grandson_h3);
        son_detail_div.appendChild(grandson_p1);
        son_detail_div.appendChild(grandson_p2);

        this_div.appendChild(son_edit_div);
        this_div.appendChild(son_detail_div);
        parent_div.appendChild(this_div);

        son_edit_div.addEventListener('click',function (ev) {
            var addressid = $(this).attr('id');
            //var addressid = son_edit_div.id;
            var openid = GetUrlParam("openid");
            var modifyDefaultUrl = "./modifyDefault";

            $.post(
                modifyDefaultUrl,
                {
                    openid:openid,
                    addressid:addressid
                },
                function (resultcode) {
                    if(resultcode == 1)
                    {
                        alert("设置默认地址成功！")
                        window.location.reload();
                    }
                }
            )

        });
    }

}

function addAdressment()
{
    //get assembly
    var username_ass = document.getElementById("input-name");
    var msisdn_ass = document.getElementById("input-phone");
    var addressPCC_ass = document.getElementById("J_Address");
    var addreesDetail_ass = document.getElementById("input-detail");
    var ecode_ass = document.getElementById("input-ecode");

    //getParam
    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    var msisdn = msisdn_ass.value;
    if (!isPoneAvailable(msisdn))
    {
        alert("请输入正确的手机号码！");
        return;
    }
    var username = username_ass.value;
    var addressPCC = addressPCC_ass.value;
    var addressDetail = addreesDetail_ass.value;
    var ecode = ecode_ass.value;

    var req_url = "./addAddressment"
    $.post(
        req_url,
        {
            openid:openid,
            msisdn:msisdn,
            username:username,
            addressPCC:addressPCC,
            addressDetail:addressDetail,
            ecode:ecode
        },
        function (returnCode) {
            if(returnCode == 1)
            {
                alert("添加新地址成功")
                var return_url = "./addressmanagement?openid="+openid+"&shopid="+shopid;

                var paramaNumber = GetUrlParamNumber();
                if (paramaNumber == 2){
                    return_url = "./addressmanagement?openid="+openid+"&shopid="+shopid;
                }else if(paramaNumber == 3){
                    var orderid = GetUrlParam("orderid");
                    return_url = "./addressmanagement?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
                }else if (paramaNumber == 4 && GetUrlParam("lastStep")=="payment"){
                    var orderid = GetUrlParam("orderid");
                    return_url = "./addressmanagement?openid="+openid+"&shopid="+shopid+"&orderid="+orderid+"&lastStep=payment";
                }
                window.location.href = return_url;
            }else if(returnCode == 2)
            {
                alert("所在地区填写有误，精度应达到 省-市-区")
            }
            else
                {
                    alert("添加地址失败！请重试。");
                }
        }
    )

    function isPoneAvailable(str) {
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(str)) {
            return false;
        } else {
            return true;
        }
    }



}


function getcookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");//这里因为传进来的的参数就是带引号的字符串，所以c_name可以不用加引号
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);//当indexOf()带2个参数时，第二个代表其实位置，参数是数字，这个数字可以加引号也可以不加（最好还是别加吧）
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
}

function jump() {

    window.location.href=getcookie("AddressReturnUrl");

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

function GetUrlParamNumber() {
    var url = document.location.toString();
    var arrObj = url.split("?");

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        return arrPara.length;
    }
    else {
        return 0;
    }
}


/***
 * 根据自身属性返回
 */
function returnFun() {

    var buttonP = document.getElementById("address-foot-button");
    var returnUrl = "";
    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    var orderid = GetUrlParam("orderid");

    if (buttonP.getAttribute("innerType") == 0){
        returnUrl = "./showOrder?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
    }else if(buttonP.getAttribute("innerType") == 1){
        returnUrl = "./classify?openid="+openid+"&shopid="+shopid;
    }else if(buttonP.getAttribute("innerType") == 2){
        returnUrl = "./submitpayment?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
    }else {
        //留个接口
    }
    window.location.href = returnUrl;
}


