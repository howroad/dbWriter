package com.nstc.temp.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.nstc.data.Table;

/**
 * <p>Title: TestNature.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年1月9日 下午1:35:56
 * 
 */
public class RunTest {

    public static void buildClassAndRun(String pathName) {
        PrintWriter out = null;
        String path = Class.class.getClass().getResource("/").getPath() + "com/nstc/temp/test/Test" + pathName + ".java";
        //String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\test";
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
        int compilationResult = javac.run(null,null,null, "-g","-verbose",path);
        System.out.println(compilationResult);
        
        
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
        out.println("public static void test() {");
        out.println("ICommonDao dao = new CommonDaoImpl();");
        out.println("PropertyConfigurator.configure(ClassLoader.getSystemResource(\"com/nstc/temp/dao/log4j.properties\"));");
        out.println(TAB + "PrintStream out = System.out;");
        out.println(TAB + String.format("%s" + Table.PO + " save" + Table.PO + " = DataUtil.getObj(%s" + Table.PO + ".class);", EntityName, EntityName));
        out.println(TAB + String.format("Integer id = dao.save%s(save" + Table.PO + ");", EntityName));
        out.println(TAB + String.format("%s" + Table.PO + " idScope = DataUtil.getIdScope(%s" + Table.PO + ".class,id);", EntityName, EntityName));
        out.println(TAB + String.format("List<%s" + Table.PO + "> list = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "if(!list.isEmpty()) {out.println(\"插入成功！\" + list.get(0));}else {out.println(\"插入或查询失败！\");return;}");
        out.println();
        out.println(TAB + String.format("%s" + Table.PO + " scope = DataUtil.getScope(%s" + Table.PO + ".class);", EntityName, EntityName));
        out.println(TAB + String.format("List<%s" + Table.PO + "> queryList = dao.get%sList(scope);", EntityName, EntityName));
        out.println(TAB + "if(!queryList.isEmpty() && queryList.size() == 1) {out.println(\"查询成功...\" + queryList);}else {out.println(\"查询失败！\");return;}");
        out.println();
        out.println(TAB + String.format("%s" + Table.PO + " update" + Table.PO + " = DataUtil.getUpdateModel(%s" + Table.PO + ".class,id);", EntityName,EntityName));
        out.println(TAB + String.format("dao.update%s(update" + Table.PO + ");", EntityName));
        out.println(TAB + String.format("List<%s" + Table.PO + "> updateList = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "out.println(\"修改成功...: \" + updateList);");
        out.println();
        out.println(TAB + String.format("dao.delete%sById(id);", EntityName));
        out.println(TAB + String.format("List<%s" + Table.PO + "> listAfterDelete = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "if(listAfterDelete.isEmpty()) {System.out.println(\"删除成功！...\");}else {System.out.println(\"删除失败！\");}");
        out.println("}");
        out.println("}");
    }
}
