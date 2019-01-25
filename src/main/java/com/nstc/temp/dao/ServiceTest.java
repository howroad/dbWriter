package com.nstc.temp.dao;

import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nstc.data.DataUtil;
import com.nstc.temp.model.BusinessLogModel;
import com.nstc.temp.model.BusinessModel;
import com.nstc.temp.model.NatureModel;

/**
 * <p>Title: ServiceTest.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 上午11:36:01
 * 
 */
public class ServiceTest {
    private ICommonDao dao;
    @Before
    public void before() {
        dao = new CommonDaoImpl();
        PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
    }
    @After
    public void after() {
        
    }
    //@Test
    public void testBusiness() {
        BusinessModel saveModel = DataUtil.getObj(BusinessModel.class);
        Integer id = dao.saveBusiness(saveModel);
        BusinessModel idScope = DataUtil.getIdScope(BusinessModel.class,id);
        List<BusinessModel> list = dao.getBusinessList(idScope);
        if(!list.isEmpty()) {System.out.println("插入成功！" + list.get(0));}else {System.out.println("插入或查询失败！");return;}

        BusinessModel scope = DataUtil.getScope(BusinessModel.class);
        List<BusinessModel> queryList = dao.getBusinessList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {System.out.println("查询成功..." + queryList);}else {System.out.println("查询失败！");return;}

        BusinessModel updateModel = DataUtil.getUpdateModel(BusinessModel.class,id);
        dao.updateBusiness(updateModel);
        List<BusinessModel> updateList = dao.getBusinessList(idScope);
        System.out.println("修改成功...: " + updateList);

        dao.deleteBusinessById(id);
        List<BusinessModel> listAfterDelete = dao.getBusinessList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
    @Test
    public void testBusinessLog(){
        PrintStream out = System.out; 
        BusinessLogModel saveModel = DataUtil.getObj(BusinessLogModel.class);
        Integer id = dao.saveBusinessLog(saveModel);
        BusinessLogModel idScope = DataUtil.getIdScope(BusinessLogModel.class,id);
        List<BusinessLogModel> list = dao.getBusinessLogList(idScope);
        if(!list.isEmpty()) {out.println("插入成功！" + list.get(0));}else {out.println("插入或查询失败！");return;}

        BusinessLogModel scope = DataUtil.getScope(BusinessLogModel.class);
        List<BusinessLogModel> queryList = dao.getBusinessLogList(scope);
        if(!queryList.isEmpty() && queryList.size() == 1) {out.println("查询成功..." + queryList);}else {out.println("查询失败！");return;}

        BusinessLogModel updateModel = DataUtil.getUpdateModel(BusinessLogModel.class,id);
        dao.updateBusinessLog(updateModel);
        List<BusinessLogModel> updateList = dao.getBusinessLogList(idScope);
        out.println("修改成功...: " + updateList);

        dao.deleteBusinessLogById(id);
        List<BusinessLogModel> listAfterDelete = dao.getBusinessLogList(idScope);
        if(listAfterDelete.isEmpty()) {System.out.println("删除成功！...");}else {System.out.println("删除失败！");}
    }
    @Test
    public void testNature(){
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
