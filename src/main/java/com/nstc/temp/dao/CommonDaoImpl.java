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
public class CommonDaoImpl extends BaseDao implements ICommonDao {

    /**
    * 新增账户表
    * @param model 账户表实体
    * @author luhao
    * @return 账户表主键
    * @since 2019-02-22 10:49:57
    */
    public Integer saveAmount(Amount model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAmount"),model);
    }
    
    /**
    * 根据主键删除账户表
    * @param id 账户表主键
    * @author luhao
    * @since 2019-02-22 10:49:57
    */
    public void deleteAmountById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteAmountById"),id);
    }
    
    /**
    * 修改账户表
    * @param model 账户表实体
    * @author luhao
    * @since 2019-02-22 10:49:57
    */
    public void updateAmount(Amount model) {
        getSqlMapClientTemplate().update(getStatement("updateAmount"),model);
    }
    
    /**
    * 查询账户表列表
    * @param scope 账户表查询条件
    * @author luhao
    * @return 账户表集合
    * @since 2019-02-22 10:49:57
    */
    public List<Amount> getAmountList(Amount scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getAmountList"),scope);
        List<Amount> resultList = Arrays.asList(list.toArray(new Amount[0]));
        return resultList;
    }
    
    /**
    * 根据Id查询账户表
    * @param id 账户表主键
    * @author luhao
    * @return 账户表
    * @since 2019-02-22 10:49:58
    */
    public Amount getAmountById(Integer id) {
        return (Amount) getSqlMapClientTemplate().queryForObject(getStatement("getAmountById"), id);
    }
    
    /**
    * 根据主键删除多个账户表
    * @param ids 账户表主键
    * @author luhao
    * @since 2019-02-22 10:49:58
    */
    public void deleteAmountByIds (List<Integer> ids) {
        getSqlMapClientTemplate().delete(getStatement("deleteAmountByIds"), ids);
    }
    
    /**
    * 查询账户表记录数
    * @param scope 账户表查询条件
    * @author luhao
    * @return 账户表记录数
    * @since 2019-02-22 10:49:58
    */
    public Integer getAmountPageCount(AmountScope scope) {
        return (Integer) getSqlMapClientTemplate().queryForObject(getStatement("getAmountPageCount"), scope);
    }
    
    /**
    * 查询账户表分页列表
    * @param scope 账户表查询条件
    * @author luhao
    * @return 账户表集合
    * @since 2019-02-22 10:49:58
    */
    public List<Amount> getAmountPageList(AmountScope scope) {
        Integer count = getAmountPageCount(scope);
        scope.getPaging().setTotalRow(count);
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getAmountPageList"),scope);
        List<Amount> resultList = Arrays.asList(list.toArray(new Amount[0]));
        return resultList;
    }
}
