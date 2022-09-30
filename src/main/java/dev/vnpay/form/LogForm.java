package dev.vnpay.form;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LogForm extends JFrame{
    private JPanel JPanel_Main;
    private JTextArea textArea_Content;
    private JButton btn_Convert;
    private JButton btn_Export;
    private JTable tbl_Output;
    private JButton btn_SelectFile;
    private JTextArea textArea_ErrorLog;
    public LogForm(){
        setTitle("LOGGING TO CONVERT...");
        setContentPane(JPanel_Main);
        setSize(1300,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        String[] columnNames = {"ITEM","TIME","USER","PAN","POSEntryMode","CVM","ErrorDescription","TransactionAmount","Track2","PINBlock","TransactionKSN","MID","TID","BankCode","CurrencyCode","CardHolderName","BillNumber","ExpireDate","AppName","AppID","TC","AppVersion","IsEasyPayment","RequestDateTime","RequestID"};
        for(int i = 0; i < columnNames.length ; i++){
            defaultTableModel.addColumn(columnNames[i]);
        }
        tbl_Output.setModel(defaultTableModel);
        DefaultTableModel tblModel = (DefaultTableModel)tbl_Output.getModel();
//        for(int i = 0; i < columnNames.length ; i++){
//            tblModel.addColumn(columnNames[i]);
//        }
//        tblModel.addColumn("ITEM");
//        tblModel.addColumn("TIME");
//        tblModel.addColumn("USER");
//        tblModel.addColumn("PAN");
//        tblModel.addColumn("POSEntryMode");
//        tblModel.addColumn("CVM");
//        tblModel.addColumn("ErrorDescription");
//        tblModel.addColumn("TransactionAmount");
//        tblModel.addColumn("Track2");
//        tblModel.addColumn("PINBlock");
//        tblModel.addColumn("TransactionKSN");
//        tblModel.addColumn("MID");
//        tblModel.addColumn("TID");
//        tblModel.addColumn("BankCode");
//        tblModel.addColumn("CurrencyCode");
//        tblModel.addColumn("CardHolderName");
//        tblModel.addColumn("BillNumber");
//        tblModel.addColumn("ExpireDate");
//        tblModel.addColumn("AppName");
//        tblModel.addColumn("AppID");
//        tblModel.addColumn("TC");
//        tblModel.addColumn("AppVersion");
//        tblModel.addColumn("IsEasyPayment");
//        tblModel.addColumn("RequestDateTime");
//        tblModel.addColumn("RequestID");
        btn_Convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblModel.setRowCount(0);
                textArea_ErrorLog.setText("");
                String input = textArea_Content.getText().trim();
                if(input.equals("")){
                    JOptionPane.showMessageDialog(null,"Please input data to text Area....");
                    return;
                }
                String [] arr_Logs = input.split("======================================================================");
                for(int i =0 ; i < arr_Logs.length; i++){
                    String log_info = arr_Logs[i].toString();
                    //System.out.println(log_info);
                    String error_log = log_info.substring(0, 21);
                    try{
                        String str = arr_Logs[i].toString();
                        String str_01 = str.substring(str.indexOf(""), str.indexOf(". Body"));
                        // Process for Array 01
                        //System.out.println("Str_01: "+ str_01);
                        String time = str_01.substring(0,22).trim();
                        String user_tem = str_01.substring(str_01.lastIndexOf(":", str_01.length()));
                        String user = user_tem.substring(1,user_tem.length()).trim();
//                        System.out.println("Time: " + time);
//                        System.out.println("User: "+ user);
                        // Process for Array 02
                        String str_02 = str.substring(str.indexOf('{'), str.indexOf("} [") + 1);
                        //System.out.println("Str_02: "+ str_02);
                        String RequestDateTime = "";
                        String RequestID = "";
                        JSONObject jsonObject = new JSONObject(str_02);
                        if(jsonObject.has("RequestDateTime")){
                            RequestDateTime = jsonObject.getString("RequestDateTime");
                        }else {
                            RequestDateTime = "";
                        }
                        if(jsonObject.has("RequestID")){
                            RequestID = jsonObject.getString("RequestID");
                        }else {
                            RequestID = "";
                        }
                        JSONObject data = jsonObject.getJSONObject("Data");
                        //System.out.println("DATA: " + data);
                        String PAN ="";
                        String CVM ="";
                        String POSEntryMode ="";
                        String TransactionAmount="";
                        String Track2 = "";
                        String PINBlock = "";
                        String TransactionKSN = "";
                        String MID = "";
                        String TID = "";
                        String BankCode = "";
                        String CurrencyCode = "";
                        String CardHolderName = "";
                        String BillNumber = "";
                        String ExpireDate = "";
                        String AppName = "";
                        String AppID = "";
                        String TC = "";
                        String AppVersion = "";
                        String IsEasyPayment = "";
                        if(data.has("PAN")){
                            PAN = data.getString("PAN");
                        }else {
                            PAN = "";
                        }
                        if (data.has("POSEntryMode")){
                            POSEntryMode = data.getString("POSEntryMode");
                        }else {
                            POSEntryMode = "";
                        }
                        if(data.has("CVM")){
                            CVM = data.getString("CVM");
                        }else {
                            CVM = "";
                        }
                        if(data.has("TransactionAmount")){
                            TransactionAmount = String.valueOf(data.getInt("TransactionAmount"));
                        }else {
                            TransactionAmount = "";
                        }
                        if(data.has("Track2")){
                            Track2 = data.getString("Track2");
                        }else {
                            Track2 = "";
                        }
                        if(data.has("PINBlock")){
                            PINBlock = data.getString("PINBlock");
                        }else {
                            PINBlock = "";
                        }
                        if(data.has("TransactionKSN")){
                            TransactionKSN = data.getString("TransactionKSN");
                        }else {
                            TransactionKSN = "";
                        }
                        if(data.has("MID")){
                            MID = data.getString("MID");
                        }else {
                            MID = "";
                        }
                        if(data.has("TID")){
                            TID = data.getString("TID");
                        }else {
                            TID = "";
                        }
                        if(data.has("BankCode")){
                            BankCode = data.getString("BankCode");
                        }else {
                            BankCode = "";
                        }
                        if(data.has("CurrencyCode")){
                            CurrencyCode = data.getString("CurrencyCode");
                        }else {
                            CurrencyCode = "";
                        }
                        if(data.has("CardHolderName")){
                            CardHolderName = data.getString("CardHolderName");
                        }else {
                            CardHolderName = "";
                        }
                        if(data.has("BillNumber")){
                            BillNumber = data.getString("BillNumber");
                        }else {
                            BillNumber = "";
                        }
                        if(data.has("ExpireDate")){
                            ExpireDate = data.getString("ExpireDate");
                        }else {
                            ExpireDate = "";
                        }
                        if(data.has("AppName")){
                            AppName = data.getString("AppName");
                        }else {
                            AppName = "";
                        }
                        if(data.has("AppID")){
                            AppID = data.getString("AppID");
                        }else {
                            AppID = "";
                        }
                        if(data.has("TC")){
                            TC = data.getString("TC");
                        }else {
                            TC = "";
                        }
                        if(data.has("AppVersion")){
                            AppVersion = data.getString("AppVersion");
                        }else {
                            AppVersion = "";
                        }
                        if(data.has("IsEasyPayment")){
                            IsEasyPayment = data.get("IsEasyPayment").toString();
                        }else {
                            IsEasyPayment = "";
                        }
//                        System.out.println("PAN: " + PAN);
//                        System.out.println("POSEntryMode: " + POSEntryMode);
//                        System.out.println("CVM: " + CVM);
                        // process for Array 03
                        String str_03 = str.substring(str.indexOf("[{"), str.indexOf("]}]") + 3);
                        //System.out.println("Str_03: "+ str_03);
                        JSONArray array_03 = new JSONArray(str_03);
                        //System.out.println("array_03: " + array_03);
                        String ErrorDescription = "";
                        for (int k=0 ; k < array_03.length(); k++){
                            JSONObject object = array_03.getJSONObject(k);
                            if(object.has("ErrorDescription")){
                                ErrorDescription = object.getJSONArray("ErrorDescription").toString();
                            }else {
                                ErrorDescription ="";
                            }
//                            try{
//                                JSONObject object = array_03.getJSONObject(k);
//                                if(object.has("ErrorDescription")){
//                                    ErrorDescription = object.getJSONArray("ErrorDescription").toString();
//                                }else {
//                                    ErrorDescription ="";
//                                }
//                                //System.out.println("ErrorDescription: " + ErrorDescription);
//                            }catch (Exception ex){
//                                //System.out.println(ex);
//                                continue;
//                            }
                        }
                        String item = String.valueOf(i+1);
                        String objectData[] = {item,time,user,PAN,CVM,POSEntryMode, ErrorDescription, TransactionAmount, Track2, PINBlock, TransactionKSN,MID,TID,BankCode,CurrencyCode,CardHolderName, BillNumber, ExpireDate, AppName, AppID, TC, AppVersion, IsEasyPayment, RequestDateTime,RequestID};
                        tblModel.addRow(objectData);
                        //System.out.println("=================");
                    }catch (Exception ex){
                        textArea_ErrorLog.setText(textArea_ErrorLog.getText().trim() + "\n" + log_info);
                        continue;
                    }
                }
                //JOptionPane.showMessageDialog(null,"Convert success.");
            }
        });
        btn_Export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblModel.getRowCount() <= 0){
                    JOptionPane.showMessageDialog(null,"No data to export");
                    return;
                }
                JFileChooser chooser = new JFileChooser();
                int i = chooser.showSaveDialog(chooser);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        // .xls  .csv
                        FileWriter excel = new FileWriter(file + ".xls");
                        BufferedWriter bwrite = new BufferedWriter(excel);
                        //DefaultTableModel model = (DefaultTableModel) table.getModel();
                        // ten Cot
                        for (int j = 0; j < tblModel.getColumnCount(); j++) {
                            bwrite.write(tblModel.getColumnName(j) + "\t");
                        }
                        bwrite.write("\n");
                        // Lay du lieu dong
                        for (int j = 0; j < tblModel.getRowCount(); j++) {
                            for (int k = 0; k < tblModel.getColumnCount(); k++) {
                                bwrite.write(tblModel.getValueAt(j, k) + "\t");
                            }
                            bwrite.write("\n");
                        }
                        bwrite.close();
                        //addNewSheet(file + ".xls", "error_log");
                        JOptionPane.showMessageDialog(null, "Save success.");
                        textArea_Content.setText("");
                        textArea_ErrorLog.setText("");
                        tblModel.setRowCount(0);
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "Error save file.");
                    }
                }
            }
            public void addNewSheet(String filePath, String newSheetName) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet(newSheetName);
                Object[][] bookData = {
                        {"Head First Java", "Kathy Serria", 79},
                        {"Effective Java", "Joshua Bloch", 36},
                        {"Clean Code", "Robert martin", 42},
                        {"Thinking in Java", "Bruce Eckel", 35},
                };
                int rowCount = 0;
                for (Object[] aBook : bookData) {
                    Row row = sheet.createRow(++rowCount);
                    int columnCount = 0;
                    for (Object field : aBook) {
                        Cell cell = row.createCell(++columnCount);
                        if (field instanceof String) {
                            cell.setCellValue((String) field);
                        } else if (field instanceof Integer) {
                            cell.setCellValue((Integer) field);
                        }
                    }
                }
                try(FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    workbook.write(outputStream);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        });
        btn_SelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea_Content.setText("");
                textArea_ErrorLog.setText("");
                tblModel.setRowCount(0);
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    //System.out.println(selectedFile.getAbsolutePath());
                    try(BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()))) {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();
                        while (line != null) {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            line = br.readLine();
                        }
                        //String everything = sb.toString();
                        //System.out.println(sb.toString());
                        textArea_Content.setText(sb.toString());
                        //JOptionPane.showMessageDialog(null,"Import success.");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });

    }
}
