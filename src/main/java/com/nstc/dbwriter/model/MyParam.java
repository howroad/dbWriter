package com.nstc.dbwriter.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.CaseFormat;
import com.nstc.dbwriter.config.TableContans;

/**
 * <p>Title: MyParam.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:38:11
 * 
 */
public class MyParam implements MapContent{
    
    private String paramName;
    private String paramRemark;
    private String columnName;
    private MyType type;
    private Map<String, String> map = new HashMap<String, String>();
    
    public MyParam(String paramName,String columnName, String paramRemark,int dateType, int columnSize , int decimalDigits) {
        super();
        this.type = new MyType(dateType, columnSize, decimalDigits);
        this.paramName = paramName;
        this.paramRemark = paramRemark;
        this.columnName = columnName;
        init();
    }
    
    public MyParam(String paramRemark, String columnName,String typeStr) {
        super();
        this.paramRemark = paramRemark;
        this.columnName = columnName.toUpperCase();
        this.paramName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        String columnType = typeStr.trim().toUpperCase();
        Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(typeStr);
        if(TableContans.DATE.equals(columnType)) {
            this.type = new MyType(Types.DATE,0,0);
        }else if(TableContans.TIMESTAMP.equals(columnType)) {
            this.type = new MyType(Types.TIMESTAMP,0,0);
        }else if(columnType.startsWith(TableContans.NUMBER)) {
            if(mat.find()) {
                String inner = mat.group();
                this.type = new MyType(Types.DECIMAL,new Integer(inner.split(",")[0]),new Integer(inner.split(",")[1]));
            }else {
                this.type = new MyType(Types.DECIMAL,0,0);
            }
        }else if(columnType.startsWith(TableContans.VARCHAR2)) {
            if(mat.find()) {
                this.type = new MyType(Types.VARCHAR,new Integer(mat.group()),0);
            }else {
                throw new RuntimeException("类型错误");
            }
        }else if(columnType.startsWith(TableContans.INTEGER)) {
            this.type = new MyType(Types.DECIMAL,0,0);
        }else if(columnType.startsWith(TableContans.BLOB)) {
            this.type = new MyType(Types.BLOB,0,0);
        }
        else {
            throw new RuntimeException("未知类型 : " + columnType);
        }
        init();
    }

    private void init() {
        map.put("paramName", this.paramName);
        map.put("paramRemark", this.paramRemark);
        map.put("paramType", this.type.getParamTypeName());
        map.put("bigName", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, this.paramName));
        map.put("columnName",this.columnName);
        map.put("columnType",this.type.getColumnTypeName());
        map.put("columnSize",String.valueOf(this.type.getColumnSize()));
    }

    public MyType getType() {
        return type;
    }

    public void setType(MyType type) {
        this.type = type;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamRemark() {
        return paramRemark;
    }

    public void setParamRemark(String paramRemark) {
        this.paramRemark = paramRemark;
    }
    @Override
    public Map<String, String> getMap() {
        return map;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    
}
