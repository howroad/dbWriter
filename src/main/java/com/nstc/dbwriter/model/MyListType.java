package com.nstc.dbwriter.model;

import java.sql.Types;

/**
 * <p>Title: MyListType.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午4:13:26
 * 
 */
public class MyListType extends MyType {

    public MyListType(String paramTypeName) {
        super(paramTypeName);
        setParamTypeName("List<" + paramTypeName + ">");
        setValue(Types.JAVA_OBJECT);
        setColumnTypeName("集合");
    }
    

    
}
