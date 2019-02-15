package com.nstc.dbwriter.control;

import java.io.File;
import java.util.List;

import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.util.WriteUtil;

/**
 * <p>Title: ClearTemp.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月15日 下午3:09:11
 * 
 */
public class ClearTemp {
    
    public static void main(String[] args) {
        //删除model
        File modelDir = new File(InnerSettings.TEST_MODEL_DIR);
        clearDir(modelDir);
        //删除测试类
        File testDir = new File(InnerSettings.TEST_DIR);
        clearDir(testDir);
        //删除输出
        File outDir = new File(InnerSettings.OUT_DIR);
        clearDir(outDir);
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
        System.out.println("clear...end");
    }
    
    public static void clearDir(File dir) {
        if(dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if(file.isFile()) {
                    file.delete();
                }
            }
        }
    }
}
