package com.nstc.dbwriter.config;
/**
 * 模块级别配置一般不进行修改
 * 
 * <p>Title: InnerSettings.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月15日 上午10:23:11
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class InnerSettings {
    public final static String TEST_MODEL_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\model\\";
    /** 输出目录 输出到本工程 */
    public static String OUT_DIR = "C:/Users/Administrator/Desktop/model/";
    public static String OUT_DIR_SQL = OUT_DIR + "ORACLE/sql/";
    public static String OUT_DIR_MODEL = OUT_DIR + "ORACLE/model/";
    public static String OUT_DIR_SCOPE = OUT_DIR + "ORACLE/scope/";
    public static String OUT_DIR_XML = OUT_DIR + "ORACLE/xml/";
    public static final String OUT_DIR_VIEW = OUT_DIR + "ORACLE/view/";
    public static final String OUT_DIR_DAO = OUT_DIR + "ORACLE/dao/";
    public static final String OUT_DIR_SERVICE = OUT_DIR + "ORACLE/service/";
    
//    public static String OUT_DIR = "com/nstc/dbwriter/out/";
    public final static String TEMPLET_DIR = "com/nstc/dbwriter/templet";
    public final static String TEST_DIR = "/src/main/java/com/nstc/temp/test/";
    public final static String TEMP_DAO_DIR = "/src/main/java/com/nstc/temp/dao/";
    
    public final static String PO_TEMPLET_PATH = TEMPLET_DIR + "PO.templet";
    public final static String DAO_TEMPLET_PATH = TEMPLET_DIR + "DAO.templet";
    public final static String DAOIMPL_TEMPLET_PATH = TEMPLET_DIR + "DAOIMPL.templet";
    public final static String XML_TEMPLET_PATH = TEMPLET_DIR + "db/IBATiS.templet";
    public final static String SCOPE_TEMPLET_PATH = TEMPLET_DIR + "Scope.templet";
    
    public final static String ICOMMONDAO_TEMPLET_PATH = TEMPLET_DIR + "common/ICommonDao.templet";
    public final static String COMMONDAOIMPL_TEMPLET_PATH = TEMPLET_DIR + "common/CommonDaoImpl.templet";
    public final static String COMMON_XML_TEMPLET_PATH = TEMPLET_DIR + "common/TEMP_Common.templet";
    public final static String ICOMMONDAO_PATH = TEMP_DAO_DIR + "ICommonDao.java";
    public final static String COMMONDAOIMPL_PATH = TEMP_DAO_DIR + "CommonDaoImpl.java";
    public final static String COMMON_XML_PATH = TEMP_DAO_DIR + "TEMP_Common.xml";
    
    public final static String PATCH_TABLE = TEMPLET_DIR + "/common/TABLE_PATCH.templet";
    public final static String PATCH_OUT_FILE = OUT_DIR_SQL;
    
    public final static String CODE = "GBK";
    
    public final static String POST_FIX = "";
    
    public static Map<String, String> templetMap = new HashMap<String, String>(16);
    
    static {
        templetMap.put("DAO.templet", "$table{entityName}Dao.java" + POST_FIX);
        templetMap.put("DAOIMPL.templet", "$table{entityName}DaoImpl.java" + POST_FIX);
        templetMap.put("IBATIS.templet", "$common{appNoUpper}_$table{entityName}.xml" + POST_FIX);
        templetMap.put("PO.templet", "$table{entityName}.java" + POST_FIX);
        templetMap.put("SEQ.templet", "$table{tableName}_SEQ.PDC" + POST_FIX);
        templetMap.put("TABLE.templet", "$table{tableName}.TAB" + POST_FIX);
        templetMap.put("Scope.templet", "$table{entityName}Scope.java" + POST_FIX);
        templetMap.put("SERVICE.templet", "$table{entityName}Service.java" + POST_FIX);
        templetMap.put("SERVICEIMPL.templet", "$table{entityName}ServiceImpl.java" + POST_FIX);
        templetMap.put("VIEW.templet", "$table{entityName}View.java" + POST_FIX);
        
        templetMap.put("DeleteListBusiness.templet", "Delete$table{entityName}ListBusiness.java" + POST_FIX);
        templetMap.put("QueryListBusiness.templet", "Query$table{entityName}ListBusiness.java" + POST_FIX);
        templetMap.put("SaveBusiness.templet", "Save$table{entityName}Business.java" + POST_FIX);
        templetMap.put("ShowBusiness.templet", "Show$table{entityName}Business.java" + POST_FIX);
        templetMap.put("SubmitListBusiness.templet", "Submit$table{entityName}ListBusiness.java" + POST_FIX);
    }
    
    
    
    
    
    
    
}
