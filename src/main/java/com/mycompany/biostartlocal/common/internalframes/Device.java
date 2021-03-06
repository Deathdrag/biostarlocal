/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.mycompany.biostartlocal.UpdateProfile;
import com.mycompany.biostartlocal.addFingerPrint;
import com.mycompany.biostartlocal.common.Base64Decoder;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public class Device extends javax.swing.JInternalFrame {

    public String namein = null;
    public String[] DeviceList = null;
    public String emailin = null;
    public String loginidin = null;
    public String passwrdin = null;
    public String Telephone = null;
    public String session1 = null;
    public String Resulsts = null;
    public String Resulsts2 = null;
    public String timage1 = null;
    public String timage2 = null;
    public String rtimage1 = null;
    public String rtimage2 = null;
    public String template0 = null;
    public String template1 = null;
    public String message= null;
    public String user_id=null;
    public static int count = 0;
    
    public Device() throws IOException, URISyntaxException {
        initComponents();
     
    }
    
    public Device(String session) throws IOException, URISyntaxException, ParseException {
        initComponents();
      
        session1 = session;
        MyProfileClass lgd  = new MyProfileClass();
        String myuserlist = lgd.myprofile(session1);
        
        AvailableDeviceClass dvicelist = new AvailableDeviceClass();
        String AvalableDevice = dvicelist.devicelist(session1);
        DevicellistDetails(AvalableDevice);
        DeviceList = deviceID(AvalableDevice);
        for (String devicelist : DeviceList) {
            dvclistcomb.addItem(devicelist);
        }
        
//        JSONObject jObject = new JSONObject(myuserlist);
//        AvailableDeviceClass addlist = new AvailableDeviceClass();
//        addlist.users(session1);
//        
//        DefaultTableModel model = (DefaultTableModel) AvailbleDevice.getModel();
//        model.addRow(addlist.users(session1));
//        
//       
    }
     public String[] deviceID(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
        ArrayList<String> list = new ArrayList<>();

    for(int i=0; i<tsmresponse.length(); i++){
        list.add(""+tsmresponse.getJSONObject(i).getInt("id")+"");
    }
    
        String[] valuID = list.toArray(new String[0]);
     
       return valuID;
    } 
    
      public String[] DevicellistDetails(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
        
        DefaultTableModel model = (DefaultTableModel) AvailbleDevice.getModel();  
        ArrayList<String> list = new ArrayList<>();

    for(int i=0; i<tsmresponse.length(); i++){
        list.add(""+tsmresponse.getJSONObject(i).getInt("id")+"");
        list.add(""+tsmresponse.getJSONObject(i).getString("name")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device_group").getString("name")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device_type").getString("name")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("lan").getJSONObject("dhcp").getString("device_ip")+"");
        list.add(""+tsmresponse.getJSONObject(i).getString("status")+"");
       model.addRow(list.toArray());
       list.clear();
    }
    

        System.out.println(list);
        String[] valuDetails = list.toArray(new String[0]);
     
       return valuDetails;
    } 

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AvailbleDevice = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SearchDevice = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        quality = new javax.swing.JLabel();
        qualityvalue = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        finger1 = new javax.swing.JLabel();
        scan1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        temp1 = new javax.swing.JLabel();
        temp0 = new javax.swing.JLabel();
        enroll = new javax.swing.JButton();
        close = new javax.swing.JButton();
        fingersNo = new javax.swing.JTextField();
        quality1 = new javax.swing.JLabel();
        usernationalID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dvclistcomb = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("DEVICE");

        AvailbleDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device ID", "Name", "Group", "Device Type(Slave/Master)", "IP Adress", "Device Status"
            }
        ));
        jScrollPane2.setViewportView(AvailbleDevice);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Available Devices", jPanel1);

        SearchDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Device ID", "Name", "Group", "Device Type(Slave/Master)", "IP Adress", "Device Status"
            }
        ));
        jScrollPane3.setViewportView(SearchDevice);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Search Device", jPanel2);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Device ID :");

        quality.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        quality.setText("Quality :");

        qualityvalue.setEditable(false);
        qualityvalue.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        qualityvalue.setText("80");
        qualityvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qualityvalueActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("ADD");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        finger1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        scan1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        scan1.setText("Scan");
        scan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scan1ActionPerformed(evt);
            }
        });

        temp1.setBackground(new java.awt.Color(204, 204, 204));

        temp0.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(temp1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(temp0, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(temp0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(temp1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap())
        );

        enroll.setBackground(new java.awt.Color(0, 51, 255));
        enroll.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        enroll.setText("ENROLL");
        enroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollActionPerformed(evt);
            }
        });

        close.setBackground(new java.awt.Color(204, 204, 204));
        close.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        close.setText("CANCEL");
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        fingersNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        fingersNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fingersNoActionPerformed(evt);
            }
        });
        fingersNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fingersNoKeyTyped(evt);
            }
        });

        quality1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        quality1.setText("Fingers To Enroll:");

        usernationalID.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        usernationalID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernationalIDActionPerformed(evt);
            }
        });
        usernationalID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernationalIDKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("User National ID :");

        dvclistcomb.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        dvclistcomb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(dvclistcomb, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(470, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(152, 152, 152)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(163, 163, 163)
                            .addComponent(enroll)
                            .addGap(26, 26, 26)
                            .addComponent(close))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scan1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(quality)
                                        .addGap(18, 18, 18)
                                        .addComponent(qualityvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quality1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fingersNo)
                                    .addComponent(usernationalID, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(finger1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(152, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dvclistcomb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(419, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(usernationalID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quality1)
                            .addComponent(fingersNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quality)
                            .addComponent(qualityvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(50, 50, 50)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(scan1)
                        .addComponent(jButton2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(finger1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(enroll, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Add FingerPrint", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernationalIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernationalIDKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)))
        evt.consume();
    }//GEN-LAST:event_usernationalIDKeyTyped

    private void usernationalIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernationalIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernationalIDActionPerformed

    private void fingersNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fingersNoKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)))
        evt.consume();
    }//GEN-LAST:event_fingersNoKeyTyped

    private void fingersNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fingersNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fingersNoActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed

    }//GEN-LAST:event_closeActionPerformed

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        int DISPOSE_ON_CLOSE1 = addFingerPrint.DISPOSE_ON_CLOSE;
    }//GEN-LAST:event_closeMouseClicked

    private void enrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollActionPerformed

        if(Resulsts2!=null && Resulsts!=null)
        {
            EnrollFingerPrintClass addtemp = new EnrollFingerPrintClass();
            try {
                addtemp.enrollfingerprint(user_id, message);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(addFingerPrint.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_enrollActionPerformed

    private void scan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scan1ActionPerformed

        if(!"1st".equals(finger1.getText()))
        {
            JOptionPane.showMessageDialog(null,"click ADD button first");
        } else
        {
            final int NUM_FIELDS =3;
            int numCorrectFields =0;
            String errorMessage ="";
            String device = null;
            int fingers = 0;

            if (dvclistcomb.getSelectedItem().equals("None")){
                errorMessage = errorMessage.concat("Device ID is missing.\n");
                dvclistcomb.setSelectedItem("None");
                dvclistcomb.setBorder(new LineBorder(Color.red));
            }else{
                numCorrectFields++;
                device = (String) dvclistcomb.getSelectedItem();

            }
            if (usernationalID.getText().isEmpty()){
                errorMessage = errorMessage.concat("User National ID is missing.\n");
                usernationalID.setText("");
                usernationalID.setBorder(new LineBorder(Color.red));
            }else{
                numCorrectFields++;
                user_id = usernationalID.getText();

            }
            if (fingersNo.getText().isEmpty()){
                errorMessage = errorMessage.concat("number of fingers is missing.\n");
                fingersNo.setText("");
                fingersNo.setBorder(new LineBorder(Color.red));
            }else if(Integer.parseInt(fingersNo.getText())>10)
            {
                errorMessage = errorMessage.concat("Minimum number of finger is 1 and Maximum is 10.\n");
                fingersNo.setText("");
                fingersNo.setBorder(new LineBorder(Color.red));
            }else{
                numCorrectFields++;
                fingers = Integer.parseInt(fingersNo.getText());

            }
            if (numCorrectFields < NUM_FIELDS){
                JOptionPane.showMessageDialog(null,errorMessage,"Incoplete/Invalid Data Entered!",
                    JOptionPane.ERROR_MESSAGE );
            }else
            {
                ScanFingerPrintClass scan = new ScanFingerPrintClass();
                try {

                    JSONObject json = new JSONObject();
                    JSONArray array = new JSONArray();
                    JSONObject item = new JSONObject();
                    int a=1;

                    for(int idx =0; idx < fingers; idx++)
                    {
                        //            Resulsts = scan.scan(device);
                        JOptionPane.showMessageDialog(null,"Click Ok to scan the "+a+" finger.");
                        temp1.setIcon(null);
                        temp0.setIcon(null);
                        Resulsts = scan.scan(device);
                        while("Scan quality is low.".equals(Resulsts))
                        {
                            JOptionPane.showMessageDialog(null,"Scan your fingerprint again");
                            Resulsts = scan.scan(device);

                        }
                        if(!"Scan quality is low.".equals(Resulsts))
                        {
                            try
                            {
                                Thread.sleep(1000);
                            }catch(InterruptedException e)
                            {}
                            Resulsts2 = scan.scan(device);
                            while("Scan quality is low.".equals(Resulsts2))
                            {
                                JOptionPane.showMessageDialog(null,"Scan your fingerprint again");
                                Resulsts2 = scan.scan(device);

                            }
                        }

                        if(Resulsts2!=null && Resulsts!=null)
                        {

                            JSONObject jObject = new JSONObject(Resulsts);
                            template0 = (String) jObject.get("template0");
                            JSONObject jObj = new JSONObject(Resulsts2);
                            template1 = (String) jObj.get("template0");

                            VerifyingFingerPrint verfy = new VerifyingFingerPrint();
                            String Results3 = verfy.verify(device, template0, template1);

                            if(Results3!=null)
                            {
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
                            

                                temp1.setIcon(imageT1);
                                temp0.setIcon(imageT2);
                            
                            }else
                            {
//                                Device.dispose();
                            }
                            
                        }

                        item.put("is_prepare_for_duress", false);
                        item.put("template0", template0);
                        item.put("template1", template1);
                        array.put(item);

                        json.put("fingerprint_template_list",array);
                        message = json.toString();
                               System.out.println("message : " + message);
                        a++;
                    }
                } catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(addFingerPrint.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            //        if(Resulsts!=null&&Resulsts2!=null)
            //        {
                //
                //        }
        }
    }//GEN-LAST:event_scan1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        finger1.setText("1st");
    }//GEN-LAST:event_jButton2MouseClicked

    private void qualityvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qualityvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qualityvalueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AvailbleDevice;
    private javax.swing.JTable SearchDevice;
    private javax.swing.JButton close;
    private javax.swing.JComboBox<String> dvclistcomb;
    private javax.swing.JButton enroll;
    private javax.swing.JLabel finger1;
    private javax.swing.JTextField fingersNo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel quality;
    private javax.swing.JLabel quality1;
    private javax.swing.JTextField qualityvalue;
    private javax.swing.JButton scan1;
    private javax.swing.JLabel temp0;
    private javax.swing.JLabel temp1;
    private javax.swing.JTextField usernationalID;
    // End of variables declaration//GEN-END:variables
}
