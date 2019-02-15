package com.nstc.dbwriter.builder;

import javax.lang.model.element.Modifier;

import com.nstc.dbwriter.config.TableContans;
import com.nstc.dbwriter.model.FileWriter;
import com.nstc.dbwriter.model.MyParam;

/**
 * <p>Title: FileWriterBuilder.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年2月15日 上午9:33:59
 * 
 */
public class FileWriterBuilder extends FileWriter{
    //TODO 可能会删除
    public void testParam(MyParam param) {
        String modifier = Modifier.PRIVATE.toString();
        StringBuffer comment = new StringBuffer("/** ").append(param.getParamRemark()).append(" */");
        addLineTab(comment);
        StringBuffer line = new StringBuffer(modifier).append(TableContans.SPACE)
                .append(param.getType().getParamTypeName()).append(TableContans.SPACE).append(param.getParamName())
                .append(TableContans.SEMC);
        addLineTab(line);
    }

}
