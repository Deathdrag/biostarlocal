/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class adduser { 
  public void register() throws IOException {
      String json = "{\n" +
"  \"access_groups\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"included_by_user_group\": \"Yes\",\n" +
"      \"name\": \"admin\"\n" +
"    }\n" +
"  ],\n" +
"  \"email\": \"Kapul7747@gmail.com\",\n" +
"  \"expiry_datetime\": \"2019-02-10T02:24:39.298Z\",\n" +
"  \"login_id\": \"admin\",\n" +
"  \"name\": \"francis\",\n" +
"  \"password\": \"franc\",\n" +
"  \"permission\": {\n" +
"    \"id\": 1,\n" +
"    \"name\": \"Administrator\",\n" +
"    \"permissions\": [\n" +
"      {\n" +
"        \"allowed_group_id_list\": [\n" +
"          \"1\"\n" +
"        ],\n" +
"        \"module\": \"CARD\",\n" +
"        \"read\": true,\n" +
"        \"write\": true\n" +
"      }\n" +
"    ]\n" +
"  },\n" +
"  \"phone_number\": \"0715668934\",\n" +
"  \"pin\": \"\",\n" +
"  \"security_level\": \"\",\n" +
"  \"start_datetime\": \"2018-02-10T12:08:05.295Z\",\n" +
"  \"status\": \"AC\",\n" +
"  \"user_group\": {\n" +
"    \"id\": 1,\n" +
"    \"name\": \"All Users\"\n" +
"  },\n" +
"  \"user_id\": \"48\"\n" +
"}";
 
    
    
      RequestBody body = RequestBody.create(
      MediaType.parse("application/json; charset=utf-8"), json);
 
    Request request = new Request.Builder()
      .url("http://127.0.0.1:8795/v2/users")
      .post(body)
      .build();
  
     OkHttpClient client = new OkHttpClient();
    Call call = client.newCall(request);
    Response response = call.execute();
    String result=response.body().string();
     System.out.println(result);
    
}
  public static void main(String args[]) {
      
      adduser user=new adduser();
      try {
          user.register();
      } catch (IOException ex) {
          Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
      }
}
     
}
