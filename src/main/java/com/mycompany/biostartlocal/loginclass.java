/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import com.mycompany.biostartlocal.common.internalframes.MyProfile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author gk
 */
public class loginclass {
   
public String LoginAction(String userid,String password) throws MalformedURLException, IOException, URISyntaxException {   
            String json = "{\n" +
"                               \"mobile_app_version\": \"\",\n" +
"                                \"mobile_device_type\": \"\",\n" +
"                                 \"mobile_os_version\": \"\",\n" +
"                                  \"name\": \"admin\",\n" +
"                                   \"notification_token\": \"\",\n" +
"                                    \"password\": \""+password+"\",\n" +
"                                     \"user_id\": \""+userid+"\"\n" +
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
                while ((output = br.readLine()) != null) { System.out.println(output+"/n");
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
               
               MyProfile seeprofile = new MyProfile();
               seeprofile.myprofile(sessionID);
               return sessionID;
               

	}
// jsonTomap j=new jsonTomap();
//  public String logindata(String userid, String pswrd, String domain) 
//  throws IOException {
//    RequestBody formBody = new FormBody.Builder()
//      .add("user_id", userid)
//      .add("password", pswrd)
//      .add("notification_token","")
//      .add("mobile_device_type","")
//      .add("mobile_app_version","")
//      .add("mobile_os_version","")
//      .add("name",domain)
//      .build();
// 
//
//    Request request = new Request.Builder()
//      .url("http://127.0.0.1:8795/v2/login")
//      .post(formBody)
//      .build();
//     OkHttpClient client = new OkHttpClient();
//    Call call = client.newCall(request);
//    Response response = call.execute();
//    String result=response.body().string();
////    JOptionPane.showMessageDialog(null,result);
//     System.out.println(result);
//     return result;
    //assertThat(response.code(),equalTo(200));
}  




