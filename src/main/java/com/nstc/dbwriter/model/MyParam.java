package com.nstc.dbwriter.model;

import java.util.Map;

import javax.lang.model.element.Modifier;

import com.nstc.dbwriter.config.TableContans;

/**
 * <p>Title: MyParam.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:38:11
 * 
 */
public class MyParam extends FileWriter implements MapContent{
    private String modifier = Modifier.PRIVATE.toString();
    private MyType type;
    private String paramName;
    private String paramRemark;
    private Map<String, String> map;
    
    public MyParam(String paramName,String paramRemark,int dateType, int columnSize , int decimalDigits) {
        super();
        this.type = new MyType(dateType, columnSize, decimalDigits);
        this.paramName = paramName;
        this.paramRemark = paramRemark;
        StringBuffer comment = new StringBuffer("/** ").append(paramRemark).append(" */");
        addLineTab(comment);
        StringBuffer line = new StringBuffer(modifier).append(TableContans.SPACE)
                .append(type.getParamTypeName()).append(TableContans.SPACE).append(paramName)
                .append(TableContans.SEMC);
        addLineTab(line);
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
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

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
    
}
