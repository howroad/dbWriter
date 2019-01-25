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
public class TestNature {
    public static void main(String[] arg) {
        PrintWriter out = null;
        String path = Class.class.getClass().getResource("/").getPath() + "com/nstc/temp/test/Test4.java";
        System.out.println(path);
        try {
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path, false), "UTF8"));
            writeJava(out,"AffairType");
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
            Class<?> threadClazz = Class.forName("com.nstc.temp.test.Test4");
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
        out.println("public class Test4 {");
        out.println("public static void test() {");
        out.println("ICommonDao dao = new CommonDaoImpl();");
        out.println("PropertyConfigurator.configure(ClassLoader.getSystemResource(\"com/nstc/temp/dao/log4j.properties\"));");
        out.println(TAB + "PrintStream out = System.out;");
        out.println(TAB + String.format("%sModel saveModel = DataUtil.getObj(%sModel.class);", EntityName, EntityName));
        out.println(TAB + String.format("Integer id = dao.save%s(saveModel);", EntityName));
        out.println(TAB + String.format("%sModel idScope = DataUtil.getIdScope(%sModel.class,id);", EntityName, EntityName));
        out.println(TAB + String.format("List<%sModel> list = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "if(!list.isEmpty()) {out.println(\"插入成功！\" + list.get(0));}else {out.println(\"插入或查询失败！\");return;}");
        out.println();
        out.println(TAB + String.format("%sModel scope = DataUtil.getScope(%sModel.class);", EntityName, EntityName));
        out.println(TAB + String.format("List<%sModel> queryList = dao.get%sList(scope);", EntityName, EntityName));
        out.println(TAB + "if(!queryList.isEmpty() && queryList.size() == 1) {out.println(\"查询成功...\" + queryList);}else {out.println(\"查询失败！\");return;}");
        out.println();
        out.println(TAB + String.format("%sModel updateModel = DataUtil.getUpdateModel(%sModel.class,id);", EntityName,EntityName));
        out.println(TAB + String.format("dao.update%s(updateModel);", EntityName));
        out.println(TAB + String.format("List<%sModel> updateList = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "out.println(\"修改成功...: \" + updateList);");
        out.println();
        out.println(TAB + String.format("dao.delete%sById(id);", EntityName));
        out.println(TAB + String.format("List<%sModel> listAfterDelete = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "if(listAfterDelete.isEmpty()) {System.out.println(\"删除成功！...\");}else {System.out.println(\"删除失败！\");}");
        out.println("}");
        out.println("}");
    }
}
