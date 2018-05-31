/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

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
    
    public void jsonToMap(String t) throws JSONException, IOException, URISyntaxException{
    
   
    HashMap<String, String> map = new HashMap<>();
    JSONObject jObject = new JSONObject(t);
    Iterator<?> keys = jObject.keys();

    while (keys.hasNext()) {
        String key = (String) keys.next();
        String value = jObject.getString(key);
        map.put(key, value);

    }

    System.out.println("json : " + jObject);
//    System.out.println("map : " + map);
//    JOptionPane.showMessageDialog(null,map);
}
   
}
