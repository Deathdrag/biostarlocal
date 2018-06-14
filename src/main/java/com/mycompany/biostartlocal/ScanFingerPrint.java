/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author gk
 */
public class ScanFingerPrint {
    
    
    public String scan(String devicId) throws IOException, URISyntaxException
    {
        LoginAction lgin = new LoginAction();
        String snID = lgin.LoginAction();
        String content= null;
        String message= null;
        Gson gson = new Gson();
        String json = "{\n" +
"  \"enroll_quality\": 80,\n" +
"  \"retrieve_raw_image\": true\n" +
"}";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices/"+devicId+"/scan_fingerprint")
                
//                .addParameter("id", devicId)
//                .addParameter("Content-type", "application/json")
                .build();
        HttpPost postNewUser = new HttpPost(uri);
        postNewUser.setEntity(new StringEntity(json, "UTF8"));
        postNewUser.setHeader("Content-type", "application/json");
        
        CookieStore cookieStore = new BasicCookieStore();
	BasicClientCookie cookie = new BasicClientCookie("bs-cloud-session-id",snID);
	cookie.setDomain("127.0.0.1");
	cookie.setPath("/");
	cookieStore.addCookie(cookie);
        
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

	try (CloseableHttpResponse httpResponse = httpClient.execute(postNewUser,context)) {
        content = EntityUtils.toString(httpResponse.getEntity());
 
        int statusCode = httpResponse.getStatusLine().getStatusCode();
//        System.out.println("statusCode = " + statusCode);
//        System.out.println("content = " + content);
        
        
        if(statusCode== 200)
        {
////            
        JOptionPane.showMessageDialog(null,"Scan was completed successfully");
        
        message= content;
        System.out.println("my templates= " + message);
        
        }
        else if(statusCode == 400)
        {
            jsonTomap msgr = new jsonTomap();
           message = msgr.jsonToMap(content);
        }
        else if(statusCode != 200 && statusCode != 400)
        {
            jsonTomap msgr = new jsonTomap();
            message= msgr.jsonToMap(content);
        }
        
        } catch (IOException e) {
            //handle exception
        }
        
        return message;
        
        }
    
      public String jsonToMap(String t) throws JSONException, IOException, URISyntaxException{
    
       JSONObject jsonObject = new JSONObject(t);
       
       String msg = (String) jsonObject.get("template0");
       

        return msg;
    
}
       public String template(String t) throws JSONException, IOException, URISyntaxException{
    
       JSONObject jsonObject = new JSONObject(t);
       
       String msg = (String) jsonObject.get("template_image0");
       

        return msg;
    
}
         
}
