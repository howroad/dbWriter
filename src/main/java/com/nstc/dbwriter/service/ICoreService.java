package com.nstc.dbwriter.service;

import com.nstc.frame.jframe.ShowFrame;

/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * @author luhao
 * @since：2019-09-12 16:32
 */
public interface ICoreService {
    void testCoonect();
    void handelCore();
    void clear();
    void autoTest();
    void showAndHideSqlPanel();
    void showAndHideLog();
    void createSql();
    void createCustQuery();
    void before(ShowFrame showFrame);

}
