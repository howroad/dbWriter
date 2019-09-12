package com.nstc.dbwriter.service;

import com.nstc.frame.jframe.ShowFrame;

/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 16:32
 */
public interface ICoreService {
    void testCoonect(ShowFrame showFrame);
    void handelCore(ShowFrame showFrame);
    void clear(ShowFrame showFrame);
    void autoTest(ShowFrame showFrame);
    void showAndHideSqlPanel(ShowFrame showFrame);
    void showAndHideLog(ShowFrame showFrame);
    void createSql(ShowFrame showFrame);
    void createCustQuery(ShowFrame showFrame);

}
