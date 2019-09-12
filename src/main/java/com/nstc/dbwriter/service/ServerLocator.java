package com.nstc.dbwriter.service;

import com.nstc.dbwriter.service.impl.BuildServiceImpl;
import com.nstc.dbwriter.service.impl.LineUtilImpl;
import com.nstc.dbwriter.service.impl.HandelService;

/**
 * <p>Title: ServerLocator.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 13:48
 */
public class ServerLocator {
    protected IHandelService sqlService;
    protected ITableService tableService;
    protected ILineUtil lineService;

    public ServerLocator() {
        this.sqlService = new HandelService();
        this.tableService = new BuildServiceImpl();
        this.lineService = new LineUtilImpl();
    }
}
