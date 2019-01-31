package com.nstc.temp.test;
import java.io.*;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.data.*;
public class TestTeacher {

    public static void main(String[] args) {
        TestTeacher.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        PrintStream out = System.out;
        Teacher save = DataUtil.getObj(Teacher.class);
        //TODO 放入外键
        Integer id = dao.saveTeacher(save);
        Teacher idScope = DataUtil.getIdScope(Teacher.class,id);
        List<Teacher> list = dao.getTeacherList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        Teacher scope = DataUtil.getScope(Teacher.class);
        //TODO 放入外键
        List<Teacher> queryList = dao.getTeacherList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        if(id == null) {System.out.println("id为空，请手动测试修改和删除！记得删掉新增的记录！");return;}

        Teacher update = DataUtil.getUpdateModel(Teacher.class,id);
        //TODO 放入外键
        dao.updateTeacher(update);
        List<Teacher> updateList = dao.getTeacherList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteTeacherById(id);
        List<Teacher> listAfterDelete = dao.getTeacherList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
}
