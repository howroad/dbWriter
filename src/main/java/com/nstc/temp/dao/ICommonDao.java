package com.nstc.temp.dao;

import java.util.List;

import com.nstc.temp.model.Affair;
import com.nstc.temp.model.AffairType;

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
public interface ICommonDao {
    /**
    * 新增事务申请
    * @param model 事务申请实体
    * @author luhao
    * @return 事务申请主键
    * @since 2019-01-29 11:44:00
    */
    public Integer saveAffair(Affair model);

    /**
    * 删除事务申请
    * @param id 事务申请主键
    * @author luhao
    * @since 2019-01-29 11:44:00
    */
    public void deleteAffairById (Integer id);

    /**
    * 修改事务申请
    * @param model 事务申请实体
    * @author luhao
    * @since 2019-01-29 11:44:00
    */
    public void updateAffair(Affair model);

    /**
    * 查询事务申请
    * @param scope 事务申请查询条件
    * @author luhao
    * @return 事务申请集合
    * @since 2019-01-29 11:44:00
    */
    public List<Affair> getAffairList(Affair scope);

    /**
    * 新增事务类型
    * @param model 事务类型实体
    * @author luhao
    * @return 事务类型主键
    * @since 2019-01-29 11:44:01
    */
    public Integer saveAffairType(AffairType model);

    /**
    * 删除事务类型
    * @param id 事务类型主键
    * @author luhao
    * @since 2019-01-29 11:44:01
    */
    public void deleteAffairTypeById (Integer id);

    /**
    * 修改事务类型
    * @param model 事务类型实体
    * @author luhao
    * @since 2019-01-29 11:44:01
    */
    public void updateAffairType(AffairType model);

    /**
    * 查询事务类型
    * @param scope 事务类型查询条件
    * @author luhao
    * @return 事务类型集合
    * @since 2019-01-29 11:44:01
    */
    public List<AffairType> getAffairTypeList(AffairType scope);


}
