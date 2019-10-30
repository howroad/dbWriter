package com.nstc.dbwriter.service.impl;

import com.nstc.dbwriter.builder.TableBuilder;
import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.TempletConstants;
import com.nstc.dbwriter.control.RunTest;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.service.Container;
import com.nstc.dbwriter.service.ICoreService;
import com.nstc.dbwriter.util.ValidateUtil;
import com.nstc.log.PanelLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * <p>Title: ICoreServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-09 10:20
 */
public class CoreServiceImpl implements ICoreService {
    @Override
    public void testCoonect() {
        String sql = "SELECT 1 FROM DUAL";
        Container.databaseService.query(sql);
    }

    @Override
    public void handelRun() {

        // 从Excel中按照所有模版生成数据
        if(CommonSettings.fromExcel) {

            List<Table> tables = TableBuilder.buildTableFromExcel(0);
            for (Table table : tables) {
                Container.ioService.writeAllTemplet(table, TempletConstants.FROM_EXCEL, TempletConstants.TEMPLET_DIR);
                //RunTest.buildClassAndRun(table.getEntityName(),false);
            }
            PanelLog.log("MODEL_ALL COMPLET（EXCEL）");

            List<Table> tablePatch = TableBuilder.buildTableFromExcel(1);
            for (Table table : tablePatch) {
                File outFile = new File(TempletConstants.PATCH_OUT_FILE + "patch/" + table.getTableName() + ".TAB");
                if(ValidateUtil.projectIsJar()) {
                    JarEntry jarEntry = Container.ioService.getJarEntry("TABLE_PATCH.templet");
                    Container.ioService.writeTempletByEntry(table, TempletConstants.FROM_EXCEL, jarEntry, outFile);
                }else {
                    String templetPath = null;
                    templetPath = TempletConstants.BASE_PATH + "/src/main/resources/" + TempletConstants.PATCH_TABLE;
                    File templet = new File(templetPath);
                    outFile.getParentFile().mkdirs();
                    Container.ioService.writeFileByTemplet(templet, outFile, table);
                }
                //List<MyParam> paramList = table.getParamList();
            }
            PanelLog.log("1.ADD_COLUMN COMPLET（EXCEL）");


            List<Table> tablePatch2 = TableBuilder.buildTableFromExcel(2);
            for (Table table : tablePatch2) {
                File outFile = new File(TempletConstants.PATCH_OUT_FILE + "patch/" + table.getTableName() + "2.TAB");
                if(ValidateUtil.projectIsJar()) {
                    JarEntry jarEntry = Container.ioService.getJarEntry("TABLE_PATCH2.templet");
                    Container.ioService.writeTempletByEntry(table, TempletConstants.FROM_EXCEL, jarEntry, outFile);
                }else {
                    File templet = new File(TempletConstants.BASE_PATH + "/src/main/resources/" + TempletConstants.PATCH_TABLE2);
                    outFile.getParentFile().mkdirs();
                    Container.ioService.writeFileByTemplet(templet, outFile, table);
                    //List<MyParam> paramList = table.getParamList();
                }
                //List<MyParam> paramList = table.getParamList();
            }
            PanelLog.log("2.ALTER_COLUMN COMPLET（EXCEL）");


            for (Table table : tables) {
                PanelLog.log("DELETE FROM " + table.getTableName());
                PanelLog.log("/");
            }
            for (Table table : tables) {
                PanelLog.log("DROP TABLE " + table.getTableName());
                PanelLog.log("/");
            }

        }

        // 从数据库中查询按照模版生成数据
        if(CommonSettings.fromDatebase) {
            String[] tablesFromDB = CommonSettings.tablesFromDB;
            for (String tableName : tablesFromDB) {
                Table table = TableBuilder.buildTableFromDB(tableName);
                //使用模版生成
                Container.ioService.writeAllTemplet(table, TempletConstants.FROM_DB, TempletConstants.TEMPLET_DIR);
                Container.ioService.writeDataFile(table);

                PanelLog.log(table.getTableName() + " write templet complete...(db)");
                // 自动测试
                if(CommonSettings.autoRunTest) {
                    // 将javabean 放入model目录
                    Container.ioService.writeJavaBean(table);
                    // 将dao方法写入
                    Container.ioService.writeCommonFileByTemplet(table);
                    PanelLog.log(table.getTableName() + " insert...Done");

                    RunTest.buildClassAndRun(table.getEntityName(),CommonSettings.autoRunTest);
                    PanelLog.log(table.getTableName() + " TestBean complete...");
                }
            }
        }
        PanelLog.log("end...");

    }


    @Override
    public void clear() {
        Container.ioService.clear();
    }

    @Override
    public void autoTest() {

    }

    @Override
    public void createCustSql(String tbNamesStr, String sqlsStr, String pkNamesStr) {
        String[] tableNames = tbNamesStr.split(";");
        String[] sqls = sqlsStr.split(";");
        String[] primaryKeyStr = pkNamesStr.split(";");
        List<String[]> primaryKeys = new ArrayList<String[]>();
        for (String pk: primaryKeyStr) {
            primaryKeys.add(pk.split(","));
        }

        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            String sql = sqls[i];
            String[] primaryKey = primaryKeys.get(i);
            Table table = TableBuilder.buildTableFromDB(tableName);
            String filName = CommonSettings.OUT_PATH + "\\sqls\\" + table.getTableName() + ".SQL";
            File file = new File(filName);
            file.getParentFile().mkdirs();
            Container.ioService.writeDataFile(table,sql,primaryKey,filName);
            PanelLog.log("build " + tableName + ".SQL down...");
        }
    }
}
