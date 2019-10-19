function initPages(obj){

    document.getElementById("order-count-display").innerHTML = "订单号:"+obj.order_no;

    initPreOrderComList(obj.shop_id,obj.comList);

    document.getElementById("preorder-com-total-amount").innerHTML = "￥ "+obj.product_amount_total;
    document.getElementById("preorder-com-dec-amount").innerHTML = "￥ "+obj.memberOffAmount;
    document.getElementById("preorder-com-act-amount").innerHTML = "￥ "+obj.actAmount;
    //preorder-com-dec-amount
    //id="preorder-com-act-amount"

}

function initPreOrderComList(shopid,obj) {

    var topDis = document.getElementById("preorder-com-list");

    for (i=0;i<obj.length;i++) {
        //顶级组件
        var liDis = document.createElement("li");
        liDis.setAttribute("class", "food_li");

        //一级组件
        var imgDis = document.createElement("img");
        imgDis.setAttribute("class", "food_li_left");
        imgDis.src = "../images/image/" + shopid + "/" + obj[i].cls_in_id + "/" + obj[i].com_id + ".jpg";

        var divFstDis = document.createElement("div");
        divFstDis.setAttribute("class", "food_li_middle");

        //二级组件
        var divScdDis = document.createElement("div");
        var divThdDis = document.createElement("div");
        divThdDis.setAttribute("class", "food_li_price");
        divThdDis.innerHTML = "￥" + obj[i].com_price * obj[i].com_num;

        //三级组件
        var pNameDis = document.createElement("p");
        pNameDis.innerHTML = obj[i].com_name;
        var pColDis = document.createElement("p");
        pColDis.innerHTML = obj[i].stander_col;
        var pNumDis = document.createElement("p");
        pNumDis.innerHTML = "× "+obj[i].com_num;

        //组装

        divScdDis.appendChild(pNameDis);
        divScdDis.appendChild(pColDis);
        divScdDis.appendChild(pNumDis);

        divFstDis.appendChild(divScdDis);
        divFstDis.appendChild(divThdDis);

        liDis.appendChild(imgDis);
        liDis.appendChild(divFstDis);

        topDis.appendChild(liDis);
    }

}

function backToClassify() {

    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");

    var classifyUrl = "./classify?openid="+openid+"&shopid="+shopid;

    window.location.href = classifyUrl;

}

function cancelOrder() {


    win.confirm('系统提示', '您确定要放弃订单吗？', function (r) {
        if (r == true){
            var orderId = document.getElementById("order-count-display").innerHTML;
            var index=orderId.lastIndexOf(":");
            orderId=orderId.substring(index+1,orderId.length);

            var cancelOrderUrl = "./order/cancelOrder";
            var openid = GetUrlParam("openid");
            var shopid = GetUrlParam("shopid");

            $.post(
                cancelOrderUrl,
                {
                    openid:openid,
                    shopid:shopid,
                    orderid:orderId
                },
                function (result) {

                    if (result == 0){
                        window.location.href = "./classify?openid="+openid+"&shopid="+shopid;
                    } else {
                        alert("删除订单失败，请联系管理员：18629149887！");
                    }
                }
            )
        } else {

        }
    });



}

function submitToPayment() {


    /***
     * 新增一个提交前判断，若用户没有地址信息，则引导用户去添加一条地址信息。
     * @type {string}
     */

    var orderId = document.getElementById("order-count-display").innerHTML;
    var index=orderId.lastIndexOf(":");
    orderId=orderId.substring(index+1,orderId.length);

    var submitPaymentUrl = "./submitpayment";
    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    var userip = returnCitySN['cip'];

    //新增提交订单所需要的表单项目
    var notes = document.getElementById("preorder-remark-enter").value;
    window.location.href = submitPaymentUrl+"?openid="+openid+"&shopid="+shopid+"&orderid="+orderId+"&notes="+notes+"&userip="+userip;

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


function confirmReceipt() {

    win.confirm('温馨提示', '请您确认您已经收到货品', function (r) {
        if (r == true){

            var postUrl = "./order/confirmReceipt";
            var orderId = GetUrlParam("orderid");
            var openid = GetUrlParam("openid");
            var shopid = GetUrlParam("shopid");

            $.post(
                postUrl,
                {
                    openid:openid,
                    orderid:orderId,
                    shopid:shopid
                },
                function (result) {
                    if (501 == result)
                    {
                        alert("您的这张订单已经确认收货，请勿重复确认！");
                    }else if (502 == result)
                    {
                        alert("您的订单还没有发货，请勿确认收货！");
                    }else if( 200 == result )
                    {
                        alert("确认收货成功，将为您转到订单页面。");
                        window.location.href = "./orderList?openid="+openid+"&shopid="+shopid;
                    }
                }
            );
        } else {

        }
    });



}

function backToMemberCenter() {
    window.location.href = "./auth2/authredirect?callType=1";

}

function backToShop() {

    window.location.href = "./auth2/authredirect?callType=3";

}


