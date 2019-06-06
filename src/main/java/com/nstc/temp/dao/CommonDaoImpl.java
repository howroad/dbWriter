package com.nstc.temp.dao;

import java.util.Arrays;
import java.util.List;

import com.nstc.temp.model.*;

/**
 * <p>Title: Guarantee2DaoImpl.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 上午11:21:45
 * 
 */
@SuppressWarnings("unused")
public class CommonDaoImpl extends BaseDao implements ICommonDao {
    /**
    * 新增或者修改附件信息表
    * @param list 附件信息表实体
    * @author luhao
    * @since 2019-06-06 14:00:55
    */
    public void saveOrUpdateAttach (Attach model){
        getSqlMapClientTemplate().update(getStatement("saveOrUpdateAttach"),model);
    } 
    /**
    * 新增附件信息表
    * @param model 附件信息表实体
    * @author luhao
    * @return 附件信息表主键
    * @since 2019-06-06 14:03:18
    */
    public Integer saveAttach(Attach model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAttach"),model);
    }
}
