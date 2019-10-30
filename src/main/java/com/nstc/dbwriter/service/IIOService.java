package com.nstc.dbwriter.service;

import com.nstc.dbwriter.model.Table;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * <p>Title: IIOService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 14:04
 */
public interface IIOService {

    List<String> readToLine(InputStream ins);
    List<String> readToLine(File file);
    List<String> readToLine(String path);

    void write(File file, List<String> lineList);
    void write(String path, List<String> lineList);
    void write(File file, String line);
    void write(String path, String line);

    void writeFileByTemplet(File templet,File outFile, Table table);
    void writeFileByTemplet(InputStream ins,File outFile, Table table);

    void writeJavaBean(Table table);
    void writeAllTemplet(Table table,String path,String templetDir);
    void writeAllTempletFromDir(Table table,String path,String templetDir);
    List<JarEntry> readAllTempletJarEntry();
    JarEntry getJarEntry(String entryName);
    void writeTempletByEntry_DefaultPath(Table table,String path, JarEntry jarEntry);
    void writeTempletByEntry(Table table,String path, JarEntry jarEntry, File outPath);
    void writeAllTempletFromJar(Table table, String path);

    int getLastKeyLineNum(List<String> lineList,String key);

    void reWriteFileByList(File file, List<String> list, Table table, int fileType);
    void writeCommonFileByTemplet(Table table);
    void writeDataFile(Table table);
    void writeDataFile(Table table, String sql, String[] primaryColUpKeys, String filName);

    void clear();
    void clearDir(File dir);
    void reBuildCommonFile();
    void clearAndRebuild();
}
