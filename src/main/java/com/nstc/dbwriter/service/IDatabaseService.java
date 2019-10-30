package com.nstc.dbwriter.service;

import com.nstc.dbwriter.model.Table;
import com.nstc.frame.panel.JoinSqlLine;

import java.io.File;
import java.util.List;

/**
 * <p>Title: ISqlService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 11:49
 */
public interface IDatabaseService {
    File createJoinSql(List<JoinSqlLine> lineList, File templet, String sqlId);
    List<Table> getTable(List<JoinSqlLine> lineList);
    List<String> getTableNames(List<JoinSqlLine> lineList);

    /**
     * 通过sql查询数据
     * @param sql
     * @return
     */
    List<List<Object>> query(String sql);

    /**
     * 获得数据库默认的DDL
     * @param tableName
     * @return
     */
    String defaultDDL(String tableName);

    List<String> getDate(Table table,String sql,String[] primaryColUpKeys,String filName);
    List<String> dataToLine(Table table, List<List<Object>> dataList);
    List<Table> buildTableFromNames(List<String> tbNames);
    Table buildTableFromName(String tbName);
    List<Table> buildTableFromExcel(int sheetNo);


}
