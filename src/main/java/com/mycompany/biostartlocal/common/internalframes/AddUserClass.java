/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import com.mycompany.biostartlocal.LoginAction;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
//import okhttp3.Call;
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;

public class AddUserClass extends LoginAction {
    
    
  public String adduser(String mail, String namee, String loginid,String expdate, String pswrd, 
          String stdate,String userid, String phone, String operator, int no) throws IOException, URISyntaxException {
      LoginAction lgn = new LoginAction();
      String content= null;
      String snID = lgn.LoginAction();
      System.out.println(snID);
//      RequestBody formBody = new FormBody.Builder()
//              .add(namee, namee)
//              .build();
     String json = "{\n" +
"  \"access_groups\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"included_by_user_group\": \"Yes\",\n" +
"      \"name\": \"admin\"\n" +
"    }\n" +
"  ],\n" +
"  \"email\": \""+ mail +"\",\n" +
"  \"expiry_datetime\": \""+ expdate +"\",\n" +
"  \"login_id\": \""+ loginid +"\",\n" +
"  \"name\": \""+ namee +"\",\n" +
"  \"password\": \""+ pswrd +"\",\n" +
"  \"permission\": {\n" +
"    \"id\": "+no+",\n" +
"    \"name\": \""+ operator +"\",\n" +
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
"  \"phone_number\": \""+ phone +"\",\n" +
"  \"pin\": \"\",\n" +
"  \"security_level\": \"\",\n" +
"  \"start_datetime\": \""+ stdate +"\",\n" +
"  \"status\": \"AC\",\n" +
"  \"user_group\": {\n" +
"    \"id\": 1,\n" +
"    \"name\": \"All Users\"\n" +
"  },\n" +
"  \"user_id\": \""+ userid +"\"\n" +
"}";


//        URL url2 = new URL("http://127.0.0.1:8795/v2/users");
//        HttpURLConnection connection = (HttpURLConnection)url2.openConnection();
//        connection.setAllowUserInteraction(false);
//        connection.setUseCaches(false);
//        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("bs-cloud-session-id", snID);
//       connection.connect();
//        
//        
//        OutputStream osp = connection.getOutputStream();
//        osp.write(json.getBytes("UTF-8"));
//        osp.close();
//        
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//       (connection.getInputStream())));
//        
//        String output;
//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) 
//        {
//            System.out.println(output+"/n");
//        }
//        
////        URL addUserRequest = new URL("http://127.0.0.1:8795/v2/users");
////        HttpURLConnection conn = (HttpURLConnection)addUserRequest.openConnection();
//        
//    
//    
////      RequestBody body = RequestBody.create(
////      MediaType.parse("application/json; charset=utf-8"), json);
//// 
////    Request request = new Request.Builder()
////      .url("http://127.0.0.1:8795/v2/users")
////      .post(body)
////      .build();
////  
////     OkHttpClient client = new OkHttpClient();
////    Call call = client.newCall(request);
////    Response response = call.execute();
////    String result=response.body().string();
////     System.out.println(result);
    LoginAction loggedinUser = new LoginAction();
                
		Gson gson = new Gson();
//                 HttpClient client = new DefaultHttpClient();
               CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		URI uri = new URIBuilder("http://127.0.0.1:8795/v2/users")
//                               .addParameter("body", json)
//                                .addParameter("Content-type", "application/json")
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
                jsonTomap results = new jsonTomap();
//                JOptionPane.showMessageDialog(null,content.);
                results.jsonToMap(content);
                
//                String[] strCookieArr = content.split("message",0);
//                for (String strCookieArr1 : strCookieArr) {
//                       System.out.println(strCookieArr1);
//                    }
//                JFrame f=new JFrame();
//                JOptionPane.showMessageDialog(f,"Successfully Updated.","Alert",JOptionPane.WARNING_MESSAGE);
//        
                } catch (IOException e) {
                    //handle exception
                }
                
            return content;
	
	}
     
}
