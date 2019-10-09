package com.nstc.dbwriter.service;

import com.nstc.frame.jframe.ShowFrame;

import javax.swing.*;

/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * @author luhao
 * @since：2019-09-12 16:32
 */
public interface ICoreService {
    void testCoonect();
    void handelRun();
    void clear();
    void autoTest();
    void createCustSql(String tbNamesStr,String sqlsStr,String pkNamesStr);
}
