package com.nstc.temp.dao;

import java.util.Arrays;
import java.util.List;

import com.nstc.temp.model.AffairModel;
import com.nstc.temp.model.AffairTypeModel;
import com.nstc.temp.model.BusinessLogModel;
import com.nstc.temp.model.BusinessModel;
import com.nstc.temp.model.NatureModel;

/**
 * <p>Title: Guarantee2DaoImpl.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 上午11:21:45
 * 
 */
public class CommonDaoImpl extends BaseDao implements ICommonDao {
    
    public Integer saveBusiness(BusinessModel model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveBusiness"),model);
    }
    public void deleteBusinessById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteBusinessById"),id);
    }
    public void updateBusiness(BusinessModel model) {
        getSqlMapClientTemplate().update(getStatement("updateBusiness"),model);
    }
    public List<BusinessModel> getBusinessList(BusinessModel scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getBusinessList"),scope);
        List<BusinessModel> resultList = Arrays.asList(list.toArray(new BusinessModel[0]));
        return resultList;
    }
    public Integer saveBusinessLog(BusinessLogModel model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveBusinessLog"),model);
    }
    public void deleteBusinessLogById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteBusinessLogById"),id);
    }
    public void updateBusinessLog(BusinessLogModel model) {
        getSqlMapClientTemplate().update(getStatement("updateBusinessLog"),model);
    }
    public List<BusinessLogModel> getBusinessLogList(BusinessLogModel scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getBusinessLogList"),scope);
        List<BusinessLogModel> resultList = Arrays.asList(list.toArray(new BusinessLogModel[0]));
        return resultList;
    }
    public Integer saveNature(NatureModel model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveNature"),model);
    }
    public void deleteNatureById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteNatureById"),id);
    }
    public void updateNature(NatureModel model) {
        getSqlMapClientTemplate().update(getStatement("updateNature"),model);
    }
    public List<NatureModel> getNatureList(NatureModel scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getNatureList"),scope);
        List<NatureModel> resultList = Arrays.asList(list.toArray(new NatureModel[0]));
        return resultList;
    }

    public Integer saveAffair(AffairModel model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAffair"),model);
    }
    public void deleteAffairById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteAffairById"),id);
    }
    public void updateAffair(AffairModel model) {
        getSqlMapClientTemplate().update(getStatement("updateAffair"),model);
    }
    public List<AffairModel> getAffairList(AffairModel scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getAffairList"),scope);
        List<AffairModel> resultList = Arrays.asList(list.toArray(new AffairModel[0]));
        return resultList;
    }
    public Integer saveAffairType(AffairTypeModel model) {
        return (Integer)getSqlMapClientTemplate().insert(getStatement("saveAffairType"),model);
    }
    public void deleteAffairTypeById (Integer id) {
        getSqlMapClientTemplate().delete(getStatement("deleteAffairTypeById"),id);
    }
    public void updateAffairType(AffairTypeModel model) {
        getSqlMapClientTemplate().update(getStatement("updateAffairType"),model);
    }
    public List<AffairTypeModel> getAffairTypeList(AffairTypeModel scope) {
        List<?> list = getSqlMapClientTemplate().queryForList(getStatement("getAffairTypeList"),scope);
        List<AffairTypeModel> resultList = Arrays.asList(list.toArray(new AffairTypeModel[0]));
        return resultList;
    }
}
