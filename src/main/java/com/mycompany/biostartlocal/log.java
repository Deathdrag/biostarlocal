/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class log {
    
    public void log()throws IOException {
    String lgin = "{\n" +
"  \"mobile_app_version\": \"\",\n" +
"  \"mobile_device_type\": \"\",\n" +
"  \"mobile_os_version\": \"\",\n" +
"  \"name\": \"admin\",\n" +
"  \"notification_token\": \"\",\n" +
"  \"password\": \"admin747\",\n" +
"  \"user_id\": \"1\"\n" +
"}";
    
    RequestBody body = RequestBody.create(
      MediaType.parse("application/json; charset=utf-8"), lgin);
    
    Request request = new Request.Builder()
      .url("http://127.0.0.1:8795/v2/login")
      .post(body)
      .build();
    
    OkHttpClient client = new OkHttpClient();
    Call call = client.newCall(request);
   // Response response = call.execute();
    //String result=response.body().string();
     //System.out.println(result);
    
}
}
