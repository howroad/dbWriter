package com.nstc.dbwriter.conf;

import com.nstc.dbwriter.config.CommonSettings;
import org.apache.poi.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title: ConfigUtil.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-16 17:01
 */
public class ConfigUtil {
    public static Map<String,Object> readProperties(){
        Map<String,Object> resultMap = new HashMap<>();
        Properties pro = new Properties();
        InputStream in = null;
        try {
            //in = ConfigUtil.class.getResourceAsStream("core.properties");
            in = ConfigUtil.class.getResourceAsStream("core.properties");
            pro.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        pro.forEach((e,f) ->{
            resultMap.put(String.valueOf(e),f);
        });
        System.out.println(resultMap);
        return resultMap;
    }
}
