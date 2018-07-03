/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public class userlist {
    public int a = 1;
    public String myuserlist = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    public String users(String snID) throws IOException, URISyntaxException
    {
        
        String content= null;
        Gson gson = new Gson();
//        String json = "{\n" +
//"  \"enroll_quality\": 80,\n" +
//"  \"retrieve_raw_image\": true\n" +
//"}";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/users")
                
                .addParameter("limit", "10000")
                .addParameter("offset", "0")
//                .addParameter("Content-type", "application/json")
                .build();
        HttpGet postNewUser = new HttpGet(uri);
        
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
        if(statusCode== 200)
        {
            
           myuserlist=content;

        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return myuserlist;
        
    }
   
}
