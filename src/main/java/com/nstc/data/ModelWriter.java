package com.nstc.data;

import java.io.File;

/**
 * <p>Title: ModelWriter.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 上午11:23:34
 * 
 */
public class ModelWriter {
    
    private Table table;
    
    private File file;
    
    public ModelWriter(Table table, File file) {
        super();
        this.table = table;
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
}
