/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ToMap {
   public Map<String, Object> jsonToMap(String t) throws JSONException {
    Map<String, Object> retMap = new HashMap<>();
    JSONObject jObject = new JSONObject(t);
    Iterator<?> keys = jObject.keys();

    if(t != JSONObject.NULL) {
        retMap = toMap(jObject);
    }
    return retMap;
}

public Map<String, Object> toMap(JSONObject object) throws JSONException {
    Map<String, Object> map = new HashMap<>();

    Iterator<String> keysItr = object.keys();
    while(keysItr.hasNext()) {
        String key = keysItr.next();
        Object value = object.get(key);

        if(value instanceof JSONArray) {
            value = toList((JSONArray) value);
        }

        else if(value instanceof JSONObject) {
            value = toMap((JSONObject) value);
        }
        map.put(key, value);
    }
    
////    System.out.println("map : " + map);
//    String id = (String) map.get("user_id");
////    System.out.println("user_id : " + id);
//    
//    if(id!=null)
//    {
//        
////        System.out.println(a);
//       
//        System.out.println(a+": user_id=" + id);
//        valu = id;
////                JSONArray array = new JSONArray();
////                array.put(""+valu+"");
////                System.out.println(array);
////                addvalues = ""+array+"";
//                
//   
//        a++;
//    }
    
    return map;
}

public  List<Object> toList(JSONArray array) throws JSONException {
    List<Object> list = new ArrayList<>();
    for(int i = 0; i < array.length(); i++) {
        Object value = array.get(i);
        if(value instanceof JSONArray) {
            value = toList((JSONArray) value);
        }

        else if(value instanceof JSONObject) {
            value = toMap((JSONObject) value);
        }
        list.add(value);
    }
//    System.out.println("map : " + list);
    return list;
}

}


