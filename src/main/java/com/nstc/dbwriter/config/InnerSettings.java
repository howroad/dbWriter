package com.nstc.dbwriter.config;
/**
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
public class InnerSettings {
    public final static String TEST_MODEL_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\model\\";
    public final static String OUT_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\dbwriter\\templet\\out\\";
    public final static String TEMPLET_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\dbwriter\\templet\\";
    public final static String TEMPLET_OUT_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\dbwriter\\templet\\out\\";
    public final static String TEST_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\test\\";
    public final static String TEMP_DAO_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\dao\\";
    
    public final static String PO_TEMPLET_PATH = TEMPLET_DIR + "PO.templet";
    public final static String DAO_TEMPLET_PATH = TEMPLET_DIR + "DAO.templet";
    public final static String DAOIMPL_TEMPLET_PATH = TEMPLET_DIR + "DAOIMPL.templet";
    public final static String XML_TEMPLET_PATH = TEMPLET_DIR + "IBATiS.templet";
    
    public final static String ICOMMONDAO_TEMPLET_PATH = TEMPLET_DIR + "common\\ICommonDao.templet";
    public final static String COMMONDAOIMPL_TEMPLET_PATH = TEMPLET_DIR + "common\\CommonDaoImpl.templet";
    public final static String COMMON_XML_TEMPLET_PATH = TEMPLET_DIR + "common\\TEMP_Common.templet";
    public final static String ICOMMONDAO_PATH = TEMP_DAO_DIR + "ICommonDao.java";
    public final static String COMMONDAOIMPL_PATH = TEMP_DAO_DIR + "CommonDaoImpl.java";
    public final static String COMMON_XML_PATH = TEMP_DAO_DIR + "TEMP_Common.xml";
    
    
    
    
    
    
    
}
