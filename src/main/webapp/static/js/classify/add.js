$(function () {

    $("#left li:first-child").addClass("active");
    var e;
	
	//商品点击增加
    $(document).on("click",".add",function(){
        $(".subFly").show();

        var stander_id = this.getAttribute("id");
        var com_id = this.getAttribute("com-id");
        var doGetStanderUrl = "./classify/getStander";
        var shopid = GetUrlParam("shopid");

        //新增post逻辑，请求规格信息
        $.post(
            doGetStanderUrl,
            {
                shopid : shopid,
                standerid : stander_id,
                com_id:com_id
            },
            function (result) {

                if (result == null){
                    alert("获取规格信息错误！请联系管理员：18629149887！");
                }
                else {

                    var basepare = document.getElementById("classify-chose-stander");
                    var choseStander = document.getElementById("stander-choseValue");
                    var chosePrice = document.getElementById("stander-pce");

                    //清除规格
                    basepare.innerHTML="";
                    var standerdt = document.createElement("dt");
                    basepare.appendChild(standerdt);

                    //获取Json数组对象
                    var jsObj = JSON.parse(result);
                    for(i=0;i<jsObj.length;i++){
                        var cookieidName = jsObj[i].stander_id + jsObj[i].stander_inner_id
                        var cookieIdValue = jsObj[i].price;
                        //cookie写入
                        setCookie(cookieidName,cookieIdValue);

                        var middleDD = document.createElement("dd");
                        //设置每单元分类信息ID
                        middleDD.setAttribute("id",cookieidName)
                        //首项增加active
                        if (i == 0){
                            middleDD.setAttribute("class","m-active");

                            //设置默认规格
                            choseStander.innerHTML = jsObj[i].instock;
                            choseStander.setAttribute("instock",jsObj[i].instock);

                            if (jsObj[i].instock == 0){
                                choseStander.innerHTML = "已售完";
                            }

                            chosePrice.innerHTML = jsObj[i].price;
                            //设置默认规格价格

                        }
                        middleDD.innerHTML = jsObj[i].stander_col;
                        basepare.appendChild(middleDD);


                        //规格组件增加点击事件监听，点击后切换active标签、并且调整对应价位
                        middleDD.addEventListener('click',function (ev) {

                            //获取转换前活跃组件
                            var getBeforActive = document.getElementsByClassName("m-active")[0];

                            //根据ID获取组件信息
                            var clickComponent = ev.srcElement;
                            //var choseStanders = clickComponent.innerHTML;
                            var choseId = clickComponent.getAttribute("id");
                            var chosePrices = getCookie(choseId);

                            //设置选择值
                           // choseStander.innerHTML = choseStanders;
                            chosePrice.innerHTML = chosePrices;

                            //切换活跃组件
                            getBeforActive.removeAttribute("class");
                            clickComponent.setAttribute("class","m-active");

                        })
                    }

                }

            }

        )

        var n = $(this).prev().text();
        var num = parseFloat(n);
        if(n==0){num =1}
        $(".ad").prev().text(num);
        e = $(this).prev();
		
        var parent = $(this).parent();
        var name=parent.parent().children("h4").text();
        var price = parseFloat(parent.prev().children("b:nth-child(2)").text());
		
        var src = $(this).parent().parent().prev().children()[0].src;
        
        $(".subName dd p:nth-child(1)").html(name);
        $(".pce").text(price);
        $(".imgPhoto").attr('src',src);
		$(this).siblings(".price").text(price);
		$(".mydd .price").html(price);
        $(".choseValue").text($(".subChose .m-active").text());
        var dataIcon=$(this).parent().parent().children("h4").attr("data-icon");
        $(".subName dd p:first-child").attr("data-icon",dataIcon);
    });
    $(".minus").click(function(){
        $('.shopcart-list,.up1').show();
    });
	
	//口味选择
    // $(".subChose dd").click(function(){
     //    $(this).addClass("m-active").siblings().removeClass("m-active");
     //    $(".choseValue").text($(".subChose .m-active").text());
    // })
	//
	//弹框 - 加
    $(".ad").click(function(){
        var n = parseFloat($(this).prev().text())+1;
        if (n == 0) { return; }

        //新增对库存量判断
        var choseStander = document.getElementById("stander-choseValue");
        var instock = choseStander.getAttribute("instock");
        //判断是否大于库存量
        if (n>instock){
            if (instock == 0)
            {
                alert("商品已售完！");
            }
            alert("选购商品不能超过库存！");
            return;
        }

        $(this).prev().text(n);
        var danjia = $(this).next().text();   //获取单价
        var a = $("#totalpriceshow").html();  //获取当前所选总价
		var b = a*1 + danjia*1;
        $("#totalpriceshow").html(b);         //计算当前所选总价
        var nm = $("#totalcountshow").html(); //获取数量
	
        $("#totalcountshow").html(nm*1+1);



    });
	
    //弹框 - 减
    $(".ms").click(function () {
        var n = $(this).next().text();
        if(n>1){
            var num = parseFloat(n) - 1;
            $(this).next().text(num);//减1

            var danjia = $(this).nextAll(".price").text();//获取单价
            var a = $("#totalpriceshow").html();//获取当前所选总价
            var b = (a*3 - danjia*3)/3;
        	$("#totalpriceshow").html(b);//计算当前所选总价

            var nm = $("#totalcountshow").html();//获取数量
            $("#totalcountshow").html(nm * 1 - 1);
        }

        //如果数量小于或等于0则隐藏减号和数量
        if (num <= 0) {
            $(this).next().css("display", "none");
            $(this).css("display", "none");
            jss();//改变按钮样式
            return
        }
    });
	
	//点击遮罩，隐藏商品详情弹框
    $(".up").click(function(){
        $(".subFly").hide();  
    });
	
	//点击加入购物车 - 按钮
	var flag = false;
    $(".foot").click(function () {

        //新增商品库存判断：
        var choseStander = document.getElementById("stander-choseValue");
        var instock = choseStander.getAttribute("instock");
        if (instock <= 0 )
        {
            alert("商品已经售完！");
            return;
        }

        //添加商品到购物车，修改为后台逻辑，防止前端欺骗；
        var getShoppingcartNumUrl = "./classify/ShoppingcartNum";
        var addToShopCartUrl = "./classify/addToShoppingCart";
        var openid = GetUrlParam("openid");
        var shopid = GetUrlParam("shopid");
        var goodsid = document.getElementById("classify-addGoods-prama").getAttribute("data-icon");
        var comStander = document.getElementsByClassName("m-active")[0].getAttribute("id");
        var comNum = document.getElementsByClassName("mask_number")[0].innerHTML;

        $.post(
            addToShopCartUrl,
            {
                openidMD5:openid,
                shopid:shopid,
                goodsid:goodsid,
                comstander:comStander,
                comNum:comNum
            },
            function (result) {

                if (result==0){
                    alert("商品已经添加至购物车！");
                    $.post(
                        getShoppingcartNumUrl,
                        {openid:openid},
                        function (result) {
                            var resArr = result.split(",");
                            document.getElementById("totalcountshow").innerHTML = resArr[0];
                            document.getElementById("totalpriceshow").innerHTML = resArr[1];
                            jss();
                        }
                    );
                    $(".subFly").hide();
                } else if (result == 1){
                    alert("商品添加至购物车异常，请截图并联系管理员：18629149887");
                }

            }
        )



    });

	//购物车 - 加
	$(document).on('click','.ad2',function(){
        modifyShoppinCart(this,1);
	});
	
	//购物车 - 减
	$('.list-content').on('click','.ms2',function(){
        modifyShoppinCart(this,0);
	});

	function modifyShoppinCart(obj,type) {
        var addToShopCartUrl = "./classify/shoppingcartAdd";
        var openid = GetUrlParam("openid");
        var comid = obj.parentNode.getAttribute("com_id_search");
        var innerId = obj.parentNode.getAttribute("com_standerinnerid_search");
        var singleTotal = obj.parentNode.parentNode.childNodes[1].childNodes[1];
        var thisCountI = obj.parentNode.childNodes[1];
        if (type == 1){
            var choseStander = document.getElementById("stander-choseValue");
            var instock = choseStander.getAttribute("instock");
            //判断是否大于库存量
            if (thisCountI.innerHTML+1>instock)
            {
                alert("选购商品不能超过库存！");
                return;
            }

            $.post(
                addToShopCartUrl,
                {
                    openid:openid,
                    comid:comid,
                    innerId:innerId,
                    optype:type
                },
                function (result) {

                    var respArr = result.split(",");
                    document.getElementById("totalcountshow").innerHTML = respArr[0];
                    document.getElementById("totalpriceshow").innerHTML = respArr[1];
                    singleTotal.innerHTML = respArr[2];
                    if (respArr[2] == 0){
                        obj.parentNode.parentNode.remove();
                    }
                }
            );

            thisCountI.innerHTML = parseInt(thisCountI.innerHTML)+1;
        } else if (type == 0){
            $.post(
                addToShopCartUrl,
                {
                    openid:openid,
                    comid:comid,
                    innerId:innerId,
                    optype:type
                },
                function (result) {

                    var respArr = result.split(",");
                    document.getElementById("totalcountshow").innerHTML = respArr[0];
                    document.getElementById("totalpriceshow").innerHTML = respArr[1];
                    singleTotal.innerHTML = respArr[2];
                    if (respArr[2] == 0){
                        obj.parentNode.parentNode.remove();
                    }
                }
            );
            thisCountI.innerHTML = parseInt(thisCountI.innerHTML)-1;
        } else if (type == 9 ){

            $.post(
                addToShopCartUrl,
                {
                    openid:openid,
                    optype:type
                },
                function (result) {

                    var respArr = result.split(",");
                    document.getElementById("totalcountshow").innerHTML = respArr[0];
                    document.getElementById("totalpriceshow").innerHTML = respArr[1];

                    var topUL = document.getElementById("shoppingcart-list-table");
                    topUL.innerHTML = "";
                    return;
                }
            );
        }


    }

    function jss() {
        var m = $("#totalcountshow").html();
        if (m > 0) {
            $(".right").find("a").removeClass("disable");
        } else {
            $(".right").find("a").addClass("disable");
        }
    };
	
    //选项卡
    $(".con>div").hide();
    $(".con>div:eq(0)").show();
    $(".left-menu li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        var n = $(".left-menu li").index(this);
        $(".left-menu li").index(this);
        $(".con>div").hide();
        $(".con>div:eq("+n+")").show();
    });
    $(".subFly").hide();
    $(".close").click(function(){
        $(".subFly").hide();
    });
    $(".footer>.left").click(function(){

        //点击购物车按钮，请求购物车控件数据
        var getShopCartUrl = "./classify/getShopCart";
        var openid = GetUrlParam("openid");
        var shopid = GetUrlParam("shopid");

        $.post(
            getShopCartUrl,
            {
                openid:openid,
                shopid:shopid
            },
            function (result) {

                if (result == 1){
                    alert("获取购物车信息失败！请联系管理员：18629149887！");
                } else{

                    /***
                     * 填充购物车部分
                     */
                    // var content = $(".list-content>ul").html();
                    fillShoppingCart(result);

                    // if(content!=""){
                    //     $(".shopcart-list.fold-transition").toggle();
                    //     $(".up1").toggle();
                    // }

                    $(".shopcart-list.fold-transition").toggle();
                    $(".up1").toggle();
                }
            }
        )


        // if(content!=""){
        //     $(".shopcart-list.fold-transition").toggle();
        //     $(".up1").toggle();
        // }
    });

    $(".up1").click(function(){
        $(".up1").hide();
        $(".shopcart-list.fold-transition").hide();
    })
	
	//清空购物车
    $(".empty").click(function(){

        modifyShoppinCart(this,9);

        $(".list-content>ul").html("");
        $("#totalcountshow").text("0");
        $("#totalpriceshow").text("0");
        $(".minus").next().text("0");
        $(".minus").hide();
        $(".minus").next().hide();
        $(".shopcart-list").hide();
        $(".up1").hide();
        jss();//改变按钮样式
    });
});
