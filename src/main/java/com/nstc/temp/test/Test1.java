package com.nstc.temp.test;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.nstc.temp.dao.*;
import com.nstc.temp.model.*;
import com.nstc.data.*;
public class Test1 {
public static void main(String[] arg) {
ICommonDao dao = new CommonDaoImpl();
PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
    PrintStream out = System.out;
    NatureModel saveModel = DataUtil.getObj(NatureModel.class);
    Integer id = dao.saveNature(saveModel);
    NatureModel idScope = DataUtil.getIdScope(NatureModel.class,id);
    List<NatureModel> list = dao.getNatureList(idScope);
    if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

    NatureModel scope = DataUtil.getScope(NatureModel.class);
    List<NatureModel> queryList = dao.getNatureList(scope);
    if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

    NatureModel updateModel = DataUtil.getUpdateModel(NatureModel.class,id);
    dao.updateNature(updateModel);
    List<NatureModel> updateList = dao.getNatureList(idScope);
    out.println("修改成功...: " + updateList);

    dao.deleteNatureById(id);
    List<NatureModel> listAfterDelete = dao.getNatureList(idScope);
    if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
}
}
