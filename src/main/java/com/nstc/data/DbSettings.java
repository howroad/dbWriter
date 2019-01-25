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
    
    public static String PATH = "C:/Users/Administrator/Desktop/model/";
    public static String URL = "jdbc:oracle:thin:@192.168.20.33:1521:nstestsid";
    public static String USER = "HAIMA_FSS20180831";
    public static String PASSWORD = "123456";
    public static String EXCEL_PATH = "C:/Users/Administrator/Desktop/model/1.xlsx";
    public static boolean fromExcel = false;
    public static boolean fromDatebase = false;
    public static String appNo = "";
    
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
        URL = pro.getProperty("URL");
        USER = pro.getProperty("USER");
        PASSWORD = pro.getProperty("PASSWORD");
        EXCEL_PATH = pro.getProperty("EXCEL_PATH");
        fromExcel = "true".equalsIgnoreCase(pro.getProperty("fromExcel"));
        fromDatebase = "true".equalsIgnoreCase(pro.getProperty("fromDatebase"));
        appNo = pro.getProperty("appNo");
    }
}
