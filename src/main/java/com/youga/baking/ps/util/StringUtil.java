package com.youga.baking.ps.util;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static String getUrlPrama(String url,String prama)
    {
        String result = null;
        String delimiter = "&";
        String [] argsArr = url.split(delimiter);

        for(String str :argsArr)
        {
            String [] tmpArr = str.split("=");

            if (tmpArr[0].equals(prama))
            {
                result = tmpArr[1];
            }
        }
        return result;
    }

//    public static List<BasketInfo> getGoodsList(String openid,String goodslist)
//    {
//        List<BasketInfo> basketInfoList = new ArrayList<>();
//
//        String [] goodsArr = goodslist.split(",");
//
//        for(String goodsInfo:goodsArr)
//        {
//            String [] tmpArr = goodsInfo.split("-");
//            basketInfoList.add(new BasketInfo(openid,tmpArr[0],tmpArr[1]));
//        }
//
//        return basketInfoList;
//
//    }

    public static String getBirthDayByIdCard(String idcard)
    {
        String str = idcard;
        str = str.substring(6,18);
        str = str.substring(0,8);
        return str;
    }

    public static String[] getAddress(String addressPCC) {

        String [] result = new String[3];
        String [] colume = addressPCC.split("\\s+");

       return colume;
    }

    public static String getURLEncoderString(String str) {//url编码
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String URLDecoderString(String str) {//url解码
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encryptOpenid(String openid) {

        return string2MD5(openid);
    }


    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
