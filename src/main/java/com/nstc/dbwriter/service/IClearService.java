package com.nstc.dbwriter.service;

import java.io.File;

/**
 * <p>Title: IClearService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-09 10:40
 */
public interface IClearService {
    void clear();
    void clearDir(File dir);
    void reBuildCommonFile();
    void clearAndRebuild();
}
