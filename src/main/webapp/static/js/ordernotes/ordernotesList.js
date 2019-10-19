function initListPages(obj)
{

    var topCom = document.getElementsByClassName("aui-order-title")[0];

    for (i = 0 ; i < obj.length ; i++){
        //顶层
        var headDiv = document.createElement("div");
        headDiv.setAttribute("class","aui-order-box");

        //Floor1
        var fA = document.createElement("a");
        fA.setAttribute("class","aui-well-item");
        fA.setAttribute("orderid",obj[i].order_no);
        fA.setAttribute("orderstus",obj[i].order_status);

        fA.addEventListener('click',function (ev) {

            var openid = GetUrlParam("openid");
            var shopid = GetUrlParam("shopid");
            var orderid = this.getAttribute("orderid");
            var orderstus = this.getAttribute("orderstus");

            if (orderstus == 0){
                window.location.href = "./showOrder?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
            }else if(orderstus == 4) {
                window.location.href = "./transportOrder?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
            }else {
                window.location.href = "./historyOrder?openid="+openid+"&shopid="+shopid+"&orderid="+orderid;
            }

        });

        var pTime = document.createElement("p");
        pTime.setAttribute("class","aui-order-fl aui-order-time");
        pTime.innerHTML = "购买时间："+obj[i].createTime;
        var pCount = document.createElement("p");
        pCount.setAttribute("class","aui-order-fl aui-order-address");
        pCount.innerHTML = "商品总数："+obj[i].product_count;
        var pAmount = document.createElement("p");
        pAmount.setAttribute("class","aui-order-fl aui-order-door");
        pAmount.innerHTML = "合计金额："+obj[i].actAmount;

        //Floor2
        var divBody = document.createElement("div");
        divBody.setAttribute("class","aui-well-item-bd");

        var spanBody = document.createElement("span");
        spanBody.setAttribute("class","aui-well-item-fr");
        spanBody.innerHTML = getWords(obj[i].order_status);

        //Floor3
        var h3Text = document.createElement("h3");
        h3Text.innerHTML = "订单号："+obj[i].order_no;

        //组装

        divBody.appendChild(h3Text);
        fA.appendChild(divBody);
        fA.appendChild(spanBody);

        headDiv.appendChild(fA);
        headDiv.appendChild(pTime);
        headDiv.appendChild(pCount);
        headDiv.appendChild(pAmount);

        topCom.appendChild(headDiv);

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

function getWords(obj) {

    if (obj == 0){
        return "待支付";
    } else if (obj == 1){
        return "已支付";
    }else if (obj == 2){
        return "配货中";
    }else if (obj == 3){
        return "配货中";
    }else if (obj == 4){
        return "已发货";
    }else if (obj == 5){
        return "已收货";
    }else if (obj == 6){
        return "已关闭";
    }

}