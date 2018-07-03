/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public class jsonTomap {
    
    public String jsonToMap(String t) throws JSONException, IOException, URISyntaxException{
    String msg = null;
   
    HashMap<String, String> map = new HashMap<>();
    JSONObject jObject = new JSONObject(t);
    Iterator<?> keys = jObject.keys();

    while (keys.hasNext()) {
        String key = (String) keys.next();
        String value = jObject.getString(key);
        map.put(key, value);

    }

//    System.out.println("json : " + jObject);
//    System.out.println("map : " + map);
     msg = map.get("message");
    JOptionPane.showMessageDialog(null,map.get("message"));
        return msg;
    
}

//    void /*jsonToMap*/(String content) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
   
}
