/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.JFrame;
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

/**
 *
 * @author gk
 */
public class testclass2 {
    public int a = 1;
    public String myuserlist = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    
   public String users() throws IOException, URISyntaxException
    {
        LoginAction lgin = new LoginAction();
        String snID = lgin.LoginAction();
        String content= null;
        Gson gson = new Gson();
        String json = "{\n" +
"  \"device_query_list\": [\n" +
"    {\n" +
"      \"device_id\": \"539571364\",\n" +
"      \"end_datetime\": \"2018-06-30T23:59:00.00Z\",\n" +
"      \"start_datetime\": \"2018-06-01T23:59:00.00Z\"\n" +
"    }\n" +
"  ],\n" +
"  \"event_type_code_list\": [\n" +
"    \"8704\"\n" +
"  ],\n" +
"  \"limit\": 10,\n" +
"  \"offset\": 0\n" +
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
           testclass uslist = new testclass();
           uslist.jsonToMap(content);
//           myuserlist=content;
//           System.out.println("My list"+uslist.jsonToMap(content));

        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return myuserlist;
        
    }
//    
    public String[] jsonToMap(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
       
        ArrayList<String> list = new ArrayList<>();
//        Vector<String> list = new Vector<String>();
        String ArryList;
        
        String[] columnNames = { "datetime", "device id", "device name", "userid", "user name", "user group name", "event type description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for(int i=0; i<tsmresponse.length(); i++){
//             ArryList =("{"+tsmresponse.getJSONObject(i).getString("datetime")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("device").getInt("id")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("device").getString("name")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("user").getInt("user_id")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("user").getString("name")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("user_group").getString("name")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("event_type").getInt("code")+
//                ", "+tsmresponse.getJSONObject(i).getJSONObject("event_type").getString("description"))+"}";
//        list.add(ArryList);
////        ArryList =(""+tsmresponse.getJSONObject(i).getString("datetime")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("device").getInt("id")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("device").getString("name")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("user").getInt("user_id")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("user").getString("name")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("user_group").getString("name")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("event_type").getInt("code")+
////                ", "+tsmresponse.getJSONObject(i).getJSONObject("event_type").getString("description"))+"";
////         list.add(ArryList);
        list.add(""+tsmresponse.getJSONObject(i).getString("datetime")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device").getInt("id")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device").getString("name")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getInt("user_id")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getString("name")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user_group").getString("name")+"");
//        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("event_type").getInt("code")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("event_type").getString("description")+"");
        System.out.println("my fucking list"+list);
        model.addRow(list.toArray());
        list.clear();
//        model.addRow( list );
        }
//        valu = list.toArray(new String[0]);
        JFrame testFrame = new JFrame();
        JTable table = new JTable( model );
//        table.setBounds(100,120,100,200);
        JScrollPane scrollPane = new JScrollPane( table );
//        scrollPane.
//        testFrame.add(scrollPane);
//        testFrame.setSize(900,500);
//        testFrame.setVisible(true);
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
//         System.out.println(list);
//        System.out.println(list.get(0));
//        System.out.println(list.get(0).charAt(1));
//        String data = list.get(0);
//        System.out.println(data.);
//        String data1 = Arrays.toString(valu).replace("[", "{").replace("]", "}");
//        String data2 = data1;
//        System.out.println(data2);
       return valu;
    }  
    
    public static void main(String args[]) throws MalformedURLException, IOException, URISyntaxException{
    testclass lg = new testclass();
    lg.users();
    
}
}
