/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import com.mycompany.biostartlocal.LoginAction;
import com.mycompany.biostartlocal.UsersInDevice;
import com.mycompany.biostartlocal.userlist;
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
public class AvailableDevicesClass {
    public int a = 1;
    public String[] myuserlist = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    public String[] users(String snID) throws IOException, URISyntaxException
    {
//        LoginAction lgn = new LoginAction();
//        
//         = lgn.LoginAction();
        String content= null;
        Gson gson = new Gson();
//        String json = "{\n" +
//"  \"enroll_quality\": 80,\n" +
//"  \"retrieve_raw_image\": true\n" +
//"}";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices")
                
                .addParameter("limit", "0")
                .addParameter("offset", "25")
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
        System.out.println("statusCode = " + statusCode);
        System.out.println("content = " + content);
        if(statusCode== 200)
        {
            AvailableDevicesClass uslist = new AvailableDevicesClass();
            uslist.jsonToMap(content);
            myuserlist=uslist.jsonToMap(content);
            System.out.println("My list"+Arrays.toString(myuserlist));

        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return myuserlist;
        
    }
    
//    json for getting the device list and details
    public String[] jsonToMap(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
//        JSONObject jObject1 =  tsmresponse.getJSONObject("device_type");
        ArrayList<String> list = new ArrayList<>();

    for(int i=0; i<tsmresponse.length(); i++){
        list.add("\""+tsmresponse.getJSONObject(i).getInt("id")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getString("name")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("device_group").getString("name")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("device_type").getString("name")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("lan").getJSONObject("dhcp").getString("device_ip")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getString("status")+"\"");
      
    }
    

        System.out.println(list);
        valu = list.toArray(new String[0]);
     
       return valu;
    } 
    
    public String[] SearchDevice( String snID) throws IOException, URISyntaxException
    {
//        LoginAction lgn = new LoginAction();
//        
//         = lgn.LoginAction();
        String content= null;
        Gson gson = new Gson();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices/search_available")
                .addParameter("timeout", "60")
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
            AvailableDevicesClass uslist = new AvailableDevicesClass();
            uslist.Search(content);
            myuserlist=uslist.jsonToMap(content);
            System.out.println("My list"+Arrays.toString(myuserlist));

        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return myuserlist;
        
    }
    
    public String[] Search(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
//        JSONObject jObject1 =  tsmresponse.getJSONObject("device_type");
        ArrayList<String> list = new ArrayList<>();

    for(int i=0; i<tsmresponse.length(); i++){
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("children").getInt("id")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("children").getString("name")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("children").getJSONObject("device_type").getString("name")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("device_group").getString("name")+"\"");
        list.add("\""+tsmresponse.getJSONObject(i).getJSONObject("children").getJSONObject("lan").getJSONObject("dhcp").getString("device_ip")+"\"");
//        list.add("\""+tsmresponse.getJSONObject(i).getString("status")+"\"");
      
    }
    

        System.out.println(list);
        
        valu = list.toArray(new String[0]);
     
       return valu;
    } 
    
//    public static void main(String args[]) throws MalformedURLException, IOException, URISyntaxException{
//    AvailableDevicesClass lg = new AvailableDevicesClass();
//    lg.SearchDevice();
////            lg.users();
////    
//}

}
