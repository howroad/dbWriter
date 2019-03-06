package com.nstc.dbwriter.control;

import java.util.List;

import com.nstc.dbwriter.builder.TableBuilder;
import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.util.WriteUtil;
import com.nstc.frame.ShowFrame;

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
        //Start.start();
        new ShowFrame().setVisible(true);
    }
    public static void start() {
        
        // 从Excel中按照所有模版生成数据
        if(CommonSettings.fromExcel) {
            List<Table> tables = TableBuilder.buildTableFromExcel(CommonSettings.appNo);
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                WriteUtil.buildAllTemplet(table,CommonSettings.FROM_EXCEL,InnerSettings.TEMPLET_DIR);
                System.out.println(table.getTableName() + " complete...");
            }
        }
        
        // 从数据库中查询按照模版生成数据
        if(CommonSettings.fromDatebase) {
            String[] tablesFromDB = CommonSettings.tablesFromDB;
            for (String tableName : tablesFromDB) {
                //如果前缀是UM的则不需要生成（因为公司中um前缀的是工单表）
                if(tableName.toUpperCase().startsWith("UM_")) {
                    continue;
                }
                Table table = TableBuilder.buildTableFromDB(tableName);
                //使用模版生成
                WriteUtil.buildAllTemplet(table,CommonSettings.FROM_DB,InnerSettings.TEMPLET_DIR);
                WriteUtil.buildDate(table);
                
                System.out.println(table.getTableName() + " complete...");
                // 自动测试
                if(CommonSettings.autoRunTest) {
                    // 将javabean 放入model目录
                    WriteUtil.buildJavaBeanByTemplet(table);
                    // 将dao方法写入
                    WriteUtil.writeCommonFileByTemplet(table);
                }
                System.out.println(table.getTableName() + "insert...Done");
                
                RunTest.buildClassAndRun(table.getEntityName(),CommonSettings.autoRunTest);
                System.out.println(table.getTableName() + "testBean complete...");
            } 
        }
        System.out.println("end...");
        
    }
}
