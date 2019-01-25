package com.nstc.temp.model;

import java.util.Date;

/** 事务类型 */
public class AffairTypeModel {
    /** 事务编号 */
    private String affairCode;
    /** 事务名称 */
    private String affairName;
    /** 描述 */
    private String description;
    /** 记录创建人userno */
    private String createPerson;
    /** 记录创建时间 */
    private Date createTime;
    /** 记录修改人userno */
    private String updatePerson;
    /** 记录修改时间 */
    private Date updateTime;
    /** 是否开启1开启0不开启 */
    private Integer openState;
    public String getAffairCode() {
        return affairCode;
    }
    public void setAffairCode(String affairCode) {
        this.affairCode = affairCode;
    }
    public String getAffairName() {
        return affairName;
    }
    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreatePerson() {
        return createPerson;
    }
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdatePerson() {
        return updatePerson;
    }
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getOpenState() {
        return openState;
    }
    public void setOpenState(Integer openState) {
        this.openState = openState;
    }
    public String toString() {
        return "AffairTypeModel [affairCode=" + affairCode + ", affairName=" + affairName + ", description="
                + description + ", createPerson=" + createPerson + ", createTime=" + createTime + ", updatePerson="
                + updatePerson + ", updateTime=" + updateTime + ", openState=" + openState + "]";
    }
    
}
