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
    * 新增测试教师表
    * @param model 测试教师表实体
    * @author luhao
    * @return 测试教师表主键
    * @since 2019-02-18 11:09:20
    */
    public Integer saveTeacher(Teacher model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveTeacher"),model);
    }
    
    /**
    * 根据主键删除测试教师表
    * @param id 测试教师表主键
    * @author luhao
    * @since 2019-02-18 11:09:20
    */
    public void deleteTeacherById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteTeacherById"),id);
    }
    
    /**
    * 修改测试教师表
    * @param model 测试教师表实体
    * @author luhao
    * @since 2019-02-18 11:09:20
    */
    public void updateTeacher(Teacher model) {
        getSqlMapClientTemplate().update(getStatement("updateTeacher"),model);
    }
    
    /**
    * 查询测试教师表列表
    * @param scope 测试教师表查询条件
    * @author luhao
    * @return 测试教师表集合
    * @since 2019-02-18 11:09:20
    */
    public List<Teacher> getTeacherList(Teacher scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getTeacherList"),scope);
        List<Teacher> resultList = Arrays.asList(list.toArray(new Teacher[0]));
        return resultList;
    }
}
