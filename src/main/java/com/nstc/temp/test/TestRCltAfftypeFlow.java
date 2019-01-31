package com.nstc.temp.test;
import java.io.*;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.data.*;
public class TestRCltAfftypeFlow {

    public static void main(String[] args) {
        TestRCltAfftypeFlow.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        PrintStream out = System.out;
        RCltAfftypeFlow save = DataUtil.getObj(RCltAfftypeFlow.class);
        //TODO 放入外键
        Integer id = dao.saveRCltAfftypeFlow(save);
        RCltAfftypeFlow idScope = DataUtil.getIdScope(RCltAfftypeFlow.class,id);
        List<RCltAfftypeFlow> list = dao.getRCltAfftypeFlowList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        RCltAfftypeFlow scope = DataUtil.getScope(RCltAfftypeFlow.class);
        //TODO 放入外键
        List<RCltAfftypeFlow> queryList = dao.getRCltAfftypeFlowList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        if(id == null) {System.out.println("id为空，请手动测试修改和删除！记得删掉新增的记录！");return;}

        RCltAfftypeFlow update = DataUtil.getUpdateModel(RCltAfftypeFlow.class,id);
        //TODO 放入外键
        dao.updateRCltAfftypeFlow(update);
        List<RCltAfftypeFlow> updateList = dao.getRCltAfftypeFlowList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteRCltAfftypeFlowById(id);
        List<RCltAfftypeFlow> listAfterDelete = dao.getRCltAfftypeFlowList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
}
