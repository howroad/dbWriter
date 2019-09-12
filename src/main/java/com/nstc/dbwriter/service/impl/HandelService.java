package com.nstc.dbwriter.service.impl;

import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.service.IHandelService;
import com.nstc.dbwriter.service.ServerLocator;
import com.nstc.frame.panel.JoinSqlLine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: SqlServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 13:21
 */
public class HandelService extends ServerLocator implements IHandelService {

    @Override
    public File createJoinSql(List<JoinSqlLine> lineList, File templet, String sqlId) {
        return null;
    }

    @Override
    public List<Table> getTable(List<JoinSqlLine> lineList) {
        final List<String> tableNames = getTableNames(lineList);
        return tableService.buildTableFromNames(tableNames);
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
        return null;
    }

    @Override
    public List<String> getDDL(String tableName) {
        return null;
    }

    @Override
    public List<String> getAllDate(String tableName, int size) {
        return null;
    }

    @Override
    public List<String> getDate(Table table, String sql, String[] primaryColUpKeys, String filName) {
        return null;
    }

    @Override
    public List<String> getDateByDate(List<List<Object>> dataList) {
        return null;
    }


}
