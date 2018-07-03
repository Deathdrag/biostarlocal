/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import com.mycompany.biostartlocal.LoginAction;
import com.mycompany.biostartlocal.testclass;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
import org.json.JSONException;
import org.json.JSONObject;

public class EventLogSearch {
       public int a = 1;
    public String[] myuserlist = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    
   public String[] logevents(String DeviceID,String enddate,String startdate,String codelist,String limit,String offset,String snID) throws IOException, URISyntaxException
    {
        String content;
        Gson gson = new Gson();
        Monitoring listcode = new Monitoring();
        System.out.println("content codelist = " + codelist);
        String json = "{\n" +
"  \"device_query_list\": [\n" +
"    {\n" +
"      \"device_id\": "+DeviceID+",\n" +
"      \"end_datetime\": \""+enddate+"\",\n" +
"      \"start_datetime\": \""+startdate+"\"\n" +
"    }\n" +
"  ],\n" +
"  \"event_type_code_list\": [\n" +
"    "+codelist+"" +
"  ],\n" +
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
        System.out.println("content = " + content);
        if(statusCode== 200)
        {
            EventLogSearch uslist = new EventLogSearch(); 
            uslist.jsonToMap(content);

        }else
        {
            jsonTomap results = new jsonTomap();
            results.jsonToMap(content);
        }
 
        } catch (IOException e) {
        }
        
        
        return myuserlist;
        
    }
   
    public String[] jsonToMap(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
       
        ArrayList<String> list = new ArrayList<>();
        
        String ArryList;
        
        String[] columnNames = { "datetime", "device id", "device name", "userid", "user name", "user group name", "event type description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for(int i=0; i<tsmresponse.length(); i++){
            
        list.add(""+tsmresponse.getJSONObject(i).getString("datetime")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device").getInt("id")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device").getString("name")+"");
        if(tsmresponse.getJSONObject(i).has("user"))
        {
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getInt("user_id")+"");
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getString("name")+"");
        }else
        {
            list.add(" ");
            list.add(" ");
        }
//      
        if(tsmresponse.getJSONObject(i).has("user_group"))
        {
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user_group").getString("name")+"");
        }else
        {
            list.add(" ");
        }
        
//        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("event_type").getInt("code")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("event_type").getString("description")+"");
        System.out.println(list);
        model.addRow(list.toArray());
        list.clear();
        
        }
        
        JFrame testFrame = new JFrame();
        JTable table = new JTable( model );
        JScrollPane scrollPane = new JScrollPane( table );
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        System.out.println(list);
        valu = list.toArray(new String[0]);
        
       return valu;
    }  

}
