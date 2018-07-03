/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common;

import com.mycompany.biostartlocal.common.internalframes.userlist;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author gk
 */
public class AddToArray {
  
    String template0="template0";
    String rawimage="rawimage";
    String template="template";
    int quality=4;
    public ArrayList <String>addElemntsToArray(){
        
        ArrayList<String> obj1 = new ArrayList<String>();
        ArrayList<Integer> obj2 = new ArrayList<Integer>();
 
        // Appending the new element at the end of the list
       
            obj1.add(template0);
            obj1.add(rawimage);
            obj1.add(template);
            obj2.add(quality);
           
        // Printing elements
//        System.out.println(obj1);
 
//        // Remove element at index 3
//        arrli.remove(3);
//// 
//        // Displaying ArrayList after deletion
//        System.out.println(arrli);
// 
//        // Printing elements one by one
//        for (int i=0; i<arrli.size(); i++)
//            System.out.print(arrli.get(i)+" ");

    ArrayList<String> obj3 = new ArrayList<String>();
            obj3.containsAll(obj1);
            obj3.containsAll(obj2);
            
            System.out.println(obj3);
            
        return obj3;
      
    }
     public static void main(String args[]) throws MalformedURLException, IOException, URISyntaxException{
    AddToArray lg = new AddToArray();
    lg.addElemntsToArray();
}
}
