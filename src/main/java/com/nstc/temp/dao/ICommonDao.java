package com.nstc.temp.dao;

import java.util.List;

import com.nstc.temp.model.AffairModel;
import com.nstc.temp.model.AffairTypeModel;
import com.nstc.temp.model.Blend;
import com.nstc.temp.model.BusinessLogModel;
import com.nstc.temp.model.BusinessModel;
import com.nstc.temp.model.NatureModel;
import com.nstc.temp.model.WarehouseBill;

/**
 * <p>Title: Guarantee2Dao.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 上午11:20:40
 * 
 */
public interface ICommonDao {
    /**
    * 新增理财登记
    * @param model 理财登记实体
    * @author luhao
    * @return 理财登记主键
    * @since 2019-01-09 10:00:31
    */
    public Integer saveBusiness(BusinessModel model);
    /**
    * 删除理财登记
    * @param id 理财登记主键
    * @author luhao
    * @since 2019-01-09 10:00:31
    */
    public void deleteBusinessById (Integer id);
    /**
    * 修改理财登记
    * @param model 理财登记实体
    * @author luhao
    * @since 2019-01-09 10:00:31
    */
    public void updateBusiness(BusinessModel model);
    /**
    * 查询理财登记
    * @param scope 理财登记查询条件
    * @author luhao
    * @return 理财登记集合
    * @since 2019-01-09 10:00:31
    */
    public List<BusinessModel> getBusinessList(BusinessModel scope);
    /**
    * 新增业务日志
    * @param model 业务日志实体
    * @author luhao
    * @return 业务日志主键
    * @since 2019-01-09 11:33:13
    */
    public Integer saveBusinessLog(BusinessLogModel model);
    /**
    * 删除业务日志
    * @param id 业务日志主键
    * @author luhao
    * @since 2019-01-09 11:33:13
    */
    public void deleteBusinessLogById (Integer id);
    /**
    * 修改业务日志
    * @param model 业务日志实体
    * @author luhao
    * @since 2019-01-09 11:33:13
    */
    public void updateBusinessLog(BusinessLogModel model);
    /**
    * 查询业务日志
    * @param scope 业务日志查询条件
    * @author luhao
    * @return 业务日志集合
    * @since 2019-01-09 11:33:13
    */
    public List<BusinessLogModel> getBusinessLogList(BusinessLogModel scope);
    /**
    * 新增存款性质设置
    * @param model 存款性质设置实体
    * @author luhao
    * @return 存款性质设置主键
    * @since 2019-01-09 11:51:33
    */
    public Integer saveNature(NatureModel model);
    /**
    * 删除存款性质设置
    * @param id 存款性质设置主键
    * @author luhao
    * @since 2019-01-09 11:51:33
    */
    public void deleteNatureById (Integer id);
    /**
    * 修改存款性质设置
    * @param model 存款性质设置实体
    * @author luhao
    * @since 2019-01-09 11:51:33
    */
    public void updateNature(NatureModel model);
    /**
    * 查询存款性质设置
    * @param scope 存款性质设置查询条件
    * @author luhao
    * @return 存款性质设置集合
    * @since 2019-01-09 11:51:33
    */
    public List<NatureModel> getNatureList(NatureModel scope);    
    /**
    * 新增事务申请
    * @param model 事务申请实体
    * @author luhao
    * @return 事务申请主键
    * @since 2019-01-10 10:02:29
    */
    public Integer saveAffair(AffairModel model);
    /**
    * 删除事务申请
    * @param id 事务申请主键
    * @author luhao
    * @since 2019-01-10 10:02:29
    */
    public void deleteAffairById (Integer id);
    /**
    * 修改事务申请
    * @param model 事务申请实体
    * @author luhao
    * @since 2019-01-10 10:02:29
    */
    public void updateAffair(AffairModel model);
    /**
    * 查询事务申请
    * @param scope 事务申请查询条件
    * @author luhao
    * @return 事务申请集合
    * @since 2019-01-10 10:02:29
    */
    public List<AffairModel> getAffairList(AffairModel scope);
    /**
    * 新增事务类型
    * @param model 事务类型实体
    * @author luhao
    * @return 事务类型主键
    * @since 2019-01-10 10:36:14
    */
    public Integer saveAffairType(AffairTypeModel model);
    /**
    * 删除事务类型
    * @param id 事务类型主键
    * @author luhao
    * @since 2019-01-10 10:36:14
    */
    public void deleteAffairTypeById (Integer id);
    /**
    * 修改事务类型
    * @param model 事务类型实体
    * @author luhao
    * @since 2019-01-10 10:36:14
    */
    public void updateAffairType(AffairTypeModel model);
    /**
    * 查询事务类型
    * @param scope 事务类型查询条件
    * @author luhao
    * @return 事务类型集合
    * @since 2019-01-10 10:36:14
    */
    public List<AffairTypeModel> getAffairTypeList(AffairTypeModel scope); 
    
    /**
    * 新增此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录
    * @param model 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录实体
    * @author luhao
    * @return 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录主键
    * @since 2019-01-25 17:50:57
    */
    public Integer saveWarehouseBill(WarehouseBill model);
    /**
    * 删除此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录
    * @param id 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录主键
    * @author luhao
    * @since 2019-01-25 17:50:57
    */
    public void deleteWarehouseBillById (Integer id);
    /**
    * 修改此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录
    * @param model 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录实体
    * @author luhao
    * @since 2019-01-25 17:50:57
    */
    public void updateWarehouseBill(WarehouseBill model);
    /**
    * 查询此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录
    * @param scope 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录查询条件
    * @author luhao
    * @return 此票据指特定人、特定时间持有的票据，一张票据多次持有形成多条记录集合
    * @since 2019-01-25 17:50:57
    */
    public List<WarehouseBill> getWarehouseBillList(WarehouseBill scope);
    /**
    * 新增
    * @param model 实体
    * @author luhao
    * @return 主键
    * @since 2019-01-25 18:58:47
    */
    public Integer saveBlend(Blend model);
    /**
    * 删除
    * @param id 主键
    * @author luhao
    * @since 2019-01-25 18:58:47
    */
    public void deleteBlendById (Integer id);
    /**
    * 修改
    * @param model 实体
    * @author luhao
    * @since 2019-01-25 18:58:47
    */
    public void updateBlend(Blend model);
    /**
    * 查询
    * @param scope 查询条件
    * @author luhao
    * @return 集合
    * @since 2019-01-25 18:58:47
    */
    public List<Blend> getBlendList(Blend scope);
}
