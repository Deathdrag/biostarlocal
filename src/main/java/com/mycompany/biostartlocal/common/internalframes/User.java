/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biostartlocal.common.internalframes;

import com.mycompany.biostartlocal.UpdateProfile;
import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class User extends javax.swing.JInternalFrame {

    public String namein = null;
    public String emailin = null;
    public String loginidin = null;
    public String passwrdin = null;
    public String Telephone = null;
    public String session1 = null;
    
    
    public User() throws IOException, URISyntaxException {
        initComponents();
     
    }
    
    public class UserList
    {
        public String Id;
        public String Name;
        public String Email;
        public String Group;
        public String Access_Group;
        public String Status;
        
    }

    public User(String session) throws IOException, URISyntaxException, ParseException {
        initComponents();
      
        session1 = session;
        MyProfileClass lgd  = new MyProfileClass();
        String myuserlist = lgd.myprofile(session1);
        userlist ulist = new userlist();
        String Userlist = ulist.users(session1);
        userList(Userlist);
        
        JSONObject jObject = new JSONObject(myuserlist);
        
//        confmpss.setVisible(false);
//        confm.setVisible(false);
        name.setText((String) jObject.get("name"));
        name1.setText(name.getText());
        email.setText((String) jObject.get("email"));
        email1.setText(email.getText());
        loginid.setText((String) jObject.get("login_id"));
        loginid1.setText(loginid.getText());
        
        Boolean pss = (Boolean) jObject.get("password_exist");
        if(pss=true)
        {
            psswd.setText("adminstrator");
            psswd1.setText(psswd.getText());
        }
        
        JSONObject jObject1 = jObject.getJSONObject("permission");
        level.setText((String) jObject1.get("name"));
        
        NationalID.setText((String) jObject.get("user_id"));
        phoneno.setText((String) jObject.get("phone_number"));
        phoneno1.setText(phoneno.getText());
        
        String start = (String) jObject.get("start_datetime");
        String[] start1 = start.split("T", 0);
        String tmdt = start1[0];
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        strartdate.setText(sf.format(sf2.parse(tmdt)));
        
        String start2 = (String) jObject.get("expiry_datetime");
        String[] start3 = start2.split("T", 0);
        String tmdt1 = start3[0];
        SimpleDateFormat sfr = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sfr2 = new SimpleDateFormat("yyyy-MM-dd");
        enddate.setText(sfr.format(sfr2.parse(tmdt1)));
        
        phoneno.setText((String) jObject.get("phone_number"));
        
        JSONArray jObject2 =  jObject.getJSONArray("access_groups");
        AccessGroup.setText(jObject2.getJSONObject(0).getString("name"));
        
        JSONObject jObject3 = jObject.getJSONObject("user_group");
        Group.setText((String) jObject3.get("name"));
        
        String st = (String) jObject.get("status");
        
        if("AC".equals(st))
        {
            stat.setSelected(true);
            
        }else if("IN".equals(st))
        {
            stat.setSelected(false);
        }
    }

    
    public String[] userList(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
       
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> Group = new ArrayList<>();
        String ArryList = null;
        DefaultTableModel model = (DefaultTableModel) UserList.getModel(); 

        for(int i=0; i<tsmresponse.length(); i++){
            
            JSONObject dataArray = tsmresponse.getJSONObject(i);
            JSONArray InterestArray = dataArray.getJSONArray("access_groups");
            
            
            for(int j= 0; j<InterestArray.length(); j++)
        {
             Group.add(""+InterestArray.getJSONObject(j).getString("name")+"");
        }
            list.add(""+tsmresponse.getJSONObject(i).getString("user_id")+"");
            list.add(""+tsmresponse.getJSONObject(i).getString("name")+"");
            list.add(""+tsmresponse.getJSONObject(i).getString("email")+"");
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user_group").getString("name")+"");
            list.add(""+Group+"");
            list.add(""+tsmresponse.getJSONObject(i).getString("status")+"");
        model.addRow(list.toArray());
        Group.clear();
        list.clear();
         
        }
     
        System.out.println(list);
        String[] valuList = list.toArray(new String[0]);
       return valuList;
    }  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblogin = new javax.swing.JLabel();
        loginid = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        lbpassword1 = new javax.swing.JLabel();
        lbpassword2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        NationalID = new javax.swing.JTextField();
        phoneno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbpassword6 = new javax.swing.JLabel();
        AccessGroup = new javax.swing.JTextField();
        lbpassword3 = new javax.swing.JLabel();
        psswd = new javax.swing.JPasswordField();
        lbpassword5 = new javax.swing.JLabel();
        Group = new javax.swing.JTextField();
        enddate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        strartdate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stat = new javax.swing.JCheckBox();
        lbpassword4 = new javax.swing.JLabel();
        level = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        psswd1 = new javax.swing.JPasswordField();
        lbpassword7 = new javax.swing.JLabel();
        lbpassword8 = new javax.swing.JLabel();
        email1 = new javax.swing.JTextField();
        name1 = new javax.swing.JTextField();
        lbpassword9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        phoneno1 = new javax.swing.JTextField();
        lblogin1 = new javax.swing.JLabel();
        loginid1 = new javax.swing.JTextField();
        confm = new javax.swing.JLabel();
        confmpss = new javax.swing.JPasswordField();
        APPLY = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserList = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("User");

        lblogin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblogin.setText("Login ID:");

        loginid.setEditable(false);
        loginid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginidActionPerformed(evt);
            }
        });

        email.setEditable(false);

        lbpassword1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword1.setText("Email:");

        lbpassword2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword2.setText("Name:");

        name.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText(" National ID:");

        NationalID.setEditable(false);
        NationalID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NationalIDActionPerformed(evt);
            }
        });
        NationalID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NationalIDKeyTyped(evt);
            }
        });

        phoneno.setEditable(false);
        phoneno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonenoActionPerformed(evt);
            }
        });
        phoneno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phonenoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phonenoKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("   Phone NO:");

        lbpassword6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword6.setText("Access Group :");

        AccessGroup.setEditable(false);

        lbpassword3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword3.setText("Password:");

        psswd.setEditable(false);
        psswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psswdActionPerformed(evt);
            }
        });

        lbpassword5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword5.setText("Group :");

        Group.setEditable(false);

        enddate.setEditable(false);

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel8.setText("To:");

        strartdate.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("      Period  :");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("     Status :");

        stat.setEnabled(false);

        lbpassword4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword4.setText("Operater Level:");

        level.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stat)
                        .addGap(513, 513, 513))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbpassword4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(strartdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblogin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbpassword1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(lbpassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(email)
                                    .addComponent(name)
                                    .addComponent(loginid, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NationalID)
                                            .addComponent(phoneno, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbpassword5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Group, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbpassword6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AccessGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(lbpassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(psswd)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbpassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NationalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(phoneno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblogin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbpassword6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccessGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbpassword4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(psswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbpassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(strartdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbpassword5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My Profile", jPanel1);

        psswd1.setEditable(false);
        psswd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psswd1ActionPerformed(evt);
            }
        });
        psswd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                psswd1KeyPressed(evt);
            }
        });

        lbpassword7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword7.setText("Password:");

        lbpassword8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword8.setText("Email:");

        email1.setEditable(false);

        name1.setEditable(false);

        lbpassword9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbpassword9.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("   Phone NO:");

        phoneno1.setEditable(false);
        phoneno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneno1ActionPerformed(evt);
            }
        });
        phoneno1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phoneno1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneno1KeyTyped(evt);
            }
        });

        lblogin1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblogin1.setText("Login ID:");

        loginid1.setEditable(false);
        loginid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginid1ActionPerformed(evt);
            }
        });

        confm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        confm.setText("confirm Password:");

        confmpss.setEditable(false);
        confmpss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confmpssActionPerformed(evt);
            }
        });

        APPLY.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        APPLY.setText("APPLY");
        APPLY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APPLYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(238, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbpassword8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbpassword9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(email1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(name1))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(phoneno1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginid1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(APPLY, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbpassword7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(psswd1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(confm, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confmpss, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneno1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbpassword9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbpassword8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(psswd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbpassword7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confmpss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(APPLY, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Edit Profile", jPanel2);

        UserList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Email", "Group", "Access Group", "Status"
            }
        ));
        jScrollPane1.setViewportView(UserList);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User List", jPanel4);

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

    private void loginidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginidActionPerformed

    private void NationalIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NationalIDActionPerformed

    }//GEN-LAST:event_NationalIDActionPerformed

    private void NationalIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NationalIDKeyTyped
        // TODO add your handling code here:
        //        enter code here
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)))
        evt.consume();
    }//GEN-LAST:event_NationalIDKeyTyped

    private void phonenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phonenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phonenoActionPerformed

    private void phonenoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonenoKeyPressed
        // TODO add your handling code her
    }//GEN-LAST:event_phonenoKeyPressed

    private void phonenoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonenoKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)))
        evt.consume();
    }//GEN-LAST:event_phonenoKeyTyped

    private void psswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psswdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_psswdActionPerformed

    private void psswd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psswd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_psswd1ActionPerformed

    private void phoneno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneno1ActionPerformed

    private void phoneno1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneno1KeyPressed
        // TODO add your handling code her
    }//GEN-LAST:event_phoneno1KeyPressed

    private void phoneno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneno1KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)))
        evt.consume();
    }//GEN-LAST:event_phoneno1KeyTyped

    private void loginid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginid1ActionPerformed

    private void confmpssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confmpssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confmpssActionPerformed

    private void APPLYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APPLYActionPerformed

        final int NUM_FIELDS =6;
        int numCorrectFields =0;
        String errorMessage ="";
        String p1=null;
        String p2=null;

        if (name.getText().isEmpty()){
            errorMessage = errorMessage.concat("Name is missing.\n");
            name.setText("");
            name.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            namein = name.getText();
        }

        if (email.getText().isEmpty()){
            errorMessage = errorMessage.concat("Email is missing.\n");
            email.setText("");
            email.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            emailin = email.getText();
        }

        if (loginid.getText().isEmpty()){
            errorMessage = errorMessage.concat("compliteprofile ID is missing.\n");
            loginid.setText("");
            loginid.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            loginidin = loginid.getText();
        }

        if (phoneno.getText().isEmpty()){
            errorMessage = errorMessage.concat("Telephone ID is missing.\n");
            phoneno.setText("");
            phoneno.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            Telephone = phoneno.getText();
        }

        if (psswd.getText().isEmpty()){
            errorMessage = errorMessage.concat("Password is missing.\n");
            psswd.setText("");
            psswd.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            p1 = psswd.getText();
        }

        if (confmpss.getText().isEmpty()){
            errorMessage = errorMessage.concat("Confirm Password is missing.\n");
            confmpss.setText("");
            confmpss.setBorder(new LineBorder(Color.red));
        }else if(!(p1.equals(p2=confmpss.getText()))){
            errorMessage = errorMessage.concat("Incorrect confirmation password.\n");
            confmpss.setText("");
            confmpss.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            passwrdin = confmpss.getText();
        }

        if (numCorrectFields < NUM_FIELDS){
            JOptionPane.showMessageDialog(null,errorMessage,"Incoplete/Invalid Data Entered!",
                JOptionPane.ERROR_MESSAGE );
        }else
        {
            UpdateProfileClass updt = new UpdateProfileClass();
            try {
                updt.updateprofile(emailin, namein, loginidin, passwrdin, Telephone,session1);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_APPLYActionPerformed

    private void psswd1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psswd1KeyPressed
        
    }//GEN-LAST:event_psswd1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton APPLY;
    private javax.swing.JTextField AccessGroup;
    private javax.swing.JTextField Group;
    private javax.swing.JTextField NationalID;
    private javax.swing.JTable UserList;
    private javax.swing.JLabel confm;
    private javax.swing.JPasswordField confmpss;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email1;
    private javax.swing.JTextField enddate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblogin;
    private javax.swing.JLabel lblogin1;
    private javax.swing.JLabel lbpassword1;
    private javax.swing.JLabel lbpassword2;
    private javax.swing.JLabel lbpassword3;
    private javax.swing.JLabel lbpassword4;
    private javax.swing.JLabel lbpassword5;
    private javax.swing.JLabel lbpassword6;
    private javax.swing.JLabel lbpassword7;
    private javax.swing.JLabel lbpassword8;
    private javax.swing.JLabel lbpassword9;
    private javax.swing.JTextField level;
    private javax.swing.JTextField loginid;
    private javax.swing.JTextField loginid1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField name1;
    private javax.swing.JTextField phoneno;
    private javax.swing.JTextField phoneno1;
    private javax.swing.JPasswordField psswd;
    private javax.swing.JPasswordField psswd1;
    private javax.swing.JCheckBox stat;
    private javax.swing.JTextField strartdate;
    // End of variables declaration//GEN-END:variables
}
