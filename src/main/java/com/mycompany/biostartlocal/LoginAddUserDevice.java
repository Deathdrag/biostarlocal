/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class LoginAddUserDevice {
    public String LoginAction() throws MalformedURLException, IOException {   
            String json = "{\n" +
"                               \"mobile_app_version\": \"\",\n" +
"                                \"mobile_device_type\": \"\",\n" +
"                                 \"mobile_os_version\": \"\",\n" +
"                                  \"name\": \"admin\",\n" +
"                                   \"notification_token\": \"string\",\n" +
"                                    \"password\": \"admin747\",\n" +
"                                     \"user_id\": \"admin\"\n" +
"}";

		URL loginRequest = new URL("http://127.0.0.1:8795/v2/login");
                HttpURLConnection conn = (HttpURLConnection)loginRequest.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.connect();
                
                OutputStream os = conn.getOutputStream();
                os.write(json.getBytes("UTF-8"));
                os.close();
                
		BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
                
                String output;
                while ((output = br.readLine()) != null) {
                }
                String cookieValue = null;
                Map<String, List<String>> headers = conn.getHeaderFields();
               for (Map.Entry<String, List<String>> entry : headers.entrySet())
               {
                 
               }
               cookieValue = conn.getHeaderField("set-cookie");
               String[] strCookieArr = cookieValue.split("bs-cloud-session-id=",0);
               String[] strCookieArr2 = strCookieArr[1].split(";", 0);
               String  sessionID =strCookieArr2[0];   
                
               return sessionID;
               

	}
                
public static void main(String args[]) throws MalformedURLException, IOException{
    LoginAddUserDevice lg = new LoginAddUserDevice();
    lg.LoginAction();
}


}
