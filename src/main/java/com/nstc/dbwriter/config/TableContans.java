package com.nstc.dbwriter.config;
/**
 * <p>Title: TableContans.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午12:38:43
 * 
 */
public abstract class TableContans {
    public final static String TAB = "    ";
    public final static String TABTAB = "        ";
    public final static int SAVE = 0;
    public final static int DELETE = 1;
    public final static int UPDATE = 2;
    public final static int GET = 3;
    public final static int DAO = 4;
    public final static int SERVICE = 5;
    public final static String[] COM_NAME = new String[] {"新增","删除","修改","查询"};
    public final static String[] COM_PARAM = new String[] {"model","id","model","scope"};
    public final static String PO = "";
    public final static String[] COM_PARAM_TYPE = new String[] { "实体","主键","实体", "查询条件"};
    public final static String UNDER_LINE = "_";
    public final static String DOT = ".";
    public final static String SPACE = " ";
    public final static String MODELSTR = "model";
    public final static String SEMC = ";";
}
