/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.mycompany.biostartlocal.common.internalframes.MyProfileClass;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public class loginclass {
   
    public String sessionID = null;
    
public String LoginAction(String userid,String password) throws MalformedURLException, IOException, URISyntaxException {
    
            String Results = null;
            String content= null;
            String json = "{\n" +
"                               \"mobile_app_version\": \"\",\n" +
"                                \"mobile_device_type\": \"\",\n" +
"                                 \"mobile_os_version\": \"\",\n" +
"                                  \"name\": \"admin\",\n" +
"                                   \"notification_token\": \"\",\n" +
"                                    \"password\": \""+password+"\",\n" +
"                                     \"user_id\": \""+userid+"\"\n" +
"}";
            
            
            
                CloseableHttpClient httpclient = HttpClients.createDefault();
                
                HttpPost login = new HttpPost("http://127.0.0.1:8795/v2/login");
                login.setEntity(new StringEntity(json, "UTF8"));
                login.setHeader("Content-type", "application/json");
                login.getHeaders("set-cookie");
                try (CloseableHttpResponse response = httpclient.execute(login)) {
                        
                        
                        content = EntityUtils.toString(response.getEntity());
                        int statusCode = response.getStatusLine().getStatusCode();
//                        System.out.println("statusCode = " + statusCode);
//                        System.out.println("content = " + content);
                        
                        if(statusCode== 200)
                        {
                            Header[] cookies = response.getHeaders("set-cookie");
                            if (cookies == null) {
                                    System.out.println("None");
                            } else if(cookies != null) {
                                System.out.println(Arrays.toString(cookies));
                                String[] strCookieArr = Arrays.toString(cookies).split("bs-cloud-session-id=",0);
                                String[] strCookieArr2 = strCookieArr[1].split(";", 0);
                                sessionID =strCookieArr2[0];
                                System.out.println(sessionID);
                            }
                            Results = content;
        
                        }
                        else if(statusCode != 200)
                        {
                            JSONObject jObject = new JSONObject(content);
//                            Results = (String) jObject.get("message");
                            JOptionPane.showMessageDialog(null,(String) jObject.get("message"));
                        }
//                        EntityUtils.consume(response.getEntity());
                        }
            
               return Results;
               

	}
}  




