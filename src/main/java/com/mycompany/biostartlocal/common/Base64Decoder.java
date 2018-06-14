/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author gk
 */
public class Base64Decoder {
    public FileOutputStream image=null;
    public FileOutputStream image2=null;
    public FileOutputStream decode(String imputimage){
    String imageString=imputimage;
    
            try {
            
            //Decoding Base64 encoded Byte Array to Image Byte array
            byte[] base64DecodedByteArray = Base64.decodeBase64(imageString);
            
            image = new FileOutputStream("C:/Users/gk/Documents/NetBeansProjects/biostartLocal/images/sample1.JPEG");
            image.write(base64DecodedByteArray);
//            imageFile.close();
            image.close();
//            System.out.println("Image successfully encoded and decoded");
        }
        catch (FileNotFoundException e) {
//            System.out.println("Image Not Found on that Location" + e);
        }
        catch (IOException ex) {
//            System.out.println("Problem in Reading The Image" + ex);
        }
        return image;
}
    
    public FileOutputStream decode2(String imputimage2){
    String imageString=imputimage2;
    
            try {
            
            //Decoding Base64 encoded Byte Array to Image Byte array
            byte[] base64DecodedByteArray = Base64.decodeBase64(imageString);
            image2 = new FileOutputStream("C:/Users/gk/Documents/NetBeansProjects/biostartLocal/images/sample2.JPEG");
            image2.write(base64DecodedByteArray);
//            imageFile.close();
            image2.close();
//            System.out.println("Image successfully encoded and decoded");
        }
        catch (FileNotFoundException e) {
//            System.out.println("Image Not Found on that Location" + e);
        }
        catch (IOException ex) {
//            System.out.println("Problem in Reading The Image" + ex);
        }
        return image2;
}
}
