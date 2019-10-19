package com.youga.baking.ps.util;

import com.youga.baking.ps.obj.ConfigFile;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IDAuthUtil{

    static ConfigFile config = new ConfigFile();
    public static final String APPID = config.getIDAppID();


    public static JSONObject doGetJson(String idCard,String name) throws IOException {

        String host = "https://eid.shumaidata.com";
        String path = "/eid/check";
        String method = "POST";
        String appcode = APPID;
        JSONObject jsonObject= null;

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("idcard",idCard );
        querys.put("name",name);
        Map<String, String> bodys = new HashMap<String, String>();

        try {

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            String result = EntityUtils.toString(response.getEntity());
            jsonObject = JSONObject.fromObject(result);

           //System.out.println(response.toString());
            //获取response的body
           // System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    /*
        读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
