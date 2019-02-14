
package com.nstc.dbwriter.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.CaseFormat;
import com.nstc.dbwriter.config.TableContans;

/**
 * 
 * <p>Title: MyClass.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:04:03
 *
 */
public class MyClass extends FileWriter{
    private MyPackage myPackage = new MyPackage();
    private MyImportPackage myImportPackage = new MyImportPackage();
    private String name;
    private List<MyParam> myParamList;
    private String remarkName;
    //TODO 以后再实现
    private List<MyFunction> myFunctionList;
    private Table table ;
    public void write() {
        PrintStream out = System.out;
        myPackage.writeLineList(out);
        myImportPackage.writeLineList(out);
        out.println("/** " + remarkName + " */");
        out.println("public class " + name + "{");
        for (MyParam myParam : myParamList) {
            myParam.writeLineList(out);
        }
        for (MyFunction myFunction : myFunctionList) {
            myFunction.writeLineList(out);
        }
        out.println("}");
        
    }
    
    public MyClass(String name, List<MyParam> myParamList,Table table) {
        this.name = table.getEntityName();
        this.myParamList = myParamList;
        this.table = table;
        this.remarkName = table.getTableRemart();
        buildSeterAndGeter();
    }

    void buildSeterAndGeter() {
        this.myFunctionList = new ArrayList<MyFunction>();
        for (MyParam myParam : myParamList) {
            StringBuffer sb = new StringBuffer("this.").append(myParam.getParamName()).append(" = ").append(myParam.getParamName()).append(TableContans.SEMC);
            FileWriter body = new FileWriter();
            body.addLine(sb);
            MyFunction function = new MyFunction("void", "set" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getTableName()),
                    myParam, body) ;
            myFunctionList.add(function);
        }
    }
    public MyPackage getMyPackage() {
        return myPackage;
    }

    public void setMyPackage(MyPackage myPackage) {
        this.myPackage = myPackage;
    }

    public MyImportPackage getMyImportPackage() {
        return myImportPackage;
    }

    public void setMyImportPackage(MyImportPackage myImportPackage) {
        this.myImportPackage = myImportPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyParam> getMyParamList() {
        return myParamList;
    }

    public void setMyParamList(List<MyParam> myParamList) {
        this.myParamList = myParamList;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }


    
}
