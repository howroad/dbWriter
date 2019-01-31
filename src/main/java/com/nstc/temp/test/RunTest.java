package com.nstc.temp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.nstc.data.DbSettings;
import com.nstc.data.Table;

/**
 * <p>Title: TestNature.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年1月9日 下午1:35:56
 * 
 */
public class RunTest {
    
    public static void buildClassAndRun(String pathName,boolean autoRunTest) {
        PrintWriter out = null;
        //String path = Class.class.getClass().getResource("/").getPath() + "com/nstc/temp/test/Test" + pathName + ".java";
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\test\\Test" + pathName + ".java";
        System.out.println(path);
        try {
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path, false), "UTF8"));
            writeJava(out,pathName);
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             out.close();
         }
        JavaCompiler javac;
        javac = ToolProvider.getSystemJavaCompiler();
        int compilationResult = -1;
        File outClassFile = new File(Class.class.getClass().getResource("/").getPath() + "com/nstc/temp/test/Test" + pathName + ".class");
        try {
            compilationResult = javac.run(null,new FileOutputStream(outClassFile),null, "-g","-verbose",path);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.out.println(compilationResult);
        
        
        if(!DbSettings.autoRunTest || !DbSettings.dealSEQ) {
            return;
        }
        
        File file = new File(Class.class.getClass().getResource("/").getPath() + "com/nstc/temp/test/"); 
        URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke(classloader, new Object[]{file.toURI().toURL()});
            Class<?> threadClazz = Class.forName("com.nstc.temp.test.Test" + pathName);
            Method method = threadClazz.getMethod("test");
            method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public static void writeJava(PrintWriter out,String entityName) {
        final String TABTAB = "        ";
        final String TAB = "    ";
        String EntityName = entityName;
        out.println("package com.nstc.temp.test;");
        out.println("import java.io.*;");
        out.println("import java.util.List;");
        out.println("import org.apache.log4j.PropertyConfigurator;");
        out.println("import com.nstc.temp.dao.*;");
        out.println("import com.nstc.temp.model.*;");
        out.println("import com.nstc.data.*;");
        out.println("public class Test" + entityName + " {");
        out.println();
        out.println(TAB + "public static void main(String[] args) {"); 
        out.println(TABTAB + "Test" + entityName + ".test();"); 
        out.println(TAB + "}");
        out.println();
        out.println(TAB + "public static void test() {");
        out.println(TABTAB + "ICommonDao dao = new CommonDaoImpl();");
        out.println(TABTAB + "PropertyConfigurator.configure(ClassLoader.getSystemResource(\"com/nstc/temp/dao/log4j.properties\"));");
        out.println(TABTAB + "PrintStream out = System.out;");
        out.println(TABTAB + String.format("%1$s save%2$s = DataUtil.getObj(%1$s%2$s.class);", EntityName,Table.PO));
        out.println(TABTAB + "//TODO 放入外键");
        out.println(TABTAB + String.format("Integer id = dao.save%1$s(save%2$s);", EntityName,Table.PO));
        out.println(TABTAB + String.format("%1$s%2$s idScope = DataUtil.getIdScope(%1$s%2$s.class,id);", EntityName,Table.PO));
        out.println(TABTAB + String.format("List<%1$s%2$s> list = dao.get%1$sList(idScope);", EntityName,Table.PO));
        out.println(TABTAB + "if(!list.isEmpty()) {out.println(\"插入成功！\" + list.get(0));}else {out.println(\"插入或查询失败！\");return;}");
        out.println();
        out.println(TABTAB + String.format("%1$s%2$s scope = DataUtil.getScope(%1$s%2$s.class);", EntityName,Table.PO));
        out.println(TABTAB + "//TODO 放入外键");
        out.println(TABTAB + String.format("List<%1$s%2$s> queryList = dao.get%1$sList(scope);", EntityName,Table.PO));
        out.println(TABTAB + "if(!queryList.isEmpty() && queryList.size() == 1) {out.println(\"查询成功...\" + queryList);}else {out.println(\"查询失败！\");return;}");
        out.println();
        out.println(TABTAB + "if(id == null) {System.out.println(\"id为空，请手动测试修改和删除！记得删掉新增的记录！\");return;}");
        out.println();
        out.println(TABTAB + String.format("%1$s%2$s update%2$s = DataUtil.getUpdateModel(%1$s%2$s.class,id);", EntityName,Table.PO));
        out.println(TABTAB + "//TODO 放入外键");
        out.println(TABTAB + String.format("dao.update%1$s(update%2$s);", EntityName,Table.PO));
        out.println(TABTAB + String.format("List<%1$s%2$s> updateList = dao.get%1$sList(idScope);", EntityName,Table.PO));
        out.println(TABTAB + "out.println(\"修改成功...: \" + updateList);");
        out.println();
        out.println(TABTAB + String.format("dao.delete%1$sById(id);", EntityName));
        out.println(TABTAB + String.format("List<%1$s%2$s> listAfterDelete = dao.get%1$sList(idScope);", EntityName,Table.PO));
        out.println(TABTAB + "if(listAfterDelete.isEmpty()) {System.out.println(\"删除成功！...\");}else {System.out.println(\"删除失败！\");}");
        out.println(TAB + "}");
        out.println("}");
    }
}
