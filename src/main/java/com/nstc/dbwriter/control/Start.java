package com.nstc.dbwriter.control;

import java.util.List;

import com.nstc.dbwriter.builder.TableBuilder;
import com.nstc.dbwriter.config.DbSettings;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.util.WriteUtil;

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
        
        if(DbSettings.fromExcel) {
            List<Table> tables = WriteUtil.buildTableFromExcel(DbSettings.appNo);
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                WriteUtil.buildTab(table);
                WriteUtil.buildSeq(table);
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
                if(DbSettings.useTemplet) {
                    //使用模版生成
                    WriteUtil.buildJavaBeanByTemplet(table);
                    WriteUtil.buildAllTemplet(table);
                }else {
                    //老方法
                    WriteUtil.buildJavaBean(table);
                    WriteUtil.buildDao(table);
                    WriteUtil.buildXml(table);
                    WriteUtil.buildDate(table);
                    WriteUtil.buildCreateFromDB(table);
                }
                
                
                System.out.println(table.getTableName() + " complete...");
                if(DbSettings.autoRunTest) {
                    if(DbSettings.useTemplet) {
                        WriteUtil.writeCommonFileByTemplet(table);
                    }else {
                        WriteUtil.writeCommonFile(table);
                    }
                }
                System.out.println(table.getTableName() + "insert...Done");
                
                RunTest.buildClassAndRun(table.getEntityName(),DbSettings.autoRunTest);
                System.out.println(table.getTableName() + "testBean complete...");
            } 
        }
        System.out.println("end...");
        
    }
}
