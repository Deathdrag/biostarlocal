/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
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

/**
 *
 * @author gk
 */
public class AddUserDevice {
    public String LoginAction() throws MalformedURLException, IOException {   
            String json = "{\n" +
"                               \"mobile_app_version\": \"\",\n" +
"                                \"mobile_device_type\": \"\",\n" +
"                                 \"mobile_os_version\": \"\",\n" +
"                                  \"name\": \"admin\",\n" +
"                                   \"notification_token\": \"string\",\n" +
"                                    \"password\": \"dragon747\",\n" +
"                                     \"user_id\": \"dragon\"\n" +
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
    
    public String users(String deviceID) throws IOException, URISyntaxException
    {
        AddUserDevice lg = new AddUserDevice();
        String snID = lg.LoginAction();
        userlist lst = new userlist();
        lst.users();
        String[] idList = lst.users();
        System.out.println("my idlist"+Arrays.toString(idList));
        String content= null;
        Gson gson = new Gson();
        String json = "{\n" +
"  \"ids\": "+Arrays.toString(idList)+"\n" +
"}";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices/"+deviceID+"/users")
                
//                .addParameter("limit", "10000")
//                .addParameter("offset", "0")
////                .addParameter("Content-type", "application/json")
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
            userlist uslist = new userlist();
            uslist.jsonToMap(content);
            jsonTomap msg = new jsonTomap();
            msg.jsonToMap(content);
//           myuserlist=uslist.jsonToMap(content);
//           System.out.println("My list"+Arrays.toString(myuserlist));

        }
        else if(statusCode != 200)
        {
            jsonTomap msg = new jsonTomap();
            msg.jsonToMap(content);
        } else {
        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return null;
        
    }
    public static void main(String args[]) throws MalformedURLException, IOException, URISyntaxException{
    AddUserDevice lg = new AddUserDevice();
    lg.users("539571364");
}
}
