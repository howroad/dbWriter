package com.nstc.dbwriter.model;

import java.sql.Types;

/**
 * <p>Title: MyType.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:52:51
 * 
 */
public class MyType {
    
    private String paramTypeName;
    
    private String columnTypeName;
    
    private int value;
    
    private int precision;
    
    private int scale;
    
    public MyType(int dateType, int columnSize , int decimalDigits) {
        this.value = dateType;
        if (Types.DECIMAL == dateType) {
            if (decimalDigits == 0 || decimalDigits == -127 && columnSize == 0) {
                //有时候有可能会出现解析失去精度
                paramTypeName = "Integer";
                columnTypeName = "NUMBER";
            } else {
                paramTypeName = "Double";
                columnTypeName = "NUMBER(" + columnSize + "," + decimalDigits + ")";
            }
        } else if (Types.VARCHAR == dateType) {
            paramTypeName = "String";
            columnTypeName = "VARCHAR2(" + columnSize + ")";
        } else if (Types.TIMESTAMP == dateType) {
            paramTypeName = "Date";
            columnTypeName = "TIMESTAMP";
        } else if(Types.DATE == dateType) {
            paramTypeName = "Date";
            columnTypeName = "Date";
        } else {
            throw new RuntimeException("未知类型！");
        }
    }
    

    public MyType(String paramTypeName) {
        super();
        this.paramTypeName = paramTypeName;
        this.value = Types.JAVA_OBJECT;
        this.columnTypeName = "Java对象";
    }


    public String getParamTypeName() {
        return paramTypeName;
    }


    public void setParamTypeName(String paramTypeName) {
        this.paramTypeName = paramTypeName;
    }


    public String getColumnTypeName() {
        return columnTypeName;
    }


    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }


    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }


    public int getPrecision() {
        return precision;
    }


    public void setPrecision(int precision) {
        this.precision = precision;
    }


    public int getScale() {
        return scale;
    }


    public void setScale(int scale) {
        this.scale = scale;
    }

}