package com.nstc.data;

import java.util.List;

/**
 * <p>
 * Title: Start.java
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Company: 北京九恒星科技股份有限公司
 * </p>
 *
 * @author luhao
 * 
 * @since：2018年12月26日 下午2:56:58
 * 
 */
public class Start {
    public static void main(String[] args) {
        WriteUtil wt = new WriteUtil();
        
        if(DbSettings.fromExcel) {
            List<Table> tables = wt.buildTableFromExcel(DbSettings.appNo);
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                wt.buildTab(table);
                wt.buildSeq(table);
                System.out.println(table.getTableName() + " complete...");
    
            }
        }
        
        if(DbSettings.fromDatebase) {
            String[] tablesFromDB = DbSettings.tablesFromDB;
            for (String tableName : tablesFromDB) {
                if(tableName.toUpperCase().startsWith("UM_")) {
                    continue;
                }
                Table table = wt.buildTableFromDB(tableName,DbSettings.appNo);
                wt.buildJavaBean(table);
                wt.buildDao(table);
                wt.buildXml(table);
                wt.buildDate(table);
                wt.buildCreateFromDB(table);
                System.out.println(table.getTableName() + " complete...");

            } 
        }
        System.out.println("end...");
        
    }
}
