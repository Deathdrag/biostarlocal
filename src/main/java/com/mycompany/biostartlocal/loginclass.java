/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author gk
 */
public class loginclass {
   
 
  public String logindata(String userid, String pswrd, String domain) 
  throws IOException {
    RequestBody formBody = new FormBody.Builder()
      .add("user_id", userid)
      .add("password", pswrd)
      .add("notification_token","")
      .add("mobile_device_type","")
      .add("mobile_app_version","")
      .add("mobile_os_version","")
      .add("name",domain)
      .build();
 

    Request request = new Request.Builder()
      .url("http://127.0.0.1:8795/v2/login")
      .post(formBody)
      .build();
     OkHttpClient client = new OkHttpClient();
    Call call = client.newCall(request);
    Response response = call.execute();
    String result=response.body().string();
     System.out.println(result);
     return result;
    //assertThat(response.code(),equalTo(200));
}  

   
    public static void main(String args[]) throws IOException{
        
}
}


