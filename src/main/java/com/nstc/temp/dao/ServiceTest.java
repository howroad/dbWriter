package com.nstc.temp.dao;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;

/**
 * <p>Title: ServiceTest.java</p>
 *
 * <p>Description: </p>
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
        dao = null;
    }
    public ICommonDao getDao() {
        return dao;
    }
    public void setDao(ICommonDao dao) {
        this.dao = dao;
    }
    
}
