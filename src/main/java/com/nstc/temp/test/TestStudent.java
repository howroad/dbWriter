package com.nstc.temp.test;
import java.io.*;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.data.*;
public class TestStudent {

    public static void main(String[] args) {
        TestStudent.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        PrintStream out = System.out;
        Student save = DataUtil.getObj(Student.class);
        //TODO 放入外键
        Integer id = dao.saveStudent(save);
        Student idScope = DataUtil.getIdScope(Student.class,id);
        List<Student> list = dao.getStudentList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        Student scope = DataUtil.getScope(Student.class);
        //TODO 放入外键
        List<Student> queryList = dao.getStudentList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        if(id == null) {System.out.println("id为空，请手动测试修改和删除！记得删掉新增的记录！");return;}

        Student update = DataUtil.getUpdateModel(Student.class,id);
        //TODO 放入外键
        dao.updateStudent(update);
        List<Student> updateList = dao.getStudentList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteStudentById(id);
        List<Student> listAfterDelete = dao.getStudentList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
}
