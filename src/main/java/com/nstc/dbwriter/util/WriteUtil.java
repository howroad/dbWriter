package com.nstc.dbwriter.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.model.Table;

/**
 * <p>
 * Title: WriteTest.java
 * </p>
 *
 * @author luhao
 * 
 * @since：2018年12月21日 下午1:24:52
 * 
 */
public class WriteUtil {

    private static String[] FILE_LAST_KEY = new String[] {"}","}","</sqlMap>"};
    public static int DAO_INTERFACE = 0;
    public static int DAO_IMPL = 1;
    public static int XML = 2;

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void buildCreateFromDB(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH  + table.getTableName() + "_FROMDB" + ".TAB";
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), InnerSettings.CODE));
           out.println(getCreateFromDB(table.getTableName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }        
    }
    
    public static String getCreateFromDB(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select dbms_metadata.get_ddl('TABLE','" + tableName + "') from dual";
        String result = null;
        try {
            conn = DriverManager.getConnection(CommonSettings.URL, CommonSettings.USER, CommonSettings.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clob clob = rs.getClob(1);
                result = clobToString(clob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;       
    }
    
    

    public static void buildDate(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH + table.getTableName() + "\\fromDB\\" + table.getTableName() + ".SQL";
        try {
            File dir = new File(CommonSettings.PATH + table.getTableName() + "\\fromDB\\");
            dir.mkdirs();
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), InnerSettings.CODE));
            String sql = "SELECT * FROM " + table.getTableName().toUpperCase() + " WHERE ROWNUM <= 1000 ORDER BY 1 ASC";
            List<List<Object>> dataList = getDataBySQL(sql);
            table.writeDate(out, dataList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
    
    public static void buildDate(Table table,String sql,String[] primaryColUpKeys,String filName) {
        PrintWriter out = null;
        try {
            File dir = new File(CommonSettings.PATH + table.getTableName() + "\\fromDB\\");
            dir.mkdirs();
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), InnerSettings.CODE));
            List<List<Object>> dataList = getDataBySQL(sql);
            table.writeDate(out, dataList, primaryColUpKeys);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
    
    
    public static List<List<Object>> getDataBySQL(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<List<Object>> resultList = new ArrayList<List<Object>>();
        try {
            conn = DriverManager.getConnection(CommonSettings.URL, CommonSettings.USER, CommonSettings.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < count; i++) {
                    list.add(rs.getObject(i + 1));
                }
                resultList.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
    
    public static void buildJavaBeanByTemplet(Table table) {
        File templet = new File(InnerSettings.PO_TEMPLET_PATH);
        File outFile = new File(InnerSettings.TEST_MODEL_DIR + table.getJaveBeanFileName());
        WriteUtil.writeFileByTemplet(templet, outFile, table);
        if(CommonSettings.usePage) {
            File scopeTemplet = new File(InnerSettings.SCOPE_TEMPLET_PATH);
            File scopeOutFile = new File(InnerSettings.TEST_MODEL_DIR + table.getEntityName() + "Scope.java");
            WriteUtil.writeFileByTemplet(scopeTemplet, scopeOutFile, table);
        }
    }
    
    public static void buildAllTemplet(Table table,String path,String templetDir) {
        if(ValidateUtil.projectIsJar()) {
            buildAllTempletFromJar(table,path);
        }else {
            //System.out.println(System.getProperty("user.dir") + "/src/main/java/" + templetDir);
            buildAllTempletFromDir(table, path, System.getProperty("user.dir") + "/src/main/java/" + templetDir);
        }
    }
    
    public static void buildAllTempletFromDir(Table table,String path,String templetDir) {
        // 根据路径创建File对象
        File temletDir = new File(templetDir);
        ValidateUtil.exsit(templetDir);
        //创建文件夹
        File dir = new File(InnerSettings.OUT_DIR + table.getTableName() + "\\" + path);
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
                    buildAllTempletFromDir(table, path + "\\" + fileName + "\\", templetDir + "\\" + fileName);
                }else if(file.isFile() && fileName.endsWith(".templet")){
                    String outName = InnerSettings.templetMap.get(fileName);
                    String newFileName = null;
                    if(outName == null) {
                        newFileName = table.getEntityName() + fileName.replace(".templet", InnerSettings.POST_FIX);
                    }else {
                        newFileName = CodeUtil.replaceTemplet(outName, table.getMap(), "table");
                        newFileName = CodeUtil.replaceTemplet(newFileName, CommonSettings.map, "common");
                    }
                    File outPath = new File(InnerSettings.OUT_DIR + table.getTableName() + "\\" + path + newFileName);
                    writeFileByTemplet(file, outPath, table);
                }
            }
            
        }
    }

    public static List<JarEntry> getAllTempletJarEntry(){
        List<JarEntry> list = new ArrayList<JarEntry>();
        try {
            @SuppressWarnings("resource")
            //获得jar包路径
            JarFile jFile = new JarFile(System.getProperty("java.class.path"));
            Enumeration<JarEntry> jarEntrys = jFile.entries();
            while (jarEntrys.hasMoreElements()) {
                JarEntry entry = jarEntrys.nextElement();
                String name = entry.getName();
                if(name.startsWith(InnerSettings.TEMPLET_DIR)) {
                    list.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    public static void buildTempletByEntry(Table table,String path, JarEntry jarEntry) {
        String jarEntryName = "/" + path + jarEntry.getName().replace(InnerSettings.TEMPLET_DIR, "");
        String templetFileName = jarEntryName.substring(jarEntryName.lastIndexOf("/") + 1);
        if(!jarEntry.isDirectory() && jarEntryName.endsWith(".templet") && !jarEntryName.startsWith("common")) {
            String outName = InnerSettings.templetMap.get(templetFileName);
            String newFileName = null;
            if(outName == null) {
                newFileName = table.getEntityName() + templetFileName.replace(".templet", InnerSettings.POST_FIX);
            }else {
                newFileName = CodeUtil.replaceTemplet(outName, table.getMap(), "table");
                newFileName = CodeUtil.replaceTemplet(newFileName, CommonSettings.map, "common");
            }
            File outPath = new File(InnerSettings.OUT_DIR + table.getTableName() + jarEntryName.replace(templetFileName, newFileName));
            outPath.getParentFile().mkdirs();
            InputStream is = WriteUtil.class.getClassLoader().getResourceAsStream(jarEntry.getName());
            writeFileByTemplet(is, outPath, table);
            System.out.println("生成：  " + jarEntryName.replace(templetFileName, newFileName));
        }
        
    }
    
    public static void buildAllTempletFromJar(Table table, String path) {
        List<JarEntry> list = getAllTempletJarEntry();
        for (JarEntry jarEntry : list) {
            buildTempletByEntry(table,path, jarEntry);
        }
    }
    
    /**
     * 将字Clob转成String类型
     * @Description:
     * @param sc
     * @return String
     * @author net
     * @since：2018年12月26日 下午4:57:21
     */
    private static String clobToString(Clob sc) {
        String reString = "";
        //得到流
        StringBuffer sb = null;
        try {
            Reader is = sc.getCharacterStream();
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();
            sb = new StringBuffer();
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            while (s != null) {
                sb.append(s.trim().replaceAll("\t", "\n") + "\n");
                s = br.readLine();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reString = sb.toString();
        return reString;
    }
    
    public static List<String> getLineList(File file){
        List<String> lineList = new ArrayList<String>();
        BufferedReader in = null ;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
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
    
    public static List<String> getLineList(InputStream ins){
        List<String> lineList = new ArrayList<String>();
        BufferedReader in = null ;
        try {
            in = new BufferedReader(new InputStreamReader(ins,"utf-8"));
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
    
    public static int getLastKeyLineNum(List<String> lineList,String key) {
        int num = -1;
        for (int i = 0; i < lineList.size(); i++) {
            String str = lineList.get(i);
            if(str != null && str.contains(key)) {
                num = i;
            }
        }
        return num;
    }
    
    public static void insertFileByList(File file, List<String> list, Table table, int fileType) {
        List<String> lineList = getLineList(file);
        for (String string : lineList) {
            if(string.contains("save" + table.getEntityName())) {
                return;
            }
        }
        int index = getLastKeyLineNum(lineList, FILE_LAST_KEY[fileType]);
        PrintWriter out = null;
        try {
            out = new PrintWriter(file,InnerSettings.CODE);
            for (int i = 0; i < index; i++) {
                out.println(lineList.get(i));
            }
            for(String line : list) {
                out.println(line);
            }
            for(int i = index ; i < lineList.size() ; i++) {
                out.println(lineList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally{
            if(out != null) {
                out.close();
            }
        }
    }    
    public static void writeCommonFileByTemplet(Table table) {
        List<String> lineList = null;
        List<String> resultList = null;
        
        lineList = getLineList(new File(InnerSettings.DAO_TEMPLET_PATH));
        resultList = CodeUtil.buildNewLine(lineList, table);
        insertFileByList(new File(InnerSettings.ICOMMONDAO_PATH), resultList, table, DAO_INTERFACE);
        
        lineList = getLineList(new File(InnerSettings.DAOIMPL_TEMPLET_PATH));
        resultList = CodeUtil.buildNewLine(lineList, table);
        insertFileByList(new File(InnerSettings.COMMONDAOIMPL_PATH), resultList, table, DAO_IMPL);
        
        lineList = getLineList(new File(InnerSettings.XML_TEMPLET_PATH));
        resultList = CodeUtil.buildNewLine(lineList, table);
        insertFileByList(new File(InnerSettings.COMMON_XML_PATH), resultList, table, XML);
        
    }
    
    public static void writeFileByTemplet(File templet,File outFile, Table table) {
        List<String> lineList = getLineList(templet);
        List<String> resultList = CodeUtil.buildNewLine(lineList, table);
        writeFile(resultList, outFile);
    }
    
    public static void writeFileByTemplet(InputStream ins,File outFile, Table table) {
        List<String> lineList = getLineList(ins);
        List<String> resultList = CodeUtil.buildNewLine(lineList, table);
        writeFile(resultList, outFile);
    }
    
    public static void writeFile(List<String> lineList,File file) {
        ValidateUtil.notEmpty(lineList);
        PrintWriter out = null;
        try {
            out = new PrintWriter(file,InnerSettings.CODE);
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
}
