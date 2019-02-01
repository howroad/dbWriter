package com.nstc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.nstc.data.TableContans;

/**
 * <p>Title: MyFunction.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午2:53:35
 * 
 */
public class MyFunction extends FileWriter {
    private String modifier = Modifier.PUBLIC.toString();
    private String returnType;
    private String functionName;
    private List<MyParam> paramList; 
    private FileWriter functionBody;
    
    public MyFunction(String returnType, String functionName,List<MyParam> paramList, FileWriter functionBody) {
        super();
        this.returnType = returnType;
        this.functionName = functionName;
        this.paramList = paramList;
        this.functionBody = functionBody;
        StringBuffer sm = new StringBuffer(modifier).append(TableContans.SPACE)
                .append(returnType).append(TableContans.SPACE)
                .append(functionName).append(TableContans.SPACE)
                .append("(");
        for (Iterator<MyParam> iterator = paramList.iterator(); iterator.hasNext();) {
            MyParam myParam = iterator.next();
            sm.append(myParam.getType().getParamTypeName()).append(TableContans.SPACE)
            .append(myParam.getParamName());
            if(iterator.hasNext()) {
                sm.append(", ");
            }
        }
        sm.append(") {");
        addLineTab(sm);
        addLineTabTab(functionBody.getLineList());
        addLineTab("}");
    }
    public MyFunction(String returnType, String functionName,MyParam param, FileWriter functionBody) {
        super();
        this.returnType = returnType;
        this.functionName = functionName;
        this.paramList = new ArrayList<MyParam>();
        paramList.add(param);
        this.functionBody = functionBody;
        StringBuffer sm = new StringBuffer(modifier).append(TableContans.SPACE)
                .append(returnType).append(TableContans.SPACE)
                .append(functionName).append(TableContans.SPACE)
                .append("(");
        for (Iterator<MyParam> iterator = paramList.iterator(); iterator.hasNext();) {
            MyParam myParam = iterator.next();
            sm.append(myParam.getType().getParamTypeName()).append(TableContans.SPACE)
            .append(myParam.getParamName());
            if(iterator.hasNext()) {
                sm.append(", ");
            }
        }
        sm.append(") {");
        addLineTab(sm);
        addLineTabTab(functionBody.getLineList());
        addLineTab("}");
    }
    
    public String getModifier() {
        return modifier;
    }
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public String getReturnType() {
        return returnType;
    }
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    public List<MyParam> getParamList() {
        return paramList;
    }
    public void setParamList(List<MyParam> paramList) {
        this.paramList = paramList;
    }
    public FileWriter getFunctionBody() {
        return functionBody;
    }
    public void setFunctionBody(FileWriter functionBody) {
        this.functionBody = functionBody;
    }
    public String getFunctionName() {
        return functionName;
    }
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    
    
}
