package com.nstc.dbwriter;

import java.util.List;

import com.nstc.dbwriter.config.DbSettings;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.model.TableBuilder;
import com.nstc.dbwriter.util.WriteUtil;
import com.nstc.temp.test.RunTest;

/**
 * <p>
 * Title: Start.java
 * </p>
 *
 * <p>
 * Description:
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
                Table table = TableBuilder.buildTableFromDB(tableName);
                wt.buildJavaBean(table);
                wt.buildDao(table);
                wt.buildXml(table);
                wt.buildDate(table);
                wt.buildCreateFromDB(table);
                System.out.println(table.getTableName() + " complete...");
                if(DbSettings.autoRunTest) {
                    table.writeCommonFile();
                }
                RunTest.buildClassAndRun(table.getEntityName(),DbSettings.autoRunTest);
                System.out.println(table.getTableName() + "testBean complete...");
            } 
        }
        System.out.println("end...");
        
    }
}
