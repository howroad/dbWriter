package com.nstc.data;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.CaseFormat;

import oracle.jdbc.driver.OracleConnection;

/**
 * <p>
 * Title: WriteTest.java
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * @author luhao
 * 
 * @since：2018年12月21日 下午1:24:52
 * 
 */
public class WriteUtil {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void buildCreateFromDB(Table table) {
        PrintWriter out = null;
        String filName = DbSettings.PATH  + table.getTableName() + "_FROMDB" + ".TAB";
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           out.println(getCreateFromDB(table.getTableName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }        
    }
    public String getCreateFromDB(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select dbms_metadata.get_ddl('TABLE','" + tableName + "') from dual";
        String result = null;
        try {
            conn = DriverManager.getConnection(DbSettings.URL, DbSettings.USER, DbSettings.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clob clob = rs.getClob(1);
                result = clobToString(clob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;       
    }
    
    public List<Table> buildTableFromExcel(String appNo) {
        ExcelUtil importExcelUtil=new ExcelUtil();
        //excel 导入数据demo
        File file = new File(DbSettings.EXCEL_PATH);
        List<List<String>> dataList= null;
        List<Table> tableList = new ArrayList<Table>();
        
        Table tempTable = null;
        List<Line> tempLineList = null;
        try {
            dataList = importExcelUtil.importExcel(file);
            for (ListIterator<List<String>> iterator = dataList.listIterator(); iterator.hasNext();) {
                List<String> list = iterator.next();
                String columnName = list.get(0);
                String type = list.get(1);
                String commont = list.get(2);
                if(type == null || type.length() == 0) {
                    if(tempTable != null && tempLineList != null) {
                        tempTable.setLineList(tempLineList);
                        Table table = tempTable.clone();
                        tableList.add(table);
                    }
                    tempLineList = new ArrayList<Line>();
                    tempTable = new Table(columnName, commont, tempLineList,appNo);
                }else {
                    tempLineList.add(new Line(columnName, type, commont));
                }
                if(!iterator.hasNext()) {
                    tempTable.setLineList(tempLineList);
                    Table table = tempTable.clone();
                    tableList.add(table);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableList;
    }
    public Table buildTableFromDB(String tableName,String appNo) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        DatabaseMetaData db = null;
        ResultSet rs = null;
        ResultSet rsTable = null;
        String tableRemark = null;
        List<Line> lineList = new ArrayList<Line>();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Table(tableName, tableRemark, lineList,appNo);
    }
    public void buildDate(Table table) {
        PrintWriter out = null;
        String filName = DbSettings.PATH  + table.getTableName() + ".SQL";
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           List<List<Object>> dataList = getData(table.getTableName());
           table.writeDate(out, dataList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }        
    }
    public List<List<Object>> getData(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + tableName + " WHERE ROWNUM <= 20";
        List<List<Object>> resultList = new ArrayList<List<Object>>();
        try {
            conn = DriverManager.getConnection(DbSettings.URL, DbSettings.USER, DbSettings.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < count; i++) {
                    list.add(rs.getObject(i + 1));
                }
                resultList.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
    public void buildSeq(Table table) {
        if(!DbSettings.dealSEQ) {
            return;
        }
        PrintWriter out = null;
        String filName = DbSettings.PATH + table.getSeqName() + ".SEQ";
        try {
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
            table.writeSEQ(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }       
    }
    public void buildTab(Table table) {
        PrintWriter out = null;
        String filName = DbSettings.PATH  + table.getTableName() + ".TAB";
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeCreateTable(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    public void buildJavaBean(Table table) {
        PrintWriter out = null;
        String filName = DbSettings.PATH  + table.getJaveBeanName();
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeJaveBean(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    public void buildDao(Table table) {
        PrintWriter out = null;
        String filName = DbSettings.PATH + table.getDaoName();
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeDao(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        } 
    }
    public void buildXml(Table table) {
        PrintWriter out = null;
        String filName = DbSettings.PATH + table.getXmlName();
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeXml(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        } 
    }

    /**
     * 将字Clob转成String类型
     * @Description:
     * @param sc
     * @return String
     * @author net
     * @since：2018年12月26日 下午4:57:21
     */
    private String clobToString(Clob sc) {
        String reString = "";
        //得到流
        StringBuffer sb = null;
        try {
            Reader is = sc.getCharacterStream();
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();
            sb = new StringBuffer();
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            while (s != null) {
                sb.append(s.trim().replaceAll("\t", "\n") + "\n");
                s = br.readLine();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reString = sb.toString();
        return reString;
    }
}
