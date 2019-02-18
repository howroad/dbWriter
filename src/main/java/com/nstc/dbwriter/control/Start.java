package com.nstc.dbwriter.control;

import java.util.List;

import com.nstc.dbwriter.builder.TableBuilder;
import com.nstc.dbwriter.config.CommonSettings;
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
        
        if(CommonSettings.fromExcel) {
            List<Table> tables = WriteUtil.buildTableFromExcel(CommonSettings.appNo);
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                if(CommonSettings.useTemplet) {
                    //使用模版生成
                    WriteUtil.buildAllTemplet(table,CommonSettings.FROM_EXCEL);
                }else {
                    //老方法
                    WriteUtil.buildTab(table);
                    WriteUtil.buildSeq(table);
                }
                System.out.println(table.getTableName() + " complete...");
    
            }
        }
        
        if(CommonSettings.fromDatebase) {
            String[] tablesFromDB = CommonSettings.tablesFromDB;
            for (String tableName : tablesFromDB) {
                if(tableName.toUpperCase().startsWith("UM_")) {
                    continue;
                }
                Table table = TableBuilder.buildTableFromDB(tableName);
                if(CommonSettings.useTemplet) {
                    //使用模版生成
                    WriteUtil.buildAllTemplet(table,CommonSettings.FROM_DB);
                    WriteUtil.buildDate(table);
                }else {
                    //老方法
                    WriteUtil.buildJavaBean(table);
                    WriteUtil.buildDao(table);
                    WriteUtil.buildXml(table);
                    WriteUtil.buildDate(table);
                    WriteUtil.buildCreateFromDB(table);
                }
                
                
                System.out.println(table.getTableName() + " complete...");
                if(CommonSettings.autoRunTest) {
                    if(CommonSettings.useTemplet) {
                        WriteUtil.buildJavaBeanByTemplet(table);
                        WriteUtil.writeCommonFileByTemplet(table);
                    }else {
                        WriteUtil.writeCommonFile(table);
                    }
                }
                System.out.println(table.getTableName() + "insert...Done");
                
                RunTest.buildClassAndRun(table.getEntityName(),CommonSettings.autoRunTest);
                System.out.println(table.getTableName() + "testBean complete...");
            } 
        }
        System.out.println("end...");
        
    }
}
