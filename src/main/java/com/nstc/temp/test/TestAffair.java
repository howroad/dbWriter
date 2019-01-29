package com.nstc.temp.test;
import java.io.*;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.data.*;
public class TestAffair {

    public static void main(String[] args) {
        TestAffair.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        PrintStream out = System.out;
        Affair save = DataUtil.getObj(Affair.class);
        //TODO 放入外键
        Integer id = dao.saveAffair(save);
        Affair idScope = DataUtil.getIdScope(Affair.class,id);
        List<Affair> list = dao.getAffairList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        Affair scope = DataUtil.getScope(Affair.class);
        //TODO 放入外键
        List<Affair> queryList = dao.getAffairList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        if(id == null) {System.out.println("id为空，请手动测试修改和删除！记得删掉新增的记录！");return;}

        Affair update = DataUtil.getUpdateModel(Affair.class,id);
        //TODO 放入外键
        dao.updateAffair(update);
        List<Affair> updateList = dao.getAffairList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteAffairById(id);
        List<Affair> listAfterDelete = dao.getAffairList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
}
