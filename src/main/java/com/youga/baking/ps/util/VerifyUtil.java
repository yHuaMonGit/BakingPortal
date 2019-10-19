package com.youga.baking.ps.util;

public class VerifyUtil {

    public static String produceVerifyCode()
    {
        String verifyCode = null;

        verifyCode = String.valueOf(getRandom(1,9999));

        return verifyCode;
    }

    public static int getRandom(int min,int max)
    {

        int authCodeNew = 0 ;

        authCodeNew = (int) Math.round(Math.random() * (9999-1000) +1000);

        return authCodeNew;
    }

}
