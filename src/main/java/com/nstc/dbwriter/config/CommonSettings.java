package com.nstc.dbwriter.config;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title: PropertiesUtil.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年1月24日 下午4:55:14
 * 
 */
public class CommonSettings {
    
    private final static String SETTING_PATH = "\\src\\main\\java\\com\\nstc\\dbwriter\\config\\settings.properties";
    
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
    public static boolean implCommont = false;
    public static boolean dealSEQ = true;
    public static boolean autoRunTest = false;
    public static String prefix = "";
    public static Map<String,String> map = new HashMap<String,String>();
    public static Map<String, String> lastMap = new HashMap<String, String>();
    public static boolean useTemplet = true;
    public static String FROM_DB = "fromDB\\";
    public static String FROM_EXCEL = "fromExcel\\";
    public static boolean usePage = true;
    
    static{
        Properties pro = new Properties();
        FileInputStream in = null;
        
        try {
            in = new FileInputStream(System.getProperty("user.dir") + SETTING_PATH);
            pro.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        PATH = pro.getProperty("PATH");
        URL = pro.getProperty("url");
        USER = pro.getProperty("username");
        PASSWORD = pro.getProperty("password");
        EXCEL_PATH = pro.getProperty("EXCEL_PATH");
        fromExcel = "true".equalsIgnoreCase(pro.getProperty("fromExcel"));
        fromDatebase = "true".equalsIgnoreCase(pro.getProperty("fromDatebase"));
        appNo = pro.getProperty("appNo");
        tablesFromDB = stringToArray(pro.getProperty("tablesFromDB"));
        SEQ_DIR = Integer.valueOf(pro.getProperty("SEQ_DIR"));
        implCommont = "true".equalsIgnoreCase(pro.getProperty("implCommont"));
        dealSEQ = "true".equalsIgnoreCase(pro.getProperty("dealSEQ"));
        autoRunTest = "true".equalsIgnoreCase(pro.getProperty("autoRunTest"));
        prefix = pro.getProperty("prefix");
        useTemplet = "true".equalsIgnoreCase(pro.getProperty("useTemplet"));
        usePage = "true".equalsIgnoreCase(pro.getProperty("usePage"));
        
        map.put("groupId","com.nstc");
        map.put("line", "");
        map.put("appNo", appNo);
        map.put("appNoUpper",appNo.toUpperCase());
        map.put("poPackage", "model");
        map.put("author", "luhao");
        map.put(",",",");
        lastMap.put(",","");
        //TODO ...
    }
    
    public static String[] stringToArray(String str) {
        if(str == null || str.length() == 0) {
            return new String[0];
        }
        String[] result = str.split(",");
        return result;
    }
}
