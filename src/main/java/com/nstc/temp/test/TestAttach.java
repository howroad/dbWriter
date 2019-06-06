package com.nstc.temp.test;
import com.nstc.dbwriter.util.DataUtil;
import com.nstc.temp.dao.CommonDaoImpl;
import com.nstc.temp.dao.ICommonDao;
import com.nstc.temp.model.Attach;
public class TestAttach {

    public static void main(String[] args) {
        TestAttach.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        //PropertyConfigurator.configure(ClassLoader.getSystemResource("com/nstc/temp/dao/log4j.properties"));
        Attach attach = new Attach();
        DataUtil.setDefault(attach);
        attach.setAttachId(122);
        dao.saveOrUpdateAttach(attach);
    }
}
