package com.nstc.dbwriter.service.impl;

import com.nstc.dbwriter.config.TempletConstants;
import com.nstc.dbwriter.service.Container;
import com.nstc.dbwriter.service.IClearService;
import com.nstc.log.PanelLog;

import java.io.File;
import java.util.List;

/**
 * <p>Title: ClearServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-09 10:42
 */
public class ClearServiceImpl implements IClearService {
    @Override
    public void clear() {
        //删除model
        File modelDir = new File(TempletConstants.TEST_MODEL_DIR);
        clearDir(modelDir);
        //删除测试类
        File testDir = new File(TempletConstants.TEST_DIR);
        clearDir(testDir);
        //删除输出
        File outDir = new File(TempletConstants.OUT_DIR);
        clearDir(outDir);
    }

    @Override
    public void clearDir(File dir) {
        if(dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if(file.isFile()) {
                    if(".gitkeep".equals(file.getName())){
                        continue;
                    }
                    if(file.getName().endsWith(".xlsx")){
                        continue;
                    }
                    if(file.getName().endsWith(".jar")){
                        continue;
                    }
                    if(file.getName().toUpperCase().endsWith(".CMD")){
                        continue;
                    }
                    file.delete();
                }else if(file.isDirectory()) {
                    clearDir(file);
                    file.delete();
                }
            }
        }
    }

    @Override
    public void reBuildCommonFile() {
        //重新生成ICommonDAO
        List<String> list = null;
        list = Container.ioService.readToLine(new File(TempletConstants.ICOMMONDAO_TEMPLET_PATH));
        Container.ioService.write(new File(TempletConstants.ICOMMONDAO_PATH),list);
        //重新生成CommonDaoImpl
        list = Container.ioService.readToLine(new File(TempletConstants.COMMONDAOIMPL_TEMPLET_PATH));
        Container.ioService.write(new File(TempletConstants.COMMONDAOIMPL_PATH),list);
        //重新生成TEMP_Common.xml
        list = Container.ioService.readToLine(new File(TempletConstants.COMMON_XML_TEMPLET_PATH));
        Container.ioService.write(new File(TempletConstants.COMMON_XML_PATH),list);
        PanelLog.log("clear...end");
    }

    @Override
    public void clearAndRebuild() {
        clear();
        reBuildCommonFile();
    }
}
