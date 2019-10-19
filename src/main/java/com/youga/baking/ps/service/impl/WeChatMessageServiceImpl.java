package com.youga.baking.ps.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.youga.baking.ps.obj.ElementData;
import com.youga.baking.ps.service.AccessService;
import com.youga.baking.ps.service.WeChatMessageService;
import com.youga.baking.ps.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class WeChatMessageServiceImpl implements WeChatMessageService {

    AccessService accessService = new AccessServiceImpl();

    @Override
    public void sendPaymentMsg(String shopid, String orderid, String openid, String openidMD5) {

        Map<String,Object> JsonMap = new HashMap<String, Object>();
        Map<String,Object> data = new HashMap<String, Object>();

        String orderType = "作业区配送";
        String temp_id = "1Qi4P0ZuFyylg1JZ7pRjusdFHoVEiVqSn1eUS2grgyU";
        String callbackUrl = "http://www.youga-pet.cn/Portal/historyOrder?openid="+openidMD5+"&shopid="+shopid+"&orderid="+orderid;

        data.put("first",new ElementData("亲爱的用户您好，您的订单已经下单，稍后将为您安排配货，请持续关注哦。","#FF0000"));
        data.put("storeName",new ElementData("宝塔区河庄坪友佳烘焙","#173177"));
        data.put("orderId",new ElementData(orderid,"#173177"));
        data.put("orderType",new ElementData(orderType,"#173177"));
        data.put("remark",new ElementData("客服联系电话：15929852605","#173177"));

        JsonMap.put("touser",openid);
        JsonMap.put("template_id",temp_id);
        JsonMap.put("url",callbackUrl);
        JsonMap.put("data",data);

        String JsonValue = JSONObject.toJSONString(JsonMap);

        String accsessToken = accessService.getAccessToken();
        String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accsessToken;

        HttpUtils.post(postUrl,JsonValue);

    }
}
