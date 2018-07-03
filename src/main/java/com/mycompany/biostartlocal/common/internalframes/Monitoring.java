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
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gk
 */
public final class Monitoring extends javax.swing.JInternalFrame {

    public String[] eventcode = null;
    public String ReferencResult = null;
    public String[] eventcodename = null;
    public String[] DeviceList = null;
    public String[] loglist = null;
    public String EventlogView = null;
    public String session1 = null;
    public String mylogs = null;
    public static int count = 0;
    
    public Monitoring() throws IOException, URISyntaxException {
        initComponents();
//        jsonToMap(mylogs);
     
    }
    
    public Monitoring(String session) throws IOException, URISyntaxException, ParseException {
        initComponents();
      
        session1 = session;
        AvailableDeviceClass dvicelist = new AvailableDeviceClass();
        String AvalableDevice = dvicelist.devicelist(session1);
        
        
        RefEventTypes evttyp  = new RefEventTypes();
        ReferencResult = evttyp.eventcode(session1);
        
        DeviceList = deviceID(AvalableDevice);
        for (String devicelist : DeviceList) {
            dvclistcomb.addItem(devicelist);
        }
        
        eventcodename = CodeListName(ReferencResult);
        for (String eventcode1 : eventcodename) {
            eventlogs.addItem(eventcode1);
        }
        String[] query=DeviceQueryList(AvalableDevice);
        EventLog lgresult = new EventLog();
        EventlogView = lgresult.eventlogs(session1, eventcode, query);
        MonthlyLogs(EventlogView);
        
    }
    
    public String[] CodeListName(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
       
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        String None = "0000";
        String ArryList;
        String ArryList1;
        list.add("\""+None+"\"");
        for(int i=0; i<tsmresponse.length(); i++){
         
        ArryList =("\""+tsmresponse.getJSONObject(i).getInt("code"))+"\"";
        ArryList1 = ("\""+tsmresponse.getJSONObject(i).getString("description"))+"\"";
        list.add(ArryList);
        list1.add(ArryList1);
         
        }
     
        String[] ValuList = list1.toArray(new String[0]);
        eventcode = list.toArray(new String[0]);
       return ValuList;
    } 
    
    public String[] deviceID(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
        ArrayList<String> list = new ArrayList<>();

    for(int i=0; i<tsmresponse.length(); i++){
        list.add("\""+tsmresponse.getJSONObject(i).getInt("id")+"\"");
    }
    
        String[] valuID = list.toArray(new String[0]);
     
       return valuID;
    } 
    
    public String[] DeviceQueryList(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
        ArrayList<String> list = new ArrayList<>();
//
   int j = 01;
    for(int i=0; i<tsmresponse.length(); i++){
//       
      list.add("{\n" +
"      \"device_id\": \""+tsmresponse.getJSONObject(i).getInt("id")+"\",\n" +
"      \"end_datetime\": \""+YearMonth.now()+"-"+Month.from(LocalDate.now()).length(true)+"T23:59:00.00Z\",\n" +
"      \"start_datetime\": \""+YearMonth.now()+"-0"+j+"T00:00:00.00Z\"\n" +
"    }");
      
    }
    
    

        System.out.println(list);
        String[] valulist = list.toArray(new String[0]);
     
       return valulist;
    } 
    
    public String[] MonthlyLogs(String t) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(t);
        JSONArray tsmresponse = (JSONArray) jsonObject.get("records");
       
        ArrayList<String> list = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) EventlogsTable.getModel();

        for(int i=0; i<tsmresponse.length(); i++){
            
        list.add(""+tsmresponse.getJSONObject(i).getString("datetime")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device").getInt("id")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("device").getString("name")+"");
        if(tsmresponse.getJSONObject(i).has("user_group"))
        {
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user_group").getString("name")+"");
        }else
        {
            list.add(" ");
        }
        if(tsmresponse.getJSONObject(i).has("user"))
        {
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getInt("user_id")+"");
        }else
        {
            list.add(" ");
        }
        if(tsmresponse.getJSONObject(i).has("user")&&tsmresponse.getJSONObject(i).getJSONObject("user").has("name"))
        {
            list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getString("name")+"");
        }else
        {
            list.add(" ");
        }
//        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("user").getString("name")+"");
//        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("event_type").getInt("code")+"");
        list.add(""+tsmresponse.getJSONObject(i).getJSONObject("event_type").getString("description")+"");
        model.addRow(list.toArray());
        list.clear();
        }
        
        String[] valuLogList = list.toArray(new String[0]);
        return valuLogList;
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        EventlogsTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        StartDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        EndDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        LimitText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        OffSetText = new javax.swing.JTextField();
        APPLEVENTLOG = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        eventlogs = new javax.swing.JComboBox<>();
        dvclistcomb = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("MONITORING EVENTS");

        EventlogsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DateTime", "Device ID", "Device Name", "User Group", "User ID", "User Name", "Event"
            }
        ));
        jScrollPane2.setViewportView(EventlogsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Event Logs", jPanel1);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Device ID");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("START DATE");

        StartDate.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("END DATE");

        EndDate.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        EndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndDateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("LIMIT");

        LimitText.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("OFFSET");

        OffSetText.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        APPLEVENTLOG.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        APPLEVENTLOG.setText("APPLY");
        APPLEVENTLOG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APPLEVENTLOGActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Events Logs");

        eventlogs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        eventlogs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\"None\"" }));

        dvclistcomb.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        dvclistcomb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\"None\"" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(APPLEVENTLOG, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eventlogs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(OffSetText))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LimitText))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dvclistcomb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(550, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dvclistcomb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(eventlogs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LimitText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(OffSetText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(APPLEVENTLOG, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        jTabbedPane1.addTab("Searching Event", jPanel4);

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

    private void EndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EndDateActionPerformed

    private void APPLEVENTLOGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APPLEVENTLOGActionPerformed
        
        final int NUM_FIELDS =6;
        int numCorrectFields =0;
        String errorMessage ="";
        String Device=null;
        String Start=null;
        String End=null;
        String Limit=null;
        String EventLOG=null;
        String Offset=null;
        int INDEXNo = 0;

        if (dvclistcomb.getSelectedItem().equals("None")){
            errorMessage = errorMessage.concat("Device ID is missing.\n");
            dvclistcomb.setSelectedItem("None");
            dvclistcomb.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            Device = (String) dvclistcomb.getSelectedItem();
        }

        if (StartDate.getText().isEmpty()){
            errorMessage = errorMessage.concat("Starting date is missing.\n");
            StartDate.setText("");
            StartDate.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
        String tmzdt = StartDate.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String sdt = "T00:00:00.00Z";
            try {
                Start = sdf2.format(sdf.parse(tmzdt))+ sdt;
            } catch (ParseException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (EndDate.getText().isEmpty()){
            errorMessage = errorMessage.concat("Ending Date ID is missing.\n");
            EndDate.setText("");
            EndDate.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
        String tmdt = EndDate.getText();
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String sdt = "T23:59:59.00Z";
            try {
                End = sf2.format(sf.parse(tmdt))+ sdt;
            } catch (ParseException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (LimitText.getText().isEmpty()){
            errorMessage = errorMessage.concat("Limit is missing.\n");
            LimitText.setText("");
            LimitText.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            Limit = LimitText.getText();
        }
        if (eventlogs.getSelectedItem().equals("None")|| eventlogs.getSelectedIndex()==0){
            errorMessage = errorMessage.concat("Event Log Name is missing.\n");
            eventlogs.setSelectedItem("None");
            eventlogs.setBorder(new LineBorder(Color.red));
        }
        else{
            numCorrectFields++;
            int EventLogName = eventlogs.getSelectedIndex();
            if(0!=EventLogName)
            {
                EventLOG = eventcode[EventLogName];
            }
        }

        if (OffSetText.getText().isEmpty()){
            errorMessage = errorMessage.concat("Offset is missing.\n");
            OffSetText.setText("");
            OffSetText.setBorder(new LineBorder(Color.red));
        }else{
            numCorrectFields++;
            Offset = OffSetText.getText();
        }

        if (numCorrectFields < NUM_FIELDS){
            JOptionPane.showMessageDialog(null,errorMessage,"Incoplete/Invalid Data Entered!",
                JOptionPane.ERROR_MESSAGE );
        }else
        {
            EventLogSearch lgd  = new EventLogSearch();
            try {
                
                lgd.logevents(Device, End, Start, EventLOG , Limit, Offset,session1);
                
                
                
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_APPLEVENTLOGActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton APPLEVENTLOG;
    private javax.swing.JTextField EndDate;
    private javax.swing.JTable EventlogsTable;
    private javax.swing.JTextField LimitText;
    private javax.swing.JTextField OffSetText;
    private javax.swing.JTextField StartDate;
    private javax.swing.JComboBox<String> dvclistcomb;
    private javax.swing.JComboBox<String> eventlogs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
