package com.nstc.dbwriter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nstc.dbwriter.model.MapContent;
import com.nstc.dbwriter.model.MyParam;
import com.nstc.dbwriter.model.Table;

/**
 * <p>Title: CodeUtil.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月14日 下午3:34:06
 * 
 */
public class CodeUtil {
    private CodeUtil() {}
    public static List<String> writeTemplet(List<String> lineList,Table table){
        ValidateUtil.checkEmpty(lineList);
        List<String> result = new ArrayList<String>();
        for (String line : lineList) {
            ValidateUtil.checkEmpty(line);
            String str = null;
            if(line.indexOf("#{") >= 0) {
                str = replaceTableInfo(line, table);
            }else if(line.indexOf("@{") >= 0) {
                for (MyParam param : table.getParamList()) {
                    str = replaceTableInfo(line, param);
                }
            }
            result.add(str);
        }
        return result;
    }
    public static String replaceTableInfo(String str,MapContent content) {
        ValidateUtil.checkNull(content);
        Map<String, String> map = content.getMap();
        String result = str;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
