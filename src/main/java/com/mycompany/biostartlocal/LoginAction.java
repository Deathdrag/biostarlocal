package com.mycompany.biostartlocal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gk
 */
public class LoginAction {

	public String LoginAction() throws MalformedURLException, IOException {   
            String json = "{\n" +
"                               \"mobile_app_version\": \"\",\n" +
"                                \"mobile_device_type\": \"\",\n" +
"                                 \"mobile_os_version\": \"\",\n" +
"                                  \"name\": \"admin\",\n" +
"                                   \"notification_token\": \"string\",\n" +
"                                    \"password\": \"admin747\",\n" +
"                                     \"user_id\": \"admin\"\n" +
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
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                System.out.println(output+"/n");
                }
                String cookieValue = null;
                Map<String, List<String>> headers = conn.getHeaderFields();
              
//                headers.forEach((key,value)-> {
//                if (key.startsWith("Set-Cookie")) {
//                value.forEach(v->System.out.println(v));
//                    }
//                });
               for (Map.Entry<String, List<String>> entry : headers.entrySet())
               {
                   System.out.println("Key : " + entry.getKey() + 
                       " ,Value : " + entry.getValue());
                  
                
               }
               cookieValue = conn.getHeaderField("set-cookie");
               System.out.println(cookieValue);
               String[] strCookieArr = cookieValue.split("bs-cloud-session-id=",0);
               String[] strCookieArr2 = strCookieArr[1].split(";", 0);
               String  sessionID =strCookieArr2[0];
//               System.out.println("Hey paul");
               System.out.println(sessionID);
               
//                List<String> cookies = conn.getHeaderFields().get("Set-Cookie");
//      
//                for (String cookie : cookies) {
//                   System.out.println("Set-Cookies" + cookie);
//                conn.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
//                
//                }
//                boolean isSessionIdContained = conn.getHeaderField("Set-Cookie").equals("Set-Cookie");
//                  if(isSessionIdContained == true)
//                  {
//                      System.out.println("Hey paul");
//                      cookieValue = conn.getHeaderField("set-cookie");
//                      System.out.println(cookieValue);
//                   }
//                
//                String cookieValue = null; 
//                for (Iterator iter = values.iterator(); iter.hasNext(); ) {
//                    String v = (String)iter.next();
//                    if (cookieValue == null)
//                        cookieValue = v;
//                    else
//                        cookieValue = cookieValue + ";" + v;
//                       System.out.println(cookieValue);
//                }
//                    os.close();
                
               return sessionID;
               

	}
                
public static void main(String args[]) throws MalformedURLException, IOException{
    LoginAction lg = new LoginAction();
    lg.LoginAction();
}


}


