package com.nstc.dbwriter.config;

import java.io.InputStream;
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
    /** 输出路径 */
    public static String OUT_PATH = "C:/Users/Administrator/Desktop/model/";
    /** 数据库URL */
    public static String URL = "jdbc:oracle:thin:@192.168.20.33:1521:nstestsid";
    /** 数据库用户 */
    public static String USER = "HAIMA_FSS20180831";
    /** 密码 */
    public static String PASSWORD = "123456";
    /** EXCEL路径 */
    public static String EXCEL_PATH = "C:/Users/Administrator/Desktop/model/1.xlsx";
    /** 是否从excel中读取 */
    public static boolean fromExcel = false;
    /** 是否从数据库中读取 */
    public static boolean fromDatebase = false;
    /** 模块前缀 */
    public static String appNo = "";
    /** 从数据库读取的表名 */
    public static String[] tablesFromDB = null;
    /** 生成序列的规则 1：SEQ_TBNAME; 2:TBNAME_SEQ */
    public static int SEQ_DIR = 1;
    /** 是否使用了序列，AutoRun使用该参数 */
    public static boolean dealSEQ = true;
    /** 是否自动执行测试 */
    public static boolean autoRunTest = false;

    /** 映射关系，对应$common{param} */
    public static Map<String,String> map = new HashMap<String,String>();
    /** 最后一行特殊处理映射，对应$split{param},非最后一行不会执行 */
    public static Map<String, String> lastMap = new HashMap<String, String>();

    /** 【下个版本去掉】是否使用分页 */
    public static boolean usePage = true;

    static{
        initSetting();
    }

    public static String[] stringToArray(String str) {
        if(str == null || str.length() == 0) {
            return new String[0];
        }
        String[] result = str.split(",");
        return result;
    }
    
    public static void initMap() {
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

    /** 从settingsProperties初始化配置， */
    private static void initSetting(){
        Properties pro = new Properties();
        InputStream in = null;

        try {
            in = CommonSettings.class.getResourceAsStream("settings.properties");
            pro.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        OUT_PATH = pro.getProperty("PATH");
        URL = pro.getProperty("url");
        USER = pro.getProperty("username");
        PASSWORD = pro.getProperty("password");
        EXCEL_PATH = pro.getProperty("EXCEL_PATH");
        fromExcel = "true".equalsIgnoreCase(pro.getProperty("fromExcel"));
        fromDatebase = "true".equalsIgnoreCase(pro.getProperty("fromDatebase"));
        appNo = pro.getProperty("appNo");
        tablesFromDB = stringToArray(pro.getProperty("tablesFromDB"));
        SEQ_DIR = Integer.valueOf(pro.getProperty("SEQ_DIR"));
        dealSEQ = "true".equalsIgnoreCase(pro.getProperty("dealSEQ"));
        autoRunTest = "true".equalsIgnoreCase(pro.getProperty("autoRunTest"));
        usePage = "true".equalsIgnoreCase(pro.getProperty("usePage"));
        initMap();
    }
}
