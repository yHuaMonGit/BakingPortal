package com.youga.baking.ps.util.wePay;

import com.youga.baking.ps.obj.WxPament;
import com.youga.baking.ps.util.AuthUtil;
import com.youga.baking.ps.util.MD5Util;
import com.youga.baking.ps.util.wePay.Util.RequestHandler;
import com.youga.baking.ps.util.wePay.Util.Sha1Util;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;


public class WxPayUtil {

    private static String timeStamp;
    private static String packageStr;
    private static String payCodeSuccess;
    private static String paySign;
    private static String nonceStr = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";//暂时不变
    private static SortedMap<String, String> map =new TreeMap<String, String>();
    //private static String WX_PAY_CALLBACK = "http://www.youga-pet.cn/SAstation/home/pay";
    private static String WX_PAY_CALLBACK = "http://www.youga-pet.cn/Portal/payCallBack";
    //test version
    public static Map<String,String> getPreparPay_id(WxPament payUser) throws Exception {

        Map<String,String> resultmap = new HashMap<>();
        String return_result = "success";
        map.put("appid", "wx0bc33b99850853f7");//公众号id
        map.put("mch_id", "1543408011");//商户平台id
        map.put("body", "TestDemo");//商品描述
        map.put("nonce_str", nonceStr);//随机字符串

        map.put("spbill_create_ip", payUser.getUserip());//异步回调api
        map.put("notify_url", WX_PAY_CALLBACK);//异步回调api
        map.put("out_trade_no", payUser.getOrderid());//商品订单号
        map.put("total_fee", payUser.getAmount() + "");//真实金额
        map.put("trade_type", "JSAPI");//JSAPI、h5调用
        map.put("openid", payUser.getOpenid());//支付用户openid

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(map.get("appid"), "52669bab3d01221acc5539c92a3c6519", "YOUJIAHONGBEI2019YAOHUANIUbi0227");
        String sign = createSign(map);

        String xml = "<xml>" +
                "<appid>"+map.get("appid") +"</appid>"+
                "<body>"+ map.get("body") +"</body>"+
                "<mch_id>"+ map.get("mch_id") +"</mch_id>"+
                "<nonce_str>"+ nonceStr +"</nonce_str>"+
                "<notify_url>"+ WX_PAY_CALLBACK +"</notify_url>"+
                "<openid>"+ map.get("openid") +"</openid>"+
                "<out_trade_no>"+ map.get("out_trade_no") +"</out_trade_no>"+
                "<spbill_create_ip>"+ map.get("spbill_create_ip") +"</spbill_create_ip>"+
                "<total_fee>"+ map.get("total_fee") + "" +"</total_fee>"+
                "<trade_type>JSAPI</trade_type>"+
                "<sign>"+ sign +"</sign>"+
                "</xml>";

        System.out.println(xml);
        String prepay_id = null;
        try {
            String WX_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            prepay_id = AuthUtil.getPayNo(WX_UNIFIEDORDER,xml);
            if (prepay_id.equals("")) {
                System.out.println("统一支付接口获取预支付订单出错");
            }
        } catch (Exception e) {
            //TODO
            resultmap.put("result","error");
            return resultmap;
        }

        //获取到prepay_id 进行封装
       // System.out.println("prepay_id:" + prepay_id);
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String appid2 = map.get("appid");
        String timestamp = Sha1Util.getTimeStamp();
        String nonceStr2 = nonceStr;
        String prepay_id2 = "prepay_id=" + prepay_id;
        String packages = prepay_id2;
        finalpackage.put("appId", appid2);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonceStr2);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalsign = reqHandler.createSign(finalpackage);
        timeStamp = timestamp;
        nonceStr = nonceStr2;
        packageStr = packages;
        paySign = finalsign;
        payCodeSuccess = map.get("out_trade_no");


        //返回结果
        resultmap.put("appId",appid2);
        resultmap.put("timeStamp",timestamp);
        resultmap.put("nonceStr",nonceStr);
        resultmap.put("packageStr",packages);
        resultmap.put("sign",finalsign);
        resultmap.put("signType","MD5");
        resultmap.put("result","success");


        return resultmap;


    }


    public static String createSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + "YOUJIAHONGBEI2019YAOHUANIUbi0227");
        System.out.println("md5 sb:" + sb);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
                .toUpperCase();
        System.out.println("packge签名:" + sign);
        return sign;

    }

    public static String createjsSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String s = sb.toString();
        s = s.substring(0,s.length() - 1);
       // System.out.println("md5 sb:" + s);
        String sign2 = DigestUtils.shaHex(s);
        String sign = MD5Util.MD5Encode(s, "UTF-8")
                .toUpperCase();
        //System.out.println("packge签名:" + sign2);
        return sign2;

    }





}
