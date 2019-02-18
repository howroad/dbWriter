package com.nstc.dbwriter.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
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
import java.util.List;
import java.util.ListIterator;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.model.Line;
import com.nstc.dbwriter.model.MyParam;
import com.nstc.dbwriter.model.Table;

/**
 * <p>
 * Title: WriteTest.java
 * </p>
 *
 * <p>
 * Description:
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
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
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
    
    public static List<Table> buildTableFromExcel(String appNo) {
        ExcelUtil importExcelUtil=new ExcelUtil();
        //excel 导入数据demo
        File file = new File(CommonSettings.EXCEL_PATH);
        List<List<String>> dataList= null;
        List<Table> tableList = new ArrayList<Table>();
        
        Table tempTable = null;
        List<Line> tempLineList = null;
        List<MyParam> tempParamList = null;
        try {
            dataList = importExcelUtil.importExcel(file);
            for (ListIterator<List<String>> iterator = dataList.listIterator(); iterator.hasNext();) {
                List<String> list = iterator.next();
                String columnName = list.get(0);
                String type = list.get(1);
                String commont = list.get(2);
                if(type == null || type.length() == 0) {
                    if(tempTable != null && tempLineList != null) {
                        tempTable.setLineList(tempLineList);
                        Table table = tempTable.clone();
                        tableList.add(table);
                    }
                    tempLineList = new ArrayList<Line>();
                    tempParamList = new ArrayList<MyParam>();
                    tempTable = new Table(columnName, commont, tempLineList,tempParamList);
                }else {
                    tempLineList.add(new Line(columnName, type, commont));
                    //TODO columnInfo
                    tempParamList.add(new MyParam(commont, columnName, type));
                }
                if(!iterator.hasNext()) {
                    tempTable.setLineList(tempLineList);
                    Table table = tempTable.clone();
                    tableList.add(table);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableList;
    }

    public static void buildDate(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH + table.getTableName() + "\\fromDB\\" + table.getTableName() + ".SQL";
        try {
            File dir = new File(CommonSettings.PATH + table.getTableName() + "\\fromDB\\");
            dir.mkdirs();
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
            List<List<Object>> dataList = getData(table.getTableName());
            table.writeDate(out, dataList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    public static List<List<Object>> getData(String tableName) {
        tableName = tableName.toUpperCase();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + tableName + " WHERE ROWNUM <= 20";
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
    public static void buildSeq(Table table) {
        if(!CommonSettings.dealSEQ) {
            return;
        }
        PrintWriter out = null;
        String filName = CommonSettings.PATH + table.getSeqName() + ".SEQ";
        try {
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
            table.writeSEQ(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }       
    }
    public static void buildTab(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH  + table.getTableName() + ".TAB";
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeCreateTable(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    
    public static void buildJavaBean(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH  + table.getJaveBeanFileName();
        PrintWriter outThis = null;
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeJaveBean(out);
           if(CommonSettings.autoRunTest) {
               String fileNameThis = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\model\\" + table.getJaveBeanFileName();
               outThis = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileNameThis, false), "GBK"));
               table.writeJaveBean(outThis);
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (outThis != null) {
                outThis.close();
            }
        }
    }
    
    public static void buildJavaBeanByTemplet(Table table) {
        File templet = new File(InnerSettings.PO_TEMPLET_PATH);
        File outFile = new File(InnerSettings.TEST_MODEL_DIR + table.getJaveBeanFileName());
        WriteUtil.writeFileByTemplet(templet, outFile, table);
        if(CommonSettings.usePage) {
            File Scopetemplet = new File(InnerSettings.SCOPE_TEMPLET_PATH);
            File scopeOutFile = new File(InnerSettings.TEST_MODEL_DIR + table.getEntityName() + "Scope.java");
            WriteUtil.writeFileByTemplet(Scopetemplet, scopeOutFile, table);
        }
    }
    
    public static void buildAllTemplet(Table table,String path) {
        // 根据路径创建File对象
        File temletDir = new File(InnerSettings.TEMPLET_DIR);
        //创建文件夹
        File dir = new File(InnerSettings.OUT_DIR + table.getTableName() + "\\" + path);
        dir.mkdirs();
        // 到的文件名列表
        if (temletDir.exists() && temletDir.isDirectory()) {
            File[] files = temletDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".templet");
                }
            });
            for (File file : files) {
                String fileName = file.getName();
                String outName = InnerSettings.templetMap.get(fileName);
                String newFileName = null;
                if(outName == null) {
                    newFileName = table.getEntityName() + fileName.replace(".templet", ".out");
                }else {
                    table.initMap();
                    newFileName = CodeUtil.replaceTemplet(outName, table.getMap(), "table");
                    newFileName = CodeUtil.replaceTemplet(newFileName, CommonSettings.map, "common");
                }
                File outPath = new File(InnerSettings.OUT_DIR + table.getTableName() + "\\" + path + newFileName);
                writeFileByTemplet(file, outPath, table);
            }
            
        }

    }
    
    public static void buildDao(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH + table.getDaoName();
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeDao(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        } 
    }
    public static void buildXml(Table table) {
        PrintWriter out = null;
        String filName = CommonSettings.PATH + table.getXmlName();
        try {
           out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filName, false), "GBK"));
           table.writeXml(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
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
    
    public static void insertFileByTable(File file,Table table,int fileType) {
        List<String> lineList = getLineList(file);
        for (String string : lineList) {
            if(string.contains("save" + table.getEntityName())) {
                return;
            }
        }
        int index = getLastKeyLineNum(lineList, FILE_LAST_KEY[fileType]);
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            for (int i = 0; i < index; i++) {
                out.println(lineList.get(i));
            }
            if(DAO_INTERFACE == fileType) {
                table.writeDaoInterface(out);
            }else if(DAO_IMPL == fileType) {
                table.writeDaoImpl(out);
            }else if(XML == fileType) {
                table.writeXml(out);
            }
            for(int i = index ; i < lineList.size() ; i++) {
                out.println(lineList.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(out != null) {
                out.close();
            }
        }
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
            out = new PrintWriter(file);
            for (int i = 0; i < index; i++) {
                out.println(lineList.get(i));
            }
            for(String line : list) {
                out.println(line);
            }
            for(int i = index ; i < lineList.size() ; i++) {
                out.println(lineList.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(out != null) {
                out.close();
            }
        }
    }    
    public static void writeCommonFile(Table table) {
        File daoInterface = new File(InnerSettings.ICOMMONDAO_PATH);
        File daoImpl = new File(InnerSettings.COMMONDAOIMPL_PATH);
        File xml = new File(InnerSettings.COMMON_XML_PATH);
        
        WriteUtil.insertFileByTable(xml, table, WriteUtil.XML);
        WriteUtil.insertFileByTable(daoInterface, table, WriteUtil.DAO_INTERFACE);
        WriteUtil.insertFileByTable(daoImpl, table, WriteUtil.DAO_IMPL);
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
    
    public static void writeFile(List<String> lineList,File file) {
        ValidateUtil.checkEmpty(lineList);
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            for (String string : lineList) {
                out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }
}
