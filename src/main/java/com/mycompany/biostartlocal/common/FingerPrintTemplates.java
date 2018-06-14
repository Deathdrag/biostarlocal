/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common;

import com.mycompany.biostartlocal.ScanFingerPrint;
import java.awt.Image;
import java.awt.Label;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public class FingerPrintTemplates {
    public String scan1=null;
    public String scan2=null;
    Label temp1,temp2; 
    public String numberoffingers(int fingers ,String device) throws IOException, URISyntaxException
    {
        for(int idx =0; idx < fingers; idx++)
        {
            ScanFingerPrint scan = new ScanFingerPrint();
        
//            Resulsts = scan.scan(device);
                scan1 = scan.scan(device);
                while("Scan quality is low.".equals(scan1))
                {
                    JOptionPane pane = new JOptionPane("Scan your fingerprint again",JOptionPane.INFORMATION_MESSAGE);
                    pane.setVisible(true);
                    JDialog dialog = pane.createDialog(null, "Scan Finger Again");
                    dialog.setModal(false);
                    dialog.setVisible(true);
                    try
                       {
                        Thread.sleep(1000);   
                       }catch(InterruptedException e)
                       {}
                    dialog.setVisible(false);
                    pane.setVisible(false);
                    scan1 = scan.scan(device);
                    
                }
                if(!"Scan quality is low.".equals(scan1))
                    {
                         try
                           {
                             Thread.sleep(1500);   
                               }catch(InterruptedException e)
                           {}
                            scan2 = scan.scan(device);
                            while("Scan quality is low.".equals(scan2))
                                {
                                    JOptionPane pane = new JOptionPane("Scan your fingerprint again",JOptionPane.INFORMATION_MESSAGE);
                                    pane.setVisible(true);
                                    JDialog dialog = pane.createDialog(null, "Scan Finger Again");
                                    dialog.setModal(false);
                                    dialog.setVisible(true);
                                try
                                   {
                                        Thread.sleep(1000);   
                                        }catch(InterruptedException e)
                                    {}
                                    dialog.setVisible(false);
                                    pane.setVisible(false);
                                    scan2 = scan.scan(device);
                    
                                 }
                    }
                
                if(scan2!=null && scan1!=null)
                {
                   
                    JSONObject jObject = new JSONObject(scan1);
                    JSONObject jObj = new JSONObject(scan2);
                    Base64Decoder myimage = new Base64Decoder();
                    myimage.decode((String) jObject.get("template_image0"));
                    myimage.decode2((String) jObj.get("template_image0"));
                    
                    ImageIcon imageT1 = new ImageIcon("C:/Users/gk/Documents/NetBeansProjects/biostartLocal/images/sample1.PNG");
                    ImageIcon imageT2 = new ImageIcon("C:/Users/gk/Documents/NetBeansProjects/biostartLocal/images/sample2.PNG");
                    Image image1 = imageT1.getImage();
                    Image image2 = imageT2.getImage();
                    Image newimg1 = image1.getScaledInstance(144, 160,  Image.SCALE_SMOOTH);
                    Image newimg2 = image2.getScaledInstance(144, 160,  Image.SCALE_SMOOTH);
                    imageT1 = new ImageIcon(newimg1);
                    imageT2 = new ImageIcon(newimg2);
//                    File imageFileData1 = new File("C:/Users/gk/Documents/NetBeansProjects/biostartLocal/images/sample1.PNG");
//                    File imageFileData2 = new File("C:/Users/gk/Documents/NetBeansProjects/biostartLocal/images/sample1.PNG");
//                    FileInputStream imageFile1 = new FileInputStream(imageFileData1);
//                    FileInputStream imageFile2 = new FileInputStream(imageFileData2);
//                    byte imageData1[] = new byte[(int) imageFileData1.length()];
//                    byte imageData2[] = new byte[(int) imageFileData2.length()];
//                    imageFile1.read(imageData1);
//                    imageFile2.read(imageData2);

                    temp1.setIcon(imageT1);
                    temp2.setIcon(imageT2);
                }
                final int fingerToAdd =fingers;
                int numCorrectfingerToAdd =0;
                String fingerMessage ="";
                if (finger2.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 2nd finger.\n");
                    finger2.setText("2nd");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger2.getText().equals("2nd")&&finger3.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 3rd finger.\n");
                    finger3.setText("3rd");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger3.getText().equals("3rd")&&finger4.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 4th finger.\n");
                    finger4.setText("4th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger4.getText().equals("4th")&&finger5.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 5th finger.\n");
                    finger5.setText("5th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger5.getText().equals("5th")&&finger6.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 6th finger.\n");
                    finger6.setText("6th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger6.getText().equals("6th")&&finger7.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 7th finger.\n");
                    finger7.setText("7th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger7.getText().equals("7th")&&finger8.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 8th finger.\n");
                    finger8.setText("8th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger8.getText().equals("8th")&&finger9.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 9th finger.\n");
                    finger9.setText("9th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (finger9.getText().equals("9th")&&finger10.getText().isEmpty()){
                    fingerMessage = fingerMessage.concat("Click Ok to scan the 10th finger.\n");
                    finger10.setText("10th");
                    deviceid.setVisible(true);
                }else{
                        numCorrectFields++;
                     }
                if (numCorrectfingerToAdd < fingerToAdd){
                        JOptionPane.showMessageDialog(null,fingerMessage,"Incoplete/Invalid Data Entered!",
                        JOptionPane.ERROR_MESSAGE );
                }else
                    {
                        break;
                    }
                
        }
        return null;
        
    }
}

