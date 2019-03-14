package com.nstc.dbwriter.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * <p>Title: TempSettings.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年3月14日 上午9:44:00
 * 
 */
public class TempSettings {
    private static String TEMP_PROPERTIES = "C:/dbWriter/temp.properties";
    
    public static boolean hasConfig = false;
    
    public static String URL;
    public static String USER;
    public static String PASSWORD;
    public static String EXCEL_PATH;
    public static boolean fromExcel;
    public static boolean fromDatebase;
    public static String appNo;
    public static String TABLES;
    public static String SEQ_DIR = "1";
    public static String OUT_DIR;
    
    static {
        Properties pro = new Properties();
        InputStream in = null;
        File tempFile = new File(TEMP_PROPERTIES);
        
        try {
            if(tempFile.exists() && tempFile.isFile()) {
                hasConfig = true;
                in = new FileInputStream(tempFile);
                pro.load(in);
                in.close();
                
                URL = pro.getProperty("URL");
                USER = pro.getProperty("USER");
                PASSWORD = pro.getProperty("PASSWORD");
                EXCEL_PATH = pro.getProperty("EXCEL_PATH");
                appNo = pro.getProperty("appNo");
                SEQ_DIR = pro.getProperty("SEQ_DIR");
                OUT_DIR = pro.getProperty("OUT_DIR");
                TABLES = pro.getProperty("TABLES");
            }else {
                hasConfig = false;
            }
            if(EXCEL_PATH == null) {
                hasConfig = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    /**
     * 写入配置文件
     */
    public static void writeProperties() {
        Properties pro = new Properties();
        FileOutputStream oFile;
        File tempFile = new File(TEMP_PROPERTIES);
        pro.setProperty("URL", URL);
        pro.setProperty("USER", USER);
        pro.setProperty("PASSWORD", PASSWORD);
        pro.setProperty("EXCEL_PATH", EXCEL_PATH);
        pro.setProperty("appNo", appNo);
        pro.setProperty("SEQ_DIR", SEQ_DIR);
        pro.setProperty("OUT_DIR", OUT_DIR);
        pro.setProperty("TABLES", TABLES);
        try {
            if(!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();
            }
            oFile = new FileOutputStream(tempFile, false);
            pro.store(new OutputStreamWriter(oFile, "utf-8"), "Comment");
            oFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
