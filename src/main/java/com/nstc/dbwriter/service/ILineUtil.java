package com.nstc.dbwriter.service;

import com.nstc.dbwriter.model.Table;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: LineService.java</p>
 * <p>Description: 行级服务</p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-05 15:13
 */
public interface ILineUtil {

    /**
     * 将模版文件转换正确的文件
     * @param lineList
     * @param table
     * @return 正确的行集合
     */
    List<String> buildNewLine(List<String> lineList, Table table);

    /**
     * 根据token替换字符串
     * @param template old字符串
     * @param tokens
     * @param type
     * @return 替换后的字符串
     */
    String replaceTemplet(String template, Map<String,String> tokens, String type);
}
