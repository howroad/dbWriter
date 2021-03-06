package com.nstc.dbwriter.service.impl;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.TempletConstants;
import com.nstc.dbwriter.model.Table;
import com.nstc.dbwriter.service.Container;
import com.nstc.dbwriter.service.IIOService;
import com.nstc.dbwriter.util.LineUtil;
import com.nstc.dbwriter.util.ValidateUtil;
import org.apache.commons.lang3.Validate;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <p>Title: IOServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-12 16:46
 */
public class IOServiceImpl implements IIOService {

    @Override
    public List<String> readToLine(InputStream ins) {
        List<String> lineList = new ArrayList<String>();
        BufferedReader in = null ;
        try {
            in = new BufferedReader(new InputStreamReader(ins,TempletConstants.INPUT_CODE));
            String line = null;
            while((line = in.readLine()) != null) {
                lineList.add(new String(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return lineList;
    }
    @Override
    public List<String> readToLine(File file) {
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return readToLine(ins);
    }

    @Override
    public List<String> readToLine(String path) {
        File file = new File(path);
        return readToLine(file);
    }



    @Override
    public void write(File file, List<String> lineList) {
        ValidateUtil.notEmpty(lineList);
        PrintWriter out = null;
        try {
            File father = file.getParentFile();
            if(!father.exists()) {
                father.mkdirs();
            }
            out = new PrintWriter(file, TempletConstants.CODE);
            for (String string : lineList) {
                out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

    @Override
    public void write(String path, List<String> lineList) {
        File file = new File(path);
        write(file,lineList);
    }

    @Override
    public void write(File file, String line) {
        Validate.notNull(line);
        PrintWriter out = null;
        try {
            File father = file.getParentFile();
            if(!father.exists()) {
                father.mkdirs();
            }
            out = new PrintWriter(file, TempletConstants.CODE);
            out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

    @Override
    public void write(String path, String line) {
        File file = new File(path);
        write(file,line);
    }

    @Override
    public void writeFileByTemplet(File templet, File outFile, Table table) {
        List<String> lineList = readToLine(templet);
        List<String> resultList = LineUtil.buildNewLine(lineList, table);
        write(outFile,resultList);
    }

    @Override
    public void writeJavaBean(Table table) {
        File templet = new File(TempletConstants.PO_TEMPLET_PATH);
        File outFile = new File(TempletConstants.TEST_MODEL_DIR + table.getJaveBeanFileName());
        writeFileByTemplet(templet, outFile, table);
    }

    @Override
    @Deprecated
    public void writeAllTemplet(Table table, String path, String templetDir) {
        if(ValidateUtil.projectIsJar()) {
            writeAllTempletFromJar(table,path);
        }else {
            writeAllTempletFromDir(table, path, TempletConstants.BASE_PATH + "/src/main/resources/" + templetDir);
        }
    }

    @Deprecated
    @Override
    public void writeAllTempletFromDir(Table table, String path, String templetDir) {
        // 根据路径创建File对象
        File temletDir = new File(templetDir);
        ValidateUtil.exsit(templetDir);
        //创建文件夹
        File dir = new File(TempletConstants.OUT_DIR + table.getTableName() + "\\" + path);
        dir.mkdirs();
        // 到的文件名列表
        if (temletDir.exists() && temletDir.isDirectory()) {
            File[] files = temletDir.listFiles();
            for (File file : files) {
                String fileName = file.getName();
                if(file.isDirectory()) {
                    if("common".equals(fileName)) {
                        continue;
                    }
                    writeAllTempletFromDir(table, path + "\\" + fileName + "\\", templetDir + "\\" + fileName);
                }else if(file.isFile() && fileName.endsWith(".templet")){
                    String outName = TempletConstants.templetMap.get(fileName);
                    String newFileName = null;
                    if(outName == null) {
                        newFileName = table.getEntityName() + fileName.replace(".templet", TempletConstants.POST_FIX);
                    }else {
                        newFileName = LineUtil.replaceTemplet(outName, table.getMap(), "table");
                        newFileName = LineUtil.replaceTemplet(newFileName, CommonSettings.map, "common");
                    }
                    File outPath = new File(TempletConstants.OUT_DIR + table.getTableName() + "\\" + path + newFileName);
                    writeFileByTemplet(file, outPath, table);
                    if(newFileName.endsWith(".TAB") || newFileName.endsWith(".PDC")) {
                        File sqlPath = new File(TempletConstants.OUT_DIR_SQL + newFileName);
                        writeFileByTemplet(file, sqlPath, table);
                    }else if(newFileName.equals(table.getEntityName() + ".java")) {
                        File modelPath = new File(TempletConstants.OUT_DIR_MODEL + newFileName);
                        writeFileByTemplet(file, modelPath, table);
                    }else if(newFileName.equals(table.getEntityName() + "Scope.java")) {
                        File scopePath = new File(TempletConstants.OUT_DIR_SCOPE + newFileName);
                        writeFileByTemplet(file, scopePath, table);
                    }else if(newFileName.equals(table.getEntityName() + "View.java")) {
                        File viewPath = new File(TempletConstants.OUT_DIR_VIEW + newFileName);
                        writeFileByTemplet(file, viewPath, table);
                    }else if(newFileName.endsWith("Dao.java") || newFileName.endsWith("DaoImpl.java")) {
                        File daoPath = new File(TempletConstants.OUT_DIR_DAO + newFileName);
                        writeFileByTemplet(file, daoPath, table);
                    }else if(newFileName.contains(".xml")) {
                        File xmlPath = new File(TempletConstants.OUT_DIR_XML + newFileName);
                        writeFileByTemplet(file, xmlPath, table);
                    }else if(newFileName.endsWith("Service.java") || newFileName.endsWith("ServiceImpl.java")) {
                        File servicePath = new File(TempletConstants.OUT_DIR_SERVICE + newFileName);
                        writeFileByTemplet(file, servicePath, table);
                    }

                }
            }

        }
    }

    @Override
    public List<JarEntry> readAllTempletJarEntry() {
        List<JarEntry> list = new ArrayList<JarEntry>();
        try {
            //获得jar包路径
            JarFile jFile = new JarFile(System.getProperty("java.class.path"));
            Enumeration<JarEntry> jarEntrys = jFile.entries();
            while (jarEntrys.hasMoreElements()) {
                JarEntry entry = jarEntrys.nextElement();
                String name = entry.getName();
                if(name.startsWith(TempletConstants.TEMPLET_DIR)) {
                    list.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public JarEntry getJarEntry(String entryName) {
        try {
            //获得jar包路径
            JarFile jFile = new JarFile(System.getProperty("java.class.path"));
            Enumeration<JarEntry> jarEntrys = jFile.entries();
            while (jarEntrys.hasMoreElements()) {
                JarEntry entry = jarEntrys.nextElement();
                String name = entry.getName();
                if(name.contains(".templet"))
                    if(name.endsWith(entryName)) {
                        return entry;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeTempletByEntry_DefaultPath(Table table, String path, JarEntry jarEntry) {
        String jarEntryName = "/" + path + jarEntry.getName().replace(TempletConstants.TEMPLET_DIR, "");
        String templetFileName = jarEntryName.substring(jarEntryName.lastIndexOf("/") + 1);
        if(!jarEntry.isDirectory() && jarEntryName.endsWith(".templet") && !jarEntryName.startsWith("common")) {
            String outName = TempletConstants.templetMap.get(templetFileName);
            String newFileName = null;
            if(outName == null) {
                newFileName = table.getEntityName() + templetFileName.replace(".templet", TempletConstants.POST_FIX);
            }else {
                newFileName = LineUtil.replaceTemplet(outName, table.getMap(), "table");
                newFileName = LineUtil.replaceTemplet(newFileName, CommonSettings.map, "common");
            }
            File outPath = new File(TempletConstants.OUT_DIR + table.getTableName() + jarEntryName.replace(templetFileName, newFileName));
            outPath.getParentFile().mkdirs();
            InputStream is = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
            writeFileByTemplet(is, outPath, table);
            if(newFileName.endsWith(".TAB") || newFileName.endsWith(".PDC")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File sqlPath = new File(TempletConstants.OUT_DIR_SQL + newFileName);
                writeFileByTemplet(is0, sqlPath, table);
            }else if(newFileName.equals(table.getEntityName() + ".java")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File modelPath = new File(TempletConstants.OUT_DIR_MODEL + newFileName);
                writeFileByTemplet(is0, modelPath, table);
            }else if(newFileName.equals(table.getEntityName() + "Scope.java")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File scopePath = new File(TempletConstants.OUT_DIR_SCOPE + newFileName);
                writeFileByTemplet(is0, scopePath, table);
            }else if(newFileName.equals(table.getEntityName() + "View.java")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File viewPath = new File(TempletConstants.OUT_DIR_VIEW + newFileName);
                writeFileByTemplet(is0, viewPath, table);
            }else if(newFileName.endsWith("Dao.java") || newFileName.endsWith("DaoImpl.java")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File daoPath = new File(TempletConstants.OUT_DIR_DAO + newFileName);
                writeFileByTemplet(is0, daoPath, table);
            }else if(newFileName.contains(".xml")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File xmlPath = new File(TempletConstants.OUT_DIR_XML + newFileName);
                writeFileByTemplet(is0, xmlPath, table);
            }else if(newFileName.endsWith("Service.java") || newFileName.endsWith("ServiceImpl.java")) {
                InputStream is0 = getClass().getClassLoader().getResourceAsStream(jarEntry.getName());
                File servicePath = new File(TempletConstants.OUT_DIR_SERVICE + newFileName);
                writeFileByTemplet(is0, servicePath, table);
            }
            //PanelLog.log("生成：  " + jarEntryName.replace(templetFileName, newFileName));
        }
    }

    @Override
    public void writeTempletByEntry(Table table, String path, JarEntry jarEntry, File outPath) {

    }

    @Override
    public void writeAllTempletFromJar(Table table, String path) {

    }

    @Override
    public int getLastKeyLineNum(List<String> lineList, String key) {
        return 0;
    }

    @Override
    public void reWriteFileByList(File file, List<String> list, Table table, int fileType) {

    }

    @Override
    public void writeCommonFileByTemplet(Table table) {

    }



    @Override
    public void writeFileByTemplet(InputStream ins, File outFile, Table table) {

    }

    @Override
    public void writeDataFile(Table table) {

    }

    @Override
    public void writeDataFile(Table table, String sql, String[] primaryColUpKeys, String filName) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void clearDir(File dir) {

    }

    @Override
    public void reBuildCommonFile() {

    }

    @Override
    public void clearAndRebuild() {

    }
}
