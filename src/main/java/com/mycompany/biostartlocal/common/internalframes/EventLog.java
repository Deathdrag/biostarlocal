/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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
import org.json.JSONArray;

public class EventLog {
       public int a = 1;
    public String myuserlist = null;
    public String[] mydeviceuserlist = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    
   public String eventlogs(String snID,String[] codelist,String[] deviceQueryList) throws IOException, URISyntaxException
    {
        String content = null;
        Gson gson = new Gson();
//       
        int limit = 0;
        int offset = 0;
        String json = "{\n" +
"  \"device_query_list\": "+Arrays.toString(deviceQueryList)+",\n" +
"  \"event_type_code_list\": "+Arrays.toString(codelist)+",\n" +
"  \"limit\": "+limit+",\n" +
"  \"offset\": "+offset+"\n" +
"}";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/monitoring/event_log/search_by_device")
                
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
        System.out.println("content fuck = " + content);
        if(statusCode== 200)
        {
             
            myuserlist=content;

        }
 
        } catch (IOException e) {
        }
        
        
        return content;
        
    }
//    
//    
//    public String[] deviceIDList(String snID) throws IOException, URISyntaxException
//    {
////        LoginAction lgn = new LoginAction();
//////        String snID = lgn.LoginAction();
//        
//        String content;
//        Gson gson = new Gson();
//        
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/devices")
//                
//                .addParameter("limit", "0")
//                .addParameter("offset", "25")
//                .build();
//        HttpGet postNewUser = new HttpGet(uri);
//        
//        CookieStore cookieStore = new BasicCookieStore();
//	BasicClientCookie cookie = new BasicClientCookie("bs-cloud-session-id",snID);
//	cookie.setDomain("127.0.0.1");
//	cookie.setPath("/");
//	cookieStore.addCookie(cookie);
//        
//        HttpClientContext context = HttpClientContext.create();
//        context.setCookieStore(cookieStore);
//
//	try (CloseableHttpResponse httpResponse = httpClient.execute(postNewUser,context)) {
//        content = EntityUtils.toString(httpResponse.getEntity());
// 
//        int statusCode = httpResponse.getStatusLine().getStatusCode();
//        System.out.println("fuck u = " + content);
//        if(statusCode== 200)
//        {
//            EventLog uslist = new EventLog();
//            uslist.devicelist(content);
//            mydeviceuserlist=uslist.devicelist(content);
////            System.out.println("fuck u 2 = " + Arrays.toString(mydeviceuserlist));
//
//        }
// 
//        } catch (IOException e) {
//            //handle exception
//        }
//        
//        
//        return mydeviceuserlist;
//        
//    }
//    
////    json for getting the device ID list only
//    public String[] devicelist(String t) throws JSONException
//    {
//        JSONObject jsonObject = new JSONObject(t);
//        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
//        ArrayList<String> list = new ArrayList<>();
////
//   int j = 01;
//    for(int i=0; i<tsmresponse.length(); i++){
////       
//      list.add("{\n" +
//"      \"device_id\": \""+tsmresponse.getJSONObject(i).getInt("id")+"\",\n" +
//"      \"end_datetime\": \""+YearMonth.now()+"-"+Month.from(LocalDate.now()).length(true)+"T23:59:00.00Z\",\n" +
//"      \"start_datetime\": \""+YearMonth.now()+"-0"+j+"T00:00:00.00Z\"\n" +
//"    }");
//      
//    }
//    
//    
//
//        System.out.println(list);
//        valu = list.toArray(new String[0]);
//     
//       return valu;
//    } 
//    
    
//    
//     public static void main(String args[]) throws MalformedURLException, IOException, URISyntaxException{
//    EventLog lg = new EventLog();
//    lg.eventlogs();
//    
//}
}
