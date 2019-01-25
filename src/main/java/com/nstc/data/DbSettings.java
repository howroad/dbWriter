package com.nstc.data;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * <p>Title: PropertiesUtil.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年1月24日 下午4:55:14
 * 
 */
public class DbSettings {
    
    private final static String SETTING_PATH = "\\src\\main\\java\\com\\nstc\\data\\settings.properties";
    private final static String SQLMAP_PATH = "\\src\\main\\java\\com\\nstc\\temp\\dao\\SqlMap.properties";
    
    public static String PATH = "C:/Users/Administrator/Desktop/model/";
    public static String URL = "jdbc:oracle:thin:@192.168.20.33:1521:nstestsid";
    public static String USER = "HAIMA_FSS20180831";
    public static String PASSWORD = "123456";
    public static String EXCEL_PATH = "C:/Users/Administrator/Desktop/model/1.xlsx";
    public static boolean fromExcel = false;
    public static boolean fromDatebase = false;
    public static String appNo = "";
    public static String[] tablesFromDB = null;
    public static int SEQ_DIR = 1;
    
    static{
        Properties pro = new Properties();
        Properties sqlMapPro = new Properties();
        FileInputStream in = null;
        FileInputStream sqlMapIn = null;
        
        try {
            in = new FileInputStream(System.getProperty("user.dir") + SETTING_PATH);
            pro.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sqlMapIn = new FileInputStream(System.getProperty("user.dir") + SQLMAP_PATH);
            sqlMapPro.load(sqlMapIn);
            sqlMapIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        PATH = pro.getProperty("PATH");
        URL = sqlMapPro.getProperty("url");
        USER = sqlMapPro.getProperty("username");
        PASSWORD = sqlMapPro.getProperty("password");
        EXCEL_PATH = pro.getProperty("EXCEL_PATH");
        fromExcel = "true".equalsIgnoreCase(pro.getProperty("fromExcel"));
        fromDatebase = "true".equalsIgnoreCase(pro.getProperty("fromDatebase"));
        appNo = pro.getProperty("appNo");
        tablesFromDB = stringToArray(pro.getProperty("tablesFromDB"));
        SEQ_DIR = Integer.valueOf(pro.getProperty("SEQ_DIR"));
    }
    
    public static String[] stringToArray(String str) {
        if(str == null || str.length() == 0) {
            return new String[0];
        }
        String[] result = str.split(",");
        return result;
    }
}
