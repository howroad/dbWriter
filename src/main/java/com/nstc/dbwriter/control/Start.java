package com.nstc.dbwriter.control;

import java.io.File;
import java.util.ArrayList;
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
        //ta0723BuildSql();
        
    }
    
    public static void ta0723BuildSql() {
        String buss_idStr = "0,1,2,3,4";
        String[] tableNames = new String[] {"GDT_CUST_BUSS","GDT_CUST_BUSS_EXTRA"
                ,"GDT_CUST_FIXED_ELEMENT","GDT_CUST_ELEMENT"
                ,"GDT_CUST_TYPE","GDT_CUST_TEMPLATE"
                ,"GDT_CUST_EXTRA"
                ,"UM_CODE"};
        String[] sqls = new String[] {"SELECT * FROM GDT_CUST_BUSS T WHERE T.BUSS_ID IN (" + buss_idStr + ") ORDER BY 1 ASC",
                "SELECT * FROM GDT_CUST_BUSS_EXTRA T WHERE T.BUSS_ID IN (" + buss_idStr + ") ORDER BY 1 ASC",
                "SELECT * FROM GDT_CUST_FIXED_ELEMENT T ORDER BY 1 ASC",
                "SELECT * FROM GDT_CUST_ELEMENT T WHERE T.BUSS_ID IN (" + buss_idStr + ") ORDER BY 1 ASC",
                "SELECT * FROM GDT_CUST_TYPE T ORDER BY 1 ASC",
                "SELECT * FROM GDT_CUST_TEMPLATE ORDER BY 1 ASC",
                "SELECT * FROM GDT_CUST_EXTRA ORDER BY 1 ASC",
                "SELECT u.* FROM UM_CODE U INNER JOIN um_ctype t on u.ctid=t.ctid where t.ctype in ('CLMS01','CLMS02','CLMS03','CLMS04')"};
        List<String[]> primaryKeys = new ArrayList<String[]>();
        primaryKeys.add(new String[] {"BUSS_ID"});
        primaryKeys.add(new String[] {"BE_ID"});
        primaryKeys.add(new String[] {"FIXED_ID"});
        primaryKeys.add(new String[] {"ELEMENT_ID"});
        primaryKeys.add(new String[] {"TYPE_NAME","TYPE_TYPE"});
        primaryKeys.add(new String[] {"TEMPLATE_ID"});
        primaryKeys.add(new String[] {"EXTRA_ID"});
        primaryKeys.add(new String[] {"MCODE"});
        
        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            String sql = sqls[i];
            String[] primaryKey = primaryKeys.get(i);
            Table table = TableBuilder.buildTableFromDB(tableName);
            String filName = CommonSettings.PATH + "\\sqls\\" + table.getTableName() + ".SQL";
            File file = new File(filName);
            file.getParentFile().mkdirs();
            WriteUtil.buildDate(table,sql,primaryKey,filName);
            System.out.println("build " + tableName + ".SQL down...");
        }
        
    }
    
    
    public static void start() {
        
        // 从Excel中按照所有模版生成数据
        if(CommonSettings.fromExcel) {
            
            List<Table> tables = TableBuilder.buildTableFromExcel();
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                WriteUtil.buildAllTemplet(table,CommonSettings.FROM_EXCEL,InnerSettings.TEMPLET_DIR);
                //RunTest.buildClassAndRun(table.getEntityName(),false);
                System.out.println(table.getTableName() + " write templet complete...(excel)");
            }
            List<Table> tablePatch = TableBuilder.buildPatchTableFromExcel();
            for (Table table : tablePatch) {
                File templet = new File(System.getProperty("user.dir") + "/src/main/java/" + InnerSettings.PATCH_TABLE);
                File outFile = new File(InnerSettings.PATCH_OUT_FILE + "patch/" + table.getTableName() + ".TAB");
                outFile.getParentFile().mkdirs();
                WriteUtil.writeFileByTemplet(templet, outFile, table);
                System.out.println(outFile.getAbsolutePath());
            }
            
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                System.out.println("DELETE FROM " + table.getTableName());
                System.out.println("/");
            }
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                System.out.println("DROP TABLE " + table.getTableName());
                System.out.println("/");
            }
        }
        
        // 从数据库中查询按照模版生成数据
        if(CommonSettings.fromDatebase) {
            String[] tablesFromDB = CommonSettings.tablesFromDB;
            for (String tableName : tablesFromDB) {
                Table table = TableBuilder.buildTableFromDB(tableName);
                //使用模版生成
                WriteUtil.buildAllTemplet(table,CommonSettings.FROM_DB,InnerSettings.TEMPLET_DIR);
                WriteUtil.buildDate(table);
                
                System.out.println(table.getTableName() + " write templet complete...(db)");
                // 自动测试
                if(CommonSettings.autoRunTest) {
                    // 将javabean 放入model目录
                    WriteUtil.buildJavaBeanByTemplet(table);
                    // 将dao方法写入
                    WriteUtil.writeCommonFileByTemplet(table);
                    System.out.println(table.getTableName() + " insert...Done");
                    
                    RunTest.buildClassAndRun(table.getEntityName(),CommonSettings.autoRunTest);
                    System.out.println(table.getTableName() + " TestBean complete...");
                }
            } 
        }
        System.out.println("end...");
        
    }
}
