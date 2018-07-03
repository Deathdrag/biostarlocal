/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import com.mycompany.biostartlocal.LoginAction;
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
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public class VerifyingFingerPrint {
    public String verify(String devicId,String template0,String template1) throws IOException, URISyntaxException
    {
        LoginAction lgin = new LoginAction();
        String snID = lgin.LoginAction();
        String content= null;
        String results = null;
        Gson gson = new Gson();
        String json = "{\n" +
"  \"security_level\": \"DEFAULT\",\n" +
"  \""+template0+"\": {},\n" +
"  \""+template1+"\": {}\n" +
"}";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices/"+devicId+"/verify_fingerprint")
                
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
        System.out.println("statusCode = " + statusCode);
        System.out.println("content = " + content);
        if(statusCode== 200)
        {
            JSONObject jObject = new JSONObject(content);
            results = (String) jObject.get("message");
         
        }
        else if(statusCode != 200)
        {
            jsonTomap msg = new jsonTomap();
            msg.jsonToMap(content);
        }
        
        } catch (IOException e) {
            //handle exception
        }
        
        return results;
        
        }
}
