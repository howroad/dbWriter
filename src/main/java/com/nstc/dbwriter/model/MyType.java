package com.nstc.dbwriter.model;

import java.sql.Types;

/**
 * <p>Title: MyType.java</p>
 *
 * <p>Description: </p>
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
    
    private int columnSize;
    
    public MyType(int dateType, int columnSize , int decimalDigits) {
        this.value = dateType;
        if (Types.DECIMAL == dateType) {
            if (isInteger(columnSize, decimalDigits)) {
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
            this.columnSize = columnSize;
        } else if (Types.TIMESTAMP == dateType) {
            paramTypeName = "Date";
            //columnTypeName = "TIMESTAMP";
            columnTypeName = "DATE";
        } else if(Types.DATE == dateType) {
            paramTypeName = "Date";
            columnTypeName = "DATE";
        } else if(Types.CHAR == dateType){
            paramTypeName = "String";
            columnTypeName = "CHAR(" + columnSize + ")";
        }else if(Types.BLOB == dateType) {
            paramTypeName = "byte[]";
            columnTypeName = "BLOB";
        }
        else {
            throw new RuntimeException("未知类型！");
        }
    }
    
    private boolean isInteger(int columnSize,int decimalDigits) {
        return decimalDigits == 0 || decimalDigits == -127 && columnSize == 0;
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

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

}
