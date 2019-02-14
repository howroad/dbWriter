package com.nstc.dbwriter.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.nstc.dbwriter.config.DbSettings;
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
    public static void main(String[] args) {
    	List<String> lineList = new ArrayList<String>();
    	lineList.add("package #{groupId}.#{appNo}.${poPackage};");
    	lineList.add("${import}");
    	lineList.add("/** ${remark} */");
    	lineList.add("public class $table{entityName} {");
    	lineList.add("@{start}");
    	lineList.add("/** $param{paramRemark} */");
    	lineList.add("private $param{paramType} $param{paramName};");
    	lineList.add("$common{line}");
    	lineList.add("@{end}");
        Map<String,String> tokens = new HashMap<String,String>(); 
        tokens.put("groupId", "com.nstc"); 
        tokens.put("appNo", "temp");
        tokens.put("poPackage", "model");
	}
    
    public static void writeTemplet(List<String> lineList,Table table,PrintWriter out) {
    	/** 循环标识 */
    	boolean loop = false;
    	/** 循环体 */
        List<String> loopLine = new ArrayList<String>();
        String outStr = null;
    	for (String line : lineList) {
    		//循环种
    		if(loop) {
    			// 遇到结束标识 解析循环体中的模板
    			if(line.matches("^.*\\@\\{end\\}$")) {
    				loop = false;
    				for (MyParam param : table.getParamList()) {
        				for (String string : loopLine) {
    						outStr = writeTemplate(string, param.getMap(), "param");
    						out.println(outStr);
    					}
					}
    			// 不是结束标识，继续添加循环体
    			}else {
    				loopLine.add(line);
    			}
    		// 开始进入循环体，设置标识	
    		}else if(line.matches("^.*\\@\\{start\\}$")) {
    			loopLine.clear();
				loop = true;
			// 不是循环，解析模板	
			} else {
				outStr = writeTemplate(line, DbSettings.map, "common");
				outStr = writeTemplate(line, table.getMap(), "table");
				out.println(outStr);
			}
		}
    }
    
    public static String writeTemplate(String template,Map<String,String> tokens,String type) {

        //生成匹配模式的正则表达式 
        String patternString = "\\$"+ type + "\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}"; 

        Pattern pattern = Pattern.compile(patternString); 
        Matcher matcher = pattern.matcher(template); 

        //两个方法：appendReplacement, appendTail 
        //appendReplacement方法：sb是一个StringBuffer，replaceContext待替换的字符串，这个方法会把匹配到的内容替换为replaceContext，并且把从上次替换的位置到这次替换位置之间的字符串也拿到，然后，加上这次替换后的结果一起追加到StringBuffer里（假如这次替换是第一次替换，那就是只追加替换后的字符串啦）。
        StringBuffer sb = new StringBuffer(); 
        while(matcher.find()) { 
            matcher.appendReplacement(sb, tokens.get(matcher.group(1))); 
        } 
        // 把最后一次匹配到内容之后的字符串追加到StringBuffer中
        matcher.appendTail(sb); 
        
        return sb.toString();
    }
}
