package com.nstc.dbwriter.util;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.model.MyParam;
import com.nstc.dbwriter.model.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>Title: DataUtil.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 下午12:43:46
 * 
 */
public class DataUtil {
    /**
     * 设置成员变量为默认值
     * @Description:
     * @param obj
     * @return void
     * @author luhao
     * @since：2018年12月28日 下午6:21:55
     */
    public static void setDefault(Object obj) {
        if(obj == null) {
            throw new RuntimeException("参数不能为NULL！");
        }
        Class<? extends Object> clazz = obj.getClass();
        Field[] fileds = clazz.getDeclaredFields();
        int index = 0;
        try {
            for (Field field : fileds) {
                field.setAccessible(true);
                Class<?> fileType = field.getType();
                if (fileType == String.class) {
                    field.set(obj, new String("字符串" + index++));
                }else if(Number.class.isAssignableFrom(fileType)) {
                    if(fileType == Double.class) {
                        field.set(obj, new Double(index++));
                    }else {
                        field.set(obj,index++);
                    }
                }else if(fileType == Date.class) {
                    field.set(obj,new Date());
                }else {
                    Object obj2 = fileType.getDeclaredConstructor().newInstance();
                    DataUtil.setDefault(obj2);
                    field.set(obj,obj2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static<T> T getObj(Class<T> clazz) {
        if(clazz == null) {
            throw new RuntimeException("参数不能为NULL！");
        }
        T obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Field[] fileds = clazz.getDeclaredFields();
        int index = 0;
        try {
            for (Field field : fileds) {
                field.setAccessible(true);
                Class<?> fileType = field.getType();
                if (fileType == String.class) {
                    field.set(obj, new String("串" + index++));
                }else if(Number.class.isAssignableFrom(fileType)) {
                    if(fileType == Double.class) {
                        field.set(obj, new Double(index++));
                    }else {
                        field.set(obj,index++);
                    }
                }else if(fileType == Date.class) {
                    field.set(obj,new Date());
                }else {
                    Object obj2 = fileType.getDeclaredConstructor().newInstance();
                    DataUtil.setDefault(obj2);
                    field.set(obj,obj2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static<T> T getScope(Class<T> clazz) {
        if(clazz == null) {
            throw new RuntimeException("参数不能为NULL！");
        }
        T obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Field[] fileds = clazz.getDeclaredFields();
        int index = 0;
        try {
            for (Field field : fileds) {
                field.setAccessible(true);
                Class<?> fileType = field.getType();
                if (fileType == String.class) {
                    field.set(obj, new String("串" + index++));
                }else if(Number.class.isAssignableFrom(fileType)) {
                    if(fileType == Double.class) {
                        field.set(obj, new Double(index++));
                    }else {
                        field.set(obj,index++);
                    }
                }else if(fileType == Date.class) {
                    continue;
                }else {
                    throw new RuntimeException("有参数无法识别！");
                }
            }
            // 主键设置为空
            fileds[0].set(obj,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static<T> T getUpdateModel(Class<T> clazz,Integer id) {
        if(clazz == null) {
            throw new RuntimeException("参数不能为NULL！");
        }
        T obj = getObj(clazz);
        Field[] fileds = clazz.getDeclaredFields();
        int index = 0;
        try {
            for (Field field : fileds) {
                field.setAccessible(true);
                Class<?> fileType = field.getType();
                if (fileType == String.class) {
                    field.set(obj, new String("串" + index++ + "_改"));
                }else if(Number.class.isAssignableFrom(fileType)) {
                    if(fileType == Double.class) {
                        field.set(obj, new Double(index++ + 100));
                    }else {
                        field.set(obj,index++ + 100);
                    }
                }else if(fileType == Date.class) {
                    field.set(obj,new Date());
                }else {
                    throw new RuntimeException("有参数无法识别！");
                }
            }
            fileds[0].set(obj,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static<T> T getIdScope(Class<T> clazz,Integer id) {
        if(clazz == null) {
            throw new RuntimeException("参数不能为NULL！");
        }
        T obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        Field[] fileds = clazz.getDeclaredFields();
        try {
            fileds[0].setAccessible(true);
            fileds[0].set(obj,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * 将字Clob转成String类型
     * @Description:
     * @param sc
     * @return String
     * @author net
     * @since：2018年12月26日 下午4:57:21
     */
    public static String clobToString(Clob sc) {
        String reString = "";
        //得到流
        StringBuffer sb = null;
        try {
            Reader is = sc.getCharacterStream();
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();
            sb = new StringBuffer();
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            while (s != null) {
                sb.append(s.trim().replaceAll("\t", "\n") + "\n");
                s = br.readLine();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reString = sb.toString();
        return reString;
    }


}
