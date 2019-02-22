package com.nstc.temp.test;
import java.io.*;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.dbwriter.util.DataUtil;
public class TestAmount {

    public static void main(String[] args) {
        TestAmount.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        PrintStream out = System.out;
        Amount save = DataUtil.getObj(Amount.class);
        //TODO 放入外键
        Integer id = dao.saveAmount(save);
        Amount idScope = DataUtil.getIdScope(Amount.class,id);
        List<Amount> list = dao.getAmountList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        Amount scope = DataUtil.getScope(Amount.class);
        //TODO 放入外键
        List<Amount> queryList = dao.getAmountList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        if(id == null) {System.out.println("id为空，请手动测试修改和删除！记得删掉新增的记录！");return;}

        Amount update = DataUtil.getUpdateModel(Amount.class,id);
        //TODO 放入外键
        dao.updateAmount(update);
        List<Amount> updateList = dao.getAmountList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteAmountById(id);
        List<Amount> listAfterDelete = dao.getAmountList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
}
