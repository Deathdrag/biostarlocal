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
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
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
public class AvailableDeviceClass {
    public int a = 1;
    public String myuserlist = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    public String devicelist(String snID) throws IOException, URISyntaxException
    {
        String content;
        Gson gson = new Gson();
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices")
                
                .addParameter("limit", "0")
                .addParameter("offset", "0")
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
        System.out.println("statusCode = " + statusCode);
        System.out.println("content = " + content);
        if(statusCode== 200)
        {
            AvailableDeviceClass uslist = new AvailableDeviceClass();
            myuserlist= content;
           
        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return myuserlist;
        
    }
    
}
