
package com.mycompany.biostartlocal.common.internalframes;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RefEventTypes {
     public int a = 1;
    public String EventCodeList = null;
    public String[] valu = null;
    public String addvalues ;
    JSONArray array = new JSONArray();
    public String eventcode(String snID) throws IOException, URISyntaxException
    {
        
        String content;
       
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder("http://127.0.0.1:8795/v2/references/event_types")
                .build();
        HttpGet postNewUser = new HttpGet(uri);
        
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
        if(statusCode== 200)
        {
            
           EventCodeList=content;

        }
 
        } catch (IOException e) {
            //handle exception
        }
        
        
        return EventCodeList;
        
    }

 
}