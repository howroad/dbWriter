package com.nstc.dbwriter.service.impl;

import com.google.common.base.CaseFormat;
import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.model.MyParam;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.service.IBuildService;
import com.nstc.dbwriter.util.ExcelUtil;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>Title: TableServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 13:40
 */
public class BuildServiceImpl implements IBuildService {
    @Override
    public List<Table> buildTableFromNames(List<String> tbNames) {
        List<Table> resultList = new ArrayList<Table>();
        tbNames.forEach(tbName ->{
            Table table = buildTableFromName(tbName);
            resultList.add(table);
        });
        return resultList;
    }

    @Override
    public Table buildTableFromName(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        DatabaseMetaData db;
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
                /*
                String tableCat = rs.getString("TABLE_CAT");//表目录（可能为空）
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表的架构（可能为空）
                String tableName_ = rs.getString("TABLE_NAME");//表名
                String columnName = rs.getString("COLUMN_NAME");//列名
                int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
                String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types类型   名称
                int columnSize = rs.getInt("COLUMN_SIZE");//列大小
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");//小数位数
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");//基数（通常是10或2）
                int nullAble = rs.getInt("NULLABLE");//是否允许为null
                String remarks = rs.getString("REMARKS");//列描述
                String columnDef = rs.getString("COLUMN_DEF");//默认值
                int sqlDataType = rs.getInt("SQL_DATA_TYPE");//sql数据类型
                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");   //SQL日期时间分?
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");   //char类型的列中的最大字节数
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");  //表中列的索引（从1开始）
                 */
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
                //是否为空
                int nullAble = rs.getInt("NULLABLE");//是否允许为null
                //默认值
                String columnDef = rs.getString("COLUMN_DEF");

                MyParam param = new MyParam(paramName, columnName, remark, columnType, columnSize, decimalDigits, nullAble, columnDef);
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
        if(paramList.isEmpty()) {
            throw new RuntimeException(tableName + "表中无字段");
        }
        Table table = new Table(tableName, tableRemark ,paramList);
        return table;
    }

    @Override
    public List<Table> buildTableFromExcel(int sheetNo) {
        //excel 导入数据
        File file = new File(CommonSettings.EXCEL_PATH);
        List<List<String>> dataList= null;
        List<Table> tableList = new ArrayList<Table>();

        Table tempTable = null;
        List<MyParam> tempParamList = null;
        try {
            dataList = ExcelUtil.importExcel(file,sheetNo);
            for (ListIterator<List<String>> iterator = dataList.listIterator(); iterator.hasNext();) {
                List<String> list = iterator.next();
                if("END".equalsIgnoreCase(list.get(0))) {
                    if(tempTable == null){
                        break;
                    }
                    Table table = tempTable.clone();
                    tableList.add(table);
                    break;
                }

                String columnName = list.size() > 0 ? list.get(0) : "";
                //PanelLog.log(columnName);
                String type = list.size() > 1 ? list.get(1) : "";
                String commont = list.size() > 2 ? list.get(2) : "";
                String nullableStr = list.size() > 3 ? list.get(3) : "";
                String defaultV = list.size() > 4 ? list.get(4) : "";

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
                    tempParamList.add(new MyParam(commont, columnName, type, nullableStr, defaultV));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tableList;
    }
}

