package com.nstc.dbwriter.util;

import java.util.List;

/**
 * <p>Title: ValidateUtil.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:13:15
 * 
 */
public class ValidateUtil {
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
}
