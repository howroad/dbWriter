package com.nstc.temp.model;

import java.util.Date;

/** 事务类型 */
public class AffairType {
    /** 事务编号 */
    private String affairCode;
    /** 事务名称 */
    private String affairName;
    /** 记录创建人USERNO */
    private String createPerson;
    /** 记录创建时间 */
    private Date createTime;
    /** 记录修改人USERNO */
    private String updatePerson;
    /** 记录修改时间 */
    private Date updateTime;
    /** 是否开启1开启0不开启 */
    private Integer openState;
    /** 描述 */
    private String affairDescription;
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
    public String getAffairDescription() {
        return affairDescription;
    }
    public void setAffairDescription(String affairDescription) {
        this.affairDescription = affairDescription;
    }
    
}
