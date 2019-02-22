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
public interface ICommonDao {

    /**
    * 新增账户表
    * @param model 账户表实体
    * @author luhao
    * @return 账户表主键
    * @since 2019-02-22 10:49:41
    */
    public Integer saveAmount(Amount model);

    /**
    * 根据主键删除账户表
    * @param id 账户表主键
    * @author luhao
    * @since 2019-02-22 10:49:41
    */
    public void deleteAmountById (Integer id);

    /**
    * 修改账户表
    * @param model 账户表实体
    * @author luhao
    * @since 2019-02-22 10:49:41
    */
    public void updateAmount(Amount model);

    /**
    * 查询账户表列表
    * @param scope 账户表查询条件
    * @author luhao
    * @return 账户表集合
    * @since 2019-02-22 10:49:41
    */
    public List<Amount> getAmountList(Amount scope);
    
    /**
    * 根据Id查询账户表
    * @param id 账户表主键
    * @author luhao
    * @return 账户表
    * @since 2019-02-22 10:49:41
    */
    public Amount getAmountById(Integer id);
    
    /**
    * 根据主键删除多个账户表
    * @param ids 账户表主键
    * @author luhao
    * @since 2019-02-22 10:49:41
    */
    public void deleteAmountByIds (List<Integer> ids);
    
        
    /**
    * 查询账户表记录数
    * @param scope 账户表查询条件
    * @author luhao
    * @return 账户表记录数
    * @since 2019-02-22 10:49:42
    */
    public Integer getAmountPageCount(AmountScope scope);
    
    /**
    * 查询账户表分页列表
    * @param scope 账户表查询条件
    * @author luhao
    * @return 账户表集合
    * @since 2019-02-22 10:49:42
    */
    public List<Amount> getAmountPageList(AmountScope scope);
}
