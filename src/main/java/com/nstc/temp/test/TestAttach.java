package com.nstc.temp.test;
import com.nstc.temp.dao.CommonDaoImpl;
import com.nstc.temp.dao.ICommonDao;
public class TestAttach {

    public static void main(String[] args) {
        TestAttach.test();
    }

    public static void test() {
        ICommonDao dao = new CommonDaoImpl();
        System.out.println(dao);
    }
}
