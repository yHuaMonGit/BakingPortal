
function checkCertification(obj) {

    if (obj == 0){
        alert("您还没有认证会员信息，请先认证会员信息！");
        window.location.href = "./auth2/authredirect?callType=1";
    }

}

function getNotice() {

    var getnoticeUrl = "./classify/getNotice";
    var shopid = GetUrlParam("shopid");
    var annSpan = document.getElementById("announcement-text");


    $.post(
        getnoticeUrl,
        {
            shopid:shopid
        },
        function (result) {
            annSpan.innerText = result;
        }

    );
}

function initComList() {

    var getnoticeUrl = "./classify/getComList";
    var getShoppingcartNumUrl = "./classify/ShoppingcartNum";
    var shopid = GetUrlParam("shopid");
    var openid = GetUrlParam("openid");
    var comUl = document.getElementById("com-list");


    $.post(
        getShoppingcartNumUrl,
        {openid:openid},
        function (result) {
            var resArr = result.split(",");
            document.getElementById("totalcountshow").innerHTML = resArr[0];
            document.getElementById("totalpriceshow").innerHTML = resArr[1];
        }
    );

    $.post(
        getnoticeUrl,
        {
            shopid:shopid,
            openid:openid
        },
        function (result)
        {
            var jsObj = JSON.parse(result);


            for(i=0;i<jsObj.length;i++)
            {

                var typeli = document.createElement("li");

                /***
                 * 初始化页面时，对第一个页签标记为选中；
                 */
                if (i == 0)
                {
                    typeli.setAttribute("class","active");
                }
                var typespan = document.createElement("span");

                typespan.innerText = jsObj[i].clsName;
                typespan.setAttribute("id",jsObj[i].clsInId);

                typeli.appendChild(typespan);
                comUl.appendChild(typeli);

                typespan.addEventListener('click',function (ev) {

                    var clsInId = this.id;
                    var changeTypeUrl = "./classify/ChangeType";
                    var shopid = GetUrlParam("shopid");

                    $(".active").removeAttr("class");


                    $.post(
                        changeTypeUrl,
                        {
                            shopid:shopid,
                            clsInId:clsInId
                        },
                        function (result) {
                            //清除元素
                            clearComBasicArea();
                            var activeSpan = document.getElementById(clsInId);
                            var activeLi = activeSpan.parentNode;
                            activeLi.setAttribute("class","active");
                            showBasicComInfo(result,clsInId);
                           // alert("changeType to："+clsInId);
                        }
                    );
                })
            }
        }

    );

}

function initFirstBasicCom() {


    var clsInId = "1";
    var changeTypeUrl = "./classify/ChangeType";
    var shopid = GetUrlParam("shopid");

    $(".active").removeAttr("class");


    $.post(
        changeTypeUrl,
        {
            shopid:shopid,
            clsInId:clsInId
        },
        function (result) {
            //清除元素
            clearComBasicArea();
            var activeSpan = document.getElementById(clsInId);
            var activeLi = null;
            if (activeSpan!= null){
                activeLi = activeSpan.parentNode;
                activeLi.setAttribute("class","active");
            }
            showBasicComInfo(result,clsInId);
           // alert("changeType to："+clsInId);
        }
    );

}

function showBasicComInfo(result,clsInId) {

    var shopid = GetUrlParam("shopid");
    //获取Json数组对象
    var jsObj = JSON.parse(result);
    //顶层组件
    var topUL = document.getElementById("com-menu-ul");

    //循环动态添加组件
    for(i=0;i<jsObj.length;i++)
    {
        //容器顶层
        var secondLI = document.createElement("li");



        //三级容器
        var thirdDivImg = document.createElement("div");
        thirdDivImg.setAttribute("class","menu-img");
        //+Inside
        var img = document.createElement("img");
        img.src = "../images/image/"+shopid+"/"+clsInId+"/"+jsObj[i].com_icon_src;

        var thirdDivTxt = document.createElement("div");
        thirdDivTxt.setAttribute("class","menu-txt");

        //四级容器
        var comNameH4 = document.createElement("h4");
        //+Inside
        //+id
        comNameH4.setAttribute("data-icon",jsObj[i].com_id);
        //+name
        comNameH4.innerHTML = jsObj[i].com_name;

        var typeP = document.createElement("p");
        typeP.setAttribute("class","list1");
        //+Inside
        typeP.innerHTML = jsObj[i].cls_name;

        var priceP = document.createElement("p");
        priceP.setAttribute("class","list2");



        var divBtn = document.createElement("div");
        divBtn.setAttribute("class","btn");
        //+Inside


        //五级容器
        var tagb = document.createElement("b");
        //+Inside
        tagb.innerText = "￥";
        var priceb = document.createElement("b");
        //+Inside
        priceb.innerText = jsObj[i].com_price;

        var addButton = document.createElement("button");
        addButton.setAttribute("class","add");

        //2109-06-19 将stander_id 设置为add加号的id；
        addButton.setAttribute("id",jsObj[i].com_stander_id);
        addButton.setAttribute("com-id",jsObj[i].com_id)

        var buttonI = document.createElement("i");
        buttonI.setAttribute("class","price");
        //+Inside
        buttonI.innerText = jsObj[i].com_price;



        //容器组装
        divBtn.appendChild(addButton);
        divBtn.appendChild(buttonI);


        priceP.appendChild(tagb);
        priceP.appendChild(priceb);

        thirdDivTxt.appendChild(comNameH4);
        thirdDivTxt.appendChild(typeP);
        thirdDivTxt.appendChild(priceP);
        thirdDivTxt.appendChild(divBtn);

        secondLI.appendChild(thirdDivImg);
        secondLI.appendChild(thirdDivTxt);

        thirdDivImg.appendChild(img);
        topUL.appendChild(secondLI);


    }

}

function clearComBasicArea() {
    var topUL = document.getElementById("com-menu-ul");
    topUL.innerHTML="";

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


//cookie operation
//JS操作cookies方法!
//写cookies
function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}


function fillShoppingCart(result){
    var shopid = GetUrlParam("shopid");
    //获取Json数组对象
    var jsObj = JSON.parse(result);
    //顶层组件
    var topUL = document.getElementById("shoppingcart-list-table");
    topUL.innerHTML = "";

    var SumOfCount = 0;
    var totalCountP = document.getElementById("totalpriceshow");

    //循环动态添加组件
    for(i=0;i<jsObj.length;i++)
    {
        //顶级容器
        //<li class="food">
        var liTop = document.createElement("li");
        liTop.setAttribute("class","food");

        //一级容器
        var div_nameAstanderCol = document.createElement("div");
        var div_moneyCount = document.createElement("div");
        var div_button = document.createElement("div");
        div_button.setAttribute("com_id_search",jsObj[i].comid);
        div_button.setAttribute("com_standerinnerid_search",jsObj[i].comstanderinnerid);
        div_button.setAttribute("class","btn");

        //二级组件
        var spanName = document.createElement("span");
        spanName.setAttribute("class","accountName");
        spanName.setAttribute("data-icon",jsObj[i].comid);
        spanName.setAttribute("data-memberid",jsObj[i].memberid);
        spanName.innerHTML = jsObj[i].comname;

        var spanStander = document.createElement("span");
        spanStander.setAttribute("class","taste");
        spanStander.innerHTML = jsObj[i].comstandername;

        var spanSign = document.createElement("span");
        spanSign.innerHTML = "￥";
        var spanMoneyAcount = document.createElement("span");
        spanMoneyAcount.setAttribute("class","accountPrice");
        var singlePrice = parseFloat(jsObj[i].comprice);
        var ComNumber = parseFloat(jsObj[i].comnum);
        SumOfCount += singlePrice*ComNumber ;
        spanMoneyAcount.innerHTML = singlePrice*ComNumber ;

        var buttonms = document.createElement("button");
        buttonms.setAttribute("class","ms2");
        buttonms.setAttribute("style","display: inline-block;");

        var iNum = document.createElement("i");
        iNum.setAttribute("class","li_acount");
        iNum.innerHTML = jsObj[i].comnum;

        var buttonad = document.createElement("button");
        buttonad.setAttribute("class","ad2");

        //组装
        div_nameAstanderCol.appendChild(spanName);
        div_nameAstanderCol.appendChild(spanStander);

        div_moneyCount.appendChild(spanSign);
        div_moneyCount.appendChild(spanMoneyAcount);

        div_button.appendChild(buttonms);
        div_button.appendChild(iNum);
        div_button.appendChild(buttonad);

        liTop.appendChild(div_nameAstanderCol);
        liTop.appendChild(div_moneyCount);
        liTop.appendChild(div_button);
        topUL.appendChild(liTop);
    }

    totalCountP.innerHTML = SumOfCount;
}


function submitOrder() {

    var submitOrderUrl = "./ordernotes"
    var openid = GetUrlParam("openid");
    var shopid = GetUrlParam("shopid");
    window.location.href = submitOrderUrl+"?openid="+openid+"&shopid="+shopid;

}
