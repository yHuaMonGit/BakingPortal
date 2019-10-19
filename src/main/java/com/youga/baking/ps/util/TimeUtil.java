package com.youga.baking.ps.util;


import com.youga.baking.ps.obj.ReturnCode;

public class TimeUtil {

    public static String getCurrentTime() {

        return String.valueOf(System.currentTimeMillis());

    }

    public static String TimeCompare(String t1,String t2,int seconds)
    {
        long time1 ,time2;
        if( null == t1 || null == t2 || "".equals(t1) ||"".equals(t2) )
        {
            return ReturnCode.TIME_NULL;
        }
        else {
            time1 = Long.valueOf(t1);
            time2 = Long.valueOf(t2);

            if ( (time2 - time1)/1000 -seconds < 0 )
            {
                return ReturnCode.VERIFY_EFFECTIVE;
            }
            else {
                return ReturnCode.VERIFY_INVALID;
            }

        }

    }

}
