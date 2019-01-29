package com.nstc.temp.dao;

import java.util.Arrays;
import java.util.List;

import com.nstc.temp.model.Affair;
import com.nstc.temp.model.AffairType;

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
public class CommonDaoImpl extends BaseDao implements ICommonDao {

    /**
    * 新增事务申请
    * @param model 事务申请实体
    * @author luhao
    * @return 事务申请主键
    * @since 2019-01-29 11:44:00
    */
    public Integer saveAffair(Affair model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAffair"),model);
    }
    
    /**
    * 删除事务申请
    * @param id 事务申请主键
    * @author luhao
    * @since 2019-01-29 11:44:00
    */
    public void deleteAffairById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteAffairById"),id);
    }
    
    /**
    * 修改事务申请
    * @param model 事务申请实体
    * @author luhao
    * @since 2019-01-29 11:44:00
    */
    public void updateAffair(Affair model) {
        getSqlMapClientTemplate().update(getStatement("updateAffair"),model);
    }
    
    /**
    * 查询事务申请
    * @param scope 事务申请查询条件
    * @author luhao
    * @return 事务申请集合
    * @since 2019-01-29 11:44:00
    */
    public List<Affair> getAffairList(Affair scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getAffairList"),scope);
        List<Affair> resultList = Arrays.asList(list.toArray(new Affair[0]));
        return resultList;
    }
       
    
    
    /**
    * 新增事务类型
    * @param model 事务类型实体
    * @author luhao
    * @return 事务类型主键
    * @since 2019-01-29 11:44:01
    */
    public Integer saveAffairType(AffairType model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAffairType"),model);
    }
    
    /**
    * 删除事务类型
    * @param id 事务类型主键
    * @author luhao
    * @since 2019-01-29 11:44:01
    */
    public void deleteAffairTypeById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteAffairTypeById"),id);
    }
    
    /**
    * 修改事务类型
    * @param model 事务类型实体
    * @author luhao
    * @since 2019-01-29 11:44:01
    */
    public void updateAffairType(AffairType model) {
        getSqlMapClientTemplate().update(getStatement("updateAffairType"),model);
    }
    
    /**
    * 查询事务类型
    * @param scope 事务类型查询条件
    * @author luhao
    * @return 事务类型集合
    * @since 2019-01-29 11:44:01
    */
    public List<AffairType> getAffairTypeList(AffairType scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getAffairTypeList"),scope);
        List<AffairType> resultList = Arrays.asList(list.toArray(new AffairType[0]));
        return resultList;
    }
    
}
