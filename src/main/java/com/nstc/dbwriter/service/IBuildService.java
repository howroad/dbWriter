package com.nstc.dbwriter.service;

import com.nstc.dbwriter.model.Table;

import java.util.List;

/**
 * <p>Title: ITableService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 13:38
 */
public interface IBuildService {
    List<Table> buildTableFromNames(List<String> tbNames);
    Table buildTableFromName(String tbName);
    List<Table> buildTableFromExcel(int sheetNo);


}
