package com.nstc.temp.dao;

import java.util.List;

import com.nstc.temp.model.*;

/**
 * <p>Title: Guarantee2Dao.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 上午11:20:40
 * 
 */
@SuppressWarnings("unused")
public interface ICommonDao {

    /**
    * 新增或者修改附件信息表
    * @param list 附件信息表实体
    * @author luhao
    * @since 2019-06-06 13:57:21
    */
    public void saveOrUpdateAttach (Attach model); 
    /**
    * 新增附件信息表
    * @param model 附件信息表实体
    * @author luhao
    * @return 附件信息表主键
    * @since 2019-06-06 14:03:18
    */
    public Integer saveAttach(Attach model);
}
