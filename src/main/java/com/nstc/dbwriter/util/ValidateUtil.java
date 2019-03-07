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
    //TODO 以后可以使用番石榴
    public static void checkEmpty(String str) {
        if(str == null || str.length() == 0) {
            throw new RuntimeException("参数值（" + str + "）不合法！");
        }
    }
    public static void checkEmpty(List<?> list) {
        if(list == null || list.isEmpty()) {
            throw new RuntimeException("参数值（" + list + "）不合法！");
        }
    }
    public static void checkNull(Object obj) {
        if(obj == null) {
            throw new RuntimeException("参数值不合法！");
        }
    }
    public static void checkVersion() {
        String version = System.getProperty("java.specification.version");
        String reg = "(1\\.8|9|10|11|12)";
        if(!version.matches(reg)) {
            throw new RuntimeException("java版本必须大于1.8，当前版本：" + version);
        }
    }
    public static void main(String[] args) {
        checkVersion();
    }
}
