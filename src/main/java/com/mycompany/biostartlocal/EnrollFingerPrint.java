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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author gk
 */
public class EnrollFingerPrint { 

    public String enrollfingerprint (String user_id,String details) throws IOException, URISyntaxException
    {
       
        LoginAction lgin = new LoginAction();
        String snID = lgin.LoginAction();
        ScanFingerPrint temp = new ScanFingerPrint();
        EnrollFingerPrint addtemp = new EnrollFingerPrint();

        String content= null;
        Gson gson = new Gson();
        String json = ""+details+"";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/users/"+user_id+"/fingerprint_templates")
//                .addParameter("id",user_id)
//                .addParameter("Content-type", "application/json")
                .build();
        HttpPut putNewfinger = new HttpPut(uri);
        putNewfinger.setEntity(new StringEntity(json, "UTF8"));
        putNewfinger.setHeader("Content-type", "application/json");
        
        CookieStore cookieStore = new BasicCookieStore();
	BasicClientCookie cookie = new BasicClientCookie("bs-cloud-session-id",snID);
	cookie.setDomain("127.0.0.1");
	cookie.setPath("/");
	cookieStore.addCookie(cookie);

	HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        
       try (CloseableHttpResponse httpResponse = httpClient.execute(putNewfinger,context)) {
        content = EntityUtils.toString(httpResponse.getEntity());
 
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        System.out.println("content = " + content);
        
        if(statusCode== 200)
        {
        jsonTomap results = new jsonTomap();
        results.jsonToMap(content);
//        JOptionPane.showMessageDialog(null,"Scan was completed successfully");
        }
        else if(statusCode != 200)
        {
            jsonTomap msg = new jsonTomap();
            msg.jsonToMap(content);
        }
        
    } catch (IOException e) {
            //handle exception
    }
        return content;
    }

}