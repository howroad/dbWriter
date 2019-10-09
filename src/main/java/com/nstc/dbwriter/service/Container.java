package com.nstc.dbwriter.service;

import com.nstc.dbwriter.service.impl.*;

/**
 * <p>Title: Container.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-09 18:10
 */
public class Container {
    public static IDatabaseService databaseService;
    public static IBuildService buildService;
    public static ILineUtil lineService;
    public static ICoreService coreService;
    public static IIOService ioService;
    public static IClearService clearService;

    static{
        databaseService = new DatabaseServiceImpl();
        buildService = new BuildServiceImpl();
        lineService = new LineUtilImpl();
        coreService = new CoreServiceImpl();
        ioService = new IOServiceImpl();
        clearService = new ClearServiceImpl();
    }
}
