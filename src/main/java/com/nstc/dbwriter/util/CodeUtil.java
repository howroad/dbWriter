package com.nstc.dbwriter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.nstc.dbwriter.config.CommonSettings;
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
    
    public static List<String> buildNewLine(List<String> lineList,Table table) {
        List<String> result = new ArrayList<String>();
    	/** 循环标识 */
    	boolean loop = false;
    	/** 是否取消第一个 */
    	boolean clearFirst = false;
    	/** 循环体 */
        List<String> loopLine = new ArrayList<String>();
        
        boolean delete = false;
        
        String outStr = null;
    	for (String line : lineList) {
    	    if(line.matches("^.*\\@\\{\\/delete\\}.*$")) {
                delete = false;
                continue;
            }
    	    else if(line.matches("^.*\\@\\{delete\\}.*$")) {
    	        delete = true;
    	        continue;
    	    }
    	    else if(delete && !CommonSettings.usePage) {
                continue;
            }
    		//循环种
    	    else if(loop) {
    			// 遇到结束标识 解析循环体中的模板
    			if(line.matches("^.*\\@\\{end\\}.*$")) {
    				loop = false;
    				for (ListIterator<MyParam> iterator = table.getParamList().listIterator(); iterator.hasNext();) {
    				    // 清除第一个属性信息
                        if(!iterator.hasPrevious() && clearFirst) {
                            iterator.next();
                            continue;
                        }
    				    MyParam param = iterator.next();
                        for (String string : loopLine) {
                            outStr = replaceTemplet(string, param.getMap(), "param");
                            outStr = replaceTemplet(outStr, table.getMap(), "table");
                            //将date 变为 SYSDATE
                            //outStr = outStr.replaceAll("#(updateDate|updateTime)#", "SYSDATE");
                            if(iterator.hasNext()) {
                                outStr = replaceTemplet(outStr, CommonSettings.map, "split");
                            }else {
                                outStr = replaceTemplet(outStr, CommonSettings.lastMap, "split");
                            }
                            result.add(outStr);
                        }                       
                    }
    			// 不是结束标识，继续添加循环体
    			}else {
    				loopLine.add(line);
    			}
    		// 开始进入循环体，设置标识	
    		}else if(line.matches("^.*\\@\\{start\\}.*$")) {
    			loopLine.clear();
				loop = true;
				clearFirst = false;
    		}else if(line.matches("^.*\\@\\{startFrom2\\}.*$")) {
                loopLine.clear();
                loop = true;
                clearFirst = true;
			// 不是循环，解析模板	
			} else {
				outStr = replaceTemplet(line, CommonSettings.map, "common");
				outStr = replaceTemplet(outStr, table.getMap(), "table");
				result.add(outStr);
			}
		}
    	return result;
    }
    
    public static String replaceTemplet(String template,Map<String,String> tokens,String type) {

        //生成匹配模式的正则表达式 
        String patternString = "\\$"+ type + "\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}"; 

        Pattern pattern = Pattern.compile(patternString); 
        Matcher matcher = pattern.matcher(template); 

        //两个方法：appendReplacement, appendTail 
        //appendReplacement方法：sb是一个StringBuffer，replaceContext待替换的字符串，这个方法会把匹配到的内容替换为replaceContext，并且把从上次替换的位置到这次替换位置之间的字符串也拿到，然后，加上这次替换后的结果一起追加到StringBuffer里（假如这次替换是第一次替换，那就是只追加替换后的字符串啦）。
        StringBuffer sb = new StringBuffer(); 
        while(matcher.find()) { 
            String value = tokens.get(matcher.group(1)) == null ? " " : tokens.get(matcher.group(1));
            matcher.appendReplacement(sb, value); 
        } 
        // 把最后一次匹配到内容之后的字符串追加到StringBuffer中
        matcher.appendTail(sb); 
        
        return sb.toString();
    }
}
