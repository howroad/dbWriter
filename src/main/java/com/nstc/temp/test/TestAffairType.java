package com.nstc.temp.test;
import java.io.*;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.data.*;
public class TestAffairType {

    public static void main(String[] args) {
        TestAffairType.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        PrintStream out = System.out;
        AffairType save = DataUtil.getObj(AffairType.class);
        //TODO 放入外键
        Integer id = dao.saveAffairType(save);
        AffairType idScope = DataUtil.getIdScope(AffairType.class,id);
        List<AffairType> list = dao.getAffairTypeList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        AffairType scope = DataUtil.getScope(AffairType.class);
        //TODO 放入外键
        List<AffairType> queryList = dao.getAffairTypeList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        if(id == null) {System.out.println("id为空，请手动测试修改和删除！记得删掉新增的记录！");return;}

        AffairType update = DataUtil.getUpdateModel(AffairType.class,id);
        //TODO 放入外键
        dao.updateAffairType(update);
        List<AffairType> updateList = dao.getAffairTypeList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteAffairTypeById(id);
        List<AffairType> listAfterDelete = dao.getAffairTypeList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
}
