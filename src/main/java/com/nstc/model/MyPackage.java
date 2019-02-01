package com.nstc.model;

import com.nstc.data.DbSettings;
import com.nstc.data.TableContans;
import com.nstc.util.ValidateUtil;

/**
 * <p>Title: MyPackage.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:08:18
 * 
 */
public class MyPackage extends FileWriter{
    private String prefix;
    private String appNo;
    public MyPackage() {
        init();
    }
    private void init() {
        
        ValidateUtil.checkEmpty(DbSettings.prefix);
        ValidateUtil.checkEmpty(DbSettings.appNo);
        
        prefix = DbSettings.prefix.toLowerCase();
        appNo = DbSettings.appNo.toLowerCase();
        
        StringBuffer sb = new StringBuffer(prefix).append(TableContans.DOT).
                append(appNo).append(TableContans.DOT).append(TableContans.MODELSTR).
                append(TableContans.SEMC);
        addLine(sb);
    }
    
}
