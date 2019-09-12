package com.nstc.dbwriter.service;

import java.io.File;
import java.util.List;

/**
 * <p>Title: IIOService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 14:04
 */
public interface IIOService {
    List<String> readFile(File file);
    List<String> readFile(String fileName);
    void write(File file, List<String> lineList);
    void write(String fileName, List<String> lineList);
    void write(File file, String line);
    void write(String fileName, String line);

}
