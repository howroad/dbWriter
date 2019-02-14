package com.nstc.dbwriter.model;

import java.sql.Types;

import com.google.common.base.CaseFormat;

/**
 * <p>Title: Line.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月14日 下午3:38:58
 * 
 */
public class Line{
    private String columnName;
    private String paramName;
    private int columnType;
    private int decimalDigits;
    private String remark;
    private String columnTypeName;
    private final static String DATE = "DATE";
    private final static String TIMESTAMP = "TIMESTAMP";
    private final static String NUMBER = "NUMBER";
    private final static String VARCHAR2 = "VARCHAR2";
    private final static String PARENTHE= "(";
    
    public String paramLine() {
        StringBuffer sb = new StringBuffer("private ");
        paramName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        sb.append(getParamType()).append(paramName).append(";");
        return sb.toString();
    }
    public String commentLine() {
        StringBuffer sb = new StringBuffer("/** ");
        sb.append(remark).append(" */");
        return sb.toString();
    }

    public Line(String columnName, String paramName, int columnType, int decimalDigits, String remark) {
        super();
        this.columnName = columnName.toUpperCase();
        this.paramName = paramName;
        this.columnType = columnType;
        this.decimalDigits = decimalDigits;
        this.remark = remark == null || "null".equals(remark) ? "" : remark; 
    }
    
    public Line(String columnName, String type, String remark) {
        super();
        this.columnName = columnName.toUpperCase();
        this.remark = remark == null || "null".equals(remark) ? "" : remark; 
        this.columnTypeName = type.toUpperCase();
        this.paramName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        String columnType = type.trim().toUpperCase();
        if(DATE.equals(columnType)) {
            this.columnType = Types.DATE;
            this.decimalDigits = 0;
        }else if(TIMESTAMP.equals(columnType)) {
            this.columnType = Types.TIMESTAMP;
            this.decimalDigits = 0;
        }else if(columnType.startsWith(NUMBER)) {
            if(!columnType.contains(PARENTHE)) {
                this.columnType = Types.DECIMAL;
                this.decimalDigits = 0;
            }else {
                this.columnType = Types.DECIMAL;
                this.decimalDigits = 2;
            }
        }else if(columnType.startsWith(VARCHAR2)) {
            this.columnType = Types.VARCHAR;
            this.decimalDigits = 0;
        }else {
            throw new RuntimeException("未知类型！");
        }
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getColumnTypeName() {
        return columnTypeName;
    }
    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }
    public String getSharpName(){
        return "#" + this.paramName + "#";
    }
    public String getParamType() {
        String paramType = null;
        if (Types.DECIMAL == columnType) {
            if (decimalDigits == 0) {
                paramType = "Integer ";
            } else {
                paramType = "Double ";
            }
        } else if (Types.VARCHAR == columnType) {
            paramType = "String ";
        } else if (Types.TIMESTAMP == columnType || Types.DATE == columnType) {
            paramType = "Date ";
        } else {
            throw new RuntimeException("未知类型！");
        }
        return paramType;
    }
    @Override
    public String toString() {
        return "Line [columnName=" + columnName + ", paramName=" + paramName + ", columnType=" + columnType
                + ", decimalDigits=" + decimalDigits + ", remark=" + remark + "]";
    }
}
