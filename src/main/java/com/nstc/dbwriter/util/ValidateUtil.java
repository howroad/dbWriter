package com.nstc.dbwriter.util;

import java.util.List;

/**
 * <p>Title: ValidateUtil.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:13:15
 * 
 */
public class ValidateUtil {

    public static void notEmpty(List<?> list) {
        if(list == null || list.isEmpty()) {
            throw new RuntimeException("参数值不能为空！");
        }
    }
    
    public static void exsit(String path) {


    }
    
    public static void checkVersion() {
        String version = System.getProperty("java.specification.version");
        String reg = "(1\\.8|9|10|11|12)";
        if(!version.matches(reg)) {
            throw new RuntimeException("java版本必须大于1.8，当前版本：" + version);
        }
    }
    
    public static boolean projectIsJar() {
        String str = System.getProperty("java.class.path");
        return str.endsWith(".jar");
    }
}
