package com.nstc.dbwriter.model;
/**
 * <p>Title: MapContent.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月14日 下午5:39:35
 * 
 */

import java.util.Map;

public interface MapContent {
    Map<String, String> getMap();
    void setMap(Map<String, String> map);
}
