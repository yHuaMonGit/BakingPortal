package com.youga.baking.ps.obj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile {

    public static final String ConfigFile = "/home/tomcat/apache-tomcat-8.5.32/webapps/Portal/WEB-INF/properties/app.properties";

    public static String debugMode = null;
    public static String AppID = null;
    public static String AppSecreat = null;
    public static String accessKeyId = null;
    public static String accessKeySecret = null;
    public static String driver = null;
    public static String url = null;
    public static String user = null;
    public static String password = null;
    public static String callback_sellcenter = null;
    public static String callBack_membercenter = null;
    public static String debug_driver = null;
    public static String debug_url = null;
    public static String debug_user = null;
    public static String debug_password = null;
    public static String debug_callback_sellcenter = null;
    public static String debug_callBack_membercenter = null;
    Properties properties = new Properties();
    /**
     * 读取配置文件 获取参数
     */
    public ConfigFile()
    {
        properties = new Properties();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ConfigFile));
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getAppID() {
        return this.properties.getProperty("AppID");
    }

    public String getAppSecreat() {
        return this.properties.getProperty("AppSecreat");
    }

    public String getAccessKeyId() {
        return this.properties.getProperty("accessKeyId");
    }

    public String getAccessKeySecret() {
        return this.properties.getProperty("accessKeySecret");
    }

    public String getDriver() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_driver");
        }else
            {
                return this.properties.getProperty("driver");
            }
    }

    public String getUrl() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_url");
        }else
        {
            return this.properties.getProperty("url");
        }
    }

    public String getUser() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_user");
        }else
        {
            return this.properties.getProperty("user");
        }
    }

    public String getPassword() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_password");
        }else {
            return this.properties.getProperty("password");
        }
    }

    public String getCallback_sellcenter() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_callback_sellcenter");
        }else {
            return this.properties.getProperty("callback_sellcenter");
        }
    }

    public String getCallBack_membercenter() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_callBack_membercenter");
        }else {
            return this.properties.getProperty("callBack_membercenter");
        }
    }

    public String getCallBack_Auth() {
        if(this.properties.getProperty("debugMode").equals("1"))
        {
            return this.properties.getProperty("debug_callBack_auth");
        }else {
            return this.properties.getProperty("callBack_auth");
        }
    }

    public  String getRegistUrl() {
        return this.properties.getProperty("regist_url");
    }

    public String getMemberCenter() {
        return this.properties.getProperty("member_url");
    }

    public String getIDAppID() {

        return this.properties.getProperty("IDAppID");
    }

    public String getIDAppSecreat() {
        return this.properties.getProperty("IDAppSecret");
    }

    public String getLottery() {
        return this.properties.getProperty("callBack_lottery");
    }

    public String getShoppingCenterURL() {
        return this.properties.getProperty("shoppingcenter_url");
    }

    public String getAddressURL() {
        return this.properties.getProperty("address_rul");
    }
}
