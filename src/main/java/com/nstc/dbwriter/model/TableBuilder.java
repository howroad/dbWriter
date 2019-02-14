package com.nstc.dbwriter.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.CaseFormat;
import com.nstc.dbwriter.config.DbSettings;

import oracle.jdbc.driver.OracleConnection;

/**
 * <p>Title: TableBuilder.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 上午11:53:09
 * 
 */
public class TableBuilder {
    private TableBuilder () {}
    
    public static Table buildTableFromDB(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        DatabaseMetaData db = null;
        ResultSet rs = null;
        ResultSet rsTable = null;
        String tableRemark = null;
        List<Line> lineList = new ArrayList<Line>();
        List<MyParam> paramList = new ArrayList<MyParam>();
        try {
            conn = DriverManager.getConnection(DbSettings.URL, DbSettings.USER, DbSettings.PASSWORD);
            ((OracleConnection) conn).setRemarksReporting(true);
            db = conn.getMetaData();
            rs = db.getColumns(null, DbSettings.USER, tableName.toUpperCase(), null);
            rsTable = db.getTables(null, DbSettings.USER, tableName.toUpperCase(), new String[] {"TABLE"});
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
                
                MyParam param = new MyParam(paramName, remark, columnType, columnSize, decimalDigits);
                paramList.add(param);
                //java 解析会出现精度错误
                if(decimalDigits == -127 && rs.getInt("COLUMN_SIZE") == 0) {
                    decimalDigits = 0;
                }
                lineList.add(new Line(columnName, paramName, columnType, decimalDigits, remark));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            }
        }
        Table table = new Table(tableName, tableRemark, lineList);
        MyClass clz = new MyClass(tableName, paramList, table);
        table.setModel(clz);
        clz.write();
        return table;
    }
}
