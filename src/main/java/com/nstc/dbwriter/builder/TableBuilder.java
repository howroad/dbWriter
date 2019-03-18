package com.nstc.dbwriter.builder;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;
import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.model.MyParam;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.util.ExcelUtil;

import oracle.jdbc.driver.OracleConnection;

/**
 * <p>Title: TableBuilder.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 上午11:53:09
 * 
 */
public class TableBuilder {
    private TableBuilder () {}
    
    /**
     * 从数据库构建Table
     * @param tableName
     * @return Table
     * @author luhao
     * @since：2019年2月25日 下午2:07:19
     */
    public static Table buildTableFromDB(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        DatabaseMetaData db = null;
        // 字段信息
        ResultSet rs = null;
        // 表信息
        ResultSet rsTable = null;
        String tableRemark = null;
        List<MyParam> paramList = new ArrayList<MyParam>();
        try {
            conn = DriverManager.getConnection(CommonSettings.URL, CommonSettings.USER, CommonSettings.PASSWORD);
            ((OracleConnection) conn).setRemarksReporting(true);
            db = conn.getMetaData();
            rs = db.getColumns(null, CommonSettings.USER, tableName.toUpperCase(), null);
            rsTable = db.getTables(null, CommonSettings.USER, tableName.toUpperCase(), new String[] {"TABLE"});
            if(rsTable.next()) {
                tableRemark = rsTable.getString("REMARKS");
            }
            while (rs.next()) {
                // 列名
                String columnName = rs.getString("COLUMN_NAME");
                // 字段名称
                String paramName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                // 数据类型
                int columnType = rs.getInt("DATA_TYPE");
                // 小数位数
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");
                // 注释
                String remark = rs.getString("REMARKS");
                //精度
                int columnSize = rs.getInt("COLUMN_SIZE");
                
                MyParam param = new MyParam(paramName, columnName, remark, columnType, columnSize, decimalDigits);
                paramList.add(param);
            }
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs!=null) {
                    rs.close();
                }
                if(rsTable != null) {
                    rsTable.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if(paramList == null || paramList.isEmpty()) {
            throw new RuntimeException(tableName + "表中无字段");
        }
        Table table = new Table(tableName, tableRemark ,paramList);
        return table;
    }
    /**
     * 从Excel中构建Table
     * @param appNo
     * @return List<Table>
     * @author luhao
     * @since：2019年2月25日 下午2:11:02
     */
    public static List<Table> buildTableFromExcel(String appNo) {
        //excel 导入数据
        File file = new File(CommonSettings.EXCEL_PATH);
        List<List<String>> dataList= null;
        List<Table> tableList = new ArrayList<Table>();
        
        Table tempTable = null;
        List<MyParam> tempParamList = null;
        try {
            dataList = ExcelUtil.importExcel(file);
            for (ListIterator<List<String>> iterator = dataList.listIterator(); iterator.hasNext();) {
                List<String> list = iterator.next();
                if(!iterator.hasNext() || "END".equalsIgnoreCase(list.get(0))) {
                    Table table = tempTable.clone();
                    tableList.add(table);
                    break;
                }
                
                String columnName = list.get(0);
                System.out.println(columnName);
                String type = list.get(1);
                String commont = list.get(2);
                if(StringUtils.isEmpty(columnName) && StringUtils.isEmpty(type) && StringUtils.isEmpty(commont)) {
                    continue;
                }

                //表名
                if(StringUtils.isEmpty(type)) {
                    //非第一次，把原来的数据放入
                    if(tempTable != null && tempParamList != null) {
                        tempTable.setParamList(tempParamList);
                        Table table = tempTable.clone();
                        tableList.add(table);
                    }
                    tempParamList = new ArrayList<MyParam>();
                    tempTable = new Table(columnName, commont ,tempParamList);
                }else {
                    //属性信息
                    tempParamList.add(new MyParam(commont, columnName, type));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tableList;
    }
    
}
