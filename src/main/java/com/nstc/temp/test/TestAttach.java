package com.nstc.temp.test;
import com.nstc.dbwriter.util.DataUtil;
import com.nstc.temp.dao.CommonDaoImpl;
import com.nstc.temp.model.Attach;
public class TestAttach {

    public static void main(String[] args) {
        TestAttach.test();
    }

    public static void test() {
        CommonDaoImpl dao = new CommonDaoImpl();
        Attach attach = new Attach();
        DataUtil.setDefault(attach);
        System.out.println(attach);
        Integer id = dao.saveAttach(attach);
        System.out.println(id);
    }
}
