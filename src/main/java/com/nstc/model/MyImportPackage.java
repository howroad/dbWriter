package com.nstc.model;

import java.util.ArrayList;
import java.util.List;

import com.nstc.data.TableContans;

/**
 * <p>Title: MyImportPackage.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 下午1:30:05
 * 
 */
public class MyImportPackage extends FileWriter {
    
    private final static String IMPORT = "import ";
    
    private List<String> packageList = new ArrayList<String>();
    
    public void addPackage(String packageName) {
        if(packageList.contains(packageName)) {
            return;
        }
        packageList.add(packageName);
        addLine(new StringBuffer(IMPORT).append(packageName).append(TableContans.SEMC));
    }
    
    public List<String> getPackageList() {
        return packageList;
    }
    
}
