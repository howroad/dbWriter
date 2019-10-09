package com.nstc.dbwriter.service.impl;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.model.MyParam;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.service.Container;
import com.nstc.dbwriter.service.IDatabaseService;
import com.nstc.dbwriter.util.DataUtil;
import com.nstc.frame.panel.JoinSqlLine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.lang.System.out;

/**
 * <p>Title: SqlServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 13:21
 */
public class DatabaseServiceImpl implements IDatabaseService {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File createJoinSql(List<JoinSqlLine> lineList, File templet, String sqlId) {
        return null;
    }

    @Override
    public List<Table> getTable(List<JoinSqlLine> lineList) {
        final List<String> tableNames = getTableNames(lineList);
        return Container.buildService.buildTableFromNames(tableNames);
    }

    @Override
    public List<String> getTableNames(List<JoinSqlLine> lineList) {
        List<String> tableNames = new ArrayList<String>(16);
        lineList.forEach(joinSqlLine -> {
            String tb1 = joinSqlLine.getTb1().getText();
            String tb2 = joinSqlLine.getTb2().getText();
            if(StringUtils.isNotBlank(tb1)){
                tableNames.add(tb1);
            }
            if(StringUtils.isNotBlank(tb2)){
                tableNames.add(tb2);
            }
        });
        return tableNames;
    }

    @Override
    public List<List<Object>> getDateBySql(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<List<Object>> resultList = new ArrayList<List<Object>>();
        try {
            conn = DriverManager.getConnection(CommonSettings.URL, CommonSettings.USER, CommonSettings.PASSWORD);
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
            throw new RuntimeException(e.getMessage());
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

    @Override
    public String getDDL(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select dbms_metadata.get_ddl('TABLE','" + tableName + "') from dual";
        String result = null;
        try {
            conn = DriverManager.getConnection(CommonSettings.URL, CommonSettings.USER, CommonSettings.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clob clob = rs.getClob(1);
                result = DataUtil.clobToString(clob);
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

    @Override
    public List<String> getAllDate(String tableName, int size) {
        String sql = "SELECT * FROM " + tableName.toUpperCase() + " WHERE ROWNUM <= " + size + " ORDER BY 1 ASC";
        List<List<Object>> dataList = getDateBySql(sql);
        Table table = Container.buildService.buildTableFromName(tableName);
        return this.dataToLine(table,dataList);
    }

    @Override
    public List<String> getDate(Table table, String sql, String[] primaryColUpKeys, String filName) {
        List<String> resultList =  new ArrayList<String>();
        List<List<Object>> dataList = this.getDateBySql(sql);
        String[] primaryKeyValues = new String[primaryColUpKeys.length];
        for (List<Object> list : dataList) {
            resultList.add("INSERT INTO " + table.getTableName() + "(");
            for (ListIterator<MyParam> iterator = table.getParamList().listIterator(); iterator.hasNext();) {
                MyParam param = iterator.next();
                String columnName = param.getColumnName();
                if (iterator.hasNext()) {
                    resultList.add(columnName + ",");
                } else {
                    resultList.add(columnName + ") ");
                }
            }
            resultList.add("SELECT ");
            int index = 0;
            for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
                boolean last = false;
                Object object = iterator.next();
                MyParam param = table.getParamList().get(index++);
                String columnName = param.getColumnName();
                String value = table.getInsertValue(object, param);
                for (int i = 0; i < primaryKeyValues.length; i++) {
                    if(columnName.equals(primaryColUpKeys[i])) {
                        primaryKeyValues[i] = value;
                    }
                }
                last = !iterator.hasNext();
                if(last) {
                    resultList.add(value);
                }else {
                    resultList.add(value + ",");
                }
            }
            StringBuffer whereStr = new StringBuffer(128);
            for (int i = 0; i < primaryColUpKeys.length; i++) {
                String primaryColUpKey = primaryColUpKeys[i];
                String primaryKeyValue = primaryKeyValues[i];
                if(i > 0) {
                    whereStr.append(" AND ");
                }
                whereStr.append(primaryColUpKey + " = " + primaryKeyValue);
            }
            resultList.add(" FROM DUAL ");
            resultList.add("WHERE NOT EXISTS (SELECT 1 FROM " + table.getTableName() + " WHERE " + whereStr + ")");
            resultList.add("/");

        }
        return resultList;
    }

    @Override
    public List<String> dataToLine(Table table, List<List<Object>> dataList) {
        List<String> resultList = new ArrayList<String>();
        String primaryKeyColumnName = table.getParamList().get(0).getColumnName();
        for (List<Object> list : dataList) {
            String primaryKeyValue = String.valueOf(list.get(0));
            resultList.add("INSERT INTO " + table.getTableName() + "(");
            for (ListIterator<MyParam> iterator = table.getParamList().listIterator(); iterator.hasNext();) {
                MyParam param = iterator.next();
                if (iterator.hasNext()) {
                    resultList.add(param.getColumnName() + ",");
                } else {
                    resultList.add(param.getColumnName() + ") ");
                }
            }
            resultList.add("SELECT ");
            int index = 0;
            for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
                boolean first = false;
                boolean last = false;
                first = !iterator.hasPrevious();
                Object object = iterator.next();
                String value = table.getInsertValue(object, table.getParamList().get(index++));
                last = !iterator.hasNext();
                boolean use = false;//此处不应有序列
                if(last) {
                    resultList.add(value);
                }else {
                    resultList.add(value + ",");
                }
            }
            resultList.add(" FROM DUAL ");
            resultList.add("WHERE NOT EXISTS (SELECT 1 FROM " + table.getTableName() + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "')");
            resultList.add("/");
        }
        return resultList;
    }
}
