package com.nstc.util;
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
}
