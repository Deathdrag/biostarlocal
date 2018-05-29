/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
//import okhttp3.Call;
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;

public class adduser extends LoginAction {
    
  public void adduser() throws IOException, URISyntaxException {
      LoginAction lgn = new LoginAction();
      String snID = lgn.LoginAction();
      System.out.println(snID);
      String json = "{\n" +
"  \"access_groups\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"included_by_user_group\": \"Yes\",\n" +
"      \"name\": \"admin\"\n" +
"    }\n" +
"  ],\n" +
"  \"email\": \"dragon@gmail.com\",\n" +
"  \"expiry_datetime\": \"2019-02-10T02:24:39.298Z\",\n" +
"  \"login_id\": \"dragon\",\n" +
"  \"name\": \"paul Kalenda dragon\",\n" +
"  \"password\": \"dragon747\",\n" +
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
"  \"user_id\": \"879\"\n" +
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
//		HttpClient client = new HttpClient() {
//          @Override
//          public HttpParams getParams() {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public ClientConnectionManager getConnectionManager() {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public HttpResponse execute(HttpUriRequest hur) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public HttpResponse execute(HttpUriRequest hur, HttpContext hc) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public HttpResponse execute(HttpHost hh, HttpRequest hr) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public HttpResponse execute(HttpHost hh, HttpRequest hr, HttpContext hc) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public <T> T execute(HttpUriRequest hur, ResponseHandler<? extends T> rh) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public <T> T execute(HttpUriRequest hur, ResponseHandler<? extends T> rh, HttpContext hc) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public <T> T execute(HttpHost hh, HttpRequest hr, ResponseHandler<? extends T> rh) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//
//          @Override
//          public <T> T execute(HttpHost hh, HttpRequest hr, ResponseHandler<? extends T> rh, HttpContext hc) throws IOException, ClientProtocolException {
//              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          }
//      };
                
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
                
//		CloseableHttpResponse response = ((CloseableHttpClient) httpClient).execute(postNewUser, context);
                                
		try (CloseableHttpResponse response = httpClient.execute(postNewUser,context)) {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String responseBody = EntityUtils.toString(entity);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				UserSearchResult searchResult = gson.fromJson(responseBody,
						UserSearchResult.class);

				String list = searchResult.toString();
                                
			} else {
				System.err.println(responseBody);
			}
			EntityUtils.consume(entity);
		}

		
	}

  public static void main(String args[]) throws IOException {
      adduser add = new adduser();
      try {
          add.adduser();
      } catch (URISyntaxException ex) {
          Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
      }
}
     
}
