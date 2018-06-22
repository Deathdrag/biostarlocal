/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.google.gson.Gson;
import com.mycompany.biostartlocal.LoginAction;
import com.mycompany.biostartlocal.jsonTomap;
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

/**
 *
 * @author gk
 */
public class UpdateProfileClass {
    
    public String updateprofile(String mail, String namee, String loginid,String pswrd, 
          String phone,String session1) throws IOException, URISyntaxException {
      
      String snID = session1;
      String content= null;
 
     String json = "{\n" +
"  \"email\": \""+mail+"\",\n" +
"  \"login_id\": \""+loginid+"\",\n" +
"  \"name\": \""+namee+"\",\n" +
"  \"password\": \""+pswrd+"\",\n" +
"  \"phone_number\": \""+phone+"\",\n" +
"  \"photo\": \"\",\n" +
"  \"pin\": \"\"\n" +
"}";
     
    LoginAction loggedinUser = new LoginAction();
                
		Gson gson = new Gson();
//                 HttpClient client = new DefaultHttpClient();
               CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		URI uri = new URIBuilder("http://127.0.0.1:8795/v2/users/my_profile")
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
                
                } catch (IOException e) {
                    //handle exception
                }
                
            return content;
	
	}
}
