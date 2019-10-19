package com.youga.baking.ps.util;

import java.util.Random;

public class OrderIdUtil {

    public static String getAddreesID(String id,String openid)
    {
        //address id生成规则：2线下标识+4位随机码（字符数字）+3位补位码+用户id+用户地址个数+1

        String shop_id = "AN";
        String head_onuc = verifyCode();
        String speach = "T0";
        String userid = String.valueOf(Integer.valueOf(id)+1);


        return shop_id+head_onuc+speach+userid;
    }

    public static String getOrderId()
    {
        //order id生成规则：2位商铺标识码+4位随机码（字符数字）+3位补位码+时间ID(时间戳)+用户id+4位数字随机码

        String shop_id = "ON";
        String head_onuc = verifyCode();
        String part_code = "010";
        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        String rear_code = rearCode();

        return shop_id+head_onuc+part_code+timestamp+rear_code;
    }


    public static String rearCode(){

        char[] c= getIntCode();//获取包含26个字母大小写和数字的字符数组
        Random rd = new Random();
        String code="";
        for (int k = 0; k <= 4; k++) {
            int index = rd.nextInt(c.length);//随机获取数组长度作为索引
            code+=c[index];//循环添加到字符串后面
        }
        return code;
    }

    private static char[] getIntCode() {

        String i = "1234567890";
        char[] c = i.toCharArray();

        return c;
    }

    public static String verifyCode(){

        char[] c= charArray();//获取包含26个字母大小写和数字的字符数组
        Random rd = new Random();
        String code="";
        for (int k = 0; k <= 4; k++) {
            int index = rd.nextInt(c.length);//随机获取数组长度作为索引
            code+=c[index];//循环添加到字符串后面
        }
        return code;
    }

    public static char[] charArray() {
        int i = 1234567890;
        String word =  String.valueOf(i);
        char[] c = word.toCharArray();

        return c;

    }

    public static String getlogisticsId() {

        String shop_id = "LNO";
        String head_onuc = verifyCode();
        String part_code = "011";
        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        String rear_code = rearCode();

        return shop_id+head_onuc+part_code+timestamp+rear_code;

    }
}
