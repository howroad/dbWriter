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
    
    /**
    * 新增单位事物类型工作流关系表
    * @param model 单位事物类型工作流关系表实体
    * @author luhao
    * @return 单位事物类型工作流关系表主键
    * @since 2019-01-31 10:39:36
    */
    public Integer saveRCltAfftypeFlow(RCltAfftypeFlow model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveRCltAfftypeFlow"),model);
    }

    /**
    * 删除单位事物类型工作流关系表
    * @param id 单位事物类型工作流关系表主键
    * @author luhao
    * @since 2019-01-31 10:39:36
    */
    public void deleteRCltAfftypeFlowById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteRCltAfftypeFlowById"),id);
    }

    /**
    * 修改单位事物类型工作流关系表
    * @param model 单位事物类型工作流关系表实体
    * @author luhao
    * @since 2019-01-31 10:39:36
    */
    public void updateRCltAfftypeFlow(RCltAfftypeFlow model) {
        getSqlMapClientTemplate().update(getStatement("updateRCltAfftypeFlow"),model);
    }

    /**
    * 查询单位事物类型工作流关系表
    * @param scope 单位事物类型工作流关系表查询条件
    * @author luhao
    * @return 单位事物类型工作流关系表集合
    * @since 2019-01-31 10:39:36
    */
    public List<RCltAfftypeFlow> getRCltAfftypeFlowList(RCltAfftypeFlow scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getRCltAfftypeFlowList"),scope);
        List<RCltAfftypeFlow> resultList = Arrays.asList(list.toArray(new RCltAfftypeFlow[0]));
        return resultList;
    }

    /**
    * 新增测试学生表
    * @param model 测试学生表实体
    * @author luhao
    * @return 测试学生表主键
    * @since 2019-01-31 11:10:41
    */
    public Integer saveStudent(Student model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveStudent"),model);
    }

    /**
    * 删除测试学生表
    * @param id 测试学生表主键
    * @author luhao
    * @since 2019-01-31 11:10:41
    */
    public void deleteStudentById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteStudentById"),id);
    }

    /**
    * 修改测试学生表
    * @param model 测试学生表实体
    * @author luhao
    * @since 2019-01-31 11:10:41
    */
    public void updateStudent(Student model) {
        getSqlMapClientTemplate().update(getStatement("updateStudent"),model);
    }

    /**
    * 查询测试学生表
    * @param scope 测试学生表查询条件
    * @author luhao
    * @return 测试学生表集合
    * @since 2019-01-31 11:10:41
    */
    public List<Student> getStudentList(Student scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getStudentList"),scope);
        List<Student> resultList = Arrays.asList(list.toArray(new Student[0]));
        return resultList;
    }

    /**
    * 新增测试教师表
    * @param model 测试教师表实体
    * @author luhao
    * @return 测试教师表主键
    * @since 2019-01-31 11:15:24
    */
    public Integer saveTeacher(Teacher model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveTeacher"),model);
    }

    /**
    * 删除测试教师表
    * @param id 测试教师表主键
    * @author luhao
    * @since 2019-01-31 11:15:24
    */
    public void deleteTeacherById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteTeacherById"),id);
    }

    /**
    * 修改测试教师表
    * @param model 测试教师表实体
    * @author luhao
    * @since 2019-01-31 11:15:24
    */
    public void updateTeacher(Teacher model) {
        getSqlMapClientTemplate().update(getStatement("updateTeacher"),model);
    }

    /**
    * 查询测试教师表
    * @param scope 测试教师表查询条件
    * @author luhao
    * @return 测试教师表集合
    * @since 2019-01-31 11:15:24
    */
    public List<Teacher> getTeacherList(Teacher scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getTeacherList"),scope);
        List<Teacher> resultList = Arrays.asList(list.toArray(new Teacher[0]));
        return resultList;
    }

}
