package dev.vnpay.form;

import dev.vnpay.model.LogATGModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LogForm extends JFrame {
    private JPanel JPanel_Main;
    private JTextArea textArea_Content;
    private JButton btn_Convert;
    private JButton btn_Export;
    private JTable tbl_Output;
    private JButton btn_SelectFile;
    private JTextArea textArea_ErrorLog;

    public LogForm() {
        setTitle("LOGGING TO CONVERT...");
        setContentPane(JPanel_Main);
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        DefaultTableModel defaultTableModel = new DefaultTableModel();
//        String[] columnNames = {"ITEM", "TIME", "USER", "PAN", "POSEntryMode", "CVM", "ErrorDescription", "TransactionAmount", "Track2", "PINBlock", "TransactionKSN", "MID", "TID", "BankCode", "CurrencyCode", "CardHolderName", "BillNumber", "ExpireDate", "AppName", "AppID", "TC", "AppVersion", "IsEasyPayment", "RequestDateTime", "RequestID"};
        String[] columnNames = {"ITEM", "TIME", "USER", "PAN", "POSEntryMode", "CVM", "ErrorDescription", "TransactionAmount", "MID", "TID", "BankCode", "BillNumber", "AppVersion", "RequestDateTime", "RequestID"};
        for (String column : columnNames) {
            defaultTableModel.addColumn(column);
        }
        tbl_Output.setModel(defaultTableModel);
        DefaultTableModel tblModel = (DefaultTableModel) tbl_Output.getModel();
        // This data needs to be written (Object[])
        Map<String, Object[]> tree_map = new TreeMap<String, Object[]>();
        //Map<String, Object[]> tree_map_error = new TreeMap<String, Object[]>();
        btn_Convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblModel.setRowCount(0);
                textArea_ErrorLog.setText("");
                // remove all data from tree_map
                tree_map.clear();
                //tree_map_error.clear();
                LogATGModel logATGModel = new LogATGModel();
                String input = textArea_Content.getText().trim();
                if (input.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please input data to text Area....");
                    return;
                }
                String[] arr_Logs = input.split("=======================================================");

                for (int i = 0; i < arr_Logs.length; i++) {
                    String log_info = arr_Logs[i];
                    try {
                        String str = arr_Logs[i];
                        // Process for Array 01
                        String str_01 = str.substring(str.indexOf(""), str.indexOf(". Body"));
//                        logATGModel.setTIME(str_01.substring(0, 22).trim());
                        logATGModel.setTIME(str_01.substring(0, 12).trim());
                        logATGModel.setUSER(str_01.substring(str_01.lastIndexOf("User:") + 6).trim());

                        // Process for Array 02
                        String str_02 = str.substring(str.indexOf('{'), str.indexOf("} [") + 1);
                        JSONObject jsonObject = new JSONObject(str_02);
                        if (jsonObject.has("RequestDateTime")) {
                            logATGModel.setRequestDateTime(jsonObject.getString("RequestDateTime"));
                        } else {
                            logATGModel.setRequestDateTime("");
                        }
                        if (jsonObject.has("RequestID")) {
                            logATGModel.setRequestID(jsonObject.getString("RequestID"));
                        } else {
                            logATGModel.setRequestID("");
                        }
                        JSONObject data = jsonObject.getJSONObject("Data");
                        if (data.has("PAN")) {
                            logATGModel.setPAN(data.getString("PAN"));
                        } else {
                            logATGModel.setPAN("");
                        }
                        if (data.has("POSEntryMode")) {
                            logATGModel.setPOSEntryMode(data.getString("POSEntryMode"));
                        } else {
                            logATGModel.setPOSEntryMode("");
                        }
                        if (data.has("CVM")) {
                            logATGModel.setCVM(data.getString("CVM"));
                        } else {
                            logATGModel.setCVM("");
                        }
                        if (data.has("TransactionAmount")) {
                            logATGModel.setTransactionAmount(String.valueOf(data.getInt("TransactionAmount")));
                        } else {
                            logATGModel.setTransactionAmount("");
                        }
                        if (data.has("Track2")) {
                            logATGModel.setTrack2(data.getString("Track2"));
                        } else {
                            logATGModel.setTrack2("");
                        }
                        if (data.has("PINBlock")) {
                            logATGModel.setPINBlock(data.getString("PINBlock"));
                        } else {
                            logATGModel.setPINBlock("");
                        }
                        if (data.has("TransactionKSN")) {
                            logATGModel.setTransactionKSN(data.getString("TransactionKSN"));
                        } else {
                            logATGModel.setTransactionKSN("");
                        }
                        if (data.has("MID")) {
                            logATGModel.setMID(data.getString("MID"));
                        } else {
                            logATGModel.setMID("");
                        }
                        if (data.has("TID")) {
                            logATGModel.setTID(data.getString("TID"));
                        } else {
                            logATGModel.setTID("");
                        }
                        if (data.has("BankCode")) {
                            logATGModel.setBankCode(data.getString("BankCode"));
                        } else {
                            logATGModel.setBankCode("");
                        }
                        if (data.has("CurrencyCode")) {
                            logATGModel.setCurrencyCode(data.getString("CurrencyCode"));
                        } else {
                            logATGModel.setCurrencyCode("");
                        }
                        if (data.has("CardHolderName")) {
                            logATGModel.setCardHolderName(data.getString("CardHolderName"));
                        } else {
                            logATGModel.setCardHolderName("");
                        }
                        if (data.has("BillNumber")) {
                            logATGModel.setBillNumber(data.getString("BillNumber"));
                        } else {
                            logATGModel.setBillNumber("");
                        }
                        if (data.has("ExpireDate")) {
                            logATGModel.setExpireDate(data.getString("ExpireDate"));
                        } else {
                            logATGModel.setExpireDate("");
                        }
                        if (data.has("AppName")) {
                            logATGModel.setAppName(data.getString("AppName"));
                        } else {
                            logATGModel.setAppName("");
                        }
                        if (data.has("AppID")) {
                            logATGModel.setAppID(data.getString("AppID"));
                        } else {
                            logATGModel.setAppID("");
                        }
                        if (data.has("TC")) {
                            logATGModel.setTC(data.getString("TC"));
                        } else {
                            logATGModel.setTC("");
                        }
                        if (data.has("AppVersion")) {
                            logATGModel.setAppVersion(data.getString("AppVersion"));
                        } else {
                            logATGModel.setAppVersion("");
                        }
                        if (data.has("IsEasyPayment")) {
                            logATGModel.setIsEasyPayment(data.get("IsEasyPayment").toString());
                        } else {
                            logATGModel.setIsEasyPayment("");
                        }
                        // Process for Array 03
                        String str_03 = str.substring(str.indexOf("[{"), str.indexOf("]}]") + 3);
                        JSONArray array_03 = new JSONArray(str_03);
                        for (int k = 0; k < array_03.length(); k++) {
                            JSONObject object = array_03.getJSONObject(k);
                            if (object.has("ErrorDescription")) {
                                logATGModel.setErrorDescription(object.getJSONArray("ErrorDescription").toString());
                            } else {
                                logATGModel.setErrorDescription("");
                            }
                        }
                        //String item = String.valueOf(i + 1);
                        // Add row data into tree_map
                        tree_map.put(String.valueOf(i + 1), new Object[]{String.valueOf(i + 1), logATGModel.getTIME(), logATGModel.getUSER(), logATGModel.getPAN(), logATGModel.getPOSEntryMode(), logATGModel.getCVM(), logATGModel.getErrorDescription(), logATGModel.getTransactionAmount(), logATGModel.getTrack2(), logATGModel.getPINBlock(), logATGModel.getTransactionKSN(), logATGModel.getMID(), logATGModel.getTID(), logATGModel.getBankCode(), logATGModel.getCurrencyCode(), logATGModel.getCardHolderName(), logATGModel.getBillNumber(), logATGModel.getExpireDate(), logATGModel.getAppName(), logATGModel.getAppID(), logATGModel.getTC(), logATGModel.getAppVersion(), logATGModel.getIsEasyPayment(), logATGModel.getRequestDateTime(), logATGModel.getRequestID()});
                        // add row data into jtable
//                        String objectData[] = {String.valueOf(i + 1), logATGModel.getTIME(), logATGModel.getUSER(), logATGModel.getPAN(),logATGModel.getPOSEntryMode(), logATGModel.getCVM(),  logATGModel.getErrorDescription(), logATGModel.getTransactionAmount(),logATGModel.getTrack2(), logATGModel.getPINBlock(), logATGModel.getTransactionKSN(), logATGModel.getMID(), logATGModel.getTID(), logATGModel.getBankCode(), logATGModel.getCurrencyCode(), logATGModel.getCardHolderName(), logATGModel.getBillNumber(), logATGModel.getExpireDate(), logATGModel.getAppName() , logATGModel.getAppID(), logATGModel.getTC(), logATGModel.getAppVersion(),logATGModel.getRequestDateTime(), logATGModel.getRequestID()};
                        String objectData[] = {String.valueOf(i + 1), logATGModel.getTIME(), logATGModel.getUSER(), logATGModel.getPAN(), logATGModel.getPOSEntryMode(), logATGModel.getCVM(), logATGModel.getErrorDescription(), logATGModel.getTransactionAmount(), logATGModel.getMID(), logATGModel.getTID(), logATGModel.getBankCode(), logATGModel.getBillNumber(), logATGModel.getAppVersion(), logATGModel.getRequestDateTime(), logATGModel.getRequestID()};
                        tblModel.addRow(objectData);
                    } catch (Exception ex) {
                        textArea_ErrorLog.setText(textArea_ErrorLog.getText().trim() + "\n" + log_info);
                        //tree_map_error.put(String.valueOf(i),new Object[]{i,log_info});
                        continue;
                    }
                }
            }
        });
        btn_Export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblModel.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(null, "No data to export");
                    return;
                }
                JFileChooser chooser = new JFileChooser();
                int i = chooser.showSaveDialog(chooser);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    // add func type to export.
                    //exportFileWriter(file);
                    exportXSSFWorkbook(file);
                }
            }

            public void addNewSheet(String filePath, String newSheetName) {
                try {
                    // Creating Workbook instances
                    Workbook wb = new HSSFWorkbook();

                    // An output stream accepts output bytes and
                    // sends them to sink
                    System.out.println(filePath);
                    OutputStream fileOut = new FileOutputStream(filePath);
                    // Now creating Sheets using sheet object
                    Sheet sheet1 = wb.getSheet(newSheetName);
                    System.out.println(
                            "Sheets Has been Created successfully");

                    // Finding number of Sheets present in Workbook
                    int numberOfSheets = wb.getNumberOfSheets();
                    System.out.println("Total Number of Sheets: "
                            + numberOfSheets);

                    wb.write(fileOut);
//                    XSSFWorkbook workbook = new XSSFWorkbook();
//                    XSSFSheet sheet = workbook.createSheet(newSheetName);
//                    Object[][] bookData = {
//                            {"Head First Java", "Kathy Serria", 79},
//                            {"Effective Java", "Joshua Bloch", 36},
//                            {"Clean Code", "Robert martin", 42},
//                            {"Thinking in Java", "Bruce Eckel", 35},
//                    };
//                    int rowCount = 0;
//                    for (Object[] aBook : bookData) {
//                        Row row = sheet.createRow(++rowCount);
//                        int columnCount = 0;
//                        for (Object field : aBook) {
//                            Cell cell = row.createCell(++columnCount);
//                            if (field instanceof String) {
//                                cell.setCellValue((String) field);
//                            } else if (field instanceof Integer) {
//                                cell.setCellValue((Integer) field);
//                            }
//                        }
//                    }
//                    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
//                        workbook.write(outputStream);
//                    } catch (FileNotFoundException e) {
//                        throw new RuntimeException(e);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                    return;
                }
            }

            public void exportFileWriter(File file) {
                try {
                    // .xls  .csv
                    FileWriter excel = new FileWriter(file + ".xls");
                    BufferedWriter bwrite = new BufferedWriter(excel);
                    //DefaultTableModel model = (DefaultTableModel) table.getModel();
                    // columnName
                    for (int j = 0; j < tblModel.getColumnCount(); j++) {
                        bwrite.write(tblModel.getColumnName(j) + "\t");
                    }
                    bwrite.write("\n");
                    // Get row data
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
                    return;
                }
            }

            public void exportXSSFWorkbook(File file) {
                try {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    // spreadsheet object
                    XSSFSheet spreadsheet = workbook.createSheet("Logs");
                    //XSSFSheet spreadsheet_error = workbook.createSheet("Logs_error");
                    // add data header to file
                    tree_map.put("1", new Object[]{"ITEM", "TIME", "USER", "PAN", "POSEntryMode", "CVM", "ErrorDescription", "TransactionAmount", "Track2", "PINBlock", "TransactionKSN", "MID", "TID", "BankCode", "CurrencyCode", "CardHolderName", "BillNumber", "ExpireDate", "AppName", "AppID", "TC", "AppVersion", "IsEasyPayment", "RequestDateTime", "RequestID"});
                    // creating a row object
                    XSSFRow row;
                    Set<String> keyid = tree_map.keySet();
                    int rowid = 0;
                    // writing the data into the sheets...
                    for (String key : keyid) {
                        row = spreadsheet.createRow(rowid++);
                        Object[] objectArr = tree_map.get(key);
                        int cellid = 0;
                        for (Object obj : objectArr) {
                            Cell cell = row.createCell(cellid++);
                            cell.setCellValue((String) obj);
                        }
                    }
                    FileOutputStream out = new FileOutputStream(new File(file + ".xlsx"));
                    workbook.write(out);
                    out.close();
                    //addNewSheet(file + ".xlsx", "Logs_error");
                    // remove all data from tree_map
                    tree_map.clear();
                    //tree_map_error.clear();
                    JOptionPane.showMessageDialog(null, "Save file success.");
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Error save file.");
                    return;
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
                    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()))) {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();
                        while (line != null) {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            line = br.readLine();
                        }
                        textArea_Content.setText(sb.toString());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });

    }
}
