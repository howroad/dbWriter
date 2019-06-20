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
    * 新增附件信息表
    * @param model 附件信息表实体
    * @author luhao
    * @return 附件信息表主键
    * @since 2019-06-20 14:32:21
    */
    public Integer saveAttach(Attach model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAttach"),model);
    }
    
    /**
    * 根据主键删除附件信息表
    * @param id 附件信息表主键
    * @author luhao
    * @since 2019-06-20 14:32:21
    */
    public void deleteAttachById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteAttachById"),id);
    }
    
    /**
    * 修改附件信息表
    * @param model 附件信息表实体
    * @author luhao
    * @since 2019-06-20 14:32:21
    */
    public void updateAttach(Attach model) {
        getSqlMapClientTemplate().update(getStatement("updateAttach"),model);
    }
    
    /**
    * 查询附件信息表列表
    * @param scope 附件信息表Scope查询条件
    * @author luhao
    * @return 附件信息表集合
    * @since 2019-06-20 14:32:21
    */
    @SuppressWarnings("unchecked")
    public List<Attach> getAttachList(Attach scope) {
        List<Attach> resultList = (List<Attach>) getSqlMapClientTemplate().queryForList(getStatement("getAttachList"),scope);
        return resultList;
    }
    
    /**
    * 根据Id查询附件信息表
    * @param id 附件信息表主键
    * @author luhao
    * @return 附件信息表
    * @since 2019-06-20 14:32:21
    */
    public Attach getAttachById(Integer id) {
        return (Attach) getSqlMapClientTemplate().queryForObject(getStatement("getAttachById"), id);
    }

    /**
    * 根据条件查询附件信息表View的第一条记录
    * @param scope 附件信息表查询条件
    * @author luhao
    * @return 附件信息表
    * @since 2019-06-20 14:32:21
    */
    public Attach getAttachByScope (Attach scope){
        return (Attach) getSqlMapClientTemplate().queryForObject(getStatement("getAttachByScope"), scope);
    }
    
    /**
    * 根据主键删除多个附件信息表
    * @param ids 附件信息表主键
    * @author luhao
    * @since 2019-06-20 14:32:21
    */
    public void deleteAttachByIds (List<Integer> ids) {
        getSqlMapClientTemplate().delete(getStatement("deleteAttachByIds"), ids);
    }
    
    /**
    * 查询附件信息表记录数
    * @param scope 附件信息表查询条件
    * @author luhao
    * @return 附件信息表记录数
    * @since 2019-06-20 14:32:21
    */
    public Integer getAttachPageCount(Attach scope) {
        return (Integer) getSqlMapClientTemplate().queryForObject(getStatement("getAttachPageCount"), scope);
    }
    
    /**
    * 查询附件信息表分页列表
    * @param scope 附件信息表查询条件
    * @author luhao
    * @return 附件信息表集合
    * @since 2019-06-20 14:32:21
    */
    public List<Attach> getAttachPageList(Attach scope) {
        Integer count = getAttachPageCount(scope);
        @SuppressWarnings("unchecked")
        List<Attach> resultList = (List<Attach>) getSqlMapClientTemplate().queryForList(getStatement("getAttachPageList"),scope);
        return resultList;
    }
    
    /**
     * 根据条件删除附件信息表
     * @param scope 附件信息表删除条件
     * @author luhao
     * @since：2019-06-20 14:32:21
     */
    public void deleteAttach(Attach scope) {
        getSqlMapClientTemplate().delete(getStatement("deleteAttach"), scope);
    }
    
    /**
    * 新增或者修改附件信息表集合中的内容
    * @param list 附件信息表集合
    * @author luhao
    * @since 2019-06-20 14:32:21
    */
    public void saveOrUpdateAttachList (List<Attach> list){
        getSqlMapClientTemplate().update(getStatement("saveOrUpdateAttachList"),list);
    }   
    
    /**
    * 新增或者修改附件信息表
    * @param list 附件信息表实体
    * @author luhao
    * @since 2019-06-20 14:32:21
    */
    public void saveOrUpdateAttach (Attach model){
        getSqlMapClientTemplate().update(getStatement("saveOrUpdateAttach"),model);
    }
}    
