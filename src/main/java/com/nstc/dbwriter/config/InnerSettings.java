package com.nstc.dbwriter.config;
/**
 * 模块级别配置一般不进行修改
 * 
 * <p>Title: InnerSettings.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
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
    public final static String OUT_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\dbwriter\\out\\";
    //public final static String OUT_DIR = "C:/Users/Administrator/Desktop/model/";
    public final static String TEMPLET_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\dbwriter\\templet\\";
    public final static String TEST_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\test\\";
    public final static String TEMP_DAO_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\dao\\";
    
    public final static String PO_TEMPLET_PATH = TEMPLET_DIR + "PO.templet";
    public final static String DAO_TEMPLET_PATH = TEMPLET_DIR + "DAO.templet";
    public final static String DAOIMPL_TEMPLET_PATH = TEMPLET_DIR + "DAOIMPL.templet";
    public final static String XML_TEMPLET_PATH = TEMPLET_DIR + "db\\IBATiS.templet";
    public final static String SCOPE_TEMPLET_PATH = TEMPLET_DIR + "Scope.templet";
    
    public final static String ICOMMONDAO_TEMPLET_PATH = TEMPLET_DIR + "common\\ICommonDao.templet";
    public final static String COMMONDAOIMPL_TEMPLET_PATH = TEMPLET_DIR + "common\\CommonDaoImpl.templet";
    public final static String COMMON_XML_TEMPLET_PATH = TEMPLET_DIR + "common\\TEMP_Common.templet";
    public final static String ICOMMONDAO_PATH = TEMP_DAO_DIR + "ICommonDao.java";
    public final static String COMMONDAOIMPL_PATH = TEMP_DAO_DIR + "CommonDaoImpl.java";
    public final static String COMMON_XML_PATH = TEMP_DAO_DIR + "TEMP_Common.xml";
    
    public static Map<String, String> templetMap = new HashMap<String, String>(16);
    
    static {
        templetMap.put("DAO.templet", "$table{entityName}Dao.java.out");
        templetMap.put("DAOIMPL.templet", "$table{entityName}DaoImpl.java.out");
        templetMap.put("IBATIS.templet", "$common{appNo}_$table{entityName}.xml.out");
        templetMap.put("PO.templet", "$table{entityName}.java.out");
        templetMap.put("SEQ.templet", "$table{tableName}_SEQ.PDC.out");
        templetMap.put("TABLE.templet", "$table{tableName}.TAB.out");
        templetMap.put("Scope.templet", "$table{entityName}Scope.java.out");
        templetMap.put("SERVICEIMPL.templet", "$table{entityName}ServiceImpl.java.out");
        
        templetMap.put("DeleteListBusiness.templet", "Delete$table{entityName}ListBusiness.java.out");
        templetMap.put("QueryListBusiness.templet", "Query$table{entityName}ListBusiness.java.out");
        templetMap.put("SaveBusiness.templet", "Save$table{entityName}Business.java.out");
        templetMap.put("ShowBusiness.templet", "Show$table{entityName}Business.java.out");
        templetMap.put("SubmitListBusiness.templet", "Submit$table{entityName}ListBusiness.java.out");
    }
    
    
    
    
    
    
    
}
