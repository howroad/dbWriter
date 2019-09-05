package com.nstc.dbwriter.control;

import java.io.File;
import java.util.List;

import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.util.WriteUtil;
import com.nstc.log.PanelLog;

/**
 * <p>Title: 清空测试文件</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月15日 下午3:09:11
 * 
 */
public class ClearTemp {
    
    public static void clearAndRebuild() {
        clear();
        reBuildCommonFile();
    }
    
    public static void clear() {
        //删除model
        File modelDir = new File(InnerSettings.TEST_MODEL_DIR);
        clearDir(modelDir);
        //删除测试类
        File testDir = new File(InnerSettings.TEST_DIR);
        clearDir(testDir);
        //删除输出
        File outDir = new File(InnerSettings.OUT_DIR);
        clearDir(outDir);
    }
    
    public static void clearDir(File dir) {
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
    
    /**
     * 重新生成公共文件
     * @Description:
     * @author luhao
     * @since：2019年3月14日 上午11:29:41
     */
    public static void reBuildCommonFile() {
        //重新生成ICommonDAO
        List<String> list = null;
        list = WriteUtil.getLineList(new File(InnerSettings.ICOMMONDAO_TEMPLET_PATH));
        WriteUtil.writeFile(list, new File(InnerSettings.ICOMMONDAO_PATH));
        //重新生成CommonDaoImpl
        list = WriteUtil.getLineList(new File(InnerSettings.COMMONDAOIMPL_TEMPLET_PATH));
        WriteUtil.writeFile(list, new File(InnerSettings.COMMONDAOIMPL_PATH));
        //重新生成TEMP_Common.xml
        list = WriteUtil.getLineList(new File(InnerSettings.COMMON_XML_TEMPLET_PATH));
        WriteUtil.writeFile(list, new File(InnerSettings.COMMON_XML_PATH));
        PanelLog.log("clear...end");
    }
}
