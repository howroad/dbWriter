package com.nstc.dbwriter.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.nstc.dbwriter.config.TableContans;

/**
 * <p>Title: FileWriter.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:20:29
 * 
 */
public class FileWriter {
    
    protected List<String> lineList = new ArrayList<String>();
    
    public List<String> getLineList(){
        return this.lineList;
    }
    
    protected void addLine(String line) {
        lineList.add(line);
    }
    protected void addLine(StringBuffer line) {
        lineList.add(line.toString());
    }
    protected void addLineTab(String line) {
        lineList.add(TableContans.TAB + line);
    }
    protected void addLineTab(StringBuffer line) {
        lineList.add(TableContans.TAB + line.toString());
    }
    protected void addLineTabTab(String line) {
        lineList.add(TableContans.TABTAB + line);
    }
    protected void addLineTabTab(StringBuffer line) {
        lineList.add(TableContans.TABTAB + line.toString());
    }
    protected void addLine(List<String> line) {
        lineList.addAll(line);
    }
    protected void addLineTab(List<String> line) {
        for (String string : line) {
            lineList.add(TableContans.TAB + string);
        }
    }
    protected void addLineTabTab(List<String> line) {
        for (String string : line) {
            lineList.add(TableContans.TABTAB + string);
        }
    }
    public void writeLineList(PrintStream out) {
        for (String string : lineList) {
            out.println(string);
        }
    }
}
